package Solutions;

import java.util.Arrays;
import java.util.Random;

public class Quicksort {
    private static Random random = new Random();

    public static void quickSort(int[] arr, int left, int right) {

        if (left < right) {
            return;
        }

        int pivotIndex = 1;
    }

    int choosePivot(int[] arr, int left, int right) {
        int size = right - left + 1;

        if (size > 100) {
            int a = random.nextInt(left,right +1);
            int b = random.nextInt(left,right +1);
            int c = random.nextInt(left,right +1);

            int va =arr[a];
            int vb =arr[b];
            int vc =arr[c];

            if ((va >= vb &&  va >= vc) || (va <= vb && va >= vc)) {
                return a;
            }
            if ((vb >= va &&  vb <= vc) || a >b ){
            }
        }
        return -1;
    }
}
