package Java;

/**
 * @author Jonas Rhbary
 * @version 1.0
 * @date 26.01.22
 */

public class Mergesort implements SorterInterface {

    private long duration;
    private int comparisons;
    private long schreibzugriffe;
    private int memory;

    Runtime rt = Runtime.getRuntime();

    /**
     * Sort() sortiert die Zahlen mit hilfe vom Mergesort
     * Best-Case: θ(n * log(n))
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

        sort(zahlen, 0, zahlen.length - 1);
        memory = (int)rt.totalMemory() - (int)rt.freeMemory();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        setTime(duration);
    }

    public void merge(int[] zahlen, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            schreibzugriffe++;
            L[i] = zahlen[l + i];
        }
        for (int j = 0; j < n2; j++) {
            schreibzugriffe++;
            R[j] = zahlen[m + 1 + j];
        }
        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            comparisons++;
            if (L[i] <= R[j]) {
                schreibzugriffe++;
                zahlen[k] = L[i];
                i++;
            } else {
                schreibzugriffe++;
                zahlen[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            schreibzugriffe++;
            zahlen[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            schreibzugriffe++;
            zahlen[k] = R[j];
            j++;
            k++;
        }
    }

    public void sort(int[] zahlen, int l, int r) {
        comparisons++;
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(zahlen, l, m);
            sort(zahlen, m + 1, r);

            merge(zahlen, l, m, r);
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
