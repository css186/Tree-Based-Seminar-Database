/**
 * A implementation of controller class that determine
 * the operations to react with trees
 * 
 * @author Guann-Luen Chen
 * @version 2024.10.15
 */
public class Controller {
    // ~ Fields .................................................
    //
    // ----------------------------------------------------------
    private BinarySearchTree<Integer> idTree;
    private BinarySearchTree<Integer> costTree;
    private BinarySearchTree<String> dateTree;
    private BinarySearchTree<String> keywordTree;
    private Bintree bintree;
    private int worldSize;
    
    // ~ Constructors ...........................................
    //
    // ----------------------------------------------------------
    /**
     * Instantiate all the trees that are needed
     * @param worldSize
     *        Initial size
     */
    public Controller(int worldSize) {
        this.idTree = new BinarySearchTree<>();
        this.costTree = new BinarySearchTree<>();
        this.dateTree = new BinarySearchTree<>();
        this.keywordTree = new BinarySearchTree<>();
        this.bintree = new Bintree(worldSize, worldSize);
        this.worldSize = worldSize;
    }
    // ~ Default Method ..........................................
    //
    // ----------------------------------------------------------
    /**
     * Helper method for print function (int type bst)
     * @param tree
     *        Binary Search Tree Integer type
     * @param output
     *        String output to be updated
     * @return
     *        String output
     */
    String printHelperInt(BinarySearchTree<Integer> tree, String output) {
        if (tree.isEmpty()) {
            output += "This tree is empty";
        }
        else {
            output += tree.printTree();
            output += 
                String.format("Number of records: %d", 
                tree.getNodeCount());
        }
        return output;
    }
    
    // ----------------------------------------------------------
    /**
     * Helper method for print function (string type bst)
     * @param tree
     *        Binary Search Tree Integer type
     * @param output
     *        String output to be updated
     * @return
     *        String output
     */
    String printHelperString(BinarySearchTree<String> tree, String output) {
        if (tree.isEmpty()) {
            output += "This tree is empty";
        }
        else {
            output += tree.printTree();
            output += 
                String.format("Number of records: %d", 
                tree.getNodeCount());
        }
        return output;
    }
    
    // ----------------------------------------------------------
    /**
     * Check location is in range
     * @param axis
     *        Axis to check
     * @param size
     *        The size limit
     * @return
     *        True is axis is in range
     */
    boolean inRange(short axis, int size) {
        int value = Short.toUnsignedInt(axis);
        return (value >= 0 && value < size);
    }
    
    
    // ~ Public Method ..........................................
    //
    // ----------------------------------------------------------
    /**
     * Method to insert seminar into corresponding trees
     * @param seminar
     *        Seminar object
     * @return
     *        Related message in string
     */
    public String insert(Seminar seminar) {
        String output = "";
        
        boolean xInRange = this.inRange((short)seminar.x(), this.worldSize);
        boolean yInRange = this.inRange((short)seminar.y(), this.worldSize);
        
        // check location is in range or not
        if (!xInRange || !yInRange) {
            // Sample: Insert FAILED - Bad x, y coordinates: -1, 10
            output += String
                .format("Insert FAILED - Bad x, y coordinates: %d, %d", 
                seminar.x(), seminar.y());
            return output;
        }
        // check if ID already exists in id tree
        int id = seminar.id();
        boolean hasId = this.idTree.contains(id);
        if (hasId) {
            output += 
                String.format(
                    "Insert FAILED - There is already a record with ID %d",
                    id);
            return output;

        }
        // if ID does not exist, insert other trees
        this.idTree.insert(id, seminar);
        output += 
            String.format("Successfully inserted record with ID %d\n", 
                id);
        this.costTree.insert(seminar.cost(), seminar);
        this.dateTree.insert(seminar.date(), seminar);
        // insert keywords using loops
        for (String s: seminar.keywords()) {
            this.keywordTree.insert(s, seminar);
        }
        // insert bintree
        this.bintree.insert(seminar);
        
        output += seminar.toString();
        return output;
    }
    
    // ----------------------------------------------------------
    /**
     * Method to print out corresponding tree
     * @param token
     *        The type for string to print
     * @return
     *        Related message in string
     */
    public String print(String token) {
        // Capitalized token
        String newToken = token.substring(0, 1).toUpperCase() 
            + token.substring(1);
        String output = newToken + " Tree:\n";
        String newOutput = "";
        switch(token) {
            case "ID":
                newOutput += this.printHelperInt(this.idTree, output);
                break;
            case "date":
                newOutput += this.printHelperString(this.dateTree, output);
                break;
            case "keyword":
                newOutput += this.printHelperString(this.keywordTree, output);
                break;
            case "cost":
                newOutput += this.printHelperInt(this.costTree, output);
                break;
            case "location":
                System.out.println("Location Tree:");
                this.bintree.printTree();
                break;
            default:
                System.out.println("Invalid token");
                break;
                
        }
        return newOutput;
    }
    
