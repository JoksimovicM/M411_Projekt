package Java;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author Marko Joksimovic
 * @version 1.0
 * @date
 */
public class KuI2 {

    SorterInterface[] sorter = new SorterInterface[7];
    int counter = 0;
    int[] zahlen;
    String[] klassen = {"Quicksort","QuicksortRandom","Shakersort","Mergesort","Heapsort","InsertionSort","BinaryTreeSort"};

    /**
     * Die main-Methode ruft die run-Methode auf
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        new KuI2().run();
    }

    /**
     * In der run-Methode läuft das gesamte Programm.
     * @throws Exception
     */
    public void run() throws Exception {
        FileWriter fileWriter = new FileWriter("Messzahlen\\messzahlen.csv");
        fileWriter.write("File,Sortieralgorithmus,Sortierdauer in ms,Speicherbedarf in bytes,Anzahl Vergleiche,Anzahl Schreibzugriffe\n");

        initSorter();

        File folder = new File("Data/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                setZahlenGroesse(file);
                runSorters(fileWriter,file);
            }
        }
        binaryTreeSort(listOfFiles,fileWriter);

        fileWriter.close();
    }

    /**
     * initSorter initialisiert alle Sortieralgorithmen
     */
    public void initSorter() {
        sorter[0] = new Quicksort();
        sorter[1] = new QuicksortRan();
        sorter[2] = new Shakersort();
        sorter[3] = new Mergesort();
        sorter[4] = new Heapsort();
        sorter[5] = new InsertionSort();
        sorter[6] = new BinaryTreeSort();
    }

    /**
     * setZahlenGroesse bestimmt die int[] Grösse für die zu sortierenden Zahlen
     * @param file
     */
    public void setZahlenGroesse(File file) {
        if (file.getName().matches("[A-Z]+[a-z]*[A-Z]?[a-z]*[1]+[0]?[0]?[0]?\\.+[a-z]*")) {
            zahlen = new int[1000];
        } else if (file.getName().matches("[A-Z]+[a-z]*[A-Z]?[a-z]*[1]+[0]?[0]?[0]?[0]?\\.+[a-z]*")) {
            zahlen = new int[10000];
        } else if (file.getName().matches("[A-Z]+[a-z]*[A-Z]?[a-z]*[1]+[0]?[0]?[0]?[0]?[0]?\\.+[a-z]*")) {
            zahlen = new int[100000];
        }
    }

    /**
     * runSorters führt alle Sortieralgorithmen nacheinander in einem Loop aus.
     * Da die Benutzung vom BinaryTreeSort zu einer StackOverflowException führt, wird dieser separat ausgeführt.
     * @param fileWriter
     * @param file
     * @throws Exception
     */
    public void runSorters(FileWriter fileWriter, File file) throws Exception{
        for (int i = 0; i < sorter.length - 1; i++) {
            Scanner numberSc = new Scanner(file);
            while (numberSc.hasNextInt()) {
                zahlen[counter] = numberSc.nextInt();
                counter++;
            }
            counter = 0;
            sorter[i].sort(zahlen);
            fileWriter.write(file.getName() + "," + klassen[i] + "," + sorter[i].getTime() + "," + sorter[i].getSpeicherbedarf() + "," +
                    sorter[i].anzVergleiche() + "," + sorter[i].anzahlSchreibzugriffe() + "\n");
        }
    }

    /**
     * binaryTreeSort wendet den BinaryTree-Algorithmus auf alle Files an.
     * @param listOfFiles
     * @param fileWriter
     * @throws Exception
     */
    public void binaryTreeSort(File[] listOfFiles, FileWriter fileWriter) throws Exception {
        for (File file : listOfFiles) {
            if (file.isFile()) {
                setZahlenGroesse(file);
                Scanner numberSc = new Scanner(file);
                while (numberSc.hasNextInt()) {
                    zahlen[counter] = numberSc.nextInt();
                    counter++;
                }
                sorter[6].sort(zahlen);
                fileWriter.write(file.getName() + "," + klassen[6] + "," + sorter[6].getTime() + "," + sorter[6].getSpeicherbedarf() + "," +
                        sorter[6].anzVergleiche() + "," + sorter[6].anzahlSchreibzugriffe() + "\n");
                counter = 0;
                zahlen = null;
            }
        }
    }
}
