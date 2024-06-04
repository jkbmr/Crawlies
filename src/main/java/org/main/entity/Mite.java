package org.main.entity;

import org.main.SimulationPanel;

import java.util.Random;

public class Mite extends Crawlie {
    public static int startingLife = 1;
    public static int delay = 20;
    public int delayLeft = delay;

    public Mite(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
    }

    public void update() {
        if (life <= 0) {
            killme = true;
            return;
        }

        Random random = new Random();
        lock.lock();

        // Check if Food on path
        Entity foodOnPath = sp.entities.stream()
                .filter(e -> e instanceof Food)
                .filter(e -> e.x == x)
                .filter(e -> e.y == y)
                .findFirst().orElse(null);

        // Consume Food
        if (foodOnPath != null) {
            try {
                sp.remove(foodOnPath);
                life++;
            } finally {
                lock.unlock();
            }
        }

        if ((xDest < 0 || yDest < 0)) {
            // Set a destination 20 tiles in a random direction
            do {
                xDest = x + 20 * (random.nextInt(3) - 1);
                yDest = y + 20 * (random.nextInt(3) - 1);
            } while (!sp.breaches(xDest, yDest));
        } else if (x == xDest && y == yDest) {
            // Set no destination
            xDest = -1; yDest = -1;
        } else {
            // Move towards destination
            if (delayLeft > 0) { delayLeft--; }
            else {
                if (x < xDest) { x++; }
                else if (x > xDest) { x--; }
                if (y < yDest) { y++; }
                else if (y > yDest) { y--; }
                delayLeft = delay;
            }
        }
    }
}