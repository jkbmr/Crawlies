package org.main.entity;

import org.main.SimulationPanel;

import java.util.Random;

public class Spider extends Crawlie {
    public static int startingLife = 5;
    public int delay = 30;
    public int delayLeft = delay;

    public int timeSinceEgg = 0;
    public Crawlie quarry;

    public Spider(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
    }

    public void update() {
        Random random = new Random();

        // If has no quarry
        if (quarry == null) {
            Entity potentialQuarry = sp.entities.stream()
                    .filter(e -> (Math.abs(e.x - x) <= 5) && (Math.abs(e.y - y) <= 5))
                    .filter(e -> (e.getClass() != Spider.class) && (e.getClass() != Food.class))
                    .findFirst().orElse(null);

            if (potentialQuarry != null) {
                quarry = (Crawlie) potentialQuarry;
                delay = 10;
            } else {
                do {
                    xDest = x + 20 * (random.nextInt(3) - 1);
                    yDest = y + 20 * (random.nextInt(3) - 1);
                } while (!sp.breaches(xDest, yDest));
            }
        } else {
            xDest = quarry.x;
            yDest = quarry.y;
        }

        if (delayLeft > 0) { delayLeft--; }
        else {
            if (x < xDest) { x++; }
            else if (x > xDest) { x--; }
            if (y < yDest) { y++; }
            else if (y > yDest) { y--; }
            delayLeft = delay;
        }

        if (quarry != null && quarry.x == x && quarry.y == y) {
            quarry.life--;
            if (quarry.life <= 0) {
                quarry = null;
                delay = 30;
            }
        }
    }
}