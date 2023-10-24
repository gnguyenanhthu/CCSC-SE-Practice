import java.util.Scanner;
import java.lang.String;
import java.util.ArrayList;

public class MyClass {
    public static void main(String args[]) {
        char data[] = {'S', 'T', 'U', 'V', 'W', 'X',
                       'Y', 'Z', '0', '1', '2', '3',
                       '4', '5', '6', '7', '8', '9',
                       'A', 'B', 'C', 'D', 'E', 'F',
                       'G', 'H', 'I', 'J', 'K', 'L',
                       'M', 'N', 'O', 'P', 'Q', 'R'
                      };
        char matrix[][] = new char[6][6];
        int index = 0;
        for (int i=0; i< 6; i++) {
            for (int j=0; j< 6; j++) {
                matrix[i][j] = data[index];
                ++index;
            }
        }
        Scanner input = new Scanner(System.in);
        int wordNumber = input.nextInt();
        String temp = input.nextLine();
        for (int k = 1; k <= wordNumber; k++) {
            String str = input.nextLine();
            char[] charArray = str.toCharArray();
            // for (int i=0; i<charArray.length; i++) {
            //     int rowNumb = findRow(matrix, charArray[i]);
            //     int columnNumb = findColumn(matrix, charArray[i]);
            //     System.out.println("Row: " + rowNumb + ", Column: " + columnNumb);
            // }
            ArrayList<Character> encrypted = new ArrayList<>();
            for (int i=0; i<charArray.length - 1; i+=2) {
                int row1 = findRow(matrix, charArray[i]), column1 = findColumn(matrix, charArray[i]);
                int row2 = findRow(matrix, charArray[i+1]), column2 = findColumn(matrix, charArray[i+1]);
                if (row1 == row2 || column1 == column2) {
                    encrypted.add(charArray[i+1]);
                    encrypted.add(charArray[i]);
                } else {
                    encrypted.add(matrix[row1][column2]);
                    encrypted.add(matrix[row2][column1]);
                }
            }
            if (charArray.length % 2 != 0)
                encrypted.add(charArray[charArray.length-1]);
            System.out.print("Word #" + k + " " + str + " is encrypted as ");
            for (int i = 0; i < encrypted.size(); i++) {
                System.out.print(encrypted.get(i));
            }
            System.out.println();
        }
        //   for (int i = 0; i < 6; i++) {
        //     for (int j = 0; j < 6; j++) {
        //         System.out.print(matrix[i][j] + " ");
        //     }
        //         System.out.println();
        //   }
    }

    public static int findRow(char[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == c) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int findColumn(char[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == c) {
                    return j;
                }
            }
        }
        return -1;
    }
}