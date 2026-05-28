package Algorithms;


public class FCFS {

    public static int calculate(int[] request, int headStart) {
        int totalSeek = 0;
        int current = headStart;
        for (int req : request) {
            totalSeek += Math.abs(current - req);
            current = req;
        }
        return totalSeek;
    }
}
