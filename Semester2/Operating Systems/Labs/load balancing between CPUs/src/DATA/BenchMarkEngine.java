package DATA;

import Strategies.*;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;

public class BenchMarkEngine {

    private final int N = 80;
    private final double p = 0.7;
    private final int z = 3;

    private final int Process_Count = 80000;

    public void run() {


        List<Process> processes = generateProcesses(Process_Count);

        SimulationResoults r1 = runStrategy(new Strategy_1(p,z), copyProcesses(processes));
        SimulationResoults r2 = runStrategy(new Strategy_2(p), copyProcesses(processes));
        SimulationResoults r3 = runStrategy(new Strategy_3(p,0.6), copyProcesses(processes));

        printTable(r1,r2,r3);
    }

    private SimulationResoults runStrategy(Strategy strategy, List<Process> processes) {
        SimulationControler sim = new SimulationControler(N,p,z, processes, strategy);
        sim.run();
        return sim.getSimulationResoults();
    }

    private List<Process> generateProcesses(int processCount) {
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < processCount; i++) {

            double workLoad = 0.10 + Math.random() * 0.1;
            int time = 5 + (int) (Math.random() * 20);
            int appear = (int)  (Math.random() *2500);

            processes.add(new Process(workLoad, time, appear));
        }

        return processes;
    }

    private void printTable(
            SimulationResoults r1,
            SimulationResoults r2,
            SimulationResoults r3
    ) {
        System.out.println("\n===== FINAL COMPARISON =====\n");

        System.out.printf("%-15s %-10s %-10s %-10s %-10s\n",
                "Strategy",
                "AvgLoad",
                "Deviation",
                "Queries",
                "Migrations");

        printRow("Strategy 1", r1);
        printRow("Strategy 2", r2);
        printRow("Strategy 3", r3);
    }

    private void printRow(
            String name,
            SimulationResoults r
    ) {
        if (name.equals("Strategy_2")) {

        }
        System.out.printf("%-15s %-10.3f %-10.3f %-10d %-10d\n",
                name,
                r.avgLoad,
                r.deviation,
                r.queries,
                r.migrations);
    }

    private List<Process> copyProcesses(List<Process> original) {

        List<Process> copy = new ArrayList<>();

        for (Process p : original) {

            copy.add(
                    new Process(
                            p.getWorkload(),
                            p.getTimeToProcess(),
                            p.getAppearTime()
                    )
            );
        }

        return copy;
    }
}
