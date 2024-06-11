package org.main.entity;

import org.main.SimulationPanel;

/**
 * Klasa Food reprezentuje jedzenie w symulacji.
 * Jedzenie jest spożywane przez inne jednostki w symulacji.
 */
public class Food extends Entity {

    /**
     * Konstruktor klasy Food.
     *
     * @param x Współrzędna x początkowej pozycji jedzenia.
     * @param y Współrzędna y początkowej pozycji jedzenia.
     * @param sp Obiekt SimulationPanel, w którym jedzenie jest symulowane.
     */
    public Food(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
    }
}
