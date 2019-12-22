/*
 * MIT License
 *
 * Copyright (c) 2019 PiggyPiglet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.piggypiglet.framework.utils;

import me.piggypiglet.framework.lang.LangEnum;
import me.piggypiglet.framework.lang.LanguageGetter;
import me.piggypiglet.framework.utils.annotations.addon.Addon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StringUtils {
    private static final Pattern SPECIFIC_FORMAT_REGEX = Pattern.compile("%\\d\\$s");
    private static final Pattern INDEX_FORMAT_REGEX = Pattern.compile("%s");

    private StringUtils() {
        throw new RuntimeException("This class cannot be instantiated.");
    }

    public static boolean anyStartWith(String str, String... triggers) {
        return anyStartWith(str, Arrays.asList(triggers));
    }

    public static boolean anyStartWith(String str, List<String> triggers) {
        return anyWith(str, triggers, String::startsWith);
    }

    public static boolean startsWithAny(String trigger, String... strings) {
        return startsWithAny(trigger, Arrays.asList(strings));
    }

    public static boolean startsWithAny(String trigger, List<String> strings) {
        return anyWith(trigger, strings, (t, s) -> s.startsWith(t));
    }

    public static boolean anyEndWith(String str, String... triggers) {
        return anyEndWith(str, Arrays.asList(triggers));
    }

    public static boolean anyEndWith(String str, List<String> triggers) {
        return anyWith(str, triggers, String::endsWith);
    }

    public static boolean anyContains(String str, String... triggers) {
        return anyContains(str, Arrays.asList(triggers));
    }

    public static boolean anyContains(String str, List<String> triggers) {
        return anyWith(str, triggers, String::contains);
    }

    public static boolean anyMatches(String str, Pattern... patterns) {
        return anyMatches(str, Arrays.asList(patterns));
    }

    public static boolean anyMatches(String str, List<Pattern> triggers) {
        return anyWith(str, triggers, (p, s) -> p.matcher(s).matches());
    }

    public static <T> boolean anyWith(String str, List<T> elements, BiPredicate<T, String> test) {
        return lowercaseStream(elements).anyMatch(s -> test.test(s, str));
    }

    @SuppressWarnings("unchecked")
    private static <T> Stream<T> lowercaseStream(List<T> triggers) {
        return triggers.stream().map(o -> o instanceof String ? (T) ((String) o).toLowerCase() : o);
    }

    public static String addonName(Class<?> addon) {
        if (!addon.isAnnotationPresent(Addon.class)) throw new UnsupportedOperationException("Class provided is not an addon class.");

        return "addon_" + addon.getSimpleName().toLowerCase().replace("addon", "");
    }

    public static String format(Object message, Object... concatenations) {
        if (concatenations.length == 0) {
            return format(message);
        }

        final List<Object> items = new ArrayList<>();
        items.add(message);
        items.addAll(Arrays.asList(concatenations));

        final List<String> messages = items.stream()
                .map(StringUtils::format)
                .collect(Collectors.toList());
        final String joined = String.join("", messages);
        final Matcher specificMatcher = SPECIFIC_FORMAT_REGEX.matcher(joined);

        int count = INDEX_FORMAT_REGEX.split(joined, -1).length - 1;
        System.out.println(count);

        if (specificMatcher.matches()) {
            final List<Integer> nums = new ArrayList<>();

            while (specificMatcher.find()) {
                nums.add(Integer.valueOf(specificMatcher.group(1).replace("%", "").replace("$s", "")));
                specificMatcher.group(2);
            }

            final int highest = Collections.max(nums);

            if (highest > count) {
                count = highest;
            }
        }

        if (concatenations.length == 1) {
            return String.format(messages.get(0), concatenations[0]);
        }

        final int midIndex = messages.size() - count;
        final String msg = String.join("", messages.subList(0, midIndex));
        final Object[] vars = messages.subList(midIndex, messages.size()).toArray();

        return String.format(msg, vars);
    }

    public static String format(Object message) {
        return message instanceof LangEnum ? LanguageGetter.get(((LangEnum) message).getPath()) : String.valueOf(message);
    }
}