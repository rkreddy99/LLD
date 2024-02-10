import java.util.List;

import models.ElevatorCar;
import models.ElevatorController;
import models.ExternalButton;
import models.Floor;
import models.InternalButton;

public class Elevator {
    int id;
    List<Floor> floors;
    List<ExternalButton> externalButtons;
    ElevatorController elevatorController;
    ElevatorCar elevatorCar;
    public Elevator(List<Floor> floors, int id) {
        this.id = id;
        this.floors = floors;
        this.elevatorCar = new ElevatorCar();
        this.elevatorController = new ElevatorController(elevatorCar);
        this.externalButtons = floors.stream().map(floor -> new ExternalButton(floor, elevatorController)).toList();
        List<InternalButton> internalButtons = floors.stream().map(floor -> new InternalButton(floor, elevatorController)).toList();
        elevatorCar.setInternalButtons(internalButtons);
    }

    public String printStatus() {
        // System.out.println("Elevator ID: " + id);
        // System.out.println("Current Floor: " + elevatorCar.getFloorNumber());
        // System.out.println("Direction: " + elevatorCar.getStatus());
        String output = "Elevator ID: " + id + "\n";
        output += "Current Floor: " + elevatorCar.getFloorNumber() + "\n";
        output += "Direction: " + elevatorCar.getStatus() + "\n";
        return output;
    }
}
