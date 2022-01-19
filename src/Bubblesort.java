/**
 * @author Marko Joksimovic
 * @version 1.0
 * @date
 */
public class Bubblesort implements SorterInterface {

    @Override
    public void sort(int[] zahlen) {
        int n = zahlen.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (zahlen[j] > zahlen[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = zahlen[j];
                    zahlen[j] = zahlen[j + 1];
                    zahlen[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public int getSpeicherbedarf() {
        return 0;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public int anzVergleiche() {
        return 0;
    }

    @Override
    public int anzahlSchreibzugriffe() {
        return 0;
    }
}
