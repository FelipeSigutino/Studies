package Algorithms;

import java.util.*;

public class OPT {
    public static int simulate(List<Integer> references, int frameCount) {
        List<Integer> frames = new ArrayList<>();

        int pageFaults = 0;

        for (int i = 0; i < references.size(); i++) {
            int page =  references.get(i);

            if (frames.contains(page)) {
                continue;
            }
            pageFaults++;
            if (frames.size() < frameCount) {
                frames.add(page);
                continue;
            }

            int indexToRemove = -1;
            int farthestUse = -1;
            for(int j = 0; j < frames.size(); j++) {
                int currentPage = frames.get(j);
                int nextUse = Integer.MAX_VALUE;
                for (int k = i + 1; k < references.size(); k++) {
                    if (references.get(k) == currentPage) {
                        nextUse = k;
                        break;
                    }
                }
                if (nextUse > farthestUse) {
                    farthestUse = nextUse;
                    indexToRemove = j;
                }
            }
            frames.remove(indexToRemove);
            frames.add(page);
        }
        return pageFaults;
    }
}
