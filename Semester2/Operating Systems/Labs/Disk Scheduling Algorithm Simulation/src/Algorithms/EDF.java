package Algorithms;

import java.util.Arrays;

public class EDF {

    public static int calculate(int[] request,int[] deadline,int headStart ){

        Arrays.sort(deadline);
        int currentTime = 0;
        for(int i = 0;i<deadline.length;i++){
            if(deadline[i]> currentTime){
                currentTime += Math.abs(headStart - request[i]);
                headStart = request[i];
                System.out.println("Request " + i + " Executed at: " + currentTime);

            } else if (deadline[i]<currentTime){
                System.out.println("Deadline " + i + " Missed");
            }
        }



        return currentTime;

    }
}
