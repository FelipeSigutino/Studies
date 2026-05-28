import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        int a = sc.nextInt();
        System.out.println("Enter the second number: ");
        int b = sc.nextInt();

        System.out.println("For addition type 1");
        System.out.println("For subtraction type 2");
        System.out.println("For multiplication type 3");
        System.out.println("For division type 4");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                int sum = a + b;
                System.out.println("The sum is equal to " +sum);
                break;
            case 2:
                int sub = a - b;
                System.out.println("The subtraction is equal to " +sub);
                break;
            case 3:
                int mul = a * b;
                System.out.println("The multiplication is equal to " +mul);
                break;
            case 4:
                if(b!=0){
                int div = a / b;
                System.out.println("The division is equal to " +div);
                }
                else{
                    System.out.println("You can't divide by zero");
                }
                break;
        }
    }
}