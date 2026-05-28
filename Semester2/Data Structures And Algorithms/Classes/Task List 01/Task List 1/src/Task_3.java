import java.util.Iterator;

public static class Task_3 implements Iterable<Integer> {
    private int a = 1;
    private int b = 1;
    private int temp = 0;
    //1,1,2,3,5,8,13
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return true;
            }
            @Override
            public Integer next() {
                temp = b;
                b = a + b;
                a = temp;
                return b;

            }
        };
    }
}

public static void main(String[] args) {
    Task_3 sequence = new Task_3();

    Iterator<Integer> iterator = sequence.iterator();

    System.out.print("1 1 ");
    for (int i = 0; i <  10; i++) {
        System.out.print(iterator.next() + " ");
    }
}