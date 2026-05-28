package Algorithms;
import java.util.*;

public class FIFO {
    public static int simulate(List<Integer> references, int frameCount) {
        Set<Integer> frames = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        int pageFaults = 0;

        for(int page : references) {
            if(!frames.contains(page)) {
                pageFaults++;

                if (frames.size() == frameCount) {
                    int removed = queue.poll();
                    frames.remove(removed);
                }
                frames.add(page);
                queue.add(page);
            }
        }
        return pageFaults;
    }
}
