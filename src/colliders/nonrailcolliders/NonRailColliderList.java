package src.colliders.nonrailcolliders;

import src.colliders.ColliderList;

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
}
