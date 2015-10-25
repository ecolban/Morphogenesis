package org.jointheleague.ecolban.morphogenesis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Organism extends JPanel implements Runnable, ActionListener {

    private static Random RNG = new Random();

    private static final int MARGIN = 10;
    private Timer ticker;
    private List<Cell> cells = new ArrayList<>();
    private Rectangle2D unitCube = Cell.getUnitCube();
    private double time = 0.0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Organism());
    }

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        double h = 786;
        double w = 1000;
        setPreferredSize(new Dimension((int) w, (int) h));
        frame.add(this, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        cells.add(new Cell(new AffineTransform()));
        ticker = new Timer(100, this);
        ticker.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.translate(MARGIN, MARGIN);
        g2.scale(getWidth() - 2 * MARGIN, getHeight() - 2 * MARGIN);
        g2.translate(-unitCube.getX(), -unitCube.getY());
        g2.scale(time, time);
        AffineTransform saved = g2.getTransform();
        for (Cell c : cells) {
            c.drawSelf(g2);
            g2.setTransform(saved);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Cell> nextGeneration = new ArrayList<>();
        time += 0.001;
        if (time >= 1) {
            ticker.stop();
        }
        for (Cell c : cells) {
            if (c.getSize(time) > 0.00005 * (1.0 + 4 * RNG.nextDouble())) {
                for (Cell cc : c.split()) {
                    nextGeneration.add(cc);
                }
            } else {
                nextGeneration.add(c);
            }
        }
        cells = nextGeneration;
        repaint();
    }

}
