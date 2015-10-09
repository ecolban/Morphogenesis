package org.jointheleague.ecolban.morphogenesis;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.sqrt;

public class Test3 implements FractalSpec {
	// Set the panel dimension such that the width is k * the height, 
	// where k = sqrt(1 + G) after removing margins.

	private static final double G = (-1.0 + sqrt(5.0)) / 2.0; // golden ratio

	@Override
	public Rectangle2D getUnitCube() {
		return new Rectangle2D.Double(-G * G, -G, 1.0, 1.0);
	}

	@Override
	public AffineTransform[] getTransforms() {
		return new AffineTransform[] {
				new AffineTransform(0.0, -1.0, G, 0, 0, 0),
				new AffineTransform(0.0, G, -G * G, 0.0, G * G, 0)
		};
	}

}
