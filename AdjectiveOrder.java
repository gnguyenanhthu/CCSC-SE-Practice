import java.util.*;

public class MyClass {
    private static final Map<Integer, String> categoryAdjectives = new HashMap<>();

    // Populate the categoryAdjectives map with categories and their adjectives
    static {
        categoryAdjectives.put(1, "beautiful, delicious, reliable, unusual");
        categoryAdjectives.put(2, "big, deep, small, tall");
        categoryAdjectives.put(3, "rough, shiny, thin, untidy");
        categoryAdjectives.put(4, "oval, round, square, rectangular");
        categoryAdjectives.put(5, "elderly, old, young, youthful");
        categoryAdjectives.put(6, "blue, green, red, yellow");
        categoryAdjectives.put(7, "American, Chilean, Dutch, Japanese");
        categoryAdjectives.put(8, "brass, plastic, steel, wooden");
        categoryAdjectives.put(9, "four-sided, general-purpose, L-shaped, U-shaped");
        categoryAdjectives.put(10, "cleaning, cooking, frying, hammering");
    }

    // Method to find the category of an adjective
    public static int getCategory(String adjective) {
        for (Map.Entry<Integer, String> entry : categoryAdjectives.entrySet()) {
            if (entry.getValue().contains(adjective.toLowerCase())) {
                return entry.getKey();
            }
        }
        // Return -1 if adjective not found (should not happen with valid input)
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        for (int i = 0; i < n; i++) {
            String phrase = scanner.nextLine();
            String[] words = phrase.split(" ");
            String adj1 = words[0];
            String adj2 = words[1];
            String noun = words[2];

            // Get categories for adj1 and adj2
            int category1 = getCategory(adj1);
            int category2 = getCategory(adj2);

            if (category1 <= category2) {
                System.out.println("\"" + phrase + "\" is correct");
            } else {
                System.out.println("\"" + phrase + "\" is incorrect - should be \"" + adj2 + " " + adj1 + " " + noun + "\"");
            }
        }
        scanner.close();
    }
}

/*Stdin inputs
3
big tall statue
shiny big car
rectangular cleaning brush
*/
