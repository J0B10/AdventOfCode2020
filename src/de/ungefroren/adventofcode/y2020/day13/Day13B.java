package de.ungefroren.adventofcode.y2020.day13;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13B {

    private static final long START = 100000000000000L;
    private static final int WORKER_RANGE = 100000000;

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        List<String> departures = Arrays.asList(PuzzleInput.of(Day13B.class).getLines().get(1).split(","));
        int max = departures.stream().filter(s -> s.matches("\\d+")).mapToInt(Integer::parseInt).max().getAsInt();
        int maxIndex = departures.indexOf(String.valueOf(max));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        long progress = START / max;
        while (true) {
            final long start = progress;
            List<Future<OptionalLong>> workers = IntStream.range(0, cores).mapToObj(i -> executorService.submit(
                    new Worker(start + i, start + i + WORKER_RANGE, cores, departures, max, maxIndex)
            )).collect(Collectors.toList());

            List<Long> values = new ArrayList<>(cores);
            for (Future<OptionalLong> worker : workers) {
                OptionalLong result = worker.get();
                if (result.isPresent()) values.add(result.getAsLong());
            }
            if (values.isEmpty()) {
                progress += cores + WORKER_RANGE - 1;
                System.out.println("Current progress: " + progress * max);
            } else {
                System.out.println("==============================================");
                System.out.println("          RESULT: " + values.stream().mapToLong(Long::longValue).min().getAsLong());
                executorService.shutdown();
                return;
            }
        }
    }

    private static class Worker implements Callable<OptionalLong> {

        private final long start;
        private final long end;
        private final int stepSize;
        private final List<String> departures;
        private final int max;
        private final int maxIndex;

        public Worker(long start, long end, int stepSize, List<String> departures, int max, int maxIndex) {
            this.start = start;
            this.end = end;
            this.stepSize = stepSize;
            this.departures = departures;
            this.max = max;
            this.maxIndex = maxIndex;
        }

        @Override
        public OptionalLong call() throws Exception {
            long t = 1;
            for (long i = start; t >= 0 && end > i; t = (i += stepSize) * max) { //runs till overflow of t (i.e. forever)
                long timestamp = t - maxIndex;
                boolean success = true;
                for (int j = 0; j < departures.size(); j++) {
                    try {
                        int busID = Integer.parseInt(departures.get(j));
                        if ((timestamp + j) % busID != 0) {
                            success = false;
                            break;
                        }
                    } catch (NumberFormatException ignored) { //skip 'x'
                    }
                }
                if (success) return OptionalLong.of(timestamp);
            }
            return OptionalLong.empty();
        }
    }
}
