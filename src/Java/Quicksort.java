package Java;

/**
 * @author Jonas Rhbary
 * @version 1.0
 * @date 26.01.22
 */

public class Quicksort implements SorterInterface{

    private long duration;
    private int comparisons;
    private long schreibzugriffe;
    private int memory;

    Runtime rt = Runtime.getRuntime();

    @Override
    public void sort(int[] zahlen) {
        schreibzugriffe = 0;
        comparisons = 0;
        memory = 0;

        long startTime = System.nanoTime();

        quicksort(zahlen,0,zahlen.length - 1);

        memory = (int)rt.totalMemory() - (int)rt.freeMemory();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        setTime(duration);
    }

    public void quicksort(int[] zahlen, int low, int high) {
        comparisons++;
        if (low < high) {
            int pi = partition(zahlen, low, high);
            quicksort(zahlen, low, pi -1);
            quicksort(zahlen, pi + 1, high);
        }
    }

    public int partition(int[] zahlen, int low, int high) {

        int pivot = zahlen[high];
        int i = low - 1;

        for (int j = low; j <= high; j++) {
            comparisons++;
            if (zahlen[j] < pivot) {
                i++;
                swap(zahlen, i, j);
            }
        }
        swap(zahlen, i + 1, high);
        return (i + 1);
    }

    public void swap(int[] zahlen, int i, int j) {
        schreibzugriffe++;
        int temp = zahlen[i];
        zahlen[i] = zahlen[j];
        zahlen[j] = temp;
    }

    @Override
    public int getSpeicherbedarf() {
        return memory;
    }

    @Override
    public long getTime() {
        return duration;
    }

    public void setTime(long duration) {
        this.duration = duration;
    }

    @Override
    public int anzVergleiche() {
        return comparisons;
    }

    @Override
    public long anzahlSchreibzugriffe() {
        return schreibzugriffe;
    }
}
