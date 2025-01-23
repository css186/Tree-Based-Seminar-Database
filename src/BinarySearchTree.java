//-------------------------------------------------------------------------
/**
 * Binary Search Tree class
 * @param <T>
 *        Any type of data
 *        
 * @author Guann-Luen Chen
 * @version 2025.09.24
 *        
 */
public class BinarySearchTree<T extends Comparable<T>> {
    // ~ Inner Class.....................................................
    //
    // ----------------------------------------------------------
    /**
     * Tree Node class
     */
    class TreeNode {
        // ~ Fields.....................................................
        //
        // ----------------------------------------------------------
        private T key;
        private Seminar value;
        private TreeNode left;
        private TreeNode right;
        
        // ~ Constructor.................................................
        //
        // ----------------------------------------------------------
        /**
         * Instantiate tree node object
         * @param key
         *        The key
         * @param value
         *        Seminar objects
         */
        public TreeNode(T key, Seminar value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
        
        // ~ public method...............................................
        //
        // ----------------------------------------------------------
        
        // ----------------------------------------------------------
        /**
         * Getter for left child
         * @return
         *         left child
         */
        public TreeNode getLeft() {
            return this.left;
        }
        
        // ----------------------------------------------------------
        /**
         * Setter for left child
         * @param left 
         *        The left to set
         */
        public void setLeft(TreeNode left) {
            this.left = left;
        }
        
        // ----------------------------------------------------------
        /**
         * Getter for right child
         * @return
         *         right child
         */
        public TreeNode getRight() {
            return this.right;
        }
        
        // ----------------------------------------------------------       
        /**
         * Setter for right child
         * @param right 
         *        The right to set
         */
        public void setRight(TreeNode right) {
            this.right = right;
        }
        
        // ----------------------------------------------------------
        /**
         * Method to check if left child exists
         * @return
         *         true if left child exists
         */
        public boolean hasLeft() {
            return this.left != null;
        }
        
        // ----------------------------------------------------------
        /**
         * Method to check if right child exists
         * @return
         *         true if right child exists
         */
        public boolean hasRight() {
            return this.right != null;
        }
        
        // ----------------------------------------------------------
        /**
         * Method to check if this node is a leaf node
         * @return
         *         true if this node is a leaf node
         */
        public boolean isLeaf() {
            return !this.hasLeft() && !this.hasRight();
        }
        
        // ----------------------------------------------------------
        /**
         * Getter for key
         * @return
         *        The key
         */
        public T getKey() {
            return this.key;
        }
        
        // ----------------------------------------------------------
        /**
         * Setter for key
         * @param key 
         *        The key to set
         */
        public void setKey(T key) {
            this.key = key;
        }
        
        // ----------------------------------------------------------
        /**
         * Getter for value
         * @return 
         *         The value
         */
        public Seminar getValue() {
            return value;
        }
        
        // ----------------------------------------------------------
        /**
         * Setter for value
         * @param value 
         *        The value to set
         */
        public void setValue(Seminar value) {
            this.value = value;
        }
        
    }
    
    // ~ Fields.....................................................
    //
    // ----------------------------------------------------------
    private TreeNode root;
    private int nodeCount;
    private int traverseCount;
    
