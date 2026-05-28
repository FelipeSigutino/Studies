import java.util.*;
Random rand = new Random();

void main() {
    Pancake_Sort pancake = new Pancake_Sort();
    int[] Out = new int[10];
    for (int i = 0; i< Out.length; i++) {
        Out[i] = rand.nextInt(100);
    }

    pancake.pancakeSort(Out);

}

