package Java;

/**
 * @author Jonas Rhbary
 * @version 1.0
 * @date 26.01.22
 */
public class BinaryTreeSort implements SorterInterface {

    private long duration;
    private long schreibzugriffe;
    private int comparisons;
    private int memory;
    private Node root;

    Runtime rt = Runtime.getRuntime();

    class Node {
        int key;
        Node left, right;
        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    public Node insertRec(Node root, int key) {
        comparisons++;
        if (root == null) {
            schreibzugriffe++;
            root = new Node(key);
            return root;
        }

        comparisons++;
        if (key < root.key) {
            schreibzugriffe++;
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            schreibzugriffe++;
            comparisons++;
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    public void inorderRec(Node root) {
        comparisons++;
        if (root != null) {
            inorderRec(root.left);
            inorderRec(root.right);
        }
    }

    public void treeins(int[] zahlen) {
        for (int zahl : zahlen) {
            schreibzugriffe++;
            insert(zahl);
        }
    }

    @Override
    public void sort(int[] zahlen) {
        schreibzugriffe = 0;
        comparisons = 0;
        memory = 0;

        long startTime = System.nanoTime();

        root = null;
        treeins(zahlen);
        inorderRec(root);

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
