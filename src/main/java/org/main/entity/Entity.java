package org.main.entity;

import org.main.SimulationPanel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Entity {
    public int x, y;
    public Lock lock = new ReentrantLock();
    public boolean killme = false;
    public SimulationPanel sp;

    public Entity(int x, int y, SimulationPanel sp) {
        this.x = x;
        this.y = y;
        this.sp = sp;
    }
    public void update() {};
}
