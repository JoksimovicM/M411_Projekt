package Java;

public class insertionSorter implements SorterInterface {
    int iterationCnt = 0;
    int comparisonCnt = 0;
    int timeCompletion = 0;
    int memoryCnt = 0;

    public insertionSorter(){
    }

    public void sort(int arr[]){



        double timeStart = System.nanoTime();

        int n = arr.length;
        comparisonCnt++;
        for (int i = 1; i < n; ++i) {
            iterationCnt++;
            comparisonCnt++;
            int key = arr[i];
            int j = i - 1;

            comparisonCnt++;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                iterationCnt++;
                comparisonCnt++;
            }
            arr[j + 1] = key;

        }
        double timeEnd =  System.nanoTime();
        timeCompletion = (int) (timeEnd - timeStart);

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
        return iterationCnt;
    }

    @Override
    public int anzahlSchreibzugriffe() {
        return comparisonCnt;
    }
}
