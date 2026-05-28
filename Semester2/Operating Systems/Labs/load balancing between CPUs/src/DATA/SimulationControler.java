package DATA;

import Strategies.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SimulationControler {
    private final int N;
    private final double p;
    private final int z;


    private final List<Processor> processors = new ArrayList<>();
    private final Strategy strategy;

    private List<Process> allProcesses = new ArrayList<>();

    private int currentTime = 0;

    private Random rand = new Random();

    private double sumAvg;
    private double sumDev;
    private int ticks;


    public SimulationControler(int N, double p, int z, List<Process> processes,  Strategy strategy)
    {
        this.N = N;
        this.p = p;
        this.z = z;
        this.allProcesses = processes;

        this.strategy = strategy;

        for (int i = 0; i < N; i++) {
            processors.add(new Processor());
        }

    }
    public void run() {
        List<Process> waiting = new ArrayList<>(allProcesses);

        while (!waiting.isEmpty() || processorsNotEmpty()) {
            currentTime += 1;

            for (int i = waiting.size() - 1; i >= 0; i--) {
                Process p = waiting.get(i);

                if (p.getAppearTime() <= currentTime) {
                    Processor home = processors.get(rand.nextInt(processors.size()));

                    strategy.assignProcess(p, home, (ArrayList<Processor>) processors);

                    waiting.remove(i);
                }

            }

            for (Processor proc : processors) {
                proc.updateProcess();

            }

            collectStats();

        }
        finishReport();
    }

    private boolean processorsNotEmpty() {
        for (Processor proc : processors) {
            if (!proc.getProcesses().isEmpty()) {return  true;}
        }
        return false;
    }

    private void collectStats() {
        double sum = 0;

        for (Processor proc : processors) {
            sum += proc.getLoad();
        }

        double avg = sum / processors.size();

        double variance = 0;

        for (Processor proc : processors) {
            double diff = proc.getLoad() - avg;
            variance += diff * diff;
        }

        variance /= processors.size();

        double deviation = Math.sqrt(variance);

        sumAvg += avg;
        sumDev += deviation;
        ticks++;
    }

    public SimulationResoults getSimulationResoults() {
        return new SimulationResoults(
                sumAvg/ticks,
                sumDev/ticks,
                strategy.getQueries(),
                strategy.getMigrations()
        );
    }

    private void finishReport() {


    }
}
