package SimulationClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public  class SimulationGenerator{

    public static List<Simulation> generate(int simulations, int requestcount){
        Random rand = new Random();
        List<Simulation> list = new ArrayList<Simulation>();

        for (int i=0;i<simulations;i++){

            int [] request = new int [requestcount];
            int [] deadline = new int [requestcount];
            for (int ii = 0; ii<requestcount;ii++){
                request[ii] = rand.nextInt(101);
                deadline[ii] = rand.nextInt(501);
            }


            int headStart = rand.nextInt(101);
            list.add(new Simulation(request,deadline,headStart));
        }
        return list;
    }
}
