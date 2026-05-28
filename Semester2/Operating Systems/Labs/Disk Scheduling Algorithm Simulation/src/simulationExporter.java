import Algorithms.*;
import SimulationClass.Simulation;
import SimulationClass.SimulationGenerator;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


public class simulationExporter {
    public static void main(String[] args)  {
        int requestCount = 15;
        int simulationCount = 50;
        List<Simulation> simulations = SimulationGenerator.generate(simulationCount,requestCount);

        try(FileWriter writer = new FileWriter("Simulation_Results.csv")){
            writer.append("Simulation,");
            writer.append("HeadStart");

            for(int i = 0; i < requestCount; i++){
                writer.append(",Value" + (i  +1));
            }
            writer.append(",FCFS_SeekTime,");
            writer.append(",SSTF_SeekTime,");
            writer.append(",LOOK_SeekTime,");
            writer.append(",SCAN_SeekTime,");
            writer.append(",CSCAN_SeekTime\n");

            int simulationIndex = 1;
            int totalfcfsSeekTime = 0;
            int totalSSTFSSeekTime = 0;
            int totalLOOKSeekTime = 0;
            int totalSCANSeekTime = 0;
            int totalCSCANSeekTime = 0;
            for(Simulation s : simulations){

                int[] request = s.getRequest();
                int headStart = s.getHeadStart();

                int fcfsSeekTime = FCFS.calculate(request,headStart);
                totalfcfsSeekTime += fcfsSeekTime;
                int SSTFSeekTime = SSTF.calculate(request,headStart);
                totalSSTFSSeekTime += SSTFSeekTime;
                int LOOKSeekTime = LOOK.calculate(request,headStart);
                totalLOOKSeekTime += LOOKSeekTime;
                int SCANSeekTime = SCAN.calculate(request,headStart);
                totalSCANSeekTime += SCANSeekTime;
                int CSCANSeekTime = CSCAN.calculate(request,headStart);
                totalCSCANSeekTime += CSCANSeekTime;
                writer.append(simulationIndex + "," + headStart);
                for(int r: request){
                    writer.append("," + r);
                }
                writer.append("," + fcfsSeekTime + ",");
                writer.append("," + SSTFSeekTime + ",");
                writer.append("," + LOOKSeekTime + ",");
                writer.append("," + SCANSeekTime + ",");
                writer.append("," + CSCANSeekTime + "\n");
                simulationIndex++;
            }

            double averageFCFSSeekTime = (double) totalfcfsSeekTime / simulationCount;
            double averageSSTFSeekTime = (double) totalSSTFSSeekTime / simulationCount;
            double averageLOOKSeekTime = (double) totalLOOKSeekTime / simulationCount;
            double averageSCANSeekTime = (double) totalSCANSeekTime / simulationCount;
            double averageCSCANSeekTime = (double) totalCSCANSeekTime / simulationCount;

            writer.append("AverageSeekTime,");
            for (int i = 0; i < requestCount ; i++) {
                writer.append(",");
            }

            writer.append("," + averageFCFSSeekTime + ",");
            writer.append("," + averageSSTFSeekTime + ",");
            writer.append("," + averageLOOKSeekTime + ",");
            writer.append("," + averageSCANSeekTime + ",");
            writer.append("," + averageCSCANSeekTime + "\n");
            System.out.println("File 'Simulation_Results.csv' successfully created");

        } catch (IOException e) {
            e.printStackTrace();
        }
        EDF.calculate(simulations.getFirst().getRequest(), simulations.getFirst().getDeadline(), simulations.getFirst().getHeadStart());
    }
}