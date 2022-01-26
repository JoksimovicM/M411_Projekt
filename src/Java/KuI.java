package Java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Marko Joksimovic
 * @version 1.0
 * @date
 */
public class KuI {

    public Scanner sc = new Scanner(System.in);
    int counter = 0;
    int[] zahlen;

    public static void main(String[] args) throws IOException {
        new KuI().run();
    }

    public void run() throws IOException {

        SorterInterface sorter;

        FileWriter fileWriter = new FileWriter("Messzahlen\\messzahlen2.csv");
        fileWriter.write("Sortieralgorithmus,Sortierdauer in ms,Speicherbedarf,Anzahl Vergleiche,Anzahl Schreibzugriffe\n");

        char auswahl = '-';
        while (auswahl != 'x') {
            if (!loadFile()) {
                printMenu();
                auswahl = sc.next().charAt(0);
                switch (auswahl) {
                    case '1':
                        /*sorter = new Quicksort();
                        sorter.sort(zahlen);
                        fileWriter.write("Quicksort,");
                        saveMesszahlen(sorter, fileWriter);*/
                        break;
                    case '2':
                        //sorter = new QuicksortRan();
                        break;
                    case '3':
                        /*sorter = new Shakersort();
                        sorter.sort(zahlen);
                        fileWriter.write("Shakersort,");
                        saveMesszahlen(sorter, fileWriter);*/
                        break;
                    case '4':
                        //sorter = new BinaryTreeSort();
                        break;
                    case '5':
                        //sorter = new Heapsort();
                        break;
                    case '6':
                        //sorter = new InsertionSort();
                        break;
                    case '7':
                        /*sorter = new Mergesort();
                        sorter.sort(zahlen);
                        fileWriter.write("Mergesort,");
                        saveMesszahlen(sorter,fileWriter);*/
                        break;
                    case 'x':
                        break;
                    default:
                        System.out.println("\nEingabe ist ungültig, bitte geben Sie eine gültige Zahl ein!\n");
                }
            } else {
                auswahl = 'x';
            }
        }
        fileWriter.close();
    }

    public void printMenu() {
        System.out.println("\nWählen Sie das gewünschte Sortierverfahren:\n[1]Quicksort mit Pivot am Anfang" +
                "\n[2]Quicksort mit random Pivot" +
                "\n[3]ShakerSort" +
                "\n[4]BinaryTreeSort" +
                "\n[5]Heapsort" +
                "\n[6]InsertionSort" +
                "\n[7]Mergesort" +
                "\n[x]Programm beenden");
        System.out.print(">");
    }

    public void saveMesszahlen(SorterInterface sorter, FileWriter fileWriter) {
        long duration = sorter.getTime();
        int speicherbedarf = sorter.getSpeicherbedarf();
        int anzVergleiche = sorter.anzVergleiche();
        long anzSwaps = sorter.anzahlSchreibzugriffe();
        try {
            fileWriter.write(Long.toString(duration) + ",");
            fileWriter.write(speicherbedarf + ",");
            fileWriter.write(anzVergleiche + ",");
            fileWriter.write(anzSwaps + "\n");
        } catch (Exception ex) {
        }
        System.out.println("Speicherbedarf: " + sorter.getSpeicherbedarf());
        System.out.println("Anzahl Schreibzugriffe: " + sorter.anzahlSchreibzugriffe());
        System.out.println("Anzahl Vergleiche: " + sorter.anzVergleiche());
        System.out.println("Sortier Dauer in ms: " + sorter.getTime() + "\n");
    }

    public boolean loadFile() {
        counter = 0;
        System.out.println("Wählen Sie die Datei:\n" +
                "[1]InversTeilsortiert\n" +
                "[2]Random\n" +
                "[3]Teilsortiert\n" +
                "[x]Programm beenden");
        System.out.print(">");
        String datei = sc.next();

        boolean beenden = false;

        switch (datei) {
            case "1":
                datei = "InversTeilsortiert";
                break;
            case "2":
                datei = "Random";
                break;
            case "3":
                datei = "Teilsortiert";
                break;
            case "x":
                beenden = true;
            default:
                break;
        }

        if (beenden) {
            return beenden;
        }

        System.out.println("\nWählen Sie die Dateigrösse:\n" +
                "[1]1000 Zahlen\n" +
                "[2]10000 Zahlen\n" +
                "[3]100000 Zahlen\n" +
                "[x]Programm beenden");
        System.out.print(">");
        String anzahlDaten = sc.next();
        int groesse = 0;


        switch (anzahlDaten) {
            case "1":
                groesse = 1000;
                zahlen = new int[1000];
                break;
            case "2":
                groesse = 10000;
                zahlen = new int[10000];
                break;
            case "3":
                groesse = 100000;
                zahlen = new int[100000];
                break;
            case "x":
                beenden = true;
            default:
                break;
        }

        if (beenden) {
            return beenden;
        }

        try {
            Scanner numberSc = new Scanner(new File("Data\\" + datei + groesse + ".dat"));
            while (numberSc.hasNextInt()) {
                zahlen[counter] = numberSc.nextInt();
                counter++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
