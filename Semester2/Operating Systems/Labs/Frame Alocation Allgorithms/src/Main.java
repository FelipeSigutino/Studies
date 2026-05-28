import javax.swing.*;
import java.util.*;


int totalPages = 200;
int referenceLength = 1000;
double jumpProbability = 0.05; // 0.02
int frames = 50; // 8
int numOfProcesses = 20;
Map<Integer, MemoryState> memoryMap = new HashMap<>();
Random rand = new Random();
List<Process> generateProcesses() {
    List<Process> processes = new ArrayList<>();

    for (int i = 0; i < numOfProcesses; i++) {

        int clusterSize = rand.nextInt(10) + 1;
        int pages = rand.nextInt(30) + 10;

        GeneratedProcessData data =
                Generator.generateWithClustersWithInfo(
                        referenceLength,
                        totalPages,
                        clusterSize,
                        jumpProbability
                );

        Process p = new Process(i, data.references, data.cluster, pages);
        processes.add(p);

        System.out.println("Process " + i +
                " | clusterSize=" + data.cluster.size() +
                " | cluster=" + data.cluster

        );
    }

    return processes;
}

void resetProcesses(List<Process> processes) {
    for (Process p : processes) {
        p.currentIndex = 0;
        p.frames = 0;
    }
    memoryMap.clear();
}

int handleLRU(Process process, MemoryState state, int page){
    if(state.frames.contains(page)){
        state.lastUsed.put(page, process.currentIndex);
        return 0;
    }

    if(state.frames.size() < process.frames){
        state.frames.add(page);
    } else{
        int lruPage = Collections.min(
                state.frames,
                Comparator.comparingInt(pg -> state.lastUsed.getOrDefault(pg, 0))
        );
        state.frames.remove(Integer.valueOf(lruPage));
        state.frames.add(page);
    }

    state.lastUsed.put(page, process.currentIndex);
    return 1;
}

int equalAllocation(List<Process> processes) {
    int pageFaults =0;
    int framesPerProcess = frames / numOfProcesses;
    memoryMap.clear();
    for (Process process : processes) {
        memoryMap.put(process.id, new MemoryState());
    }
    for (Process process : processes) {
        process.frames = framesPerProcess;
    }

    boolean workRemaining = true;
    while (workRemaining) {
        workRemaining = false;
        for (Process process : processes) {
            if(process.hasNext()) {
                workRemaining = true;
                int page = process.nextPage();
                MemoryState state = memoryMap.get(process.id);
                pageFaults += handleLRU(process, state, page);
            }
        }
    }
    System.out.println("Equall allocation: " + pageFaults);
    return pageFaults;
}

int proportionalAllocation(List<Process> processes) {
    int pageFaults = 0;

    memoryMap.clear();
    for(Process process : processes){
        memoryMap.put(process.id, new MemoryState());
        process.currentIndex = 0;
    }

    int[] demand = new int[processes.size()];
    int totalDemand = 0;

    for (int i = 0; i < processes.size(); i++) {
        Process p = processes.get(i);

        demand[i] = new HashSet<>(p.generatedReferences).size();
        totalDemand += demand[i];
    }

    double[] rawFrames = new double[processes.size()];
    int totalAssigned = 0;

    for (int i = 0; i < processes.size(); i++) {
        rawFrames[i] = ((double) demand[i] / totalDemand) * frames;
    }

    for (int i = 0; i < processes.size(); i++) {
        processes.get(i).frames = (int) Math.floor(rawFrames[i]);
        totalAssigned += processes.get(i).frames;
    }

    while (totalAssigned < frames) {

        int best = 0;
        double bestFraction = -1;

        for (int i = 0; i < processes.size(); i++) {
            double fraction = rawFrames[i] - processes.get(i).frames;

            if (fraction > bestFraction) {
                bestFraction = fraction;
                best = i;
            }
        }

        processes.get(best).frames++;
        totalAssigned++;
    }

    for (Process p : processes) {
        if (p.frames < 1) p.frames = 1;
    }

    System.out.println("|| Proportional Allocation (frames per process) ||");

    for (int i = 0; i < processes.size(); i++) {
        Process p = processes.get(i);

        System.out.println(
                "Process " + p.id +
                        " | frames=" + p.frames +
                        " | demand=" + demand[i] +
                        " | clusterSize=" + p.generatedClusters.size()
        );
    }

    boolean workRemaining = true;

    while (workRemaining) {
        workRemaining = false;

        for (Process p : processes) {
            if (p.hasNext()) {
                workRemaining = true;

                int page = p.nextPage();
                MemoryState state = memoryMap.get(p.id);

                pageFaults += handleLRU(p, state, page);
            }
        }
    }

    System.out.println("Proportional allocation: " + pageFaults);
    return pageFaults;
}

