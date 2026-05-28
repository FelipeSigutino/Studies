package Algorithms;

import java.util.Arrays;
public class CSCAN {

    public static int calculate(int[] requested,int headStart){
        int seekTime = 0;
        int current = headStart;
        int diskmin = 0;
        int diskmax = 100;

        int index = 0;
        while (index < requested.length && requested[index] < headStart){
            index++;
        }

        for (int i = index; i < requested.length; i++){
            seekTime += Math.abs(current-requested[i]);
            current = requested[i];
        }
        seekTime += Math.abs(current - diskmax);
        current = diskmax;

        seekTime += Math.abs(current - diskmin);
        current = diskmin;

        for (int i = 0; i < index; i++){
            seekTime += Math.abs(current-requested[i]);
            current = requested[i];
        }

        return seekTime;
    }
}
