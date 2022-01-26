package Java;

/**
 * @author Jonas Rhbary
 * @version 1.0
 * @date 26.01.22
 */

public class Heapsort implements SorterInterface {

    private long duration;
    private int comparisons;
    private long schreibzugriffe;
    private int memory;

    Runtime rt = Runtime.getRuntime();

    /**
     * Sort() sortiert die Zahlen mit hilfe vom Heapsort
     * Best-Case: θ(n *log(n))
     * Average-Case: θ(n * log(n))
     * Worst-Case: θ(n * log(n))
     * @param zahlen
     */
    
    @Override
    public void sort(int[] zahlen) {
        schreibzugriffe = 0;
        comparisons = 0;
        memory = 0;

        long startTime = System.nanoTime();

        int n = zahlen.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(zahlen, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            schreibzugriffe++;
            int temp = zahlen[0];
            zahlen[0] = zahlen[i];
            zahlen[i] = temp;

            heapify(zahlen, i, 0);
        }

        memory = (int)rt.totalMemory() - (int)rt.freeMemory();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        setTime(duration);
    }

    public void heapify(int[] zahlen,int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        comparisons++;
        if (l < n && zahlen[l] > zahlen[largest]) {
            largest = l;
        }

        comparisons++;
        if (r < n && zahlen[r] > zahlen[largest]) {
            largest = r;
        }

        comparisons++;
        if (largest != i) {
            schreibzugriffe++;
            int swap = zahlen[i];
            zahlen[i] = zahlen[largest];
            zahlen[largest] = swap;

            heapify(zahlen, n, largest);
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
