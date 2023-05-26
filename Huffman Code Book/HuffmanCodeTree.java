// Artemis Pearson
public class HuffmanCodeTree {
    private HuffmanNode root;

    /**
     * Creates a new HuffmanCodeTree given a root node.
     * @param root The root node.
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * Creates a new HuffmanCodeTree given a HuffmanCodeBook. Assumes an Iterable HuffmanCodeBook.
     * @param codeBook The HuffmanCodeBook to base the HuffmanCodeTree on.
     */
    public HuffmanCodeTree(HuffmanCodeBook codeBook) {
        root = new HuffmanNode();
        for (Character c:
             codeBook) {
            put(codeBook.getSequence(c), c);
        }
    }

    /**
     * Checks if the tree is valid.
     * @return If the tree is valid.
     */
    public boolean isValid() {
        return root.isValid();
    }

    /**
     * Adds a new leaf node with given data at the node in the tree corresponding to the BinarySequence.
     * @param seq The BinarySequence corresponding to the spot to put the char.
     * @param letter The char to add to the tree.
     */
    public void put(BinarySequence seq, char letter) {
        HuffmanNode node = root;
        for (boolean b:
             seq) {
            if (b) {
                if (node.getOne() == null) {
                    node.setOne(new HuffmanNode());
                }
                node = node.getOne();
            } else {
                if (node.getZero() == null) {
                    node.setZero(new HuffmanNode());
                }
                node = node.getZero();
            }
        }
        node.setData(letter);
    }

    /**
     * Decodes a BinarySequence into a String.
     * @param s The sequence to decode.
     * @return A String representing the decoded BinarySequence.
     */
    public String decode(BinarySequence s) {
        StringBuilder sb = new StringBuilder();
        HuffmanNode node = root;
        for (boolean b:
             s) {
            if (b) {
                node = node.getOne();
            } else {
                node = node.getZero();
            }
            if (node.isLeaf()) {
                sb.append(node.getData());
                node = root;
            }
        }
        return sb.toString();
    }
}
