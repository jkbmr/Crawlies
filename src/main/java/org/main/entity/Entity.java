package org.main.entity;

import org.main.SimulationPanel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Klasa Entity reprezentuje podstawową jednostkę w symulacji.
 * Jest klasą bazową dla wszystkich jednostek w symulacji.
 */
public class Entity {
    public int x, y;
    public Lock lock = new ReentrantLock();
    public boolean killme = false;
    public SimulationPanel sp;

    /**
     * Konstruktor klasy Entity.
     *
     * @param x Współrzędna x początkowej pozycji jednostki.
     * @param y Współrzędna y początkowej pozycji jednostki.
     * @param sp Obiekt SimulationPanel, w którym jednostka jest symulowana.
     */
    public Entity(int x, int y, SimulationPanel sp) {
        this.x = x;
        this.y = y;
        this.sp = sp;
    }

    /**
     * Metoda aktualizująca stan jednostki.
     * Powinna być nadpisana przez klasy dziedziczące.
     */
    public void update() {};
}
