import SimulationClass.*;
import Algorithms.*;
import java.util.List;

public class Testing {
    public void main(String[] args){
        List<Simulation> simulations = SimulationGenerator.generate(10, 10);

        for (Simulation sim : simulations) {
            System.out.println("Head start: " + sim.getHeadStart());
            System.out.print("Requests: ");
            for (int r : sim.getRequest()) {
                System.out.print(r + " ");
            }
            System.out.println("\n---");
        }

    }
}
