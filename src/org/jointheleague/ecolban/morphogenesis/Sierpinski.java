package org.jointheleague.ecolban.morphogenesis;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Sierpinski implements FractalSpec{

	@Override
	public AffineTransform[] getTransforms() {
		return new AffineTransform[] {
		        new AffineTransform(0.5, 0.0, 0.0, 0.5, -0.25, 0.25),
		        new AffineTransform(0.5, 0.0, 0.0, 0.5, 0.25, 0.25),
		        new AffineTransform(0.5, 0.0, 0.0, 0.5, 0.0, -0.25)
		};
	}

	@Override
	public Rectangle2D getUnitCube() {
		return new Rectangle2D.Double(-0.5, -0.5, 1, 1);
	}

}
