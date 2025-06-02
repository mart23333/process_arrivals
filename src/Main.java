import interfaces.Scheduler;
import logic.FCFS;
import logic.RoundRobin;
import logic.SJF;
import model.Process;
import service.CLI;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        CLI cli = new CLI();
        List<Process> processes = cli.getUserInput();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Choose Scheduling Algorithm (1.FCFS 2.SJF 3.RoundRobin): ");
        int choice = scanner.nextInt();

        Scheduler scheduler = switch (choice) {
            case 2 -> new SJF();
            case 3 -> new RoundRobin(2);
            default -> new FCFS();
        };
        scheduler.schedule(processes);
    }
}