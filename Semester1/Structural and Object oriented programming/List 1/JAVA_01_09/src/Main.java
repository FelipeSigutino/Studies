import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a: ");
        int a = input.nextInt();
        System.out.print("Enter b: ");
        int b = input.nextInt();
        System.out.print("Enter c: ");
        int c = input.nextInt();
        if (a > b && a > c) {
            System.out.println(a + " is greater than remaining numbers");
        }
        else if (b>a && b>c) {
            System.out.println(b+ " is greater than remaining numbers");
        }
        else {
            System.out.println(c+ " is greater than remaining numbers");
        }
    }
}