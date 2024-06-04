package org.main.entity;

import org.main.SimulationPanel;

import java.util.ArrayList;
import java.util.Random;

public class Scolopendra {
    public static int startingLife = 5;
    public int life = startingLife;
    public SimulationPanel sp;
    public ArrayList<ScolopendraSegment> segments = new ArrayList<>();

    public Scolopendra(int x, int y, SimulationPanel sp) {
        this.sp = new SimulationPanel();
        initScolopendra(x, y, sp);
    }

    public void initScolopendra(int x, int y, SimulationPanel sp) {
        Random random = new Random();
        int segmentCount = 3 + random.nextInt(3);
        ScolopendraSegment initSegment = new ScolopendraSegment(x, y, sp, this, segmentCount);
        sp.entities.add(initSegment);
        segments.add(initSegment);
    }

    public void update() {
        if (life <= 0) {
            segments.forEach(e -> sp.remove(e));
        }
    }
}