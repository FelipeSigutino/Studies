package DATA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Processor {

    private static int nextId = 1;
    private final int id;
    private final List<Process> processes;

    public  Processor() {
        this.id = nextId++;
        this.processes = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public List<Process> getProcesses() {
        return processes;
    }
    public void addProcess(Process process) {
        processes.add(process);
    }

    public double getLoad(){
        double total = 0;
        for (Process process : processes) {
            total += process.getWorkload();
        }

        return total;
    }
    public void updateProcess() {
        Iterator<Process> iterator = processes.iterator();
        while (iterator.hasNext()) {
            Process process = iterator.next();
            process.update();
            if (process.isDone()) {
                iterator.remove();
            }
        }

    }
    @Override
    public String toString(){
        return String.format(
                "CPU %d | load = %.0f%% | active = %d",
                id,
                getLoad() *100,
                processes.size()
        );
    }
    public Process removeHeaviesProcess() {
        if (processes.isEmpty()) {
            return null;
        }

        Process heaviest = processes.get(0);
        for(Process process : processes) {
            if (process.getWorkload() > heaviest.getWorkload()) {
                heaviest = process;
            }
        }

        processes.remove(heaviest);

        return heaviest;
    }


}
