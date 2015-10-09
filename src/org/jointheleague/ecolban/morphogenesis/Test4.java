package org.jointheleague.ecolban.morphogenesis;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.sqrt;

public class Test4 implements FractalSpec {
	// Set the panel dimension such that the width is k * the height, where k = sqrt(1 + golden) 
	// after removing margins.

	@Override
	public AffineTransform[] getTransforms() {
		double golden = (-1.0 + sqrt(5.0)) / 2.0; // golden ratio
		double golden2 = 1.0 - golden; // also == golden * golden
		return new AffineTransform[] {
		        new AffineTransform(0.0, -1.0, golden, 0, 0.0, 1.0),
		        new AffineTransform(0.0, golden, -golden2, 0.0, 1.0, 0.0)
		};
	}

	@Override
	public Rectangle2D getUnitCube() {
		return new Rectangle2D.Double(0, 0, 1, 1);
	}

}