    // ~ Constructor.................................................
    //
    // ----------------------------------------------------------
    /**
     * Instantiate the binary search tree
     */
    public BinarySearchTree() {
        this.root = null;
        this.nodeCount = 0;
        this.traverseCount = 0;
    }
    // ~ default method..............................................
    //
    // ----------------------------------------------------------
    /**
     * Method to find the maximum tree node
     * @param node
     *        Root node
     * @return
     *        The max node
     */
    TreeNode findMaxNode(TreeNode node) {
        TreeNode curr = node;
        while (curr.hasRight()) {
            curr = curr.getRight();
        }
        return curr;
    }
    // ----------------------------------------------------------
    /**
     * Helper for the insert method
     * @param node
     *        Root node
     * @param key
     *        Key to be inserted
     * @param value
     *        Value to be inserted
     * @return
     *        Root node
     */
    TreeNode insertHelper(TreeNode node, T key, Seminar value) {
        if (node == null) {
            return new TreeNode(key, value);
        }
        
        int comparison = key.compareTo(node.getKey());
        
        // Duplicates are allowed and should be placed on the left tree
        if (comparison <= 0) {
            node.setLeft(insertHelper(node.getLeft(), key, value));
        }
        else if (comparison > 0) {
            node.setRight(insertHelper(node.getRight(), key, value));
        }
        return node;
    }
    // ----------------------------------------------------------
    /**
     * Helper for the delete method
     * @param node
     *        Root node
     * @param key
     *        The key to delete
     * @param seminar
     *        Seminar to delete
     * @return
     *        Root node
     */
    TreeNode deleteHelper(TreeNode node, T key, Seminar seminar) {
        if (node == null) {
            return null;
        }
        
        int comparison = key.compareTo(node.getKey());
        
        if (comparison < 0) {
            node.setLeft(this.deleteHelper(node.getLeft(), key, seminar));
        }
        else if (comparison > 0) {
            node.setRight(this.deleteHelper(node.getRight(), key, seminar));
        }
        else {
            // Check if two object is the same or not 
            if (node.getValue() != seminar) {
                node.setLeft(this.deleteHelper(node.getLeft(), key, 
                    seminar));
            }
            else {
                // Case with deleting node have two children
                if (!node.hasLeft()) {
                    return node.getRight();
                }
                else if (!node.hasRight()) {
                    return node.getLeft();
                }
                else {
                    TreeNode maxNode = findMaxNode(node.getLeft());
                    node.setKey(maxNode.getKey());
                    node.setValue(maxNode.getValue());
                    node.setLeft(this.deleteHelper(
                        node.getLeft(), maxNode.getKey(), maxNode.getValue()));
                }
            }
        }
        return node;
    }
    // ----------------------------------------------------------
    /**
     * Helper for searching single key
     * @param node
     *        Root node
     * @param key
     *        The key to be searched
     * @param arr
     *        Seminar Array
     * @return
     *        Seminar Array
     */
    SemArray searchSingleHelper(TreeNode node, T key, SemArray arr) {    
        // Increment traverse count
        this.traverseCount++;
        
        if (node == null) {
            return arr;
        }
        
        int comparison = key.compareTo(node.getKey());
        
        if (comparison < 0) {
            return this.searchSingleHelper(node.getLeft(), key, arr);
        }
        // if key is duplicated
        else if (comparison == 0) {
            this.searchSingleHelper(node.getLeft(), key, arr);
            arr.addTail(node.getValue());
            return arr;
        }
        
        else {
            return this.searchSingleHelper(node.getRight(), key, arr);
        }
        
    }
    
    // ----------------------------------------------------------
    /**
     * Helper method for range search
     * @param node
     *        Root node
     * @param lowerKey
     *        Lower bound of search key
     * @param upperKey
     *        Upper bound of search key
     * @param arr
     *        Seminar array
     * @return
     *        Seminar array
     */
    SemArray rangeHelper(TreeNode node, T lowerKey, 
        T upperKey, SemArray arr) {
        // Increment traverse count
        this.traverseCount++;
        
        if (node == null) {
            return arr;
        }
        // Left of lower bound
        if (lowerKey.compareTo(node.getKey()) <= 0) {
            this.rangeHelper(node.getLeft(), lowerKey, upperKey, arr);
        }
        // In range
        if (lowerKey.compareTo(node.getKey()) <= 0 
            && upperKey.compareTo(node.getKey()) >= 0) {         
            arr.addTail(node.getValue());
        }
        // Right of higher bound
        if (upperKey.compareTo(node.getKey()) > 0) {
            this.rangeHelper(node.getRight(), lowerKey, upperKey, arr);
        }
        return arr;
    }
    
    // ----------------------------------------------------------
    /**
     * Helper for the contains method
     * @param node
     *        Root node
     * @param key
     *        The key to be searched
     * @return
     *        return the node if found, return null if not found
     */
    TreeNode containsHelper(TreeNode node, T key) {
        if (node == null) {
            return null;
        }
        int comparison = key.compareTo(node.getKey());
        
        if (comparison < 0) {
            return this.containsHelper(node.getLeft(), key);
        }
        
        else if (comparison > 0) {
            return this.containsHelper(node.getRight(), key);
        }
        
        else {
            return node;
        }
    }
    
