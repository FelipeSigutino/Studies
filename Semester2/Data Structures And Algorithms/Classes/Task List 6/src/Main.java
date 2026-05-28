import Solutions.*;



public class Main{

    // == Input First Task ==
    //  int[] arr = {4};
    int[] arr = {4,8,2,7,5,9,1};
    // int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    int i = 2;
    //

    void solveFirstTask(int[] arr, int i){
        System.out.println("Serching for index = " + i);
        if (i > arr.length - 1 || i < 0) {
            System.out.println("Invalid Input");
            return;
        }
        SzybkiWyborWariacie.quickSelect(arr,0 , arr.length-1, i);
    }

    void main(String[] args) {
        solveFirstTask(arr,i);

    }
}

