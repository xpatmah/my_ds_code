package com.my.ds.code.questions.priority_queue.near_by_car;

import java.util.*;

public class NearbyCars {

    public static List<Car> findKClosestCars(Car[] cars, int k) {

        // Max Heap based on distance
        PriorityQueue<Car> maxHeap = new PriorityQueue<>(
            (a, b) -> b.distanceSquared() - a.distanceSquared()
        );

        for (Car car : cars) {
            maxHeap.offer(car);

            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove farthest car
            }
        }

        return new ArrayList<>(maxHeap);
    }

    public static void main(String[] args) {

        Car[] cars = {
            new Car(1, 3),
            new Car(3, 4),
            new Car(2, -1),
            new Car(-2, 2),
            new Car(5, 8)
        };

        int k = 3;

        List<Car> result = findKClosestCars(cars, k);

        System.out.println("Nearest " + k + " cars:");
        for (Car car : result) {
            System.out.println("(" + car.x + ", " + car.y + ")");
        }
    }
}