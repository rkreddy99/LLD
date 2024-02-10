package models;

import java.util.List;

public class ElevatorCar {
    ElevatorCarStatus status;
    List<InternalButton> internalButtons;
    int floorNumber;

    public ElevatorCar() {
        this.status = ElevatorCarStatus.IDLE;
        this.floorNumber = 0;
    }

    public void setInternalButtons(List<InternalButton> internalButtons) {
        this.internalButtons = internalButtons;
    }
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
    public int getFloorNumber() {
        return this.floorNumber;
    }
    public void setStatus(ElevatorCarStatus status) {
        this.status = status;
    }
    public ElevatorCarStatus getStatus() {
        return this.status;
    }
    public void tick() {
        if (status == ElevatorCarStatus.UP) {
            this.floorNumber++;
        } else if (status == ElevatorCarStatus.DOWN) {
            this.floorNumber--;
        }
    }
}
