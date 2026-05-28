import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first integer: ");
        int a = input.nextInt();
        System.out.print("Enter the second integer: ");
        int b = input.nextInt();

        if(a>b) {
            System.out.println(a+" is greater than "+b);
        }
        else if(a<b) {
            System.out.println(b+" is greater than "+a);
        }
        else if (a == b){
            System.out.println(a+" is equal to "+b);
        }
    }
}