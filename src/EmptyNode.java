//-------------------------------------------------------------------------
/**
 * An implementation of EmptyNode class from BintreeNode
 * 
 * @author Guann-Luen Chen
 * @version 2025.10.15
 */
public class EmptyNode implements BintreeNode {
    // ~ Fields ................................................................
    //
    // ----------------------------------------------------------
    // create instance using static final
    private static EmptyNode emptyNode = new EmptyNode();
    
    // ~ Constructors ..........................................................
    //
    // ----------------------------------------------------------
    /**
     * Private constructor to ensure only once instance is created
     */
    private EmptyNode() {
        
    }
    
    // ~ Public Method ........................................................
    //
    // ----------------------------------------------------------
    /**
     * Static method to get the emptyNode object
     * @return
     *         EmptyNode object
     */
    public static EmptyNode getEmptyNode() {
        return emptyNode;
    }

    // ----------------------------------------------------------
    /**
     * Insert method for EmptyNode
     */
    @Override
    public BintreeNode insert(
        Seminar seminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY) {
        return new LeafNode(seminar);
    }
    
    // ----------------------------------------------------------
    /**
     * Delete method for EmptyNode
     */
    @Override
    public BintreeNode delete(
        Seminar seminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY) {
        return this;
    }
    
    // ----------------------------------------------------------
    /**
     * Search method for EmptyNode
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
    }
    
    // ----------------------------------------------------------
    /**
     * Print method for EmptyNode
     */
    @Override
    public void print(int depth, int level) {
        String spaces = "    ".repeat(depth - level);
        System.out.println(spaces + "(E)");
    }
    
    // ----------------------------------------------------------
    /**
     * Method to compute current depth of EmptyNode
     */
    @Override
    public int computeDepth() {
        return 0;
    }   
}