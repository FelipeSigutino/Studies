package dsaa.lab05;

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

}
