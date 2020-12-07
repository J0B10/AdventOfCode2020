package de.ungefroren.adventofcode.y2020.day07;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LuggageBag {

    private static final Pattern BAG_REGEX = Pattern.compile("(?<color>.*)\\sbags\\scontain\\s(?<content>.*)\\.");
    private static final Pattern CONTENT_REGEX = Pattern.compile("(?<amount>\\d+)\\s(?<color>.+?)\\sbags?");

    private final String color;
    private final HashMap<LuggageBag, Integer> containedBy = new HashMap<>();
    private final HashMap<LuggageBag, Integer> contains = new HashMap<>();

    private LuggageBag(String color) {
        this.color = color;
    }

    public static Map<String, LuggageBag> parseAll(PuzzleInput input) {
        HashMap<String, LuggageBag> all = new HashMap<>();
        for (String line : input) {
            Matcher matchB = BAG_REGEX.matcher(line);
            if (!matchB.matches()) throw new IllegalArgumentException();
            String color = matchB.group("color");
            String content = matchB.group("content");
            LuggageBag bag = all.containsKey(color) ? all.get(color) : new LuggageBag(color);
            all.put(color, bag);
            Matcher matchC = CONTENT_REGEX.matcher(content);
            while (matchC.find()) {
                String c = matchC.group("color");
                int a = Integer.parseInt(matchC.group("amount"));
                LuggageBag b = all.containsKey(c) ? all.get(c) : new LuggageBag(c);
                all.put(c, b);
                bag.addContent(b, a);
            }
        }
        return all;
    }

    public Set<LuggageBag> getAllContainers() {
        HashSet<LuggageBag> containers = new HashSet<>(containedBy.keySet());
        containedBy.keySet().forEach(bag -> containers.addAll(bag.getAllContainers()));
        return Collections.unmodifiableSet(containers);
    }

    public long getAmountInside() {
        return contains.entrySet().stream().mapToLong(e -> e.getValue() * (1 + e.getKey().getAmountInside())).sum();
    }

    private void addContent(LuggageBag bag, int amount) {
        bag.containedBy.put(this, amount);
        this.contains.put(bag, amount);
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return getColor();
    }
}
