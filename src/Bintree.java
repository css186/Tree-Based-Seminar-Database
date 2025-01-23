//-------------------------------------------------------------------------
/**
* Bintree implementation
* 
* @author Guann-Luen Chen
* @version 2025.10.15
*/
public class Bintree {
    // ~ Fields ................................................................
    //
    // ----------------------------------------------------------
    private BintreeNode root;
    private int worldTopLeftX; 
    private int worldBotRightX; 
    private int worldTopLeftY;
    private int worldBotRightY;
    
    // ~ Constructors ..........................................................
    //
    // ----------------------------------------------------------
    /**
     * Instantiate bintree with boundaries
     * @param width
     *        Initial width of box
     * @param height
     *        Initial height of box
     */
    public Bintree(int width, int height) {
        this.root = EmptyNode.getEmptyNode();
        this.worldTopLeftX = 0;
        this.worldBotRightX = width - 1;
        this.worldTopLeftY = 0;
        this.worldBotRightY = height - 1;
    }
    
    // ~ Public Method ........................................................
    //
    // ----------------------------------------------------------
    /**
     * Insert method
     * @param seminar
     *        seminar object
     */
    public void insert(Seminar seminar) {
        root = root.insert(
            seminar, 
            worldTopLeftX, 
            worldBotRightX, 
            worldTopLeftY, 
            worldBotRightY);
    }
    
    // ----------------------------------------------------------
    /**
     * Delete method
     * @param seminar
     *        seminar object
     */
    public void delete(Seminar seminar) {
        root = root.delete(
            seminar, 
            worldTopLeftX, 
            worldBotRightX, 
            worldTopLeftY, 
            worldBotRightY);
    }
    
    // ----------------------------------------------------------
    /**
     * Search method
     * @param x
     *        x coordinate
     * @param y
     *        y coordinate
     * @param radius
     *        radius
     */
    public void search(int x, int y, int radius) {
        // Create containers to store found seminar
        // and numbers of node visited
        SemArray semFound = new SemArray(10);
        int[] visitedCount = {0};
        
        root.search(
            x, 
            y, 
            this.worldTopLeftX,
            this.worldBotRightX, 
            this.worldTopLeftY, 
            this.worldBotRightY, 
            radius, 
            semFound, 
            visitedCount);
        
        System.out.println(
            "Seminars within " + radius + 
            " units of " + x + ", " + y + ":");
        
        for (int i = 0; i < semFound.getSize(); i++) {
            Seminar found = semFound.getAt(i);
            
            System.out.println(
                "Found a record with key value " + 
                found.id() + " at " + found.x() + 
                ", " + found.y());
        }
        System.out.println(
            visitedCount[0] + 
            " nodes visited in this search");
    }
    
    // ----------------------------------------------------------
    /**
     * Print method
     */
    public void printTree() {
        int depth = root.computeDepth();
        root.print(depth, 0);
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for root
     * @return 
     *         the root
     */
    public BintreeNode getRoot() {
        return root;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for root
     * @param root 
     *        the root to set
     */
    public void setRoot(BintreeNode root) {
        this.root = root;
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for worldTopLeftX coordinate
     * @return 
     *         the worldTopLeftX
     */
    public int getTopLeftX() {
        return worldTopLeftX;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for worldTopLeftX coordinate
     * @param minX
     *        the worldTopLeftX to set
     */
    public void setTopLeftX(int minX) {
        this.worldTopLeftX = minX;
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for worldBotRightX coordinate
     * @return 
     *         the worldBotRightX
     */
    public int getBotRightX() {
        return worldBotRightX;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for worldBotRightX coordinate
     * @param maxX 
     *        the worldBotRightX to set
     */
    public void setBotRightX(int maxX) {
        this.worldBotRightX = maxX;
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for worldTopLeftY coordinate
     * @return 
     *         the worldTopLeftY
     */
    public int getTopLeftY() {
        return worldTopLeftY;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for worldTopLeftY coordinate
     * @param minY 
     *        the worldTopLeftY to set
     */
    public void setTopLeftY(int minY) {
        this.worldTopLeftY = minY;
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for worldBotRightY coordinate
     * @return the worldBotRightY
     */
    public int getBotRightY() {
        return worldBotRightY;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for worldBotRightY coordinate
     * @param maxY 
     *        the worldBotRightY to set
     */
    public void setBotRightY(int maxY) {
        this.worldBotRightY = maxY;
    }
    
}
