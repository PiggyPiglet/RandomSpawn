package me.piggypiglet.randomspawn.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.commands.CommandManager;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static me.piggypiglet.randomspawn.file.types.Lang.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class HelpCommand extends Command {
    @Inject private CommandManager commandManager;

    public HelpCommand() {
        super("help", "this menu.", "[command]", "randomspawn.default", "randomspawn.help");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        List<Command> commands = commandManager.getCommands();

        if (args.length == 0) {
            StringBuilder builder = new StringBuilder(getMessage(HELP_HEADER) + "\n");

            commands.stream()
                    .filter(c -> Arrays.stream(c.getPermissions()).anyMatch(sender::hasPermission))
                    .forEach(c -> builder.append(getMessage(HELP_FORMAT, c.getCommand(), c.getUsage(), c.getDescription())).append("\n"));
            builder.append(getMessage(HELP_FOOTER));
            sender.sendMessage(builder.toString());
        } else {
            Optional<Command> command = commands.stream().filter(c -> c.getCommand().equalsIgnoreCase(args[0])).findFirst();

            if (command.isPresent()) {
                Command c = command.get();

                sender.sendMessage(getMessage(HELP_SPECIFIC, c.getCommand(), c.getUsage(), c.getDescription()));
            } else {
                return false;
            }
        }

        return true;
    }
}