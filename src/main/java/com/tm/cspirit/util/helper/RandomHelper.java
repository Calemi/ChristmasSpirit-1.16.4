package com.tm.cspirit.util.helper;

import java.util.Random;

public class RandomHelper {

    private static final Random RNG = new Random();

    public static int getWeightedRandom(int... weights) {

        int totalWeights = 0;

        for (int weight : weights) {
            totalWeights += weight;
        }

        int randomValue = RNG.nextInt(totalWeights);
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {

            sum += weights[i];

            if (randomValue < sum) {
                return i;
            }
        }

        return -1;
    }
}
