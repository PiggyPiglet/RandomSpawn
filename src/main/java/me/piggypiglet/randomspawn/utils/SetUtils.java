package me.piggypiglet.randomspawn.utils;

import me.piggypiglet.framework.user.User;
import me.piggypiglet.randomspawn.lang.Lang;

import java.util.Iterator;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetUtils {
    public static <T> boolean removeIndexCommand(String argument, User user, Set<T> set, Lang message) {
        final int index;

        try {
            index = Integer.parseInt(argument);
        } catch (Exception e) {
            return false;
        }

        if (set.size() < index + 1) {
            user.sendMessage(Lang.INDEX_TOO_BIG, index, set.size() - 1);
            return true;
        }

        int i = 0;
        for (Iterator<T> iterator = set.iterator(); iterator.hasNext(); i++) {
            iterator.next();

            if (i == index) {
                iterator.remove();
                break;
            }
        }

        user.sendMessage(message, index);
        return true;
    }
}
