package Strategies;
import DATA.*;
import DATA.Process;

import java.util.ArrayList;

public interface Strategy {

    void assignProcess(Process process, Processor home, ArrayList<Processor> processors);

    int getQueries();

    int getMigrations();
}
