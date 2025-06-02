package model;

public class Process {

  public int pid;
  public int arrivalTime;
  public int burstTime;
  public int memoryRequired;
  public int completionTime;
  public int waitingTime;
  public int turnaroundTime;
  public int cpuUsage;
  public int memoryUtilization;

  public Process(int pid, int arrivalTime, int burstTime, int memoryRequired) {
    this.pid = pid;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.memoryRequired = memoryRequired;
  }
    // public int pid;// public int arrivalTime;// public int burstTime;
    // public int memoryRequired;

    // public Process(int pid, int arrivalTime, int burstTime, int memoryRequired) {
    // this.pid = pid;
    // this.arrivalTime = arrivalTime;
    // this.burstTime = burstTime;
    // this.memoryRequired = memoryRequired;
    // }

}
