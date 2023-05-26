// Artemis Pearson
import java.util.Iterator;

public class HuffmanCodeBook implements Iterable<Character>{
    private HuffmanHashNode[] hashMap = new HuffmanHashNode[7];
    private int size;

    /**
     * Create a new HuffmanCodeBook
     */
    public HuffmanCodeBook() {
        for (HuffmanHashNode n:
                hashMap) {
            n = null;
        }
        size = 0;
    }

    /**
     * Adds a sequence paired to a character
     * @param letter The character to pair the sequence to.
     */
    public void addSequence(Character letter, BinarySequence seq) {
        int hash = getHash(letter);
        if (!contains(letter)) {
            hashMap[hash] = new HuffmanHashNode(letter, seq, hashMap[hash]);
            size++;
            recalculateTableSize();
        }
    }

    /**
     * Gets the hash index of the Character
     * @param data The Character to get the hash index of
     * @return The hash index of the Character
     */
    private int getHash(Character data) {
        return data.hashCode() % hashMap.length;
    }

    /**
     * Gets the hash index of the Character using a new array size
     * @param data The Character to get the hash index of
     * @param length The new array length.
     * @return The hash index of the Character
     */
    private int getNewHash(Character data, int length) {
        return data.hashCode() % length;
    }

    /**
     * Calculates the load and resizes the table if needed (by size * 2 + 1)
     */
    private void recalculateTableSize() {
        double load = (double) size / hashMap.length;
        if (load >= 0.75) {
            HuffmanHashNode[] newHashMap = new HuffmanHashNode[hashMap.length * 2 + 1];
            for (Character c:
                 this) {
                int h = getNewHash(c, newHashMap.length);
                newHashMap[h] = new HuffmanHashNode(c, getSequence(c), newHashMap[h]);
            }
            hashMap = newHashMap;
        }
    }

    /**
     * Finds the binary sequence of the correlated Character.
     * @param c The Character to check for the sequence of.
     * @return The binary sequence of the correlated Character or null if it doesn't exist.
     */
    public BinarySequence getSequence(Character c) {
        int hash = getHash(c);
        HuffmanHashNode node = hashMap[hash];
        while (node != null) {
            if (node.getData().equals(c)) {
                return node.getBS();
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * Checks if the HuffmanCodeBook contains a specific char
     * @param letter the char to check for
     * @return If the char is contained
     */
    public boolean contains(Character letter) {
        int h = getHash(letter);
        HuffmanHashNode node = hashMap[h];
        while (node != null) {
            if (node.getData().equals(letter)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    /**
     * Checks if the HuffmanCodeBook contains all chars in a String
     * @param letters The String of chars to check
     * @return If all the chars are contained
     */
    public boolean containsAll(String letters) {
        for (Character letter:
                letters.toCharArray()) {
            if (!contains(letter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Encodes a String into a BinarySequence
     * @param s The String to encode
     * @return The encoded BinarySequence
     */
    public BinarySequence encode(String s) {
        BinarySequence bs = new BinarySequence();
        for (Character c :
                s.toCharArray()) {
            bs.append(getSequence(c));
        }
        return bs;
    }

    /**
     * Gets the number of characters in the code book.
     * @return the size of the code book
     */
    public int getSize() {
        return this.size;
    }
    // toString from testing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{|");
        for (int i = 0; i < hashMap.length; i++) {
            HuffmanHashNode node = hashMap[i];
            sb.append(i).append(": ");
            while (node != null) {
                sb.append(node.getData()).append(' ');
                node = node.getNext();
            }
            sb.append('|');
        }
        sb.append('}');
        return sb.toString();
    }

    /**
     * Creates a new Character Iterator
     * @return An Iterator
     */
    public Iterator<Character> iterator() {
        return new CodeBookIter();
    }

    private class CodeBookIter implements Iterator<Character> {
        private HuffmanHashNode node;
        private int index;
        private int checkedElem;

        /**
         * Sets up the Iterator.
         */
        public CodeBookIter() {
            index = 0;
            node = hashMap[index];
            checkedElem = 0;
        }

        /**
         * Checks if there's another Character.
         * @return If there is another Character.
         */
        @Override
        public boolean hasNext() {
            return checkedElem < size;
        }

        /**
         * Gets the next Character
         * @return The next Character
         */
        public Character next() {
            Character retVal = null;
            while(node == null) {
                index++;
                node = hashMap[index];
            }
            retVal = node.getData();
            checkedElem++;
            node = node.getNext();
            return retVal;
        }
    }
    
    // The node for the hash version of the HuffmanCodeBook
    public class HuffmanHashNode {
        private Character data;
        private BinarySequence bs;
        private HuffmanHashNode node;

        public HuffmanHashNode(Character data, BinarySequence seq) {
            this.data = data;
            bs = seq;
            node = null;
        }

        public HuffmanHashNode(Character data, BinarySequence seq, HuffmanHashNode node) {
            this.data = data;
            bs = seq;
            this.node = node;
        }

        public Character getData() {
            return data;
        }

        public void setData(Character data) {
            this.data = data;
        }

        public HuffmanHashNode getNext() {
            return node;
        }

        public void setNext(HuffmanHashNode node) {
            this.node = node;
        }

        public BinarySequence getBS() {
            return bs;
        }

        public void setBS(BinarySequence bs) {
            this.bs = bs;
        }
    }

}
