import java.util.Scanner;


int[] Numbers = {9, 3, 5, 4, 7, 1, 5, 1, 9};
int Smallest;
int SecondSmallest;
void main() {
    Smallest = Numbers[0];
        for ( int i = 0; i < Numbers.length; i++ ) {
        if  ( Numbers[i] < Smallest ) {
            Smallest = Numbers[i];
        }
    }

}
