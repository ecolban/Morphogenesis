package org.jointheleague.ecolban.morphogenesis;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public interface FractalSpec {
	
	AffineTransform[] getTransforms();
	
	Rectangle2D getUnitCube();
	
}
