package org.main.entity;

import org.main.SimulationPanel;

import java.util.ArrayList;
import java.util.List;

public class Scolopendra {
    public static int startingLife = 5;

    public Scolopendra(int x, int y, SimulationPanel sp) {
        initScolopendra(x, y, sp);
    }

    public void initScolopendra(int x, int y, SimulationPanel sp) {
        List<ScolopendraSegment> segments = new ArrayList<ScolopendraSegment>();
        segments.add(new ScolopendraSegment(x, y, sp));
    }
}