package Algorithms;
import java.util.Arrays;
public class LOOK {

    public static int calculate(int[] request, int arrowStart) {
        int seekTime = 0;
        Arrays.sort(request);
        int current = arrowStart;

        int index = 0;
        while (index < request.length && request[index] < arrowStart) {
            index++;
        }

        for( int i = index - 1; i >= 0; i--){
            seekTime += Math.abs(request[i] - current);
            current = request[i];
        }

        for( int i = index; i < request.length; i++){
            seekTime += Math.abs(request[i] - current);
            current = request[i];
        }

        return seekTime;
    }
}
