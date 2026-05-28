import java.util.Iterator;

public static class Task_2 implements Iterable<Integer> {
    private int start;
    public Task_2(int start) {
        this.start = start;
    }
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int current = start;
            @Override
            public boolean hasNext() {
                return true;
            }
            @Override
            public Integer next() {
                return current++;
            }
        };
    }
}

public static void  main(String[] args) {
    Task_2 sequence = new Task_2(5);

    Iterator<Integer> iterator = sequence.iterator();

    for (int i = 0; i < 5; i++) {
        System.out.print(iterator.next() + " ");
    }
}
