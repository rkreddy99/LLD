import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Floor;

public class App {
    public static void main(String[] args) throws Exception {
        List<Floor> floors = new ArrayList<>();
        for (int i = -1; i < 10; i++) {
            floors.add(new Floor(i));
        }
        ElevatorSystem elevatorSystem = new ElevatorSystem(floors);
        elevatorSystem.addElevator();
        elevatorSystem.addElevator();
        Runnable tick = () -> {
            try {
                elevatorSystem.tick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(tick);
        thread.start();
        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            String[] commands = userInput.split(" ");
            if (commands[0].equals("request")) {
                int floorNumber = Integer.parseInt(commands[1]);
                int elevatorNumber = Integer.parseInt(commands[2]);
                elevatorSystem.elevators.get(elevatorNumber).elevatorController.requestElevator(floorNumber);
            } else if (commands[0].equals("add")) {
                elevatorSystem.addElevator();
            } else if (commands[0].equals("status")) {
                elevatorSystem.printStatus();
            } else if (commands[0].equals("quit")) {
                break;
            };
    }
    }
}
