package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ElevatorController {
    PriorityQueue<Integer> upQueue;
    PriorityQueue<Integer> downQueue;
    List<Integer> pendingRequests;
    ElevatorCar elevatorCar;

    public ElevatorController(ElevatorCar elevatorCar) {
    upQueue = new PriorityQueue<>();
    downQueue = new PriorityQueue<>(Collections.reverseOrder());
    this.elevatorCar = elevatorCar;
    this.pendingRequests = new ArrayList<>();
    }

    public void requestElevator(Integer floorNumber) {
        if (elevatorCar.status == ElevatorCarStatus.IDLE) {
            if (floorNumber > elevatorCar.floorNumber) {
                upQueue.add(floorNumber);
                elevatorCar.status = ElevatorCarStatus.UP;
            } else if (floorNumber < elevatorCar.floorNumber) {
                downQueue.add(floorNumber);
                elevatorCar.status = ElevatorCarStatus.DOWN;
            }
        } else if (elevatorCar.status == ElevatorCarStatus.UP) {
            if (floorNumber > elevatorCar.floorNumber) {
                upQueue.add(floorNumber);
            } else {
                pendingRequests.add(floorNumber);
            }
        } else if (elevatorCar.status == ElevatorCarStatus.DOWN) {
            if (floorNumber < elevatorCar.floorNumber) {
                downQueue.add(floorNumber);
            } else {
                pendingRequests.add(floorNumber);
            }
        }
    }

    public void tick() {
        if (elevatorCar.status == ElevatorCarStatus.UP) {
            if (upQueue.peek() == elevatorCar.floorNumber) {
                upQueue.remove();
            }
            if (upQueue.isEmpty()) {
                if (downQueue.isEmpty()) {
                    elevatorCar.status = ElevatorCarStatus.IDLE;
                } else {
                    elevatorCar.status = ElevatorCarStatus.DOWN;

                }
                if (!pendingRequests.isEmpty()) {
                    downQueue.addAll(pendingRequests);
                    pendingRequests.clear();
                }
            }
        } else if (elevatorCar.status == ElevatorCarStatus.DOWN) {
            if (downQueue.peek() == elevatorCar.floorNumber) {
                downQueue.remove();
            }
            if (downQueue.isEmpty()) {
                if (upQueue.isEmpty()) {
                    elevatorCar.status = ElevatorCarStatus.IDLE;
                } else {
                    elevatorCar.status = ElevatorCarStatus.UP;
                }
                if (!pendingRequests.isEmpty()) {
                    upQueue.addAll(pendingRequests);
                    pendingRequests.clear();
                }
            }
        } else if (elevatorCar.status == ElevatorCarStatus.IDLE) {
            if (!upQueue.isEmpty()){
                elevatorCar.status = ElevatorCarStatus.UP;
            } else if (!downQueue.isEmpty()) {
                elevatorCar.status = ElevatorCarStatus.DOWN;
            }
        }
        elevatorCar.tick();
    }
}
