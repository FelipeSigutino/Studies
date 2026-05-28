package SimulationClass;

public class Simulation {
    int[] request;
    int headStart;
    int[] deadline;

    public Simulation(int[] request, int[] deadline, int headStart){
        this.request = request;
        this.headStart = headStart;
        this.deadline = deadline;
    }
    public int[] getRequest() {
        return request;
    }

    public int getHeadStart() {
        return headStart;
    }
    public int[] getDeadline() {
        return deadline;
    }
}
