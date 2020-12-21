package de.ungefroren.adventofcode.y2020.day21;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class Day21B {

    private static final Pattern REGEX = Pattern.compile("(?<ingredients>.+) \\(contains (?<allergens>.+)\\)");

    public static void main(String[] args) throws IOException {
        //map containing possible ingredients for each allergen
        List<String> allIngredients = new LinkedList<>();
        HashMap<String, Set<String>> allergens = new HashMap<>();
        for (String line : PuzzleInput.of(Day21B.class)) {
            Matcher match = REGEX.matcher(line);
            if (!match.matches()) throw new IllegalArgumentException();
            List<String> ingredientsList  = Arrays.asList(match.group("ingredients").split(" "));
            allIngredients.addAll(ingredientsList);
            String[] allergensList = match.group("allergens").split(", ");
            for (String allergen : allergensList) {
                if (allergens.containsKey(allergen)) {
                    Set<String> a = ingredientsList.stream()
                            .filter(allergens.get(allergen)::contains).collect(Collectors.toSet());
                    allergens.put(allergen, a);
                } else {
                    allergens.put(allergen, new HashSet<>(ingredientsList));
                }
            }
        }

        //cleanup ingredients (remove ingredients that are known to be other allergens)
        int knownSize = 0;
        Map<String, String> known;
        while (true) {
             known = allergens.entrySet().stream().filter(e -> e.getValue().size() == 1)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toArray(new String[0])[0]));
            if (knownSize == known.size()) break; //no new known allergens found, abort
            knownSize = known.size();
            known.forEach((allergen, ingredient) -> {
                allergens.forEach((a, ingredients) -> {
                    if (!a.equals(allergen)) ingredients.remove(ingredient);
                });
            });
        }
        String canonicalDangerousIngredientList = known.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(","));
        System.out.println(canonicalDangerousIngredientList);
    }
}
