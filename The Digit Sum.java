import java.util.*;

public class MyClass {
	public static void main(String args[]) {
		Scanner in = new Scanner (System.in);
		int c = in.nextInt();
		for (int i = 0; i < c; i++) {
			int x = in.nextInt();
			int sum = 0;
			if (x < 0)
				x *= -1;
			while (x!=0) {
				sum += x%10;
				x /= 10;
			}
			System.out.println(sum);
		}
	}
}

/*Stdin Inputs
5
785
-1000
2
567000
9999
*/