//-------------------------------------------------------------------------
/**
* An implementation of InternalNode class from BintreeNode
* 
* @author Guann-Luen Chen
* @version 2025.10.15
*/
public class InternalNode implements BintreeNode {
    // ~ Fields ................................................................
    //
    // ----------------------------------------------------------
    private BintreeNode leftChild;
    private BintreeNode rightChild;
    
    // ~ Constructors ..........................................................
    //
    // ----------------------------------------------------------
    /**
     * Internal Node with two empty children
     */
    public InternalNode() {
        this.leftChild = EmptyNode.getEmptyNode();
        this.rightChild = EmptyNode.getEmptyNode();
    }

    // ~ Public Method ........................................................
    //
    // ----------------------------------------------------------
    /**
     * Insert method for InternalNode
     */
    @Override
    public BintreeNode insert(
        Seminar newSeminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY) {
        // If the width of the world is larger than height
        // split on X axis
        if ((worldBotRightX - worldTopLeftX) >= 
            (worldBotRightY - worldTopLeftY)) {
            
            int midX = (worldTopLeftX + worldBotRightX) / 2;
            
            if (newSeminar.x() <= midX) {
                this.leftChild = this.leftChild.insert(
                    newSeminar, 
                    worldTopLeftX, 
                    midX, 
                    worldTopLeftY, 
                    worldBotRightY);
            }
            
            else {
                this.rightChild = this.rightChild.insert(
                    newSeminar, 
                    midX + 1, 
                    worldBotRightX, 
                    worldTopLeftY, 
                    worldBotRightY);
            }
        }
        // else, split on Y axis
        else {
            
            int midY = (worldTopLeftY + worldBotRightY) / 2;
            
            if (newSeminar.y() <= midY) {
                this.leftChild = this.leftChild.insert(
                    newSeminar, 
                    worldTopLeftX, 
                    worldBotRightX, 
                    worldTopLeftY, 
                    midY);
            }
            
            else {
                this.rightChild = this.rightChild.insert(
                    newSeminar, 
                    worldTopLeftX, 
                    worldBotRightX, 
                    midY + 1, 
                    worldBotRightY);
            }
        }
        return this;
    }
    
    // ----------------------------------------------------------
    /**
     * Delete method for Internal Node
     */
    @Override
    public BintreeNode delete(
        Seminar seminar, 
        int worldTopLeftX, 
        int worldBotRightX, 
        int worldTopLeftY, 
        int worldBotRightY) {
        // Same condition as insert
        if ((worldBotRightX - worldTopLeftX) >= 
            (worldBotRightY - worldTopLeftY)) {
            
            int midX = (worldTopLeftX + worldBotRightX) / 2;
            
            if (seminar.x() <= midX) {
                this.leftChild = this.leftChild.delete(
                    seminar, 
                    worldTopLeftX, 
                    midX, 
                    worldTopLeftY, 
                    worldBotRightY);
            } 
            
            else {
                this.rightChild = this.rightChild.delete(
                    seminar, 
                    midX + 1, 
                    worldBotRightX, 
                    worldTopLeftY, 
                    worldBotRightY);
            }
        }
        else {
            
            int midY = (worldTopLeftY + worldBotRightY) / 2;
            
            if (seminar.y() <= midY) {
                this.leftChild = this.leftChild.delete(
                    seminar, 
                    worldTopLeftX, 
                    worldBotRightX, 
                    worldTopLeftY, 
                    midY);
            }
            else {
                this.rightChild = this.rightChild.delete(
                    seminar, 
                    worldTopLeftX, 
                    worldBotRightX, 
                    midY + 1, 
                    worldBotRightY);
            }
        }
        // Shrinking condition checks
        if (this.leftChild instanceof EmptyNode && 
            this.rightChild instanceof EmptyNode) {
            return EmptyNode.getEmptyNode();
        }
        
        if (this.leftChild instanceof LeafNode && 
            this.rightChild instanceof EmptyNode) {
            return this.leftChild;
        }
        if (this.leftChild instanceof EmptyNode && 
            this.rightChild instanceof LeafNode) {
            return this.rightChild;
        }
        
        return this;
    }
    
