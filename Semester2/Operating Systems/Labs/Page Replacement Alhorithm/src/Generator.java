import java.util.*;
public class Generator {

    public static List<Integer> generateWithClusters(
            int length,
            int totalPages,
            int clusterSize,
            double jumpProbability
    ) {
        Random rand = new Random();
        List<Integer> refs = new ArrayList<>();

        List<Integer> cluster = generateCluster(totalPages, clusterSize, rand);

        for (int i = 0; i < length; i++) {
            if (rand.nextDouble() < jumpProbability) {
                cluster = generateCluster(totalPages, clusterSize, rand);
            }

            int page = cluster.get(rand.nextInt(clusterSize));
            refs.add(page);
        }

        return refs;
        }
    private static List<Integer> generateCluster(int totalPages, int size, Random rand){
        Set<Integer> clusters = new HashSet<>();

        while (clusters.size() < size){
            clusters.add(rand.nextInt(totalPages));
        }

        return new ArrayList<>(clusters);
    }
}
