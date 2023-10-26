import java.util.Scanner;

public class MyClass {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String temp = input.nextLine();

        for (int i = 1; i <= n; i++) {
            double s, w, r;
            s = input.nextDouble();
            w = input.nextDouble();
            r = (s * Math.sqrt(2) - w)/2;
            temp = input.nextLine();
            System.out.printf("Metal plate %d needs r = %.3f\n", i,r);
        }
    }
}'

/*Stdin Inputs
3
1    0.5
2    2
3.5  4
*/