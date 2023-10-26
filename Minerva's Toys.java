import java.util.Scanner;
import java.util.ArrayList;

public class MyClass {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        ArrayList<Double> xdata = new ArrayList<>();
        ArrayList<Double> ydata = new ArrayList<>();
        double distance = 0, minDistance = 0;
        int m = input.nextInt();
        String temp = input.nextLine();
        for (int j = 0; j < m; j++) {
            int n = input.nextInt();
            temp = input.nextLine();
            for (int k = 1; k <= n; k++) {
                double x, y;
                x = input.nextDouble();
                y = input.nextDouble();
                temp = input.nextLine();

                xdata.add(x);
                ydata.add(y);

                if (xdata.size() == 1)
                    continue;
                else {
                    for (int i = xdata.size()-1; i > 0; i--) {
                        distance = Math.sqrt(Math.pow(xdata.get(xdata.size()-1) - xdata.get(i-1),2) + Math.pow(ydata.get(ydata.size()-1) - ydata.get(i-1),2));
                        if (minDistance == 0)
                            minDistance = distance;
                        if (distance < minDistance)
                            minDistance = distance;
                    }
                }
            }
             System.out.printf("%.3f", minDistance);
        }
    }
}

/*Stdin Inputs
1
4
1.0 2.0
2.0 1.0
1.0 3.0
-1.0 5.0
*/