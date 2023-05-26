// Artemis Pearson

public class HuffmanNode {
    private HuffmanNode zero;
    private HuffmanNode one;
    private Character data;

    /**
     * Creates a new leaf node.
     * @param data The data of the node.
     */
    public HuffmanNode(Character data) {
        this.data = data;
    }

    /**
     * Creates a new internal node.
     * @param zero The zero side child.
     * @param one The one side child.
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
    }

    /**
     * Creates an empty node.
     */
    public HuffmanNode() {
        // Keep all null to create an empty node
    }

    /**
     * Gets the zero side child.
     * @return The zero side child node.
     */
    public HuffmanNode getZero() {
        return zero;
    }

    /**
     * Sets the zero side child.
     * @param zero The node to set the zero side child to.
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * Gets the one side child.
     * @return The one side child node.
     */
    public HuffmanNode getOne() {
        return one;
    }

    /**
     * Sets the one side child
     * @param one The node to set the one side child to.
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * Gets the data of the node.
     * @return A Character representing the data of the node.
     */
    public Character getData() {
        return data;
    }

    /**
     * Sets the data of the node.
     * @param data The data to set the node data to
     */
    public void setData(Character data) {
        this.data = data;
    }

    /**
     * Checks if a node is a leaf using whether is has data.
     * @return If the node is a leaf or not
     */
    public boolean isLeaf() {
        return data != null;
    }

    /**
     * Checks if the node and it's descendants are valid.
     * @return If the tree starting at the node is valid.
     */
    public boolean isValid() {
        if (zero == null || one == null) {
            return data != null && (zero == null && one == null);
        } else {
            return data == null && zero.isValid() && one.isValid();
        }
    }
}
