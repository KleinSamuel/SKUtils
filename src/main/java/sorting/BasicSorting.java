package sorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class BasicSorting {

    public static void bubblesort(Object[] array, GenericSortComparator<Object> comparator){
        int i = 0;
        int n = array.length;
        boolean swapNeeded = true;
        while(i < n-1 && swapNeeded){
            swapNeeded = false;
            for (int j = 1; j < n-1; j++) {
                if(comparator.compareBool(array[j-1], array[j])){
                    Object tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                    swapNeeded = true;
                }
            }
            if(!swapNeeded){
                break;
            }
            i++;
        }
    }

    public static void insertionsort(Object[] array, GenericSortComparator<Object> comparator){
        for (int i = 1; i < array.length; i++) {
            Object key = array[i];
            int j = i-1;
            while(j >= 0 && comparator.compareBool(array[j], key)){
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1] = key;
        }
    }

    public static void quicksort(Object[] array, GenericSortComparator<Object> comparator){
        _quicksort(array, 0, array.length-1, comparator);
    }
    public static void _quicksort(Object[] array, int begin, int end, GenericSortComparator<Object> comparator){
        if(begin < end){
            int partitionIndex = _quicksort_partition(array, begin, end, comparator);
            _quicksort(array, begin, partitionIndex-1, comparator);
            _quicksort(array, partitionIndex+1, end, comparator);
        }
    }
    public static int _quicksort_partition(Object[] array, int begin, int end, GenericSortComparator<Object> comparator){
        Object pivot = array[end];
        int i = begin-1;
        for (int j = begin; j < end; j++) {
            if(comparator.compareBoolLessEquals(array[j], pivot)){
                i++;
                Object swapTmp = array[i];
                array[i] = array[j];
                array[j] = swapTmp;
            }
        }
        Object swapTmp = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTmp;
        return i+1;
    }

    public static void mergesort(Object[] array, GenericSortComparator<Object> comparator){
        _mergesort(array, array.length, comparator);
    }
    public static void _mergesort(Object[] array, int length, GenericSortComparator<Object> comparator){
        if(length < 2){
            return;
        }
        int mid = length/2;
        Object[] l = new Object[mid];
        Object[] r = new Object[length-mid];

        for (int i = 0; i < mid; i++) {
            l[i] = array[i];
        }
        for (int i = mid; i < length; i++) {
            r[i-mid] = array[i];
        }
        _mergesort(l, mid, comparator);
        _mergesort(r, length-mid, comparator);

        _merge(array, l, r, mid, length-mid, comparator);
    }
    public static void _merge(Object[] array, Object[] l, Object[] r, int left, int right, GenericSortComparator<Object> comparator){
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < left && j < right){
            if(comparator.compareBoolLessThan(l[i], r[j])){
                array[k++] = l[i++];
            }else{
                array[k++] = r[j++];
            }
        }
        while(i < left){
            array[k++] = l[i++];
        }
        while(j < right){
            array[k++] = r[j++];
        }
    }

    public static void main(String[] args) {

        Integer[] toSort = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100)).limit(10).boxed().toArray(Integer[]::new);

        System.out.println("BEFORE:");
        System.out.println(Arrays.toString(toSort));

        BasicSorting.mergesort(toSort, new GenericSortComparator<Object>(){
            @Override
            public int compare(Object o1, Object o2) {
                Integer p1 = (Integer)o1;
                Integer p2 = (Integer)o2;
                return p1.compareTo(p2);
            }
        });

        System.out.println("AFTER:");
        System.out.println(Arrays.toString(toSort));

    }

}
