package src;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;

/**
 @author dericksonb */
public class Missile extends PFigure
 
{
   public static int angle = 90;
   public static int counter = 10;
   private boolean shouldDie;
   int xVel;
   int yVel;

   // TODO: 12/11/17 [AndrewKaiser] Class needs a 'ghost' constructor

   public Missile( int initX, int initY, int initXVel, int initYVel, Color initColor, int initRadius, Panel p )
   {
      super(initX, initY, 10, 10, initRadius, p);
      xVel = initXVel;
      yVel = initYVel;
   }
   
   public boolean getShouldDie() {
       return shouldDie;
   }
   
   public boolean isOutOfBounds() {
       return x < 0 || y < 0 || x > panel.getWidth() || y > panel.getHeight();
   }
   
   @Override
   public void move() {
       x += xVel;
       y += yVel;
   }

   @Override
   public void draw()
   {
        Graphics g = panel.getGraphics();
        g.setColor(Color.blue);
        g.fillOval(x, y, 10, 10);
        
   }
   
   public void kys()
   {
       shouldDie = true;
   }
}
