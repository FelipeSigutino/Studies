public class Pancake_Sort {
    public void showArray(int[] arr) {
        for (int i = 0 ; i < arr.length - 1; i++) {
            int weight = arr[i];
            System.out.print(weight+" ");
        }
        System.out.println(arr[arr.length-1]);
    }
    public int[] pancakeSort(int[] arr) {
        showArray(arr);
        int n = arr.length;
        int[] temp = new int[10 * n];


        for (int size = arr.length; size > 1; size--) {
            int maxID = 0;

            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxID]) {
                    maxID = i;
                }
            }

            //life good
            if (maxID == size - 1)  {
                continue;
            }

            if (maxID != 0)  {
                flip(arr, maxID + 1);
                showArray(arr);
            }

            flip(arr, size);
            showArray(arr);

        }

        return temp;

    }

    public void flip(int[] arr, int maxID) {
        int left = 0;
        int right = maxID - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
