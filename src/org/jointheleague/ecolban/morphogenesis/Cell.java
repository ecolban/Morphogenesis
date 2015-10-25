package org.jointheleague.ecolban.morphogenesis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Cell {
    
    private static final Random RNG = new Random();
    private AffineTransform affineTransform;
    private final Color color;
    private static final FractalSpec SPEC = new Sierpinski();
    
    public static Rectangle2D getUnitCube() {
        return SPEC.getUnitCube();
    }
    
    private static Color randomColor() {
        return new Color(RNG.nextInt(256), RNG.nextInt(256), RNG.nextInt(256));
    }
    
    public Cell(AffineTransform at) {
        this.affineTransform = at;
        this.color = randomColor();
    }

    public Cell[] split() {
        AffineTransform[] transforms = SPEC.getTransforms();
        Cell[] result = new Cell[transforms.length];
        for (int i = 0; i < result.length; i++) {
            AffineTransform at = new AffineTransform(affineTransform);
            at.concatenate(transforms[i]);
            result[i] = new Cell(at);
        }
        return result;
    }
    
    public void drawSelf(Graphics2D g2) {
        g2.transform(affineTransform);
        g2.setColor(color);
        g2.fill(SPEC.getUnitCube());
    }


    public double getSize(double growth) {
        return Math.abs(affineTransform.getDeterminant()) * growth * growth;
    }



}
