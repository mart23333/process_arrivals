package logic;

import interfaces.Scheduler;
import model.Process;
import service.MemoryManager;
import service.Metrics;

import java.util.Comparator;
import java.util.List;

public class FCFS implements Scheduler {

    @Override
    public void schedule(List<Process> processes) {
        MemoryManager mem = new MemoryManager();

        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;

        for (Process p : processes){
            var block = mem.allocateMemory(p.memoryRequired);
            if ( block == -1 ){
                System.out.println("Memory allocation failed for Process " + p.pid);
                continue;
            }

            if (currentTime < p.arrivalTime)
                currentTime = p.arrivalTime;

            p.waitingTime = currentTime - p.arrivalTime;
            p.completionTime = currentTime + p.burstTime;
            currentTime += p.burstTime;

            System.out.println("Process " + p.pid + " Wait Time: " + p.waitingTime);

            mem.deallocateMemory(block, p.memoryRequired);
        }
        Metrics m = new Metrics();
        m.calculateMetrics(processes);
    }
}
