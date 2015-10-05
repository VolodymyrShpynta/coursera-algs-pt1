package sorts;

import edu.princeton.cs.algs4.StdRandom;

public class SortHelper {

    public static Comparable[] mergeSort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];
        mergeSortRec(array, aux, 0, array.length - 1);
        return array;
    }

    public static Comparable[] quickAort(Comparable[] comparables){

    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static void mergeSortRec(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSortRec(a, aux, lo, mid);
        mergeSortRec(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(1000);
            System.out.printf(array[i] + " ");
        }
        System.out.println();
        mergeSort(array);
        for (Integer i : array) {
            System.out.printf(i + " ");
        }
    }

}
