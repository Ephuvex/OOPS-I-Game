/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author Quinn
 */
public class PFigureList
{
   public final int MAX_SIZE = 10;
   private PFigure list[] = new PFigure[MAX_SIZE];
   private int numFigs = 0;


   private int find ( PFigure fig )
   {
      for ( int i = numFigs - 1; i > -1; i-- )
         if ( list[i].equals(fig) )
            return i;
      return -1;
   }

   public boolean delete ( PFigure fig )
   {
      int index = find(fig);
      if ( index > -1 )
      {
         for ( int i = index; i < numFigs - 1; i++ )
            list[i] = list[i + 1];
         numFigs--;
         return true;
      }
      return false;
   }

   public void add( PFigure fig )
   {
      if ( numFigs < this.MAX_SIZE )
      {
         list[numFigs - 1] = fig;
         numFigs++;
      }
   }
}