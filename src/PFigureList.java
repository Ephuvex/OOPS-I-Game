/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 @author Quinn */
public class PFigureList
{

   private final int MAX_SIZE = 100;
   private PFigure items[] = new PFigure[MAX_SIZE];
   private int size = 0;

   public int getSize()
   {
      return size;
   }

   public PFigure get( int i )
   {
      return items[i];
   }

   private int find( PFigure fig )
   {
      for ( int i = size - 1; i > -1; i-- )
      {
         if ( items[i].equals(fig) )
         {
            return i;
         }
      }
      return -1;
   }

   public boolean delete( PFigure fig )
   {
      int index = find(fig);
      if ( index > -1 )
      {
         for ( int i = index; i < getSize() - 2; i++ )
         {
            items[i] = items[i + 1];
         }
         size--;
         return true;
      }
      return false;
   }

   public void add( PFigure fig )
   {
      if ( fig == null )
      {
         return;
      }
      if ( size < this.MAX_SIZE )
      {
         items[size++] = fig;
      }
   }
}
