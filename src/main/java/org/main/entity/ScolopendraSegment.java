package org.main.entity;

import org.main.SimulationPanel;

import java.util.Random;

public class ScolopendraSegment extends Crawlie {
    public static int delay = 20;
    public int delayLeft = delay;
    public Scolopendra parent;
    public int segmentsLeft;
    public ScolopendraSegment(int x, int y, SimulationPanel sp, Scolopendra parent, int segmentsLeft) {
        super(x, y, sp);
        this.parent = parent;
        this.segmentsLeft = segmentsLeft;
    }

    public void update() {
        Random random = new Random();

        if (parent.segments.indexOf(this) == 0) {
            if (xDest < 0 || yDest < 0) {
                do {
                    xDest = x + 20 * (random.nextInt(3) - 1);
                    yDest = y + 20 * (random.nextInt(3) - 1);
                } while (!sp.breaches(xDest, yDest));
            } else if (xDest == x && yDest == y) {
                xDest = -1; yDest = -1;
            }
        } else {
            ScolopendraSegment follows = parent.segments.get(parent.segments.indexOf(this) - 1);
            xDest = follows.x;
            yDest = follows.y;
        }

        if (delayLeft > 0) { delayLeft--; }
        else {
            if (segmentsLeft > 0) {
                ScolopendraSegment segment = new ScolopendraSegment(x, y, sp, parent, --segmentsLeft);
                sp.entityQueue.add(segment);
                parent.segments.add(segment);
                segmentsLeft = 0;
            }

            if (parent.segments.indexOf(this) == 0) {
                if (x < xDest) { x++; }
                else if (x > xDest) { x--; }
                if (y < yDest) { y++; }
                else if (y > yDest) { y--; }
                delayLeft = delay;
                parent.segments.subList(1, parent.segments.size()).forEach(e -> e.x = e.xDest);
                parent.segments.subList(1, parent.segments.size()).forEach(e -> e.y = e.yDest);
            }
        }
    }
}
