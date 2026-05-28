package Algorithms;

import java.util.*;

public class LRU {
    public static int simulate(List<Integer> references, int frameCount) {
    List<Integer> frames = new ArrayList<>();

    int pageFaults = 0;

        for(int page : references) {
        if(!frames.contains(page)) {
            pageFaults++;

            if (frames.size() == frameCount) {
                frames.remove(0);
            }

        } else {
            frames.remove((Integer) page);
        }
        frames.add(page);
    }
        return pageFaults;
}
}
