package src;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

/**
 *
 * @author dericksonb
 */
public class Ball extends PFigure
{
   private int x, y;        
   private int xVel, yVel;  
   private int radius;   
   private Panel panel;
   private Color color;

   // TODO: 12/11/17 [AndrewKaiser] Class needs a 'ghost' constructor
    
    public Ball(int initX, int initY, int initXVel, int initYVel, Color initColor, int initRadius, Panel p) 
    {
       super(initX, initY, initXVel, initYVel, initRadius, p);
    }
    
    @Override
    public void move()
   {

    
      if ( xVel < 0 && x - radius <= 0 ||
           xVel > 0 && x + radius >= panel.getSize().width )
         xVel = - xVel;

     
      if ( yVel < 0 && y - radius <= 0 ||
           yVel > 0 && y + radius >= panel.getSize().height )
         yVel = - yVel;




    
      x = x + xVel;
      y = y + yVel;
   }

   @Override
   public void draw()
   {
       if (panel == null)
           return;
       Graphics g = panel.getGraphics();
       g.fillOval(x, y, 10, 10);
       
   }
   
   public boolean inBounds()
   {
       return x - radius > 0 && x + radius < panel.getSize().width  && 
               y - radius > 0 && y + radius < panel.getSize().height;
   }
}
