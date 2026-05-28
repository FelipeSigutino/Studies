
import static java.lang.IO.*;
import static java.lang.IO.println;
import static term.term.*;
import java.util.*;
import java.util.Random;
import java.util.stream.IntStream;

class NumberGenerator {

    int[] array;
    int[] array1;
    int[] combined;
    int[] random;
    Random rand;

    NumberGenerator(long seed) {
        array = IntStream.rangeClosed(9, 19).map(i -> i + 1).toArray();
        array1 = IntStream.rangeClosed(49, 69).map(i -> i + 1).toArray();

        combined = new int[array.length + array1.length];
        System.arraycopy(array, 0, combined, 0, array.length);
        System.arraycopy(array1, 0, combined, array.length, array1.length);
        random = new int[combined.length];

        rand = new Random(seed);
    }

    double calcuateAverage(int N) {
        double avg = 0;

        for (int i = 0; i < N; i++) {
            int index = rand.nextInt(combined.length);
            int value = combined[index];
            random[i] = value;
            avg += value;
        }

        System.out.println();
        return avg / N;
    }
    void  printArray(int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(random[i] + ", ");
        }
    }
}

NumberGenerator createNewNumberGenerator(long seed) {

    return new NumberGenerator(seed);
}

void main() {
    clrscr();

    NumberGenerator G = createNewNumberGenerator(1);
    double avg = G.calcuateAverage(30);
    System.out.println("average: " + avg);
    G.printArray(30);
    println();


    NumberGenerator G2 = createNewNumberGenerator(2);
    double avg2 = G2.calcuateAverage(30);
    System.out.println("average2: " + avg2);
    G2.printArray(30);
    println();

    NumberGenerator G3 = createNewNumberGenerator(1);
    double avg3 = G3.calcuateAverage(30);
    System.out.println("average3: " + avg3);
    G3.printArray(30);

}