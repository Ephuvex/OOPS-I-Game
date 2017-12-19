package src.colliders.nonrailcolliders;

import src.colliders.ColliderList;
import src.colliders.railcolliders.Rail;

/**
 A list class for NonRailColliders. This class simply extends ColliderList, but with a different get function to return
 NonRailColliders.
 @author andrewkaiser */
public class NonRailColliderList extends ColliderList
{
    /**
     Gets the collider at the target index.
     @param i the index of the target collider
     @return the collider at the target index
     */
    @Override
    public NonRailCollider get( int i )
    {
        return (NonRailCollider) super.get(i);
    }

    /**
     Hides all the nonRailColliders in the List.
    */
    public void hide()
    {
        for ( int i = 0; i < getSize(); i++ )
            get(i).hide();
    }
}
