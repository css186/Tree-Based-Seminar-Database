//-------------------------------------------------------------------------
/**
 * BintreeNode interface
 * 
 * @author Guann-Luen Chen
 * @version 2025.10.15
 */
public interface BintreeNode {
    // ~ Abstract Method ........................................
    //
    // ----------------------------------------------------------
    /**
     * Insert method
     * @param seminar
     *        Seminar Object
     * @param worldTopLeftX
     *        X coordinate at top left
     * @param worldBotRightX
     *        X coordinate at bottom right
     * @param worldTopLeftY
     *        Y coordinate at top left
     * @param worldBotRightY
     *        Y coordinate at bottom right
     * @return
     *        BintreeNode
     */
    BintreeNode insert(
        Seminar seminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY);
    
    // ----------------------------------------------------------
    /**
     * Delete method
     * @param seminar
     *        Seminar Object
     * @param worldTopLeftX
     *        X coordinate at top left
     * @param worldBotRightX
     *        X coordinate at bottom right
     * @param worldTopLeftY
     *        Y coordinate at top left
     * @param worldBotRightY
     *        Y coordinate at bottom right
     * @return
     *        BintreeNode
     */
    BintreeNode delete(
        Seminar seminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY);
    
    // ----------------------------------------------------------
    /**
     * Search method
     * @param searchX
     *        X coordinate to search
     * @param searchY
     *        Y coordinate to search
     * @param worldTopLeftX
     *        X coordinate at top left
     * @param worldBotRightX
     *        X coordinate at bottom right
     * @param worldTopLeftY
     *        Y coordinate at top left
     * @param worldBotRightY
     *        Y coordinate at bottom right
     * @param radius
     *        radius to search
     * @param semFound
     *        Seminars found
     * @param visitedCount
     *        Numbers of node visited
     */
    void search(
        int searchX, 
        int searchY, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY, 
        int radius, 
        SemArray semFound, 
        int[] visitedCount);
    
    // ----------------------------------------------------------
    /**
     * Print method
     * @param depth
     *        Depth of node
     * @param level
     *        Level of node
     */
    void print(int depth, int level);
    
    // ----------------------------------------------------------
    /**
     * Compute depth
     * @return
     *         depth of node
     */
    int computeDepth();
}
