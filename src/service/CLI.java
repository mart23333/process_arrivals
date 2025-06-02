package service;

import model.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private final Scanner scanner = new Scanner(System.in);

    public List<Process> getUserInput() {
        List<Process> processes = new ArrayList<>();
        System.out.print("Please Enter number of processes: ");
        var s = scanner.nextInt();

        for (int i = 0; i < s; i++){
            System.out.println("Process " + (i + 1));
            System.out.print("Arrival Time: ");
            int arrival = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burst = scanner.nextInt();
            System.out.print("Memory Required: ");
            int mem = scanner.nextInt();
            processes.add(new Process(i+1, arrival, burst, mem));
        }
        return  processes;
    }

}
