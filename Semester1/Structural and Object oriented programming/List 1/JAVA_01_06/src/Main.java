import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s=0;
        System.out.println("Enter the n value (integers): ");
        int N= sc.nextInt();
        for (int i=1; i <=N ; i++) {
            s=s+i;
        }
        System.out.println("The sum from 1 to n is equal to:" +s);


    }
}