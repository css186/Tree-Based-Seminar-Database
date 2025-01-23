
//-------------------------------------------------------------------------
/**
 * An implementation of LeafNode class from BintreeNode
 * 
 * @author Guann-Luen Chen
 * @version 2025.10.15
 */
public class LeafNode implements BintreeNode {
    // ~ Fields ................................................................
    //
    // ----------------------------------------------------------
    private SemArray semArray;
    
    // ~ Constructors ..........................................................
    //
    // ----------------------------------------------------------
    /**
     * LeafNode constructor, instantiated by a seminar object
     * 
     * @param seminar
     *        seminar object
     */
    public LeafNode(Seminar seminar) {
        this.semArray = new SemArray(10);
        this.semArray.addInOrder(seminar);
    }
    
    // ~ Default Method ........................................................
    //
    // ----------------------------------------------------------
    /**
     * Method to split node when data stored in leaf exceeds capacity
     * @param newSeminar
     *        Seminar object to insert
     * @param worldTopLeftX
     *        x coordinate at top left
     * @param worldBotRightX
     *        x coordinate at bottom right
     * @param worldTopLeftY
     *        y coordinate at top left
     * @param worldBotRightY
     *        y coordinate at bottom right
     * @return
     *        Internal Node
     */
    BintreeNode splitNode(
        Seminar newSeminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY) {
        // Create a new internal node      
        BintreeNode internalNode = new InternalNode();
        
        // Insert all objects stored on the leaf node
        for (int i = 0; i < this.semArray.getSize(); i++) {      
            Seminar s = this.semArray.getAt(i);            
            internalNode = internalNode.insert(
                s, 
                worldTopLeftX, 
                worldBotRightX, 
                worldTopLeftY, 
                worldBotRightY);
        }
        // Insert the new object 
        internalNode = internalNode.insert(
            newSeminar, 
            worldTopLeftX, 
            worldBotRightX, 
            worldTopLeftY, 
            worldBotRightY);
        return internalNode;
    }
    
    // ~ Public Method ........................................................
    //
    // ----------------------------------------------------------
    /**
     * Insert method for LeafNode
     */
    @Override
    public BintreeNode insert(
        Seminar newSeminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY) {
        // Get the first object to compare 
        // since all elements will have the same
        // location (x, y)
        Seminar currSeminar = this.semArray.getAt(0);
        
        // Check if the coordinates are the same; if so, don't split
        // Just add into the container in ID order and return
        // the reference of current leaf node
        if (newSeminar.x() == currSeminar.x() && 
            newSeminar.y() == currSeminar.y()) {
            this.semArray.addInOrder(newSeminar);
            return this;
        }
        else {
            return splitNode(
                newSeminar, 
                worldTopLeftX,
                worldBotRightX,
                worldTopLeftY, 
                worldBotRightY);
        }
    }

    // ----------------------------------------------------------
    /**
     * Delete method for LeafNode
     */
    @Override
    public BintreeNode delete(
        Seminar seminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY) {
        // Remove object from the container
        this.semArray.remove(seminar);
        // If there is nothing left, return the empty node
        if (this.semArray.isEmpty()) {
            return EmptyNode.getEmptyNode();
        }
        return this;
    }

    // ----------------------------------------------------------
    /**
     * Search method for LeafNode
     */
    @Override
    public void search(
        int searchX, 
        int searchY, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY, 
        int radius, 
        SemArray semFound, 
        int[] visitedCount) {
        visitedCount[0]++;
        
        Seminar result = this.semArray.getAt(0);
        int disX = result.x() - searchX;
        int disY = result.y() - searchY;
        // Compute the range of the search
        if (disX * disX + disY * disY <= radius * radius) {
            for (int i = 0; i < this.semArray.getSize(); i++) {
                semFound.addTail(this.semArray.getAt(i));
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * Print method for LeafNode
     */
    @Override
    public void print(int depth, int level) {
        String spaces = "    ".repeat(depth - level);
        
        System.out.print(
            spaces + 
            "(Leaf with " + this.semArray.getSize() + 
            " objects: ");
        
        for (int i = 0; i < this.semArray.getSize(); i++) {
            System.out.print(this.semArray.getAt(i).id());
            if (i < this.semArray.getSize() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println(")");

    }

    // ----------------------------------------------------------
    /**
     * Method to compute current depth of LeafNode
     */
    @Override
    public int computeDepth() {
        return 0;
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for seminar array
     * @return 
     *         the semArray
     */
    public SemArray getSemArray() {
        return semArray;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for seminar array
     * @param semArray 
     *        the semArray to set
     */
    public void setSemArray(SemArray semArray) {
        this.semArray = semArray;
    }
    
}