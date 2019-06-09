package sorting;

import java.util.Comparator;

public class GenericSortComparator<T> implements Comparator<T> {

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    public boolean compareBool(Object o1, Object o2){
        return compare(o1, o2) > 0;
    }

    public boolean compareBoolLessThan(Object o1, Object o2){
        return compare(o1, o2) < 0;
    }

    public boolean compareBoolLessEquals(Object o1, Object o2){
        return compare(o1, o2) <= 0;
    }

    public boolean compareBoolGreaterThan(Object o1, Object o2){
        return compare(o1, o2) > 0;
    }

    public boolean compareBoolGreaterEquals(Object o1, Object o2){
        return compare(o1, o2) >= 0;
    }
}
