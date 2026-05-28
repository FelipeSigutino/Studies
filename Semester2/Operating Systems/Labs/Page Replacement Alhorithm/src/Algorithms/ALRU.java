package Algorithms;
import java.awt.print.Pageable;
import java.util.*;

public class ALRU {
    static class Frame{
        int page;
        boolean reference;
        Frame(int page){
            this.page = page;
            this.reference =  true;
        }
    }
        public static int simulate(List<Integer> references, int frameCount) {


            Queue<Frame> queue = new LinkedList<>();
            Map<Integer,Frame> lookup = new HashMap<>();

            int pageFaults = 0;

            for(int page : references) {
               if (lookup.containsKey(page)) {
                   lookup.get(page).reference = true;
                   continue;
               }
               pageFaults++;
               if (queue.size() < frameCount) {
                   Frame frame = new Frame(page);
                   queue.add(frame);
                   lookup.put(page, frame);
                   continue;
               }

               while(true){
                   Frame frame = queue.peek();
                   if (frame.reference) {
                       frame.reference = false;
                       queue.poll();
                       queue.add(frame);
                   }
                   else{
                       queue.poll();
                       lookup.remove(frame.page);
                       break;
                   }

               }

               Frame newFrame = new Frame(page);
               queue.add(newFrame);
               lookup.put(page, newFrame);
            }
            return pageFaults;
        }
    }

