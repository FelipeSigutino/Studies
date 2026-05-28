
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;
import java.util.*;
import java.util.Random;
import java.util.stream.IntStream;



int[] array= java.util.stream.IntStream.rangeClosed(9,19).map(i -> i+1).toArray();
int[] array1= java.util.stream.IntStream.rangeClosed(49, 69).map(i -> i+1).toArray();
int random;
int index=0;
double average=0;
void main() {
    Random rand = new Random(42069);
    int N=30;
    int[] combarray= new int[array.length + array1.length];

    System.arraycopy(array, 0, combarray, 0, array.length);
    System.arraycopy(array1, 0, combarray, array.length, array1.length);

      for(int i=0;i<=N;i++) {
           index=rand.nextInt(combarray.length);
          IO.print(combarray[index]+",");
          average=average+combarray[index];
    }
      IO.println(" ");
      average=average/(N+1);
    IO.print("average: "+average);
}