package org.main.entity;

import org.main.SimulationPanel;

import java.util.Random;

/**
 * Klasa Spider reprezentuje pająka w symulacji.
 * Jest jednym z rodzajów pełzających stworzeń w symulacji.
 */
public class Spider extends Crawlie {
    public static int startingLife = 10;
    public int delay = 30;
    public int delayLeft = delay;
    public Crawlie quarry;

    /**
     * Konstruktor klasy Spider.
     *
     * @param x  Współrzędna x początkowej pozycji pająka.
     * @param y  Współrzędna y początkowej pozycji pająka.
     * @param sp Obiekt SimulationPanel, w którym pająk jest symulowany.
     */
    public Spider(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
    }

    /**
     * Aktualizuje stan pająka w symulacji.
     */
    public void update() {
        Random random = new Random();

        if (life <= 0) {
            killme = true;
        }

        // Jeśli nie ma żadnej ofiary
        if (quarry == null) {
            // Znajdź potencjalną ofiarę
            Entity potentialQuarry = sp.entities.stream()
                    .filter(e -> (Math.abs(e.x - x) <= 5) && (Math.abs(e.y - y) <= 5))
                    .filter(e -> !e.killme)
                    .filter(e -> (e.getClass() != Spider.class) && (e.getClass() != Food.class))
                    .findFirst().orElse(null);

            // Jeśli znaleziono, przyspiesz
            if (potentialQuarry != null) {
                quarry = (Crawlie) potentialQuarry;
                delay = 10;
            } else {
                // Idź w losowym kierunku
                do {
                    xDest = x + 20 * (random.nextInt(3) - 1);
                    yDest = y + 20 * (random.nextInt(3) - 1);
                } while (!sp.breaches(xDest, yDest));
            }
        } else {
            xDest = quarry.x;
            yDest = quarry.y;
        }

        if (delayLeft > 0) {
            delayLeft--;
        } else {
            if (x < xDest) {
                x++;
            } else if (x > xDest) {
                x--;
            }
            if (y < yDest) {
                y++;
            } else if (y > yDest) {
                y--;
            }
            delayLeft = delay;
        }

        if (quarry != null && quarry.x == x && quarry.y == y) {
            // Odejmij życie od Stonogi zamiast od Segmentu
            if (quarry instanceof ScolopendraSegment) {
                ((ScolopendraSegment) quarry).parent.life--;
                life--;
                if (((ScolopendraSegment) quarry).parent.life <= 0) {
                    if (life < startingLife) {
                        life++;
                    }
                    quarry = null;
                    delay = 30;
                }
            } else {
                quarry.life--;
                if (quarry.life <= 0) {
                    if (life < startingLife) {
                        life++;
                    }
                    quarry = null;
                    delay = 30;
                }
            }
        }
    }
}
