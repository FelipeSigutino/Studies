package Algorithms;
import java.util.*;
public class RAND {
    public static int simulate(List<Integer> references, int frameCount) {

        List<Integer> frames = new ArrayList<>();
        Random rand = new Random();

        int pageFaults = 0;
        for (int page : references) {

            if (frames.contains(page)) {
                continue;
            }
            pageFaults++;

            if(frames.size() == frameCount) {
                int randomIndex = rand.nextInt(frameCount);
                frames.remove(randomIndex);
            }
            frames.add(page);
        }
        return pageFaults;
    }
}
