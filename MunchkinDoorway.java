package CCSC2023;

import java.util.*;

/**
 * 1. For each Munchkin, read their height and list of doors. Each height measurement may use different units (inches, feet, yards, centimeters, or meters).
 * 2. Convert All Heights to a Common Unit - inch.
 * 3. Determine Travel Method.
 * 4. Print the Munchkin's name and their method of passing through each door.
 */

public class MunchkinDoorway {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numMunchkins = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < numMunchkins; i++) {
            // Read Munchkin data
            String[] munchkinData = scanner.nextLine().trim().split(" ");
            String munchkinName = munchkinData[0];
            int numDoors = Integer.parseInt(munchkinData[1]);
            double munchkinHeight = convertToInches(Double.parseDouble(munchkinData[2]), munchkinData[3]);

            System.out.println(munchkinName);
            // Process each door for the Munchkin
            for (int j = 0; j < numDoors; j++) {
                String[] doorData = scanner.nextLine().trim().split(" ");
                double doorHeight = convertToInches(Double.parseDouble(doorData[0]), doorData[1]);
                String travelMethod = getTravelMethod(munchkinHeight, doorHeight);
                System.out.printf("Doorway %d: %s%n", j + 1, travelMethod);
            }
            System.out.println();
        }
        scanner.close();
    }

    private static double convertToInches(double height, String unit) {
        switch (unit) {
            case "i": return height;
            case "f": return height * 12;
            case "y": return height * 36;
            case "c": return height / 2.54;
            case "m": return height * 39.37;
            default: throw new IllegalArgumentException("Unknown unit: " + unit);
        }
    }

    private static String getTravelMethod(double munchkinHeight, double doorHeight) {
        if (doorHeight > munchkinHeight * 1.25) {
            return "Stilts";
        } else if (doorHeight > munchkinHeight * 1.05) {
            return "Walk";
        } else if (doorHeight > munchkinHeight * 0.65) {
            return "Duck";
        } else if (doorHeight > munchkinHeight * 0.40) {
            return "Crawl";
        } else if (doorHeight > munchkinHeight * 0.25) {
            return "Limbo";
        } else {
            return "Blocked";
        }
    }
}
