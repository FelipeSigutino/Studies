import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the positive integer n: "); //this condition could be avoided implementing absolute value to i??
        int n = input.nextInt();

        for (int i = 1; i <= n; i++) {
            if(i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}