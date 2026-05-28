import java.util.*;
import Algorithms.*;

int totalPages = 50;
int referenceLength = 10000;
int clusterSize = 15;
double jumpProbability = 0.01; // 0.02
int frames = 12; // 8

List<Integer> showGeneratedReferences(){


    List<Integer> references = Generator.generateWithClusters(
            referenceLength,
            totalPages,
            clusterSize,
            jumpProbability
    );

    for (Integer i : references) {
        System.out.print(i + ",");
    }
    System.out.println();
    return references;
}

void main() {
    List<Integer> references = showGeneratedReferences();

    int fifoscore = FIFO.simulate(references, frames);
    int LRUscore = LRU.simulate(references, frames);
    int ALRUscore = ALRU.simulate(references, frames);
    int RANDscore = RAND.simulate(references, frames);
    int OPTscore = OPT.simulate(references, frames);

    System.out.println("PageFaults:");
    System.out.println("FIFO: "+fifoscore);
    System.out.println("LRU: "+LRUscore);
    System.out.println("ALRU: "+ALRUscore);
    System.out.println("RAND: "+RANDscore);
    System.out.println("OPT: "+OPTscore);

}
