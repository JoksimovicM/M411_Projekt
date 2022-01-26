package Java;

/**
 * @author Jonas Rhbary
 * @version 1.0
 * @date 26.01.22
 */

public class InsertionSort implements SorterInterface {

    private long duration;
    private int comparisons;
    private long schreibzugriffe;
    private int memory;

    Runtime rt = Runtime.getRuntime();

    /**
     * Sort() sortiert die Zahlen mit hilfe vom InsertionSort
     * Insertionsort entnimmt der unsortierten Eingabefolge ein beliebiges Element und fügt es an richtiger Stelle in die (anfangs leere) Ausgabefolge ein.
     * Geht man hierbei in der Reihenfolge der ursprünglichen Folge vor, so ist das Verfahren stabil.
     * Wird auf einem Array gearbeitet, so müssen die Elemente hinter dem neu eingefügten Element verschoben werden.
     * Dies ist die eigentlich aufwendige Operation des Insertionsorts.
     * Das Auffinden der richtigen Einfügeposition kann über eine binäre Suche vergleichsweise effizient erfolgen.
     * Grundsätzlich gilt aber, dass Insertionsort weit weniger effizient arbeitet als andere anspruchsvollere Sortierverfahren.
     *
     * Best-Case: θ(n)
     * Average-Case: θ(n^2)
     * Worst-Case: θ(n^2)
     * @param zahlen
     */
    
    @Override
    public void sort(int[] zahlen) {
        schreibzugriffe = 0;
        comparisons = 0;
        memory = 0;

        long startTime = System.nanoTime();

        int n = zahlen.length;
        for (int i = 1; i < n; i++) {
            int key = zahlen[i];
            int j = i - 1;

            while (j >= 0 && zahlen[j] > key) {
                schreibzugriffe++;
                zahlen[j + 1] = zahlen[j];
                j = j - 1;
            }
            schreibzugriffe++;
            zahlen[j + 1] = key;
        }

        memory = (int)rt.totalMemory() - (int)rt.freeMemory();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        setTime(duration);
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
