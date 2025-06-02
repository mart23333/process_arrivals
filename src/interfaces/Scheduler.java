package interfaces;

import model.Process;

import java.util.List;

public interface Scheduler {
    void schedule(List<Process> processes);
}
