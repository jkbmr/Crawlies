package org.main;

import org.main.entity.Entity;
import org.main.entity.Food;
import org.main.entity.Mite;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimulationPanel extends JPanel implements Runnable {
    // A bunch of variables that control the panel
    private final int originalTileSize = 3;
    private final int scale = 2;
    private final int tileSize = originalTileSize * scale;
    private final int maxCol = 64;
    private final int maxRow = 48;
    private final int panelWidth = tileSize * maxCol;
    private final int panelHeight = tileSize * maxRow;
    private final Lock lock = new ReentrantLock();
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final int delay = 10;
    private int delayLeft = 0;
    Thread thread;

    public ArrayList<Entity> entities = new ArrayList();
    public SimulationPanel() {
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        // Don't run if paused
        while (running & thread != null) {
            synchronized (lock) {
                if (!running) { break; }
                if (paused) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        break;
                    } if (!running) {
                        break;
                    }
                }
            }

            double interval = 1000000000/60;
            double delta = 0;
            long timeLast = System.nanoTime();
            long timeNow;

            while (!paused) {
                timeNow = System.nanoTime();
                delta += (timeNow - timeLast)/interval;
                timeLast = timeNow;

                if (delta >= 1) {
                    update();
                    repaint();
                    delta -= 1;
                }
            }
        }
    }

    public void update() {
        // Destroy entities with kill flag
        entities.removeIf(e -> e.killme);
        for (Entity e: entities) {
            // Update each entity separately
            e.update();
        }

        if (delayLeft > 0) { delayLeft--; }
        else if (entities.size() < 30) {
            delayLeft = delay;
            spawn();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Entity e : entities) {
            if (e.getClass() == Mite.class) {
                g.setColor(new Color(255, 253, 208));
            } else if (e.getClass() == Food.class) {
                g.setColor(new Color(150, 75, 0));
            }
            g.fillRect(e.x * tileSize, e.y * tileSize, tileSize, tileSize);
        }

        g.dispose();
    }

   // Check if coordinates breach containment
    public boolean breaches(int x, int y) {
        return !(x < 0 || y < 0 || x > maxCol || y > maxRow);
    }

    public void pauseSimulation() {
        paused = true;
    }
    public void unpauseSimulation() {
        synchronized (lock) {
            paused = false;
            lock.notifyAll();
        }
    }
    public void resetSimulation() {
        delayLeft = 0;
        entities.clear();
        unpauseSimulation();
    }

    private void spawn() {
        Random random = new Random();

        int x = random.nextInt(65), y = random.nextInt(49);

        int entityRandomizer = random.nextInt(100);
        if (entityRandomizer < 66) {
            entities.add(new Mite(x, y, this));
        } else if (entityRandomizer > 67 && entityRandomizer < 100) {
            entities.add(new Food(x, y, this));
        } else {
        }
    }

    public void remove(Entity e) {
        e.killme = true;
    }
}
