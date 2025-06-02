package service;

import model.Process;

import java.util.List;

public class Metrics {
    public void calculateMetrics(List<Process> processes){
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double totalCPUUsage = 0;
        double totalMemoryUtilization = 0;

        for (Process p : processes){
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
            p.cpuUsage = p.turnaroundTime + p.waitingTime;
            p.memoryUtilization = p.cpuUsage - p.memoryRequired;

            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
            totalCPUUsage += p.cpuUsage;
            totalMemoryUtilization += p.memoryUtilization;

            System.out.println("Process " + p.pid +
                    " | Waiting Time: " + p.waitingTime +
                    " | Turnaround Time: " + p.turnaroundTime +
                    " | Cpu Usage: " + p.cpuUsage +
                    " | Memory Utilization: " + p.memoryUtilization
            );
        }
        double averageWaitingTime = totalWaitingTime / processes.size();
        double averageTurnaroundTime = totalTurnaroundTime / processes.size();
        double averageCpuUsage = totalCPUUsage / processes.size();
        double averageMemoryUtilization = totalMemoryUtilization / processes.size();

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("\nAverage Turnaround Time: " + averageTurnaroundTime);
        System.out.println("\nAverage Cpu Usage: " + averageCpuUsage);
        System.out.println("\nAverage Memory Utilization: " + averageMemoryUtilization);
    }
}
