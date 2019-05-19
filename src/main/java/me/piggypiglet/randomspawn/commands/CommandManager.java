package me.piggypiglet.randomspawn.commands;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import me.piggypiglet.randomspawn.commands.implementations.HelpCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.piggypiglet.randomspawn.file.types.Lang.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class CommandManager implements CommandExecutor {
    @Inject private HelpCommand helpCommand;

    @Getter private final List<Command> commands = new ArrayList<>();

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command bCommand, String label, String[] badArgs) {
        if (badArgs.length > 0) {
            String text = String.join(" ", badArgs);
            String command = "/" + bCommand.getName() + " " + badArgs[0].toLowerCase();

            for (Command c : commands) {
                String cmd = c.getCommand();

                if (text.toLowerCase().startsWith(cmd.toLowerCase())) {
                    if (c.isPlayerOnly() && !(sender instanceof Player)) {
                        sender.sendMessage(getMessage(PLAYER_ONLY));
                        return true;
                    }

                    String[] permissions = c.getPermissions();

                    if (Arrays.stream(permissions).anyMatch(sender::hasPermission) || permissions.length == 0) {
                        String[] args = args(text);

                        if (!c.run(sender, args)) {
                            sender.sendMessage(getMessage(INCORRECT_USAGE, command, c.getUsage()));
                        }
                    } else {
                        sender.sendMessage(getMessage(NO_PERMISSION));
                    }

                    return true;
                }
            }

            sender.sendMessage(getMessage(UNKNOWN_COMMAND));
        }

        helpCommand.run(sender, new String[]{});

        return true;
    }

    private String[] args(String text) {
        String[] args = text.trim().split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        args = Arrays.copyOfRange(args, 1, args.length);

        return args.length == 0 ? new String[]{} : args;
    }
}