    // ----------------------------------------------------------
    /**
     * Search method for internal node
     * 
     * search condition:
     * 
     * (box1TopLeftX <= box2BottomRightX && 
     * box1BottomRightX >= box2TopLeftX) && 
     * (box1TopLeftY <= box2BottomRightY && 
     * box1BottomRightY >= box2TopLeftY)
     * 
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
        
        int topLeftX = searchX - radius;
        int botRightX = searchX + radius;
        int topLeftY = searchY - radius;
        int botRightY = searchY + radius;
        
        if ((worldBotRightX - worldTopLeftX) >= 
            (worldBotRightY - worldTopLeftY)) {
            // Compare x axis
            int midX = (worldTopLeftX + worldBotRightX) / 2;
            
            // Search using normal pre-order (left -> right)
            if ((topLeftX <= midX && 
                botRightX >= worldTopLeftX) && 
                (topLeftY <= worldBotRightY) && 
                (botRightY >= worldTopLeftY)) {
                
                this.leftChild.search(
                    searchX, 
                    searchY, 
                    worldTopLeftX, 
                    midX, 
                    worldTopLeftY, 
                    worldBotRightY, 
                    radius, 
                    semFound, 
                    visitedCount);
            }
            
            if ((topLeftX <= worldBotRightX && 
                botRightX >= midX + 1) && 
                (topLeftY <= worldBotRightY) && 
                (botRightY >= worldTopLeftY)) {
                
                this.rightChild.search(
                    searchX, 
                    searchY, 
                    midX + 1, 
                    worldBotRightX, 
                    worldTopLeftY, 
                    worldBotRightY, 
                    radius, 
                    semFound, 
                    visitedCount);
            }
            
        }
        else {
            // y axis
            int midY = (worldTopLeftY + worldBotRightY) / 2;
            
            // Search using normal pre-order (left then right)
            if ((topLeftX <= worldBotRightX && 
                botRightX >= worldTopLeftX) && 
                (topLeftY <= midY) && 
                (botRightY >= worldTopLeftY)) {
                
                this.leftChild.search(
                    searchX, 
                    searchY, 
                    worldTopLeftX, 
                    worldBotRightX, 
                    worldTopLeftY, 
                    midY, 
                    radius, 
                    semFound, 
                    visitedCount);
            }
            
            if ((topLeftX <= worldBotRightX && 
                botRightX >= worldTopLeftX) &&
                (topLeftY <= worldBotRightY) && 
                (botRightY >= midY + 1)) {
                
                this.rightChild.search(
                    searchX, 
                    searchY, 
                    worldTopLeftX, 
                    worldBotRightX, 
                    midY + 1, 
                    worldBotRightY, 
                    radius, 
                    semFound, 
                    visitedCount);
            }
            
        }

    }
    
    // ----------------------------------------------------------
    /**
     * Print method for Internal Node
     */
    @Override
    public void print(int depth, int level) {
        String spaces = "    ".repeat(depth - level);
        System.out.println(spaces + "(I)");
        // Print using adjusted pre-order (right then left)
        rightChild.print(depth, level + 1);
        leftChild.print(depth, level + 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Method to compute current depth of Internal Node
     */
    @Override
    public int computeDepth() {
        return 1 + Math.max(
            leftChild.computeDepth(), 
            rightChild.computeDepth());
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for left child
     * @return 
     *         the leftChild
     */
    public BintreeNode getLeftChild() {
        return leftChild;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for left child
     * @param leftChild 
     *        the leftChild to set
     */
    public void setLeftChild(BintreeNode leftChild) {
        this.leftChild = leftChild;
    }

    // ----------------------------------------------------------
    /**
     * Getter for right child
     * @return 
     *         the rightChild
     */
    public BintreeNode getRightChild() {
        return rightChild;
    }

    // ----------------------------------------------------------
    /**
     * Setter for right child
     * @param rightChild 
     *        the rightChild to set
     */
    public void setRightChild(BintreeNode rightChild) {
        this.rightChild = rightChild;
    }
    
}
