package dsaa.lab09;

public class DisjointSetForest implements DisjointSetDataStructure {
	
	private class Element{
		int rank;
		int parent;
	}

	Element []arr;
	
	public DisjointSetForest(int size) {

        arr = new Element[size];

        for (int i = 0; i < size; i++){
            arr[i] = new Element();
        }
	}
	
	@Override
	public void makeSet(int item) {
		arr[item].parent = item;
        arr[item].rank = 0;
	}

	@Override
	public int findSet(int item) {

		if(arr[item].parent != item){
            arr[item].parent = findSet(arr[item].parent);
        }

        return arr[item].parent;
	}

	@Override
	public boolean union(int itemA, int itemB) {

        int parentA = findSet(itemA);
        int parentB = findSet(itemB);

        if (parentA == parentB) {
            return false;
        }

        if (arr[parentA].rank < arr[parentB].rank) {
            arr[parentA].parent = parentB;
        } else if (arr[parentA].rank > arr[parentB].rank){
            arr[parentB].parent = parentA;
        } else {
            arr[parentA].parent = parentB;
            arr[parentB].rank++;
        }

		return true;
	}

    @Override
    public boolean split(int n) {
        return false;
    }


    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

        sb.append("Disjoint sets as forest:\n");

        for (int i = 0; i < arr.length; i++) {
            sb.append(i).append(" -> ").append(arr[i].parent).append("\n");
        }

		return sb.toString().trim();
	}


}
