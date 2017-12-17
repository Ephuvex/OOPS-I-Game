package src.colliders.nonrailcolliders;

import src.colliders.ColliderList;

public class NonRailColliderList extends ColliderList {
    @Override
    public NonRailCollider get(int i) {
        return (NonRailCollider) super.get(i);
    }
}
