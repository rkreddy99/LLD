package models;

public class ElevatorRequestButton {
    Floor floor;
    ElevatorController elevatorController;
    public ElevatorRequestButton(Floor floor, ElevatorController elevatorController) {
        this.floor = floor;
        this.elevatorController = elevatorController;
    }
    public void pushButton() {
        elevatorController.requestElevator(floor.floorNumber);
    }
}
