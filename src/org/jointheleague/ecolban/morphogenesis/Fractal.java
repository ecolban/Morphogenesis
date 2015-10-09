package org.jointheleague.ecolban.morphogenesis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Fractal extends JPanel implements Runnable {

	private static final int MARGIN = 10;
	private static final Random RNG = new Random();
	private final FractalSpec fractalSpec;
	private double growth = 0.01;
	private Rectangle2D unitCube;
	private Timer ticker;

	public static void main(String[] args) {
		FractalSpec spec = new Test3();
		SwingUtilities.invokeLater(new Fractal(spec));
	}

	public Fractal(FractalSpec spec) {
		this.fractalSpec = spec;
		unitCube = spec.getUnitCube();
	}

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
		ticker = new Timer(100, e -> grow());
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
		g2.scale(growth, growth);
		// g2.setColor(Color.GRAY);
		fillFractal(g2, fractalSpec.getTransforms());
	}

	private void fillFractal(Graphics2D g2, AffineTransform[] transforms) {
		if (Math.abs(g2.getTransform().getDeterminant()) < 40.0) {
			g2.setColor(randomColor());
			g2.fill(unitCube);
		} else {
			AffineTransform cachedTransform = g2.getTransform();
			for (AffineTransform t : transforms) {
				g2.transform(t);
				fillFractal(g2, transforms);
				g2.setTransform(cachedTransform);
			}
		}
	}

	private void grow() {
		if (growth < 1.0) {
			growth += 0.001;
			repaint();
		} else {
			ticker.stop();
		}
	}

	private Color randomColor() {
		return new Color(RNG.nextInt(256), RNG.nextInt(256), RNG.nextInt(256));
	}

}
