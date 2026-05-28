import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Process {
    int id;
    List<Integer> generatedReferences = new ArrayList<>();
    List<Integer> generatedClusters = new ArrayList<>();
    int frames;
    int pages;
    int currentIndex = 0;
    int faultCounter = 0;
    int referenceCounter = 0;

    public Process(int id, List<Integer> generatedReferences, List<Integer> cluster,int pages) {
        this.id = id;
        this.generatedReferences = generatedReferences;
        this.generatedClusters = cluster;
        this.pages = pages;
    }

    public boolean hasNext() {
        return currentIndex< generatedReferences.size();
    }

    public int nextPage() {
        return generatedReferences.get(currentIndex++);
    }

}

class MemoryState {
    List<Integer> frames = new ArrayList<>();
    Map<Integer, Integer> lastUsed = new HashMap<>();
}

class GeneratedProcessData {
    List<Integer> references;
    List<Integer> cluster;

    public GeneratedProcessData(List<Integer> references, List<Integer> cluster) {
        this.references = references;
        this.cluster = cluster;
    }
}