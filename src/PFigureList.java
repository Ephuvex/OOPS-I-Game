
package src;

/**
 @author Quinn
 */
public class PFigureList
{

   private final int MAX_SIZE = 100;
   private PFigure list[] = new PFigure[MAX_SIZE];
   private int numFigs = 0;

   public int getSize()
   {
      return numFigs;
   }

   public PFigure get( int i )
   {
      return list[i];
   }

   private int find( PFigure fig )
   {
      for ( int i = numFigs - 1; i > -1; i-- )
      {
         if ( list[i].equals(fig) )
         {
            return i;
         }
      }
      return -1;
   }

   public boolean delete( PFigure fig )
   {
      int index = find(fig);
      if (index > -1) 
      {
        for (int i = index; i < numFigs - 2; i++) 
        {
            list[i] = list[i + 1];
        }
        numFigs--;
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
      if ( numFigs < this.MAX_SIZE )
      {
         list[numFigs++] = fig;
      }
   }
}
