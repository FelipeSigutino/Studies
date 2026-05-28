import java.util.Scanner;
//  1 2 2 1
// 1 3 4 3 1

public class PascalTriangle {

    int[] NextPascalLine(int[] InputArray ) {
        int[] OutputArray = new int[InputArray.length + 1];
        OutputArray[0] = 1;
        OutputArray[OutputArray.length - 1] = 1;

        for (int i = 1; i < InputArray.length; i++) {
            OutputArray[i] = InputArray[i - 1] + InputArray[i];
        }

        return OutputArray;
    }


    void main() {
        System.out.println("Please input Pascal Line: (Numbers should be separated by coma) ");
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] inputArrayS = input.split(",");

        int[] inputArray = new int[inputArrayS.length];

        for (int i = 0; i < inputArrayS.length; i++) {
            inputArray[i] = Integer.parseInt(inputArrayS[i]);
        }

        int [] outputArray = NextPascalLine(inputArray);

        for  (int i = 0; i < outputArray.length; i++) {
            if (i == outputArray.length - 1){
                System.out.println(outputArray[i]);
            } else {
                System.out.print(outputArray[i] + ",");
            }
        }
    }
}
