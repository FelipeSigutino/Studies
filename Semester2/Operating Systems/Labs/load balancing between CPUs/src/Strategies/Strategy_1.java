package Strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DATA.Process;
import DATA.Processor;

public class Strategy_1 implements Strategy {

    private final double p;
    private final int z;
    private final Random rand = new Random();

    private int queries = 0;
    private int migrations = 0;

    public Strategy_1(double p, int z) {
        this.p = p;
        this.z = z;
    }

    @Override
    public void assignProcess(Process process, Processor home, ArrayList<Processor> processors) {

        for (int i = 0; i < z; i++) {

            Processor candidate = processors.get(rand.nextInt(processors.size()));

            queries++;

            if (candidate.getLoad() < p) {
                candidate.addProcess(process);
                migrations++;
                return;
            }
        }
        home.addProcess(process);
    }

    public int getQueries() {
        return queries;
    }
    public int getMigrations() {
        return migrations;
    }

}
