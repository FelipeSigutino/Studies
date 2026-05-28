import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the integer:");
        int A = input.nextInt();
        if(A%2==1) {
            System.out.println("Number is odd");
        }
        else{
            System.out.println("Number is even");
        }
    }
}