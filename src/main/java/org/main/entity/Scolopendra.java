package org.main.entity;

import org.main.SimulationPanel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa Scolopendra reprezentuje stonogę w symulacji.
 * Jest jednym z rodzajów stworzeń w symulacji.
 */
public class Scolopendra {
    public static int startingLife = 5;
    public int life = startingLife;
    public SimulationPanel sp;
    public ArrayList<ScolopendraSegment> segments = new ArrayList<>();

    /**
     * Konstruktor klasy Scolopendra.
     *
     * @param x Współrzędna x początkowej pozycji stonogi.
     * @param y Współrzędna y początkowej pozycji stonogi.
     * @param sp Obiekt SimulationPanel, w którym stonoga jest symulowana.
     */
    public Scolopendra(int x, int y, SimulationPanel sp) {
        this.sp = new SimulationPanel();
        initScolopendra(x, y, sp);
    }

    /**
     * Inicjalizuje stonogę na planszy symulacyjnej.
     *
     * @param x Współrzędna x początkowej pozycji stonogi.
     * @param y Współrzędna y początkowej pozycji stonogi.
     * @param sp Obiekt SimulationPanel, w którym stonoga jest symulowana.
     */
    public void initScolopendra(int x, int y, SimulationPanel sp) {
        Random random = new Random();
        int segmentCount = 3 + random.nextInt(3);
        ScolopendraSegment initSegment = new ScolopendraSegment(x, y, sp, this, segmentCount);
        sp.entities.add(initSegment);
        segments.add(initSegment);
    }

    /**
     * Aktualizuje stan stonogi w symulacji.
     */
    public void update() {
        if (life <= 0) {
            segments.forEach(e -> sp.remove(e));
        }
    }
}
