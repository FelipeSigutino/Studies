import java.util.Arrays;
import java.util.Scanner;

// {1 , 2 , 3}

public class Permutations {
    int [] Numbers = {1,2,3};

    int [] SortArray() {


        for (int j = 0; j < Numbers.length; j++) {
            for (int i = 0; i < Numbers.length - 1; i++) {
                if (Numbers[i] > Numbers[i + 1]) {
                    int temp = Numbers[i];
                    Numbers[i] = Numbers[i + 1];
                    Numbers[i + 1] = temp;
                }
            }
        }
        return Numbers;
    }

    boolean nextPermutation(int[] inputArray) {
        int i =  inputArray.length - 2;
        while (i >= 0 && inputArray[i] > inputArray[i + 1]) i--;
        if (i < 0) {
            System.out.println("No Next Lexicographic Permutation");
            return false;
        }

        int j = inputArray.length - 1;
        while (inputArray[j] <= inputArray[i]) j--;

        int t = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = t;

        for(int l   = i + 1, r = inputArray.length - 1; l < r; l++, r--) {
            t = inputArray[l];
            inputArray[l] = inputArray[r];
            inputArray[r] = t;
        }
        return true;
    }

    void main(){
        System.out.println("Please enter a number to permute: (numbers should be separated by coma)");

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] inputArrayS = input.split(",");

        int[] inputArray = new int[inputArrayS.length];

        for (int i = 0; i < inputArrayS.length; i++) {
            inputArray[i] = Integer.parseInt(inputArrayS[i]);
        }

        while(nextPermutation(inputArray)){
            System.out.println(Arrays.toString(inputArray) + " ");
        }
    }
}
