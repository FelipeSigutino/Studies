package Solutions;
import java.util.Arrays;

public class SzybkiWyborWariacie {
    public static int quickSelect(int[] arr, int left, int right, int i) {

        System.out.println("Current array: " +  Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));




        if (left == right) {
            System.out.println("Only one element found -> ANSWER: " + arr[left]);
            return arr[left];
        }

        int pivotIndex = partition(arr, left, right);

        System.out.println("PivotIndex: " + pivotIndex);

        if (pivotIndex == i) {
            System.out.println("PivotIndex found -> ANSWER: " + arr[pivotIndex]);
            return arr[pivotIndex];
        }

        if(i < pivotIndex){
            System.out.println("Go Left (i < pivotIndex)");
            return quickSelect(arr, left, pivotIndex - 1, i);
        } else {
            System.out.println("Go Right (i > pivotIndex)");
            return quickSelect(arr, pivotIndex + 1, right, i);
        }


    }

    private static int partition(int[] arr, int left, int right) {


        int pivot =arr[right];

        System.out.println("Partition Part");
        System.out.println("Pivot Value: " + pivot);

        int i = left;

        for (int j = left; j < right; j++) {

            System.out.println("Checking arr[" + j + "] = " + arr[j]);

            if (arr[j] <= pivot) {

                System.out.println(arr[j] + " <= " + pivot + " Move to the Left side");

                System.out.println(
                        "Swapping arr[" + i +"] = " + arr[i] + " with arr[" + j +"] = " + arr[j]
                );

                swap(arr, i, j);

                System.out.println("Array after swapping: " + Arrays.toString(arr));
                i++;
            } else{
                System.out.println(arr[j] + " > " + pivot + " We do nothin");
            }
        }

        System.out.println("Swapping pivot with arr["+i+"]");

        swap(arr, i, right);

        System.out.println("Array after pivot swapping: " + Arrays.toString(arr));
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
