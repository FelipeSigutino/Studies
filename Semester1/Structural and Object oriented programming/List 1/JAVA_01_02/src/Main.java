import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int a;
        System.out.print("Enter first integer: ");
        a = input.nextInt();

        int b;
        System.out.print("Enter second integer: ");
        b = input.nextInt();

        int quotient=0;

        int sum = a + b;
        int difference = a - b;
        int product = a * b;
        if (b != 0) {
            quotient = a / b;
        }
        else{}

        System.out.println("The sum is: " + sum);
        System.out.println("The difference is: " + difference);
        System.out.println("The product is: " + product);
        if (b == 0) {
            System.out.println("It's impossible to divide by zero");
        }
        else {
        System.out.println("The quotient is: " + quotient);
    }   }
}