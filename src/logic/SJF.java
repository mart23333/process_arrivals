package logic;

import interfaces.Scheduler;
import model.Process;
import service.MemoryManager;
import service.Metrics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SJF implements Scheduler {

    @Override
    public void schedule(List<model.Process> processes) {
        MemoryManager mem = new MemoryManager();

        List<model.Process> readyQueue = new ArrayList<>();
        int currentTime = 0;
        int completed = 0;

        while (completed < processes.size()) {
            for (model.Process p : processes) {
                int block = mem.allocateMemory(p.memoryRequired);
                if (block == -1) {
                    System.out.println("Memory allocation failed for process " + p.pid);
                    continue;
                }

                if (!readyQueue.contains(p) && p.arrivalTime <= currentTime) {
                    readyQueue.add(p);
                }
                mem.deallocateMemory(block, p.memoryRequired);
            }

            Process shortest = readyQueue.stream()
                    .min(Comparator.comparingInt(p -> p.burstTime))
                    .orElse(null);

            if (shortest != null) {
                if (currentTime < shortest.arrivalTime)
                    currentTime = shortest.arrivalTime;

                shortest.waitingTime = currentTime - shortest.arrivalTime;
                shortest.completionTime = currentTime + shortest.burstTime;
                currentTime += shortest.burstTime;

                System.out.println("Process " + shortest.pid + "Wait Time: " + shortest.waitingTime);
                readyQueue.remove(shortest);
                completed++;
            } else {
                currentTime++;
            }
        }

        Metrics m = new Metrics();
        m.calculateMetrics(processes);
    }
}
