package Java;

public class Gnomesorter implements SorterInterface{
    int iterationCnt = 0; //Anz. Durchgänge durch while-Schleife
    int compCnt = 0;   //Anz. benötigte Vergleiche
    int timeCnt = 0;
    int memoryCnt = 0;


    public Gnomesorter() {
    }

    /**
     * Die Methode sortiert das übergebene Array aufsteigend, indem sie jeweils  von links nach rechts zwei aufeinanderfolgende,
     * vom Counter definierte Werte vergleicht. Wenn diese in der richtigen Reihenfolge sind, bewegt sich der Counter
     * einen Schritt nach rechts und vergleicht dort erneut. Falls die zwei verglichenen Werte jedoch in falscher
     * Reihenfolge stehen, werden diese getauscht und der Counter bewegt sich nach links und vergleicht dort wieder von neuem.
     *
     * @param
     * @return int[] gnomesortMessungen
     */

    @Override
    public void sort(int[] zahlen) {
        double[] messArray = new double[4]; //[0]:iterationCntr, [1]:compCntr, [2]:timeCntr, [3]: memoryCntr

        int cntr = 0;
        int temp;

        double timeStart = System.nanoTime();

        while (cntr < zahlen.length - 1) {
            iterationCnt++;

            compCnt++;
            if (zahlen[cntr + 1] < zahlen[cntr]) {

                //swap Beginn
                temp = zahlen[cntr];
                zahlen[cntr] = zahlen[cntr + 1];
                zahlen[cntr + 1] = temp;
                //swap Ende

                compCnt++;
                if (cntr > 0) {
                    cntr--;
                } else {
                    cntr++;
                }
            } else {
                cntr++;
            }

        }

        double timeEnd = System.nanoTime();
        timeCnt = (int) (timeEnd - timeStart);

        memoryCnt = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());


    }

    @Override
    public int getSpeicherbedarf() {
        return memoryCnt;
    }

    @Override
    public long getTime() {
        return timeCnt;
    }

    @Override
    public int anzVergleiche() {
        return iterationCnt;
    }

    @Override
    public int anzahlSchreibzugriffe() {
        return compCnt;
    }
}
