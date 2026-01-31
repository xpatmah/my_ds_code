package com.my.ds.code.questions.priority_queue.near_by_car;

class Car {
    int x, y;

    Car(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int distanceSquared() {
        return x * x + y * y;
    }
}
