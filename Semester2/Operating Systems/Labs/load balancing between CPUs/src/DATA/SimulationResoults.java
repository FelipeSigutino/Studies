package DATA;

public class SimulationResoults {

    public double avgLoad;
    public double deviation;
    public int queries;
    public int migrations;

    public SimulationResoults(double avgLoad, double deviation, int queries, int migrations) {
        this.avgLoad = avgLoad;
        this.deviation = deviation;
        this.queries = queries;
        this.migrations = migrations;

    }

}
