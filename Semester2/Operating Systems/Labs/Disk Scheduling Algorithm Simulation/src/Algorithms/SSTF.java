package Algorithms;
import SimulationClass.*;


public class SSTF {
    public static int calculate(int[] request, int arrowStart) {
        int seekTime = 0;
        int distance;
        int current = arrowStart;
        boolean[] visited = new boolean[request.length];


        for (int i = 0; i < request.length; i++) {
            int min = Integer.MAX_VALUE;
            int nextIndex = 0;
            for (int j = 0; j < request.length; j++) {
                if (!visited[j]) {
                    distance = Math.abs(current - request[j]);
                    if (distance < min) {
                        min = distance;
                        nextIndex = j;
                    }
                }
            }
            seekTime += min;
            current = request[nextIndex];
            visited[nextIndex] = true;
        }
        return seekTime;
    }

}