    // ----------------------------------------------------------
    /**
     * Method to search in string type trees
     * @param type
     *        Type of trees
     * @param searchKey
     *        The key to search (in String)
     * @return
     *        Related message in string
     */
    public String search(String type, String searchKey) {
        String output = "";
        
        switch(type) {
            case "keyword":
                output += String.format("Seminars matching %s %s:", 
                    type, searchKey);
                SemArray result = this.keywordTree.searchSingle(searchKey);
                
                if (result.isEmpty()) {
                    return output;
                }
                
                output += "\n";
                for (int i = 0; i < result.getSize(); i++) {
                    output += result.getAt(i).toString();
                    if (i != result.getSize() - 1) {
                        output += "\n";
                    }
                }
                break;
            
            case "ID":
                int id = Integer.parseInt(searchKey);
                boolean hasId = this.idTree.contains(id);
                if (!hasId) {
                    output += String.format(
                        "Search FAILED -- There is no record with %s %d",
                        type, id);
                    return output;
                }
                // found id
                output += String.format(
                    "Found record with %s %d:", 
                    type, id);
                
                result = this.idTree.searchSingle(id);
                
                output += "\n";
                for (int i = 0; i < result.getSize(); i++) {
                    output += result.getAt(i).toString();
                    if (i != result.getSize() - 1) {
                        output += "\n";
                    }
                }
                break;
                
            default:
                System.out.println("Invalid token");
                break;
        }
        
        return output;
    }
    
    // ----------------------------------------------------------
    /**
     * Overloading method to do range search on trees
     * @param type
     *        Type of tree
     * @param lowerKey
     *        Lower bound of search key in string
     * @param upperKey
     *        Upper bound of search key in string
     * @return
     *        Related message in string
     */
    public String search(String type, String lowerKey, String upperKey) {
        String output = "";
        
        switch(type) {
            case "cost":
                // convert boundaries to integer
                int lower = Integer.parseInt(lowerKey);
                int upper = Integer.parseInt(upperKey);
                
                output += 
                    String.format("Seminars with costs in range %d to %d:",
                        lower, upper);
                
                SemArray result = this.costTree.searchRange(lower, upper);
                
                if (result.isEmpty()) {
                    output += "\n";
                    output += String.format("%d nodes visited in this search",
                        this.costTree.getTraverseCount());
                    return output;
                }
                
                output += "\n";
                for (int i = 0; i < result.getSize(); i++) {
                    output += result.getAt(i).toString();
                    output += "\n";
                }
                output += String.format("%d nodes visited in this search",
                    this.costTree.getTraverseCount());
                break;
                
            case "date":
                output += 
                String.format("Seminars with dates in range %s to %s:",
                    lowerKey, upperKey);
            
                result = this.dateTree.searchRange(lowerKey, upperKey);
                
                if (result.isEmpty()) {
                    output += "\n";
                    output += String.format("%d nodes visited in this search",
                        this.dateTree.getTraverseCount());
                    return output;
                }
                
                output += "\n";
                for (int i = 0; i < result.getSize(); i++) {
                    output += result.getAt(i).toString();
                    output += "\n";
                }
                output += String.format("%d nodes visited in this search",
                    this.dateTree.getTraverseCount());
                break;
                
            default:
                System.out.println("Invalid token");
                break;
        }
        return output;
    }
    
    // ----------------------------------------------------------
    /**
     * Method for searching bintree
     * @param x
     *        x coordinate
     * @param y
     *        y coordinate
     * @param radius
     *        radius
     */
    public void searchBinTree(int x, int y, int radius) {
        this.bintree.search(x, y, radius);
    }
    
    // ----------------------------------------------------------
    /**
     * Delete node in trees (specifically id tree)
     * @param type
     *        Type of tree
     * @param deleteKey
     *        The key to delete
     * @return
     *        Related message in string
     */
    public String delete(String type, String deleteKey) {
        int intKey = Integer.parseInt(deleteKey);
        String output = "";
        SemArray semArray = this.idTree.searchSingle(intKey);
        if (semArray.isEmpty()) {
            output += String.format(
                "Delete FAILED -- There is no record with %s %s",
                type, deleteKey);
            return output;
        }
        
        // get the seminar object from seminar array
        Seminar semToDelete = semArray.getAt(0);
        this.idTree.delete(intKey, semToDelete);
        
        // delete records from other trees
        this.costTree.delete(semToDelete.cost(), semToDelete);
        this.dateTree.delete(semToDelete.date(), semToDelete);
        String[] keywordsToDelete = semToDelete.keywords();
        for (String keyword: keywordsToDelete) {
            this.keywordTree.delete(keyword, semToDelete);
        }  
        
        // delete bintree
        this.bintree.delete(semToDelete);
        
        output += 
            String.format(
                "Record with %s %s successfully deleted from the database", 
                type, deleteKey);

        return output;
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for idTree
     * @return 
     *         The idTree
     */
    public BinarySearchTree<Integer> getIdTree() {
        return idTree;
    }
    /**
     * Getter for costTree
     * @return 
     *         The costTree
     */
    public BinarySearchTree<Integer> getCostTree() {
        return costTree;
    }
    /**
     * Getter for dateTree
     * @return 
     *         The dateTree
     */
    public BinarySearchTree<String> getDateTree() {
        return dateTree;
    }
    /**
     * Getter for keywordTree
     * @return 
     *         The keywordTree
     */
    public BinarySearchTree<String> getKeywordTree() {
        return keywordTree;
    }
    
    
}