Process findDonor(List<Process> processes, Process needy) {
    Process best = null;
    double lowestRate = Double.MAX_VALUE;
    for (Process process : processes) {
        if (process == needy || process.frames <= 1){
            continue;
        }

        double rate = (double) process.faultCounter / (process.referenceCounter + 1);
        if (rate < lowestRate) {
            lowestRate = rate;
            best = process;
        }
    }
    return best;
}

int pageFaultFrequency(List<Process> processes) {
    int pageFaults = 0;
    memoryMap.clear();

    for (Process process : processes) {
        memoryMap.put(process.id, new MemoryState());
        process.currentIndex = 0;
        process.frames = frames/ numOfProcesses;
        process.faultCounter = 0;
        process.referenceCounter = 0;
    }

    double HIGH = 0.05;
    double low = 0.005;
    int interval = 50;

    boolean workRemaining = true;
    while (workRemaining) {
        workRemaining = false;
        for (Process process : processes) {
            if (!process.hasNext()) {continue;}

            workRemaining = true;

            int page = process.nextPage();
            MemoryState state = memoryMap.get(process.id);

            int fault = handleLRU(process, state, page);
            pageFaults += fault;

            process.referenceCounter++;
            process.faultCounter += fault;

            if(process.referenceCounter % interval == 0){
                double rate =(double)  process.faultCounter / process.referenceCounter;
                if (rate > HIGH) {
                    Process donor = findDonor(processes, process);
                    if (donor != null && donor.frames >1) {
                        donor.frames--;
                        process.frames++;
                    }
                } else if (rate < low && process.frames > 1) {
                    process.frames--;
                }
            }
        }
    }

    System.out.println("PFF: " + pageFaults);
    return pageFaults;
}

int workingSetSize(Process process, int window) {
    Set<Integer> pages = new HashSet<>();

    int start = Math.max(0, process.currentIndex - window);
    int end = process.currentIndex;

    for (int i = start; i < end; i++) {
        pages.add(process.generatedReferences.get(i));
    }

    return pages.size();
}

int workingSetAllocation(List<Process> processes) {
    int pageFaults = 0;
    memoryMap.clear();

    for (Process process : processes) {
        memoryMap.put(process.id, new MemoryState());
        process.currentIndex = 0;
    }

    int window = 50;
    boolean workRemaining = true;

    while (workRemaining) {
        workRemaining = false;

        int[] ws = new int[processes.size()];
        int totalWS = 0;

        for (int i = 0; i < processes.size(); i++) {
            Process p = processes.get(i);

            if (p.hasNext()) {
                workRemaining = true;
            }

            ws[i] = workingSetSize(p, window);
            totalWS += ws[i];
        }

        for (int i = 0; i < processes.size(); i++) {
            Process p = processes.get(i);

            if (totalWS <= frames) {
                p.frames =Math.max(ws[i],1);
            } else{
                p.frames = (int) Math.max(1, (double) ws[i] / totalWS * frames);
            }
        }

        for (Process process : processes) {

            if(!process.hasNext()){continue;}

            int page = process.nextPage();
            MemoryState state = memoryMap.get(process.id);

            pageFaults += handleLRU(process, state, page);
        }
    }
    System.out.println("Working Set: " + pageFaults);
    return pageFaults;
}


void main() {
    List<Process> processes = generateProcesses();

    equalAllocation(processes);
    resetProcesses(processes);

    proportionalAllocation(processes);
    resetProcesses(processes);

    pageFaultFrequency(processes);
    resetProcesses(processes);

    workingSetAllocation(processes);
}
