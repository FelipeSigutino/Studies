import java.util.Random;

record Process(int arrivalTime, int burstTime) {}

Process generateProcess(){
    Random rand = new Random();
    int at = rand.nextInt(10);
    int bt = rand.nextInt(10)+1;
    return new Process(at, bt);
}

record ProcessResult(int waitTime){}

Process[] generateProcesses(int n){
    Process[] processes = new Process[n];
    for(int i = 0; i < n; i++){
        processes[i] = generateProcess();
    }
    return processes;
}

ProcessResult[] FCFS(Process[] processes){
    Arrays.sort(processes, Comparator.comparing(Process::arrivalTime));
    ProcessResult[] result = new ProcessResult[processes.length];
    int currentTime = 0;

    for(int i=0; i < processes.length; i++){

        int arrivalTime = processes[i].arrivalTime;
        int burstTime = processes[i].burstTime;

        if (arrivalTime > currentTime){
            currentTime = arrivalTime;
        }

        int completionTime = currentTime + burstTime;
        int turnaroundTime = completionTime - arrivalTime;
        int waitingTime = turnaroundTime - burstTime;
        currentTime = completionTime;

        result[i] = new ProcessResult(waitingTime);
    }
    return result;
}

ProcessResult[] SJFNonPreemptive(Process[] processes){
    int n = processes.length;
    ProcessResult[] result = new ProcessResult[n];

    boolean[] completed = new boolean[n];

    int currentTime = 0;
    int finished = 0;

    while(finished < n){
        int shortestIndex = -1;
        int shortestBurst = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            int at = processes[i].arrivalTime;
            int bt = processes[i].burstTime;

            if (!completed[i] && at <= currentTime && bt < shortestBurst){
                shortestIndex = i;
                shortestBurst = bt;
            }
        }
        if(shortestIndex == -1){
            currentTime++;
            continue;
        }

        int arrivalTime = processes[shortestIndex].arrivalTime;
        int burstTime = processes[shortestIndex].burstTime;

        int completionTime = currentTime + burstTime;
        int turnaroundTime = completionTime - arrivalTime;
        int waitingTime = turnaroundTime - burstTime;

        currentTime = completionTime;
        result[shortestIndex] = new ProcessResult(waitingTime);
        completed[shortestIndex] = true;

        finished++;
    }
    return result;
}

ProcessResult[] SJFPreemptive(Process[] processes){
    int n = processes.length;

    ProcessResult[] result = new ProcessResult[n];

    int[] remainingTime = new int[n];
    int[] waitingTime = new int[n];

    ProcessResult[] results = new ProcessResult[n];

    for (int i = 0; i < n; i++){
        remainingTime[i] = processes[i].burstTime;
    }

    int currentTime = 0;
    int completed = 0;
    while(completed < n){

        int shortestIndex = -1;
        int shortestRemaining = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            int at = processes[i].arrivalTime;
            if (at <= currentTime && remainingTime[i] > 0 && remainingTime[i] < shortestRemaining){
                shortestRemaining = remainingTime[i];
                shortestIndex = i;
            }
        }

        if(shortestIndex == -1){
            currentTime++;
            continue;
        }

        remainingTime[shortestIndex] --;
        currentTime++;

        if(remainingTime[shortestIndex] == 0){
            completed++;

            int completionTime = currentTime;
            int turnaroundTime = completionTime - processes[shortestIndex].arrivalTime();


            waitingTime[shortestIndex] = turnaroundTime - processes[shortestIndex].burstTime();
            results[shortestIndex] = new ProcessResult(waitingTime[shortestIndex]);
        }
    }
    return results;
}

ProcessResult[] RR(Process[] processes) {
    int n = processes.length;
    ProcessResult[] result = new ProcessResult[n];
    int[] remainingTime = new int[n];
    int quantum = 10;

    for (int i = 0; i < n; i++){
        remainingTime[i] = processes[i].burstTime;
    }
    Queue<Integer> queue = new LinkedList<>();

    boolean[] added = new boolean[n];
    int currentTime = 0;
    int completed = 0;

    while(n > completed){
        for(int i = 0; i < n; i++) {
            if (!added[i] && processes[i].arrivalTime <= currentTime){
                queue.add(i);
                added[i] = true;
            }
        }
        if(queue.isEmpty()){
            currentTime++;
            continue;
        }

        int index =  queue.poll();

        int executionTime = Math.min(quantum, remainingTime[index]);

        remainingTime[index] -= executionTime;
        currentTime += executionTime;

        for(int i = 0; i < n; i++){
            if (!added[i] && processes[i].arrivalTime <= currentTime){
                queue.add(i);
                added[i] = true;
            }
        }
        if(remainingTime[index] > 0){
            queue.add(index);
        } else {
            completed++;

            int completionTime = currentTime;
            int turnaroundTime = completionTime - processes[index].arrivalTime();
            int waitingTime = turnaroundTime - processes[index].burstTime();

            result[index] = new ProcessResult(waitingTime);
        }
    }
    return result;
}

