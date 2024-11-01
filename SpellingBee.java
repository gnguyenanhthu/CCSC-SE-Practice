package CCSC2023;

import java.util.*;
import java.io.*;

/**
 * Parse the input to get a list of words.
 * Calculate points based on the length of each word:
 * 4-letter words: 1 point.
 * Words with 5+ letters: 1 point per letter.
 * Pangrams (words that use all 7 letters) receive an extra 7 points.
 * Create a frequency chart that shows the count of words starting with each letter and length.
 * Identify and print any pangrams and the maximum score.
 */

public class SpellingBee {
    private static final Character[] SPELLING_BEE = new Character[] {'C', 'D', 'E', 'F', 'N', 'O', 'U'};
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int totalWords = Integer.parseInt(scanner.nextLine().trim());
        List<String> words = new ArrayList<>();
        Map<Character, Map<Integer, Integer>> frequencyTable = initializeFrequencyTable();
        List<String> pangrams = new ArrayList<>();
        int maxScore = 0;

        // Reading words and filling data structures
        for (int i = 0; i < totalWords; i++) {
            String word = scanner.nextLine().trim();
            words.add(word);
            int len = word.length();
            char firstChar = word.charAt(0);
            frequencyTable.get(firstChar).put(len, frequencyTable.get(firstChar).get(len) + 1);

            // Score calculation
            int wordScore = (len > 4) ? len : 1;
            if (isPangram(word)) {
                wordScore += 7;
                pangrams.add(word);
            }
            maxScore += wordScore;
        }

        // Output frequency table
        System.out.printf("%4s %4d %4d %4d %4d %4d %4d %4d %4d %4d %4s\n", " ", 4, 5, 6, 7, 8, 9, 10, 11, 12, "Sum");
        for (Map.Entry<Character, Map<Integer, Integer>> entry : frequencyTable.entrySet()) {
                System.out.printf("%-4s ", entry.getKey());
                int rowSum = 0;
                for (int len = 4; len <= 12; len++) {
                    int freq = entry.getValue().get(len);
                    System.out.printf("%4d ", freq);
                    rowSum += freq;
                }
                System.out.printf("%4d\n", rowSum);
        }
        // Sum row
        //mapObject.keySet -> return a set of key in the map
        System.out.printf("%-4s ", "Sum");
        for (int len = 4; len <= 12; len++) {
            int colSum = 0;
            for (char ch : frequencyTable.keySet()) {
                colSum += frequencyTable.get(ch).get(len);
            }
            System.out.printf("%4d ", colSum);
        }
        System.out.printf("%4d\n", totalWords);

        // Output pangrams and max score
        System.out.println();
        for (String pangram : pangrams) {
            System.out.println(pangram);
        }
        System.out.println(maxScore + " pts");

        scanner.close();
    }

    /**
     * if the spelling bee set change, can approach with for (char ch = 'A', ch <= 'Z', ch++)
     * @return
     */
    private static Map<Character, Map<Integer, Integer>> initializeFrequencyTable() {
        Map<Character, Map<Integer, Integer>> frequencyTable = new TreeMap<>();
        for (char ch : SPELLING_BEE) {
            frequencyTable.put(ch, initializeDefaultLengthMap());
        }
        return frequencyTable;
    }


    private static Map<Integer, Integer> initializeDefaultLengthMap() {
        Map<Integer, Integer> lengthMap = new TreeMap<>();
        for (int i = 4; i <= 12; i++) {
            lengthMap.put(i, 0);
        }
        return lengthMap;
    }

    private static boolean isPangram(String word) {
        Set<Character> uniqueChars = new HashSet<>();
        for (char ch : word.toCharArray()) {
            uniqueChars.add(Character.toLowerCase(ch));
        }
        return uniqueChars.size() == 7;
    }
}
