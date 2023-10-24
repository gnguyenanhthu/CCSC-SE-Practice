import java.util.Scanner;
import java.lang.String;

public class MyClass {
    public static void main(String args[]) {
      Scanner input = new Scanner(System.in);
      System.out.println("Ready!");
      String mirrorPair = "bd db qp pq";
      int countMirror = 0, countOrdinary = 0;
      String temp = input.nextLine();
      while(temp!=null){
        if ((int)temp.charAt(0) == 32 && (int)temp.charAt(1) == 32){
            System.out.print("Summary: ");
            if (countMirror < 2)
                System.out.print(countMirror + " Mirrored pair, ");
            else
                System.out.print(countMirror + " Mirrored pairs, ");
            if (countOrdinary < 2)
                System.out.print(countOrdinary + " Ordered pair");
            else
                System.out.print(countOrdinary + " Ordered pairs");
            break;
        }
        else if (mirrorPair.contains(temp)){
            System.out.println("Mirrored pair");
            ++countMirror;
        }
        else{
            System.out.println("Ordinary pair");
            ++countOrdinary;
        }
        temp = input.nextLine();
      }
    }
}