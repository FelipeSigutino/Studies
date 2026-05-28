package dsaa.lab09;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;
	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		arr = new Element[size];

        for (int i=0;i<size;i++) {
            arr[i] = new Element();
        }
	}
	
	@Override
	public void makeSet(int item) {
		arr[item].representant = item;
        arr[item].next = NULL;

        arr[item].length = 1;
        arr[item].last = item;
	}

	@Override
	public int findSet(int item) {
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {

		int repA = findSet(itemA);
        int repB = findSet(itemB);

        if (repA == repB) {
            return false;
        }

        if (arr[repA].length < arr[repB].length) {
            int temp = repA;
            repA = repB;
            repB = temp;
        }

        arr[arr[repA].last].next =repB;

        arr[repA].last = arr[repB].last;
        arr[repA].length += arr[repB].length;

        int current = repB;

        while (current != NULL) {
            arr[current].representant =  repA;

            current = arr[current].next;
        }

		return true;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

        sb.append("Disjoint sets as linked list:\n");

        boolean[] printedRep = new boolean[arr.length];

        for (int i=0;i<arr.length;i++) {
            int rep= arr[i].representant;

            if (printedRep[rep]) {
                continue;
            }

            printedRep[rep]=true;

            int current = rep;

            while (current != NULL) {
                sb.append(current);

                current = arr[current].next;

                if (current != NULL) {
                    sb.append(", ");
                }
            }

            sb.append("\n");
        }
		return sb.toString().trim();
	}

    public boolean split(int item) {

        if (item > arr.length || item < 0) {return false;}

        int repA = findSet(item);

        int sizeB = arr[repA].length/2;
        int sizeA = arr[repA].length - sizeB;

        int current = repA;
        for (int i=1;i<sizeA;i++) {
            System.out.println(current);
            arr[current].length = sizeA;
            current = arr[current].next;
        }

        int newRep = arr[current].next;
        arr[newRep].last = arr[repA].last;
        arr[repA].last = current;

        arr[current].next = NULL;
        arr[current].length = sizeB;

        current = newRep;
        for (int i=1;i<= sizeB ;i++) {
            System.out.println(current);
            arr[current].representant = newRep;
            current = arr[current].next;
        }


        return true;
    }
}
