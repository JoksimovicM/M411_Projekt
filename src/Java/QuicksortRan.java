package Java;

import java.util.Random;

/**
 * @author Jonas Rhbary
 * @version 1.0
 * @date 26.01.22
 */

public class QuicksortRan implements SorterInterface {

    private long duration;
    private int comparisons;
    private long schreibzugriffe;
    private int memory;

    Runtime rt = Runtime.getRuntime();

    /**
     * Sort() sortiert die Zahlen mit hilfe vom Quicksort, jedoch mit einem random Pivot
     * Best-Case: θ(n * log(n))
     * Average-Case: θ(n * log(n))
     * Worst-Case: θ(n^2)
     * @param zahlen
     */
    
    @Override
    public void sort(int[] zahlen) {
        comparisons = 0;
        schreibzugriffe = 0;
        memory = 0;

        long startTime = System.nanoTime();

        sort(zahlen,0, zahlen.length - 1);

        memory = (int)rt.totalMemory() - (int)rt.freeMemory();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        setTime(duration);
    }

    public void random(int[] zahlen, int low, int high) {
        Random random = new Random();
        int pivot = random.nextInt(high - low) + low;

        schreibzugriffe++;
        int temp1 = zahlen[pivot];
        zahlen[pivot] = zahlen[high];
        zahlen[high] = temp1;
    }

    public int partition(int[] zahlen, int low, int high) {
        random(zahlen,low,high);
        int pivot = zahlen[high];

        int i = (low - 1);
        for (int j = low; j < high; j++) {
            comparisons++;
            if (zahlen[j] < pivot) {
                i++;
                schreibzugriffe++;
                int temp = zahlen[i];
                zahlen[i] = zahlen[j];
                zahlen[j] = temp;
            }
        }
        schreibzugriffe++;
        int temp = zahlen[i + 1];
        zahlen[i + 1] = zahlen[high];
        zahlen[high] = temp;

        return i + 1;
    }

    public void sort(int[] zahlen, int low, int high) {
        comparisons++;
        if (low < high) {
            int pi = partition(zahlen, low, high);
            sort(zahlen, low, pi - 1);
            sort(zahlen, pi + 1, high);
        }
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
