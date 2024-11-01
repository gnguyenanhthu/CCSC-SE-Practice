package CCSC2023;

import java.util.*;

/**
 * 1. Input Parsing:
 * The program reads the number of cases, each specifying:
 * The available types of plates with their weights and quantities.
 * The number of scenarios with target weights.
 *
 * 2. Solution:
 * For each target weight:
 *      Subtract the base barbell weight (45 lbs) to get the effective target weight.
 *      Use the largest weights first (greedy approach) while ensuring not to exceed the target.
 *      Pair the plates as required, ensuring that only an even number of each plate type can be used.
 *      If reaching the exact target weight isn’t possible, find the closest possible weight below the target.
 *
 * 3. Output Requirements:
 * Print each plate type and the count of plates used.
 * If a solution isn’t possible, print "No solution".
 * If no plates are needed to hit the target (effective weight is 0), print "No plates".
 */

public class OlympicDilemma {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int caseIdx = 1; caseIdx <= numCases; caseIdx++) {
            //Read number of plate type
            int plateTypes = scanner.nextInt();
            scanner.nextLine();
            // TreeMap with descending order to use the largest weights first
            TreeMap<Integer, Integer> plates = new TreeMap<>(Collections.reverseOrder());
            for (int i = 0; i < plateTypes; i++) {
                int weight = scanner.nextInt();
                int count = scanner.nextInt();
                scanner.nextLine();
                plates.put(weight, count);
            }
            //Read number of scenarios
            int numScenarios = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Case #" + caseIdx + ":");
            for (int i = 0; i < numScenarios; i++) {
                int targetWeight = scanner.nextInt();
                scanner.nextLine();

                String result = solveScenario(plates, targetWeight);
                System.out.println(result);
            }
            System.out.println();
        }
        scanner.close();
    }

    private static String solveScenario(TreeMap<Integer, Integer> plates, int targetWeight) {
        final int baseWeight = 45;
        int effectiveTarget = targetWeight - baseWeight;
        if (effectiveTarget < 0) {
            return "No solution";
        }

        int currentWeight = 0;
        Map<Integer, Integer> usedPlates = new LinkedHashMap<>(); // To keep insertion order
        for (Map.Entry<Integer, Integer> entry : plates.entrySet()) {
            int weight = entry.getKey();
            int count = entry.getValue();

            int pairsAvailable = count / 2;
            int maxPairsUsable = effectiveTarget / (2 * weight);
            int pairsUsed = Math.min(pairsAvailable, maxPairsUsable);
            if (pairsUsed > 0) {
                currentWeight += pairsUsed * 2 * weight;
                usedPlates.put(weight, pairsUsed * 2);
                //Update the target weight, if the target is met then break the loop
                effectiveTarget -= pairsUsed * 2 * weight;
                if (effectiveTarget <= 0) {
                    break;
                }
            }
        }

        if (usedPlates.isEmpty()) {
            return "No plates";
        } else {
            String output = "";
            for (Map.Entry<Integer, Integer> entry : usedPlates.entrySet()) {
                output += entry.getKey() + " x " + entry.getValue() + ", ";
            }
            return output.substring(0, output.length() - 2);  // Remove trailing ", "
            //should use StringBuilder with append and setLength for better edit output
        }
    }
}
