package Strategies;

import DATA.Process;
import DATA.Processor;

import java.util.ArrayList;
import java.util.Random;

public class Strategy_2 implements Strategy {

    private final double p;

    private final Random rand = new Random();

    private int queries = 0;
    private int migrations = 0;

    public Strategy_2(double p) {
        this.p = p;
    }

    @Override
    public void assignProcess(Process process, Processor home, ArrayList<Processor> processors) {


        int attempts = 0;
        int maxAttempts = processors.size() * 2;
        while (attempts < maxAttempts) {
            Processor Candidate = processors.get(rand.nextInt(processors.size()));

            queries++;
            attempts += 1;

            if (Candidate.getLoad() < p) {
                Candidate.addProcess(process);

                migrations++;
                return;
            }
        }
        home.addProcess(process);
    }

    @Override
    public int getQueries() {
        return queries;
    }

    @Override
    public int getMigrations() {
        return migrations;
    }
}
