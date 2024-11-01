package CCSC2023;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 1. Input Parsing: Read multiple days of running data until "END" is reached. Each day ends with "LAST".
 * 2. Data Processing:
 *      For each day, convert distances to both miles and kilometers.
 *      Calculate the total time for each day, and determine the pace per mile and kilometer.
 *      Accumulate the overall distance in miles and kilometers, and the total time.
 * 3. Output Formatting:
 * Print a table with daily summaries, including total miles, kilometers, and pace per unit.
 * Include an overall distance and time summary.
 */

public class MyrtleBeachMarathon {
    private static final double MILES_TO_KM = 1.60934;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<DaySummary> daySummaries = new ArrayList<>();

        double totalMiles = 0.0, totalKm = 0.0;
        Duration totalDuration = Duration.ZERO;
        int dayNumber = 1;

        String line = scanner.nextLine().trim();
        while (!line.equals("END")) {
            ArrayList<Run> runs = new ArrayList<>();
            while (!line.equals("LAST")) {
                String[] parts = line.split(" ");
                double distance = Double.parseDouble(parts[0].replace("m", "").replace("k", ""));
                boolean inMiles = parts[0].endsWith("m");
                // Parse time using LocalTime and convert to Duration
                LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER);
                Duration runDuration = Duration.ofSeconds(time.toSecondOfDay());
                runs.add(new Run(distance, inMiles, runDuration));
                line = scanner.nextLine().trim();
            }
            DaySummary daySummary = calculateDaySummary(dayNumber, runs);
            daySummaries.add(daySummary);

            // Accumulate overall stats
            totalMiles += daySummary.totalMiles;
            totalKm += daySummary.totalKm;
            totalDuration = totalDuration.plus(daySummary.totalDuration);
            dayNumber++;
            line = scanner.nextLine().trim();
        }
        printResults(daySummaries, totalMiles, totalKm, totalDuration);
    }

    private static DaySummary calculateDaySummary(int dayNumbers, ArrayList<Run> runs) {
        double totalMiles = 0.0;
        double totalKm = 0.0;
        Duration totalDuration = Duration.ZERO;

        for (Run run : runs) {
            double miles = run.inMiles ? run.distance : run.distance / MILES_TO_KM;
            double km = run.inMiles ? run.distance * MILES_TO_KM : run.distance;
            totalMiles += miles;
            totalKm += km;
            totalDuration = totalDuration.plus(run.duration);
        }
        String pacePerMile = formatDuration(totalDuration.dividedBy((long)totalMiles));
        String pacePerKm = formatDuration(totalDuration.dividedBy((long)totalKm));

        return new DaySummary(dayNumbers, totalMiles, totalKm, totalDuration, pacePerMile, pacePerKm);
    }

    private static void printResults(List<DaySummary> daySummaries, double totalMiles, double totalKm, Duration totalDuration) {
        System.out.println("\t   Miles\tKm\tTime\tPace/mile\tPace/km");
        System.out.println();
        for (DaySummary day : daySummaries) {
            System.out.printf("\nDay %d\t%.2f\t%.2f\t%s\t%s\t%s%n",
                    day.dayNumber, day.totalMiles, day.totalKm, formatDuration(day.totalDuration), day.pacePerMile, day.pacePerKm);
        }

        System.out.printf("\nOverall Distance of %.2f miles (%.2f km)\n", totalMiles, totalKm);
        System.out.printf("Overall Time of %s\n", formatDuration(totalDuration));
    }

    private static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        if (days >= 1) {
            return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
        }
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // Represents a single run with distance and duration
    static class Run {
        double distance;
        boolean inMiles;
        Duration duration;

        Run(double distance, boolean inMiles, Duration duration) {
            this.distance = distance;
            this.inMiles = inMiles;
            this.duration = duration;
        }
    }

    // Represents a summary of a day's runs with total distance, duration, and run details
    static class DaySummary {
        int dayNumber;
        double totalMiles;
        double totalKm;
        Duration totalDuration;
        String pacePerMile;
        String pacePerKm;

        DaySummary(int dayNumber, double totalMiles, double totalKm, Duration totalDuration, String pacePerMile, String pacePerKm) {
            this.dayNumber = dayNumber;
            this.totalMiles = totalMiles;
            this.totalKm = totalKm;
            this.totalDuration = totalDuration;
            this.pacePerMile = pacePerMile;
            this.pacePerKm = pacePerKm;
        }
    }
}
