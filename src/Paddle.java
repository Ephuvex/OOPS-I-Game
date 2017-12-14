package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import src.PFigure;

import java.awt.*;


import java.awt.Panel;

/**
 *
 * @author dericksonb
 */
public class Paddle extends PFigure
{
    protected Color colorD = new Color(255, 0, 0, 255); 
    protected Color colorLight = new Color(255, 140, 140, 255);
    
    public Paddle(int startX, int startY, int _width, int _height, int pr, Panel p) 
    {
        super(startX, startY, _width, _height, pr, p);
    }

    @Override
   public void draw()
   {
      Graphics g = panel.getGraphics();

      g.setColor(colorD);
      g.drawRect(x*2, y, width, height);

      g.setColor(colorD);
      g.drawRect(x + 1, y + 1, width - 2, height - 2);

      g.setColor(colorLight);
      g.fillRect(x + 2, y + 2, width - 4, height - 4);
   }
   public void move ( int deltaX, int deltaY )
   {
      super.move(deltaX, deltaY);
      if ( x < -width / 2 )
         x = panel.getSize().width - width / 2;
      else if ( (x + width / 2) > panel.getSize().width )
         x = -width / 2;

   }
}
