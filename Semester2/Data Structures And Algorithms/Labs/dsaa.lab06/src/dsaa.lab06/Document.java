package dsaa.lab06;

import java.util.ListIterator;
import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }
    public void load(Scanner scan) {
        String pattern = "link=";
        int charsInPattern = pattern.length();
        while (true) {
            String line = scan.nextLine();

            if (line.equals("eod")) {
                break;
            }

            String[] words = line.split(" ");
            for (String word : words) {

                word = word.toLowerCase();

                if (word.startsWith(pattern)) {

                    String candidate = word.substring(charsInPattern);

                    if (isCorrectId(candidate)) {
                        if(candidate.contains("(")){
                            int start  = candidate.indexOf("(");
                            int end   = candidate.indexOf(")");

                            String ref = candidate.substring(0, start);
                            int weight = Integer.parseInt(candidate.substring(start + 1, end));
                            link.add(new Link(ref, weight));
                        } else {
                            link.add(new Link(candidate));
                        }
                    }
                }
            }
        }
    }

    public static boolean isCorrectId(String id) {
        return id.matches("^[A-Za-z][A-Za-z0-9_]*(\\(\\d+\\))?$");
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    static Link createLink(String link) {
        if(isCorrectId(link)){
            if(link.contains("(")){
                int start  = link.indexOf("(");
                int end   =  link.indexOf(")");

                String ref = link.substring(0, start);
                int weight = Integer.parseInt(link.substring(start + 1, end));
                return new Link(ref, weight);
            } else {
                return new Link(link);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String retStr="Document: "+name;
        if (link.size == 0){
            return retStr;
        }
        for (Link l:link) {
            retStr += "\n";
            retStr += l.toString();
        }
        return retStr;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        if (link.size == 0){
            return retStr;
        }
        ListIterator<Link> iter=link.listIterator();
        retStr += "\n";
        while(iter.hasNext())
            iter.next();

        iter.previous();
        Link li=iter.next();
        retStr += li.toString();
        while(iter.hasPrevious()){
            retStr += "\n";
            Link l=iter.previous();
            retStr+=l.toString();

        }
        return retStr;
    }

    public int[] getWeights() {
        int[] weights = new int[link.size()];
        for (int i = 0; i < link.size(); i++) {
            weights[i] = link.get(i).weight;
        }
        return weights;
    }

    public static void showArray(int[] arr) {
        for (int i = 0 ; i < arr.length - 1; i++) {
            int weight = arr[i];
            System.out.print(weight+" ");
        }
        System.out.println(arr[arr.length-1]);
    }

    void bubbleSort(int[] arr) {
        showArray(arr);
        for (int i = 0 ; i < arr.length - 1; i++) {

            for (int j = arr.length - 1; j >= 1 + i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }

            }

            showArray(arr);
        }
    }

    public void insertSort(int[] arr) {
        showArray(arr);
        for (int i = arr.length - 2; i >= 0; i--) {
            int currentInsert = arr[i];
            int j = i + 1;
            for (; arr.length > j; j++) {
                if (currentInsert > arr[j]) {
                    arr[j-1] = arr[j];
                } else{
                    break;
                }
            }
            arr[j-1] =  currentInsert;

            showArray(arr);
        }

    }

    public void selectSort(int[] arr) {
        showArray(arr);
        for (int i = 0 ; i < arr.length - 1; i++) {
            int maxIndex = 0;

            for (int j = 1; j <= arr.length - 1 - i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[maxIndex];
            arr[maxIndex] = temp;

            showArray(arr);
        }
    }

	public void iterativeMergeSort(int[] arr) {
        showArray(arr);
        int[] temp = new int[arr.length];
        if  (arr.length < 2) {
            return;
        }

        int n = arr.length;

        for (int currSize = 1; currSize < n; currSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);

                merge(arr, temp,leftStart, mid, rightEnd);
            }
            showArray(arr);
        }
	}

    private static void merge(int[] arr,int[]temp, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        for(int i = 0; i < n1; i++){
            temp[i] = arr[left+i];
        }
        for(int j = 0; j < n2; j++){
            temp[mid+1+j] = arr[mid+1+j];
        }

        int i = 0, j = 0, k = left;

        while  (i < n1 && j < n2) {
            if (temp[i] <= temp[mid+1+j]) {
                arr[k++] = temp[i++];
            }else{
                arr[k++] = temp[mid+1+j++];
            }
        }

        while(i < n1) {
            arr[k++] = temp[i++];
        }
        while(j < n2) {
            arr[k++] = temp[j++ + mid+ 1];
        }
    }


	public void radixSort(int[] arr) {
        showArray(arr);
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
        }

        if (max > 999){
            return;
        }

        for (int exp = 1; exp <= 100; exp *= 10){
            countingSort(arr,exp);
            showArray(arr);
        }
	}

    public static void countingSort(int arr[], int exp) {
        int n=arr.length;

        int[] pos= new int[10];
        int[] result=new int[n];
        int i,j;
        for(i=0;i<10;i++){
            pos[i] = 0;
        }
        for(j=0;j<n;j++) {
            pos[(arr[j] / exp) % 10]++;
        }

        for(i=1;i<10;i++) {
            pos[i] += pos[i - 1];
        }
        for(j=n-1;j>=0;j--) {
            int digit=(arr[j] / exp) % 10;
            result[pos[digit] - 1]=arr[j];
            pos[digit]--;
        }
        for(j=0;j<n;j++) {
            arr[j] = result[j];
        }
    }


}

/*
go 10
ld doc1
link=c(10) and link=b(7)
write also link=z end finish link=a(20)
eod
add f(8)
add g(4)
add e(3)
show
mergesort
radixsort


*/