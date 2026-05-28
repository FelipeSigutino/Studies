package DATA;

public class Process {
    private static int nextId = 1;

    private final int id;
    double workload;
    int timeToProcess;
    int appearTime;
    boolean done = false;

    public Process(double workload, int timeToProcess,  int appearTime) {
        this.id = nextId++;
        this.workload = workload;
        this.timeToProcess = timeToProcess;
        this.appearTime = appearTime;

    }

    public double getWorkload() {
        return workload;
    }
    public int getTimeToProcess() {
        return timeToProcess;
    }
    public int getAppearTime() {
        return appearTime;
    }
    public boolean isDone() {
        return timeToProcess <= 0;
    }

    public void update() {
        if (!done) {
            timeToProcess -= 1;
            if (timeToProcess <= 0) {
                done = true;
            }
        }
    }

    @Override
    public String toString() {
        return String.format(
                "DATA.Process %d | load=%.0f%% | time=%d",
                id,
                workload * 100,
                timeToProcess
        );
    }
}
