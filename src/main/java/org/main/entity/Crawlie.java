package org.main.entity;

import org.main.SimulationPanel;
public class Crawlie extends Entity {
    public int life;
    public int xDest, yDest;
    public static int startingLife = 1;

    public Crawlie(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
        this.life = this.startingLife;
        this.xDest = -1;
    }
}