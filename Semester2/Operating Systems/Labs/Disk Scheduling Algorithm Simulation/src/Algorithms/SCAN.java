package Algorithms;

import java.util.Arrays;

public class SCAN {
    public static int calculate(int[] requested, int headStart){
        int seekTime = 0;
        int current = headStart;

        Arrays.sort(requested);

        int diskmin = 0;


        int index = 0;
        while (index < requested.length && requested[index] < headStart){
            index++;
        }

        for (int i = index - 1; i > 0; i--){
            seekTime += Math.abs(requested[i] - current);
            current = requested[i];
        }
        seekTime += Math.abs(current - diskmin);
        current = diskmin;
        for (int i = index; i < requested.length; i++ ){
            seekTime += Math.abs(requested[i] - current);
            current = requested[i];
        }
        return seekTime;
    }
}
