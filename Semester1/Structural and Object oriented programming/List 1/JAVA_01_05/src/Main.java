import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the temperatura in Celcius: ");
        int C = input.nextInt();
        int F = C*9/5 + 32;
        System.out.println("The temperatura in Faradajach is: "+F);
    }
}