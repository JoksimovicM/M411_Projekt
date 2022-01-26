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
     * Zunächst wird die zu sortierende Liste in zwei Teillisten („linke“ und „rechte“ Teilliste) getrennt.
     * Dazu wählt Quicksort ein sogenanntes Pivotelement aus der Liste aus. Alle Elemente, die kleiner als das Pivotelement sind, kommen in die linke Teilliste, und alle, die größer sind, in die rechte Teilliste.
     * Die Elemente, die gleich dem Pivotelement sind, können sich beliebig auf die Teillisten verteilen. Nach der Aufteilung sind die Elemente der linken Liste kleiner oder gleich den Elementen der rechten Liste.
     * Anschließend muss man also noch jede Teilliste in sich sortieren, um die Sortierung zu vollenden. Dazu wird der Quicksort-Algorithmus jeweils auf der linken und auf der rechten Teilliste ausgeführt.
     * Jede Teilliste wird dann wieder in zwei Teillisten aufgeteilt und auf diese jeweils wieder der Quicksort-Algorithmus angewandt, und so weiter. Diese Selbstaufrufe werden als Rekursion bezeichnet.
     * Wenn eine Teilliste der Länge eins oder null auftritt, so ist diese bereits sortiert und es erfolgt der Abbruch der Rekursion.
     * Die Positionen der Elemente, die gleich dem Pivotelement sind, hängen vom verwendeten Teilungsalgorithmus ab. Sie können sich beliebig auf die Teillisten verteilen.
     * Da sich die Reihenfolge von gleichwertigen Elementen zueinander ändern kann, ist Quicksort im Allgemeinen nicht stabil.
     * Das Verfahren muss sicherstellen, dass jede der Teillisten mindestens um eins kürzer ist als die Gesamtliste.
     * Dann endet die Rekursion garantiert nach endlich vielen Schritten. Das kann z. B. dadurch erreicht werden, dass das ursprünglich als Pivot gewählte Element auf einen Platz zwischen den Teillisten gesetzt wird und somit zu keiner Teilliste gehört.
     *
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
