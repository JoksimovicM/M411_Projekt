package Java;

/**
 * beinhaltet die Methode sortQuick(), die für die Aufbereitung der Parameter, für quickSort(), und Rückgabe der Messwerte verantwortlich ist
 * quickSort() ist rekursiv, deshalb muss man das Messungen-Array ausserhalb der Methode deklarieren
 *
 */

public class Quicksort implements SorterInterface{

    int iterationCnt = 0;
    int comparisonCnt = 0;
    int timeCompletion = 0;
    int memoryCnt = 0;

    public Quicksort(){

    }


    /**
     * Bereitet die Daten für quickSort vor
     *
     * @param zahlen
     * @return dobule[] quicksortMessungen
     */
    
    public void sort(int[] zahlen){
        double timeCnt = System.nanoTime();

        quickSort(0,zahlen.length - 1,zahlen);

        timeCompletion = (int) (System.nanoTime() - timeCnt);
        memoryCnt = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

    }



    /**
     * Sortiert die Zahlen rekursiv, der Output ist in aufsteigender Reihenfolge
     *
     * @param leftIndex
     * @param rightIndex
     * @param arr
     */
    private void quickSort(int leftIndex, int rightIndex, int[] arr){

        //schaut, ob es  bereits sortiert ist
        comparisonCnt++;
        if (leftIndex >= rightIndex){
            return;
        }

        //ACHTUNG es wird zwischen pos/index unterschieden
        int leftPos = leftIndex;
        int rightPos = rightIndex - 1;

        int pivot = arr[rightIndex];

        do {
            //wegen do-while schleife
            iterationCnt++;
            comparisonCnt++;

            comparisonCnt++; //Schleifen machen +1, wenn sie hinein/hinaus gehen (ansichtsweise)
            while (arr[leftPos] <= pivot && leftPos < rightIndex){
                iterationCnt++;
                comparisonCnt++;

                leftPos++;
            }
            comparisonCnt++;
            while (arr[rightPos] >= pivot && rightPos > leftIndex){
                iterationCnt++;
                comparisonCnt++;

                rightPos--;
            }
            comparisonCnt++;
            if (leftPos < rightPos){

                int tmp = arr[leftPos];

                arr[leftPos] = arr[rightPos];
                arr[rightPos] = tmp;
            }
        }
        while (leftPos < rightPos);

        comparisonCnt++;
        if (arr[leftPos] > pivot){
            int tmp = arr[leftPos];

            arr[leftPos] = arr[rightIndex];
            arr[rightIndex] = tmp;
        }


        quickSort(leftIndex,leftPos - 1, arr);
        quickSort(leftPos + 1,rightIndex, arr);

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
