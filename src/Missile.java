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
   public static int counter = 0;

   // TODO: 12/11/17 [AndrewKaiser] Class needs a 'ghost' constructor

   public Missile( int initX, int initY, int initXVel, int initYVel, Color initColor, int initRadius, Panel p )
   {
      super(initX, initY, initXVel, initYVel, initRadius, p);
   }

   @Override
   public void draw()
   {

   }
}