double calculateAverageWaitingTime(ProcessResult[] results) {
    int totalWaitingTime = 0;
    for(ProcessResult result : results){
        totalWaitingTime += result.waitTime;
    }
    return (double) totalWaitingTime / results.length;
}

double performSimulationFCFS(Process[] processes){
    ProcessResult[] resultFCFS = FCFS(processes);
    return calculateAverageWaitingTime(resultFCFS);
}

double performSimulationSJFNonPreemptive(Process[] processes){
    ProcessResult[] resultsSjFNonPreemptive = SJFNonPreemptive(processes);
    return calculateAverageWaitingTime(resultsSjFNonPreemptive);
}

double performSimulationSJFPreemptive(Process[] processes){
    ProcessResult[] resultSJFPreemptive = SJFPreemptive(processes);
    return calculateAverageWaitingTime(resultSJFPreemptive);
}

double performSimulationRR(Process[] processes){
    ProcessResult[] resultRR = RR(processes);
    return calculateAverageWaitingTime(resultRR);
}

int repetitions = 50;
Results Results = new Results();
class Results {
    double[] FCFS = new double[repetitions];
    double[] SJFPreemptive = new double[repetitions];
    double[] SJFNonPreemptive = new double[repetitions];
    double[] RR = new double[repetitions];
}

void printResults(){
    System.out.print("FCFS:             ");
    for(int i = 0; i < repetitions; i++){
        System.out.print(Results.FCFS[i] + ", ");
        if (i == repetitions-1){
            double avgWaitTime = 0;
            for(int j = 0; j < Results.FCFS.length; j++){
                avgWaitTime += Results.FCFS[j];
            }
            avgWaitTime /= repetitions;
            System.out.println("    Average Waiting Time(all simulations): " + avgWaitTime);
        }
    }

    System.out.print("SJFNonPreemptive: ");
    for(int i = 0; i < repetitions; i++){
        System.out.print(Results.SJFNonPreemptive[i] + ", ");
        if (i == repetitions-1){
            double avgWaitTime = 0;
            for(int j = 0; j < Results.FCFS.length; j++){
                avgWaitTime += Results.SJFNonPreemptive[j];
            }
            avgWaitTime /= repetitions;
            System.out.println("    Average Waiting Time(all simulations): " + avgWaitTime);
        }
    }

    System.out.print("SJFPreemptive: ");
    for(int i = 0; i < repetitions; i++){
        System.out.print(Results.SJFPreemptive[i] + ", ");
        if (i == repetitions-1){
            double avgWaitTime = 0;
            for(int j = 0; j < Results.FCFS.length; j++){
                avgWaitTime += Results.SJFPreemptive[j];
            }
            avgWaitTime /= repetitions;
            System.out.println("    Average Waiting Time(all simulations): " + avgWaitTime);
        }
    }

    System.out.print("RR:            ");
    for(int i = 0; i < repetitions; i++){
        System.out.print(Results.RR[i] + ", ");
        if (i == repetitions-1){
            double avgWaitTime = 0;
            for(int j = 0; j < Results.FCFS.length; j++){
                avgWaitTime += Results.RR[j];
            }
            avgWaitTime /= repetitions;
            System.out.println("    Average Waiting Time(all simulations): " + avgWaitTime);

        }
    }
}

void performSimulations(int repetitions){
    for(int i = 0; i < repetitions; i++) {
        Process[] processes = generateProcesses(20);
        Results.FCFS[i] = performSimulationFCFS(processes);
        Results.SJFNonPreemptive[i] = performSimulationSJFNonPreemptive(processes);
        Results.SJFPreemptive[i] = performSimulationSJFPreemptive(processes);
        Results.RR[i] = performSimulationRR(processes);
    }
}

void main() {
    performSimulations(repetitions);
    printResults();
}
