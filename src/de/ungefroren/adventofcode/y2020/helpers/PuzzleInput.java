package de.ungefroren.adventofcode.y2020.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Helper class that provides easy ways of parsing the input of a puzzle.
 * <p>
 * Use {@link #toString()} to just get the content of the file.
 */
public class PuzzleInput implements Iterable<String> {

    /**
     * By default the puzzle input should be located in a file called <code>puzzle_input.txt</code>
     * inside the same package as the class that uses this input.
     * <p>
     * This is mandatory if you use {@link #of(Class)}
     */
    public static final String DEFAULT_NAME = "puzzle_input.txt";

    private final ArrayList<String> lines;

    private PuzzleInput(List<String> lines) {
        this.lines = new ArrayList<>(lines);
    }

    /**
     * Read the puzzle input from a file defined by the given url using a buffered reader.
     * UTF-8 Charset must be used.
     *
     * @param resource the resource file that should be the puzzle input
     * @return a new instance of the puzzle input
     * @throws IOException if an io exception occurred while reading, see {@link URL#openStream()}
     */
    public static PuzzleInput read(URL resource) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.openStream(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        br.close();
        return new PuzzleInput(lines);
    }

    /**
     * Read the puzzle input for the given class using a buffered reader.
     * <p>
     * The puzzle input should be located in a file called <code>puzzle_input.txt</code>
     * inside the same package as the given class. UTF-8 Charset must be used.
     *
     * @param clazz clazz that will use the input
     * @return a new instance of the puzzle input
     * @throws IOException if an io exception occurred while reading, see {@link URL#openStream()}
     */
    public static PuzzleInput of(Class<?> clazz) throws IOException {
        return PuzzleInput.read(clazz.getResource(DEFAULT_NAME));
    }

    /**
     * @return a list containing the lines of the input file.
     */
    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }

    /**
     * @return the lines of the input file as stream.
     */
    public Stream<String> lines() {
        return getLines().stream();
    }

    /**
     * Parses the lines of the input as integer before returning the stream.
     */
    public IntStream ints() {
        return lines().mapToInt(Integer::parseInt);
    }

    /**
     * Parses the lines of the input as integer before returning the list.
     */
    public List<Integer> getInts() {
        return ints().boxed().collect(Collectors.toList());
    }

    /**
     * Parses the lines of the input as double before returning the stream.
     */
    public DoubleStream doubles() {
        return lines().mapToDouble(Double::parseDouble);
    }

    /**
     * Parses the lines of the input as double before returning the list.
     */
    public List<Double> getDoubles() {
        return doubles().boxed().collect(Collectors.toList());
    }

    /**
     * Parses the lines of the input as long before returning the stream.
     */
    public LongStream longs() {
        return lines().mapToLong(Long::parseLong);
    }

    /**
     * Parses the lines of the input as long before returning the list.
     */
    public List<Long> getLongs() {
        return longs().boxed().collect(Collectors.toList());
    }

    /**
     * Parses the lines of the input using the provided mapper before returning the stream.
     * See {@link Stream#map(Function)}
     */
    public <R> Stream<R> map(Function<String, ? extends R> mapper) {
        return getLines().stream().map(mapper);
    }

    /**
     * Parses the lines of the input using the provided mapper before returning the list.
     * See {@link Stream#map(Function)}
     */
    public <R> List<R> mapToList(Function<String, ? extends R> mapper) {
        return map(mapper).collect(Collectors.toList());
    }

    /**
     * Split the PuzzleInput around places of the given regular expression, instead of the new line character
     *
     * @param regex regex at which the input should be split
     * @return new Puzzle input split at regex
     */
    public PuzzleInput split(String regex) {
        return new PuzzleInput(Arrays.asList(toString().split(regex)));
    }

    /**
     * Returns an iterator for iterating over the lines of the input.
     * Useful for for-each loops.
     */
    @Override
    public Iterator<String> iterator() {
        return getLines().iterator();
    }

    /**
     * @return the original file contents as string
     */
    @Override
    public String toString() {
        return lines().collect(Collectors.joining("\n"));
    }
}
