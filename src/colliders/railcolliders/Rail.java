/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.colliders.railcolliders;

import src.PFigure;

/**
 *
 * @author dericksonb
 */
public class Rail extends PFigure{
    public Rail(int startX, int startY, int _width, int _height) {
        super(startX, startY, _width, _height, 0, null);
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
