package Java;

/**
 * @author Marko Joksimovic
 * @version 1.0
 * @date
 */
public interface SorterInterface {

    public void sort(int[] zahlen);

    public int getSpeicherbedarf();

    public long getTime();

    public int anzVergleiche();

    public int anzahlSchreibzugriffe();
}