    // ----------------------------------------------------------
    /**
     * Helper method to get the max depth of the tree
     * @param node
     *        Root node
     * @return
     *        Max depth of the tree
     */
    int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(this.getMaxDepth(node.getLeft())
            , this.getMaxDepth(node.getRight()));
    }
    
    // ----------------------------------------------------------
    /**
     * Helper method to print the required indentation
     * @param level
     *        level where the node at
     * @return
     *        spaces
     */
    String printSpace(int level) {
        return "    ".repeat(level);
    }
    
    // ----------------------------------------------------------
    /**
     * Helper method to visualize the tree
     * @param node
     *        Root node
     * @param level
     *        level where the node at
     * @return
     *        tree visualization in String object
     */
    String printTreeHelper(TreeNode node, int level) {
        if (node == null) {
            return (this.printSpace(level) + "(null)\n");
        }
        String output = this.printTreeHelper(node.getLeft(), level - 1);
        output +=
            this.printSpace(level) + "\\\n"
            + this.printSpace(level) + "(" + node.getKey().toString()
            + ")\n"
            + this.printSpace(level) + "/\n";
        output += this.printTreeHelper(node.getRight(), level - 1);
        return output;
    }
    
    
    // ~ public method...............................................
    //
    // ----------------------------------------------------------
    /**
     * Public insert method for BST
     * @param key
     *        Key to be inserted
     * @param value
     *        Value to be inserted
     */
    public void insert(T key, Seminar value) {
        this.root = this.insertHelper(this.root, key, value);
        this.nodeCount++;
    }
    
    // ----------------------------------------------------------
    /**
     * Public delete method for BST
     * @param key
     *        The key
     * @param seminar
     *        Seminar object
     * @return
     *        Seminar object
     */
    public Seminar delete(T key, Seminar seminar) {
        this.root = this.deleteHelper(this.root, key, seminar);
        if (this.root == null) {
            return null;
        }
        this.nodeCount--;
        return this.root.getValue();
    }
    
    // ----------------------------------------------------------
    /**
     * Public search method for BST (single key)
     * @param key
     *        The key
     * @return
     *        Seminar Array
     */
    public SemArray searchSingle(T key) {
        // Reset traverse count
        this.setTraverseCount(0);
        SemArray arr = new SemArray(10);
        return this.searchSingleHelper(this.root, key, arr);
    }
    
    // ----------------------------------------------------------
    /**
     * Public search method for BST (range key)
     * @param lowerKey
     *        Lower bound of search key
     * @param upperKey
     *        Upper bound of search key
     * @return
     *        Seminar Array
     */
    public SemArray searchRange(T lowerKey, T upperKey) {
        // Reset traverse count
        this.setTraverseCount(0);
        SemArray arr = new SemArray(10);
        return this.rangeHelper(this.root, lowerKey, upperKey, arr);
    }
    
    // ----------------------------------------------------------
    /**
     * Public print method to visualize the tree
     * @return
     *         Visualization in string
     */
    public String printTree() {
        int depth = this.getMaxDepth(this.root);
        String output = this.printTreeHelper(this.root, depth);
        return output;
    }
    
    // ----------------------------------------------------------
    /**
     * Public method to check if certain key is inside the tree
     * @param key
     *        The key
     * @return
     *        true if key is inside the tree
     */
    public boolean contains(T key) {
        TreeNode node = this.containsHelper(this.root, key);
        return (node != null);
    }
    
    // ----------------------------------------------------------
    /**
     * Public method to see if the tree is empty
     * @return
     *        Return true if root is empty
     */
    public boolean isEmpty() {
        return this.root == null;
    }
 
    // ----------------------------------------------------------
    /**
     * Getter for the root tree node
     * @return 
     *         The root
     */
    public TreeNode getRoot() {
        return this.root;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for the root
     * @param root 
     *        The root to set
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for the node count
     * @return 
     *         Numbers of nodes
     */
    public int getNodeCount() {
        return this.nodeCount;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for the node count
     * @param nodeCount 
     *        The nodeCount to set
     */
    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for counting node traversed
     * @return 
     *         Number of node traversed
     */
    public int getTraverseCount() {
        return traverseCount;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for counting node traversed
     * @param traverseCount 
     *        Number of node traversed to set
     */
    public void setTraverseCount(int traverseCount) {
        this.traverseCount = traverseCount;
    }    
    
}
