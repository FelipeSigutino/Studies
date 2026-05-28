import java.util.Scanner;

public class SecondSmallestNumber {

    int Smallest = Integer.MAX_VALUE;
    int SecondSmallest = Integer.MAX_VALUE;

    int getSecondSmallest(int[] inputArray) {

        for ( int i = 0; i < inputArray.length ; i++ ) {
            if  (inputArray[i] <  Smallest) {
                SecondSmallest = Smallest;
                Smallest = inputArray[i];
            } else if (inputArray[i] <  SecondSmallest && inputArray[i] > Smallest) {
                SecondSmallest = inputArray[i];
            }
        }
        return SecondSmallest;
    }

    void main() {

        System.out.println("Please input array of values : (Numbers should be separated by coma) ");
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] inputArrayS = input.split(",");

        int[] inputArray = new int[inputArrayS.length];

        for (int i = 0; i < inputArrayS.length; i++) {
            inputArray[i] = Integer.parseInt(inputArrayS[i]);
        }

        int secondSmallestOutPut = getSecondSmallest(inputArray);

        if (secondSmallestOutPut != Integer.MAX_VALUE) {
            System.out.println("The Second Smallest Value in given array is: " + secondSmallestOutPut);
        } else {
            System.out.println("There is no second smallest value because array consist of Numbers of the same value!");
        }
    }
}
