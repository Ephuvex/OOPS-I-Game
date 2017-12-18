package src.colliders;

import src.PFigureList;

/**
 A very simple extension of PFigure list, adopted to return colliders.
 @author andrewkaiser */
public class ColliderList extends PFigureList
{
   /**
    Returns the collider at the passed index in the list.
    @param i the index of the target collider
    @return the collider at the target index
    */
   @Override
   public Collider get( int i )
   {
      return (Collider) super.get(i);
   }

}
