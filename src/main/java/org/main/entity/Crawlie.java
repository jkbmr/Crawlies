package org.main.entity;

import org.main.SimulationPanel;

/**
 * Klasa Crawlie reprezentuje pełzającego robaka w symulacji.
 * Jest klasą bazową dla innych typów pełzających stworzeń.
 */
public class Crawlie extends Entity {
    public int life;
    public int xDest, yDest;
    public static int startingLife = 1;

    /**
     * Konstruktor klasy Crawlie.
     *
     * @param x Współrzędna x początkowej pozycji robaka.
     * @param y Współrzędna y początkowej pozycji robaka.
     * @param sp Obiekt SimulationPanel, w którym robak jest symulowany.
     */
    public Crawlie(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
        this.life = this.startingLife;
        this.xDest = -1;
    }
}
