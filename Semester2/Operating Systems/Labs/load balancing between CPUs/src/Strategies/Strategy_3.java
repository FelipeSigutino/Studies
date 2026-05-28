package Strategies;


import DATA.*;
import DATA.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Strategy_3 implements Strategy {

    private double p;
    private double rask;

    private final Random rand = new Random();

    private int queries;
    private int migrations;

    public Strategy_3(double p, double rask) {
        this.p = p;
        this.rask = rask;
    }
    @Override
    public void assignProcess(Process process, Processor home, ArrayList<Processor> processors) {
        Processor target = getMostLoaded(processors);

        if (home.getLoad() < rask && target.getLoad() > p) {
            queries += processors.size()/ processors.size()* 3;

            Process stolen = target.removeHeaviesProcess();

            if (stolen != null) {
                home.addProcess(stolen);
                migrations++;
            }

            home.addProcess(process);
            return;
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

    public Processor getMostLoaded(List<Processor> processors) {
        Processor mostLoaded = null;
        double maxLoad = -1;
        for (Processor processor : processors) {

            if (processor.getLoad() > maxLoad) {
                maxLoad = processor.getLoad();
                mostLoaded = processor;
            }
        }
        return mostLoaded;
    }
}
