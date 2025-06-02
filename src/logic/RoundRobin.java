package logic;

import interfaces.Scheduler;
import model.Process;
import service.MemoryManager;
import service.Metrics;

import java.util.*;

public class RoundRobin implements Scheduler {

    private final int quantum;

    public RoundRobin(int quantum){
        this.quantum = quantum;
    }

    @Override
    public void schedule(List<Process> processes) {
        MemoryManager mem = new MemoryManager();

        Queue<Process> queue = new LinkedList<>();
        int currentTime = 0;
        Map<Integer, Integer> remainingBurst = new HashMap<>();
        Map<Integer, Integer> lastSeen = new HashMap<>();

        for (Process p : processes) {
            int block = mem.allocateMemory(p.memoryRequired);
            if (block == -1) {
                System.out.println("Memory allocation failed for process" + p.pid);
                continue;
            }
            remainingBurst.put(p.pid, p.burstTime);
            mem.deallocateMemory(block, p.memoryRequired);
        }
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        queue.addAll(processes);

        while (!queue.isEmpty()){
            Process p = queue.poll();
            int remaining = remainingBurst.get(p.pid);
            int execTime = Math.min(quantum, remaining);

            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            currentTime += execTime;
            remaining -= execTime;

            if (!lastSeen.containsKey(p.pid)) {
                p.waitingTime = currentTime - p.arrivalTime - p.burstTime + remaining;
            }
            if (remaining > 0) {
                remainingBurst.put(p.pid, remaining);
                p.arrivalTime = currentTime;
                queue.add(p);
            } else {
                p.completionTime = currentTime;
            }
            lastSeen.put(p.pid, currentTime);
        }
        Metrics m = new Metrics();
        m.calculateMetrics(processes);
    }
}
