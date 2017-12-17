package src.colliders;

import src.PFigureList;

public class ColliderList extends PFigureList {
    @Override
    public Collider get(int i) {
        return (Collider) super.get(i);
    }
}
