package org.main.entity;

import org.main.SimulationPanel;

public class Spider extends Crawlie {
    public static int startingLife = 5;

    public int timeSinceEgg = 0;
    public Crawlie quarry;

    public Spider(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
    }

    public void update() {
        // If has no quarry
        if (quarry == null) {
            Entity potentialQuarry = sp.entities.stream()
                    .filter(e -> e.getClass().isAssignableFrom(Crawlie.class))
                    .filter(e -> e.getClass() != Spider.class)
                    .findFirst().orElse(null);
        }
    }
}
