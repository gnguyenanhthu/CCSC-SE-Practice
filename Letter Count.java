import java.util.Scanner;
import java.lang.String;
import java.util.ArrayList;

public class MyClass {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int attempts = input.nextInt();
        String temp = input.nextLine();
        
        for (int i = 1; i <= attempts; i++){
        String str = input.nextLine();
        str = str.replaceAll(" ", "").toLowerCase();

		char[] arr = str.toCharArray();
		ArrayList<Character> used = new ArrayList<>();
		ArrayList<Integer> frequency = new ArrayList<>();
		ArrayList<Character> missing = new ArrayList<>();

		for (int j = 97; j <= 122; j++){
		    char c = (char)j;
		    if (str.indexOf(c)!=-1){
		        used.add(c);
		        int f = 0;
		        for (char x : arr){
		            if (x == c)
		                ++f;
		        }
		        frequency.add(f);
		    }
		    else
		        missing.add(c);
		}
		
		int mostUsed = frequency.get(0), mostUsedIndex = 0;
        for (int k = 1; k < frequency.size(); k++) {
            if (mostUsed < frequency.get(k)){
                mostUsed = frequency.get(k);
                mostUsedIndex = k;
            }
        }
		System.out.print("Case " + i + "> Used: ");
		for (char x : used)
		{
			System.out.print(x);
		}
		
		System.out.print("; Missing: ");
		
		for (char x : missing)
		{
			System.out.print(x);
		}
		
		System.out.println("; Most Used: " + used.get(mostUsedIndex));
        }
		
		//System.out.println((char)97); -> a
		//System.out.println((char)122); -> z
    }
    /*
    3
    The quick brown fox jumped over the lazy dog.
    She sold seashells by the seashore.
    Go hang a salami...I am a lasagna hog.
    */
}