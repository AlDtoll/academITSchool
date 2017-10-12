package ru.academits.tolmachev.main;

import ru.academits.tolmachev.shapes.Shape;
import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}
