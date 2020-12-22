package de.ungefroren.adventofcode.y2020.day08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Assembler implements Runnable {

    public static final Operation ACCUMULATE = new Operation() {
        @Override
        public void perform(Assembler assembler, int argument) {
            assembler.accumulator += argument;
            assembler.position++;
        }

        @Override
        public String getName() {
            return "acc";
        }

        @Override
        public String toString() {
            return getName();
        }
    };
    public static final Operation JUMP = new Operation() {
        @Override
        public void perform(Assembler assembler, int argument) {
            assembler.position += argument;
        }

        @Override
        public String getName() {
            return "jmp";
        }

        @Override
        public String toString() {
            return getName();
        }
    };
    public static final Operation NO_OPERATION = new Operation() {
        @Override
        public void perform(Assembler assembler, int argument) {
            assembler.position++;
        }

        @Override
        public String getName() {
            return "nop";
        }

        @Override
        public String toString() {
            return getName();
        }
    };

    private final Operation[] operations = new Operation[]{ACCUMULATE, JUMP, NO_OPERATION};
    private final List<Instruction> instructions;
    private final Set<Integer> positions = new HashSet<>();
    private int position;
    private int accumulator;
    private boolean error = false;

    public Assembler(Stream<String> instructions) {
        this.instructions = instructions.map(this::parseInstruction).collect(Collectors.toList());
    }

    @Override
    public void run() {
        try {
            do {
                positions.add(position);
                instructions.get(position).run();
            } while (!positions.contains(position));  // error, infinite loop
            error = true;
        } catch (IndexOutOfBoundsException ignored) { //Stop execution, end of program reached
        }
    }

    public int getAccumulator() {
        return accumulator;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean abortedAbnormally() {
        return error;
    }

    public Instruction parseInstruction(String string) {
        String[] args = string.trim().split(" ");
        if (args.length < 2) throw new IllegalArgumentException("missing arguments: " + string);
        Optional<Operation> operation = Arrays.stream(operations)
                .filter(o -> o.getName().equalsIgnoreCase(args[0])).findAny();
        if (!operation.isPresent()) throw new IllegalArgumentException("unknown operation: " + args[0]);
        int arg = Integer.parseInt(args[1]);
        return new Instruction(operation.get(), arg);
    }

    public interface Operation {
        void perform(Assembler assembler, int argument);

        String getName();
    }

    public class Instruction {
        private final Operation op;
        private final int argument;

        public Instruction(Operation op, int argument) {
            this.op = op;
            this.argument = argument;
        }

        private void run() {
            op.perform(Assembler.this, argument);
        }
    }
}
