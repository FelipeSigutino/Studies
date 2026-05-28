package dsaa.lab08;

import java.util.LinkedList;

public class HashTable{
	LinkedList arr[]; 
	private final static int defaultInitSize=8;
	private final static double defaultMaxLoadFactor=0.7;
	private int size;	
	private final double maxLoadFactor;
	public HashTable() {
		this(defaultInitSize);
	}
	public HashTable(int size) {
		this(size,defaultMaxLoadFactor);
	}


	public HashTable(int initCapacity, double maxLF) {
		
		if(initCapacity<2)
			initCapacity=2;
		arr=new LinkedList[initCapacity];
		size = initCapacity;
		maxLoadFactor=maxLF;
	}

    private int hash(Object object) {
        IWithName x =(IWithName) object;
        String key = x.getName();

        int[] seq = {7, 11, 13, 17, 19};
        int seqIndex = 0;

        int result = (int) key.charAt(0);

        for(int i = 1; i < key.length(); i++) {
            result = (result * seq[seqIndex++] + key.charAt(i)) %Document.MODVALUE;
            seqIndex = seqIndex % (seq.length);
        }

        return Math.abs(result) % arr.length;
    }

    public boolean add(Object elem) {
        if(elem==null) {
            return false;
        }

        double loadFactor = (double) (size +1)/ arr.length;

        if (loadFactor >=  maxLoadFactor) {
            doubleArray();
        }

        int index = hash(elem);

        if(arr[index]==null) {
            arr[index]=new LinkedList();
        }
        if(arr[index].contains(elem)) {
            return false;
        }

        arr[index].add(elem);
        size++;

        return true;
    }


    private void doubleArray() {
        LinkedList[] temp = arr;
        arr=new LinkedList[temp.length*2];
        size = 0;

        for (LinkedList list : temp) {
            if(list!=null) {
                for(Object o : list) {
                    add(o);
                }
            }
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i< arr.length; i++) {
            sb.append(i + ":");

            if (arr[i] != null && !arr[i].isEmpty()) {
                boolean first=true;

                for(Object o : arr[i]) {
                    IWithName x = (IWithName) o;
                    if(!first) {
                        sb.append(",");
                    }

                    sb.append(" " +x.getName());
                    first=false;
                }
            }
            sb.append("\n");

        }
        return sb.toString();
    }

    public Object get(Object toFind) {
        if(toFind==null) {
            return null;
        }
        int index = hash(toFind);

        if(arr[index]==null) {
            return null;
        }

        for(Object o : arr[index]) {
            if(o.equals(toFind)) {
                return o;
            }
        }

        return null;
    }
}

