package org.main.entity;

import org.main.SimulationPanel;

public class ScolopendraSegment extends Crawlie {
    public ScolopendraSegment(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
        this.life = Scolopendra.startingLife;
    }

    public void update() {
        if (xDest < 0 || yDest < 0) {
            // Do something
        }
    }
}
