package com.mcfly.springtemp.algorithms;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseAlgorithm<Input> {

    public abstract Input[] getArguments();

    public abstract Object calculate(Input input);

    public Collection<Object> perform() {
        return perform(getArguments());
    }

    public Collection<Object> perform(Input[] inputs) {
        return Arrays.stream(inputs)
                .map(input -> getClass().getSimpleName()
                        + ":\nInput: "
                        + input
                        + "\nOutput: "
                        + calculate(input)
                        + "\n-----")
                .collect(Collectors.toList());
    }

    public void performAndPrint() {
        perform().forEach(result -> System.out.println("$$$ " + result.toString()));
    }
}
