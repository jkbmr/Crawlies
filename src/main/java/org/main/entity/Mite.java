package org.main.entity;

import org.main.SimulationPanel;

import java.util.Random;

/**
 * Klasa Mite reprezentuje roztocza w symulacji.
 * Są one jednym z rodzajów pełzających stworzeń w symulacji.
 */
public class Mite extends Crawlie {
    public static int startingLife = 1;
    public static int delay = 20;
    public int delayLeft = delay;

    /**
     * Konstruktor klasy Mite.
     *
     * @param x Współrzędna x początkowej pozycji roztocza.
     * @param y Współrzędna y początkowej pozycji roztocza.
     * @param sp Obiekt SimulationPanel, w którym roztocze jest symulowane.
     */
    public Mite(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
    }

    /**
     * Aktualizuje stan roztocza w symulacji.
     */
    public void update() {
        if (life <= 0) {
            killme = true;
            return;
        }

        Random random = new Random();
        lock.lock();

        // Sprawdzenie, czy na ścieżce znajduje się jedzenie
        Entity foodOnPath = sp.entities.stream()
                .filter(e -> e instanceof Food)
                .filter(e -> e.x == x)
                .filter(e -> e.y == y)
                .findFirst().orElse(null);

        // Konsumpcja jedzenia
        if (foodOnPath != null) {
            try {
                sp.remove(foodOnPath);
                life++;
            } finally {
                lock.unlock();
            }
        }

        if ((xDest < 0 || yDest < 0)) {
            // Ustawienie celu 20 kafelków w losowym kierunku
            do {
                xDest = x + 20 * (random.nextInt(3) - 1);
                yDest = y + 20 * (random.nextInt(3) - 1);
            } while (!sp.breaches(xDest, yDest));
        } else if (x == xDest && y == yDest) {
            // Brak celu
            xDest = -1; yDest = -1;
        } else {
            // Poruszanie się w kierunku celu
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
