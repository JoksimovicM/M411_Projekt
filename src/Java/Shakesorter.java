package Java;

/**
 * Diese Klasse sortiert ein Array aufsteigen mittels Shakesort.
 *
 * @author Jonas Rhbary
 * @version 1.5
 * @since 20.01.2021
 */

public class Shakesort implements SorterInterface {

    int iterationCnt = 0;
    int comparisonCnt = 0;
    int timeCompletion = 0;
    int memoryCnt = 0;

    /**
     * Default konstruktor
     */
    public Shakesort() {
    }

    /**
     * Diese Methode sortiert das gegebene Array mit dem Shakesort. Dieser funktioniert folgenderweise, beim ersten Durchgang
     * speichert er die erste Zahl in die Variabel i. Dann vergleicht er jede Zahl danach und schaut, ob diese grösser ist.
     * Die grösste Zahl wird dann so oft geswapt, bis es am ende des Arrays ankommt und lässt die Zahl dort. Danach passiert
     * genau das Gleiche, einfach umgekehrt. Die kleinste Zahl wird in die Variabel j gespeichert und wird so oft geswapt
     * bis diese Zahl wieder am anfang des Arrays ankommt und lässt die dort. Dieses Prozedere wird so oft wiederholt, bis
     * alle Zahlen sortiert sind.
     *
     * @param
     * @return messArray[]
     */
    public void sort(int[] zahlen) {

        double start = System.nanoTime();

        for (int i = 0; i < zahlen.length/2; i++) {
            comparisonCnt++; iterationCnt++;
            boolean swapped = false;
            for (int j = i; j < zahlen.length - i - 1; j++) {
                iterationCnt++; comparisonCnt++;
                if (zahlen[j] < zahlen[j+1]) {
                    comparisonCnt++;
                    int temp = zahlen[j];
                    zahlen[j] = zahlen[j+1];
                    zahlen[j+1] = temp;
                }
            }
            for (int j = zahlen.length - 2 - i; j > i; j--) {
                comparisonCnt++; iterationCnt++;
                if (zahlen[j] > zahlen[j-1]) {
                    comparisonCnt++;
                    int temp = zahlen[j];
                    zahlen[j] = zahlen[j-1];
                    zahlen[j-1] = temp;
                    swapped = true;
                }
            }
            if(!swapped) break;
        }

        double finish = System.nanoTime();

        timeCompletion = (int) (finish - start);
        memoryCnt = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());


    }

    @Override
    public int getSpeicherbedarf() {
        return memoryCnt;
    }

    @Override
    public long getTime() {
        return timeCompletion;
    }

    @Override
    public int anzVergleiche() {
        return comparisonCnt;
    }

    @Override
    public int anzahlSchreibzugriffe() {
        return iterationCnt;
    }
}