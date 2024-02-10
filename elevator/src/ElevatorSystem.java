import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import models.Floor;

public class ElevatorSystem {
    List<Floor> floors;
    List<Elevator> elevators;
    public ElevatorSystem(List<Floor> floors) {
        this.floors = floors;
        this.elevators = new ArrayList<>();
    }

    public void addElevator() {
        elevators.add(new Elevator(floors, elevators.size()));
    }

    public void printStatus() {
        try {
        PrintWriter out = new PrintWriter(new FileWriter("status.txt", false));
        for (Elevator elevator : elevators) {
            out.println(elevator.printStatus());
        }
        out.close();
    } catch (IOException e) {
        System.out.println("An error occurred while writing to the file.");
        e.printStackTrace();
    }
    }

    public void tick() throws InterruptedException {
        while (true) {
            printStatus();
            for (Elevator elevator : elevators) {
                elevator.elevatorController.tick();
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
