import student.TestCase;

/**
 * Test cases for binary search tree
 * 
 * @author Guann-Luen Chen
 * @version 2025.09.25
 * 
 */
public class BinarySearchTreeTest extends TestCase {
    // ~ Fields ......................................................
    //
    // ----------------------------------------------------------
    private BinarySearchTree<Integer> intBST;
    private BinarySearchTree<Integer>.TreeNode root;
    private BinarySearchTree<Integer>.TreeNode node0;
    private BinarySearchTree<Integer>.TreeNode node1;
    private BinarySearchTree<Integer>.TreeNode node2;
    private BinarySearchTree<Integer>.TreeNode node4;
    private BinarySearchTree<Integer>.TreeNode node5;
    private Seminar seminar;
    
    // ~ Set Up ......................................................
    //
    // ----------------------------------------------------------
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        this.intBST = new BinarySearchTree<Integer>();
        this.seminar = new Seminar();
        this.root = this.intBST.new TreeNode(3, this.seminar);
        this.node0 = this.intBST.new TreeNode(0, this.seminar);
        this.node1 = this.intBST.new TreeNode(1, this.seminar);
        this.node2 = this.intBST.new TreeNode(2, this.seminar);
        this.node4 = this.intBST.new TreeNode(4, this.seminar);
        this.node5 = this.intBST.new TreeNode(5, this.seminar);
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node instantiation
     */
    public void testTreeNodeInit() {
        assertTrue(this.root.getKey() == 3);
        assertTrue(this.root.getValue() == this.seminar);
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node key setter
     */
    public void testTreeNodeKeySetter() {
        this.root.setKey(20);
        assertTrue(this.root.getKey() == 20);
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node value setter
     */
    public void testTreeNodeValueSetter() {
        Seminar seminar2 = new Seminar();
        this.root.setValue(seminar2);
        Seminar result = this.root.getValue();
        assertEquals(result, seminar2);
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node getter and setter for left child
     */
    public void testTreeNodeGetSetLeft() {
        this.root.setLeft(node1);
        assertTrue(this.root.getLeft().getKey() == 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node getter and setter for right child
     */
    public void testTreeNodeGetSetRight() {
        this.root.setRight(node4);
        assertTrue(this.root.getRight().getKey() == 4);
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node has left
     */
    public void testTreeNodeHasLeft() {
        this.root.setLeft(node1);
        assertTrue(this.root.hasLeft());
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node has right
     */
    public void testTreeNodeHasRight() {
        this.root.setRight(node4);
        assertTrue(this.root.hasRight());
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node is leaf
     */
    public void testTreeNodeIsLeaf() {
        assertTrue(this.root.isLeaf());
        this.root.setLeft(node1);
        this.root.setRight(node4);
        assertTrue(this.root.getLeft().isLeaf());
        assertTrue(this.root.getRight().isLeaf());
        assertFalse(this.root.isLeaf());
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node left leaf
     */
    public void testLeftLeaf() {
        assertTrue(this.root.isLeaf());
        this.root.setLeft(node1);
        assertFalse(this.root.hasRight());
        assertTrue(this.root.hasLeft());
        assertTrue(this.root.getLeft().isLeaf());
        assertFalse(this.root.isLeaf());
    }
    
    // ----------------------------------------------------------
    /**
     * Test tree node right leaf
     */
    public void testLeaf() {
        assertTrue(this.root.isLeaf());
        this.root.setRight(node4);
        assertFalse(this.root.hasLeft());
        assertTrue(this.root.hasRight());
        assertTrue(this.root.getRight().isLeaf());
        assertFalse(this.root.isLeaf());
    }
    
    // ----------------------------------------------------------
    /**
     * Test bst instantiation
     */
    public void testBSTInit() {
        assertNull(this.intBST.getRoot());
        assertEquals(this.intBST.getNodeCount(), 0);
        assertEquals(this.intBST.getTraverseCount(), 0);
    }
    
    // ----------------------------------------------------------
    /**
     * Test bst insertion
     */
    public void testInsert() {
        assertNull(this.intBST.getRoot());
        this.intBST.insert(this.root.getKey(), this.seminar);
        assertNotNull(this.intBST.getRoot());
        assertEquals(this.intBST.getNodeCount(), 1);
    }
    
    // ----------------------------------------------------------
    /**
     * More test on bst insertion
     */
    public void testInsertMore() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        assertEquals(this.intBST.getNodeCount(), 3);
        assertTrue(this.intBST.getRoot().getLeft().getKey() == 1);
        assertTrue(this.intBST.getRoot().getRight().getKey() == 4);
    }
    
    // ----------------------------------------------------------
    /**
     * Test complete tree
     */
    public void testBSTComplete() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        this.intBST.insert(node0.getKey(), this.seminar);
        this.intBST.insert(node2.getKey(), this.seminar);
        this.intBST.insert(node5.getKey(), this.seminar);
        
        assertEquals(this.intBST.getNodeCount(), 6);
        assertTrue(this.intBST.getRoot().getLeft().getKey() == 1);
        assertTrue(this.intBST.getRoot().getLeft().getLeft().getKey() == 0);
        assertTrue(this.intBST.getRoot().getLeft().getRight().getKey() == 2);
        assertTrue(this.intBST.getRoot().getRight().getKey() == 4);
        assertTrue(this.intBST.getRoot().getRight().getRight().getKey() == 5);
        assertNull(this.intBST.getRoot().getLeft().getLeft().getLeft());
        assertNull(this.intBST.getRoot().getLeft().getLeft().getRight());
        assertNull(this.intBST.getRoot().getLeft().getRight().getLeft());
        assertNull(this.intBST.getRoot().getLeft().getRight().getRight());
        assertNull(this.intBST.getRoot().getRight().getRight().getLeft());
        assertNull(this.intBST.getRoot().getRight().getRight().getRight());
        
    }
    
    // ----------------------------------------------------------
    /**
     * Test inserting duplicates
     */
    public void testInsertDuplicate() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        this.intBST.insert(node0.getKey(), this.seminar);
        this.intBST.insert(node2.getKey(), this.seminar);
        this.intBST.insert(node5.getKey(), this.seminar);
        
        this.intBST.insert(node1.getKey(), this.seminar);
        assertTrue(this.intBST.getRoot().getLeft()
            .getLeft().getRight().getKey() == 1);
        this.intBST.insert(node4.getKey(), this.seminar);
        assertTrue(this.intBST.getRoot().getRight().getLeft().getKey() == 4);
        
    }
    
    // ----------------------------------------------------------
    /**
     * Test insert helper with null root
     */
    public void testInsertHelperNull() {
        BinarySearchTree<Integer>.TreeNode result = this.intBST
            .insertHelper(null, 1, this.seminar);
        
        assertEquals(result.getValue(), this.seminar);
        assertTrue(result.getKey() == 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test find max method
     */
    public void testFindMax() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        this.intBST.insert(node0.getKey(), this.seminar);
        this.intBST.insert(node2.getKey(), this.seminar);
        this.intBST.insert(node5.getKey(), this.seminar);

        BinarySearchTree<Integer>.TreeNode result1 = this.intBST
            .findMaxNode(this.intBST.getRoot());
        assertTrue(result1.getKey() == 5);
        
        BinarySearchTree<Integer>.TreeNode result2 = this.intBST
            .findMaxNode(this.intBST.getRoot().getLeft());
        assertTrue(result2.getKey() == 2);
    }
    
    // ----------------------------------------------------------
    /**
     * Test simple deletion (root node)
     */
    public void testDeleteRootSimple() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        assertEquals(this.intBST.getNodeCount(), 3);
        assertNotNull(this.intBST.delete(this.root.getKey(), 
            this.root.getValue()));
        assertEquals(this.intBST.getNodeCount(), 2);
        assertTrue(this.intBST.getRoot().getKey() == 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test complex deletion (root node)
     */
    public void testDeleteRootComplex() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        this.intBST.insert(node0.getKey(), this.seminar);
        this.intBST.insert(node2.getKey(), this.seminar);
        this.intBST.insert(node5.getKey(), this.seminar);
        assertEquals(this.intBST.getNodeCount(), 6);
        assertNotNull(this.intBST.delete(this.root.getKey(), 
            this.root.getValue()));
        assertEquals(this.intBST.getNodeCount(), 5);
        assertTrue(this.intBST.getRoot().getKey() == 2);
    }
    
    // ----------------------------------------------------------
    /**
     * Test deletion on node with two child (not root)
     */
    public void testDeleteTwoChild() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        this.intBST.insert(node0.getKey(), this.seminar);
        this.intBST.insert(node2.getKey(), this.seminar);
        this.intBST.insert(node5.getKey(), this.seminar);
        assertNotNull(this.intBST.delete(this.node1.getKey(), 
            this.node1.getValue()));
        assertTrue(this.intBST.getRoot().getLeft().getKey() == 0);
    }
    
    // ----------------------------------------------------------
    /**
     * Test deletion on node with right child
     */
    public void testDeleteRightChild() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        this.intBST.insert(node0.getKey(), this.seminar);
        this.intBST.insert(node2.getKey(), this.seminar);
        this.intBST.insert(node5.getKey(), this.seminar);
        assertNotNull(this.intBST.delete(this.node5.getKey(), 
            this.node5.getValue()));
        assertTrue(this.intBST.getRoot().getRight().getKey() == 4);
        assertNull(this.intBST.getRoot().getRight().getRight());
    }
    
    // ----------------------------------------------------------
    /**
     * Test deletion on node with left child
     */
    public void testDeleteLeftChild() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        this.intBST.insert(node0.getKey(), this.seminar);
        assertNotNull(this.intBST.delete(this.node1.getKey(), 
            this.node1.getValue()));
        assertTrue(this.intBST.getRoot().getLeft().getKey() == 0);
        assertNull(this.intBST.getRoot().getLeft().getLeft());
    }
    
    // ----------------------------------------------------------
    /**
     * Test delete helper method with null root
     */
    public void testDeleteHelperNull() {
        assertNull(this.intBST.deleteHelper(null, 1, this.seminar));
    }
    
    // ----------------------------------------------------------
    /**
     * Test single key search method
     */
    public void testSearchSingleHelperNull() {
        SemArray semArr = new SemArray(2);
        assertNotNull(this.intBST.searchSingleHelper(null, 1, semArr));
        
        this.intBST.setTraverseCount(0);
        SemArray result = this.intBST.searchSingleHelper(null, 1, semArr);
        assertEquals(result, semArr);
        assertTrue(result.isEmpty());
        assertEquals(this.intBST.getTraverseCount(), 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test single key search when key is not found
     */
    public void testSearchSingleHelperKeyNotFound() {
        Seminar sem0 = new Seminar();
        Seminar sem1 = new Seminar();
        Seminar sem4 = new Seminar();
        SemArray semArr = new SemArray(5);
        this.intBST.insert(3, sem0);
        this.intBST.insert(1, sem1);
        this.intBST.insert(4, sem4);
        
        
        // go right
        SemArray result = this.intBST
            .searchSingleHelper(this.intBST.getRoot(), 7, semArr);
        assertTrue(result.isEmpty());
        assertEquals(this.intBST.getTraverseCount(), 3);
        
        // go left
        this.intBST.setTraverseCount(0);
        result = this.intBST
            .searchSingleHelper(this.intBST.getRoot(), 0, semArr);
        assertTrue(result.isEmpty());
        assertEquals(this.intBST.getTraverseCount(), 3);
    }
    
    // ----------------------------------------------------------
    /**
     * Test single key search when key is found
     */
    public void testSearchSingleHelperKeyFound() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem0 = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem4 = new Seminar(
            4, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        SemArray semArr = new SemArray(5);
        this.intBST.insert(3, sem0);
        this.intBST.insert(1, sem1);
        this.intBST.insert(4, sem4);
        
        this.intBST.setTraverseCount(0);
        SemArray result = this.intBST
            .searchSingleHelper(this.intBST.getRoot(), 1, semArr);
        assertFalse(result.isEmpty());
        assertTrue(result.getSize() == 1);
        assertEquals(result.getAt(0), sem1);
        assertEquals(this.intBST.getTraverseCount(), 3);
    }
    
    // ----------------------------------------------------------
    /**
     * Test single key search when searching for duplicate items
     */
    public void testSearchSingleDuplicates() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem0 = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem4 = new Seminar(
            4, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        SemArray semArr = new SemArray(5);
        this.intBST.insert(3, sem0);
        this.intBST.insert(1, sem1);
        this.intBST.insert(1, sem4);
        assertTrue(this.intBST.getNodeCount() == 3);
        
        this.intBST.setTraverseCount(0);
        SemArray result = this.intBST
            .searchSingleHelper(this.intBST.getRoot(), 1, semArr);
        assertFalse(result.isEmpty());        
        assertTrue(result.getSize() == 2);
        assertEquals(result.getAt(0), sem4);
        assertEquals(result.getAt(1), sem1);
        assertEquals(this.intBST.getTraverseCount(), 4);
        
    }
    
    // ----------------------------------------------------------
    /**
     * Test single key search (normal case)
     */
    public void testSearchSingleNormal() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem0 = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        Seminar sem2 = new Seminar(
            2, "Duplicate Seminar", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a duplicate seminar");
        
        this.intBST.insert(sem0.id(), sem0);
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem2.id(), sem2);
        
        SemArray result = this.intBST.searchSingle(1);
        assertFalse(result.isEmpty());        
        assertTrue(result.getSize() == 1);
        assertEquals(result.getAt(0), sem1);
        assertEquals(this.intBST.getTraverseCount(), 3);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for traverse count
     */
    public void testSearchSingleTraverseCount() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem0 = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        Seminar sem2 = new Seminar(
            2, "Duplicate Seminar", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a duplicate seminar");
        
        this.intBST.insert(sem0.id(), sem0);
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem2.id(), sem2);
        
        this.intBST.searchSingle(1);
        assertEquals(this.intBST.getTraverseCount(), 3);
        
        this.intBST.searchSingle(2);
        assertEquals(this.intBST.getTraverseCount(), 4);
    }
    
    // ----------------------------------------------------------
    /**
     * Test for range helper (null case)
     */
    public void testRangeHelperNull() {
        SemArray semArr = new SemArray(2);
        assertNotNull(this.intBST.rangeHelper(null, 1, 10, semArr));
        this.intBST.setTraverseCount(0);
        SemArray result = this.intBST.rangeHelper(null, 1, 10, semArr);
        assertEquals(result, semArr);
        assertTrue(result.isEmpty());
        assertEquals(this.intBST.getTraverseCount(), 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test for range helper (found)
     */
    public void testRangeHelperKeyFound() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem2 = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem3 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar"); 
        Seminar sem4 = new Seminar(
            4, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem5 = new Seminar(
            5, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        SemArray semArr = new SemArray(5);
        this.intBST.insert(sem2.id(), sem2);
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem4.id(), sem4);
        this.intBST.insert(sem3.id(), sem3);
        this.intBST.insert(sem5.id(), sem5);
        
        this.intBST.setTraverseCount(0);
        SemArray result = this.intBST
            .rangeHelper(this.intBST.getRoot(), 3, 10, semArr);
        assertFalse(result.isEmpty());
        assertTrue(result.getSize() == 3);
        assertEquals(result.getAt(0), sem3);
        assertEquals(result.getAt(1), sem4);
        assertEquals(result.getAt(2), sem5);
        assertEquals(this.intBST.getTraverseCount(), 8);
    }
    
    // ----------------------------------------------------------
    /**
     * Test for range helper (key not found)
     */
    public void testRangeHelperKeyNotFound() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem2 = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem3 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar"); 
        Seminar sem4 = new Seminar(
            4, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem5 = new Seminar(
            5, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        SemArray semArr = new SemArray(5);
        this.intBST.insert(sem2.id(), sem2);
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem4.id(), sem4);
        this.intBST.insert(sem3.id(), sem3);
        this.intBST.insert(sem5.id(), sem5);
        
        this.intBST.setTraverseCount(0);
        SemArray result = this.intBST
            .rangeHelper(this.intBST.getRoot(), 6, 10, semArr);
        assertTrue(result.isEmpty());
        assertTrue(result.getSize() == 0);
        assertEquals(this.intBST.getTraverseCount(), 4);
    }
    
    // ----------------------------------------------------------
    /**
     * Test range search normal case
     */
    public void testRangeSearchNormal() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem2 = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem3 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar"); 
        Seminar sem4 = new Seminar(
            4, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem5 = new Seminar(
            5, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        this.intBST.insert(sem2.id(), sem2);
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem4.id(), sem4);
        this.intBST.insert(sem3.id(), sem3);
        this.intBST.insert(sem5.id(), sem5);
        
        SemArray result = this.intBST.searchRange(3, 10);
        assertFalse(result.isEmpty());        
        assertTrue(result.getSize() == 3);
        assertEquals(result.getAt(0), sem3);
        assertEquals(result.getAt(1), sem4);
        assertEquals(result.getAt(2), sem5);
        assertEquals(this.intBST.getTraverseCount(), 8);
    }
    // ----------------------------------------------------------
    /**
     * Test range search with duplicates
     */
    public void testRangeSearchDuplicate() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem2 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem3 = new Seminar(
            3, "Duplicate Seminar", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar"); 
        Seminar sem4 = new Seminar(
            4, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem5 = new Seminar(
            5, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem2.id(), sem2);
        this.intBST.insert(sem3.id(), sem3);
        this.intBST.insert(sem4.id(), sem4);
        this.intBST.insert(sem5.id(), sem5);
        SemArray result = this.intBST.searchRange(3, 4);
        assertFalse(result.isEmpty());
        assertTrue(result.getSize() == 3);
        assertEquals(result.getAt(0), sem3);
        assertEquals(result.getAt(1), sem2);
        assertEquals(result.getAt(2), sem4);
        assertEquals(this.intBST.getTraverseCount(), 7);
    }
    
    // ----------------------------------------------------------
    /**
     * Test print space method
     */
    public void testPrintSpace() {
        String result = this.intBST.printSpace(2);
        assertNotNull(result);
        assertEquals(result, "        ");
    }
    
    // ----------------------------------------------------------
    /**
     * Test getting the max depth of a tree
     */
    public void testGetMaxDepth() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        this.intBST.insert(node1.getKey(), this.seminar);
        this.intBST.insert(node4.getKey(), this.seminar);
        this.intBST.insert(node0.getKey(), this.seminar);
        this.intBST.insert(node2.getKey(), this.seminar);
        this.intBST.insert(node5.getKey(), this.seminar);
        int depth = this.intBST.getMaxDepth(this.intBST.getRoot());
        assertEquals(depth, 3);
        
    }
    
    // ----------------------------------------------------------
    /**
     * Test getting the max depth of a tree node
     */
    public void testDepthWithOneNode() {
        int depth = this.intBST.getMaxDepth(this.root);
        assertEquals(depth, 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for is empty method
     */
    public void testEmptyTree() {
        assertTrue(this.intBST.isEmpty());
    }
    
    // ----------------------------------------------------------
    /**
     * Another test case for is empty method
     */
    public void testNonEmptyTree() {
        this.intBST.insert(this.root.getKey(), this.seminar);
        assertFalse(this.intBST.isEmpty());
    }
        
    // ----------------------------------------------------------
    /**
     * Test print helper method
     */
    public void testPrintHelper() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem2 = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem10 = new Seminar(
            10, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem3 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem2.id(), sem2);
        this.intBST.insert(sem10.id(), sem10);
        this.intBST.insert(sem3.id(), sem3);
        int d = this.intBST.getMaxDepth(this.intBST.getRoot());
        String result = this.intBST
            .printTreeHelper(this.intBST.getRoot(), d);
        String output = 
            "            (null)\n"
            + "                \\\n"
            + "                (1)\n"
            + "                /\n"
            + "        (null)\n"
            + "            \\\n"
            + "            (2)\n"
            + "            /\n"
            + "(null)\n"
            + "    \\\n"
            + "    (3)\n"
            + "    /\n"
            + "(null)\n"
            + "        \\\n"
            + "        (10)\n"
            + "        /\n"
            + "    (null)\n";
        assertEquals(result, output);
        
    }
    
    // ----------------------------------------------------------
    /**
     * Test print public method
     */
    public void testPrint() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem2 = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem10 = new Seminar(
            10, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem3 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem2.id(), sem2);
        this.intBST.insert(sem10.id(), sem10);
        this.intBST.insert(sem3.id(), sem3);
        String result = this.intBST.printTree();
        String output = 
            "            (null)\n"
            + "                \\\n"
            + "                (1)\n"
            + "                /\n"
            + "        (null)\n"
            + "            \\\n"
            + "            (2)\n"
            + "            /\n"
            + "(null)\n"
            + "    \\\n"
            + "    (3)\n"
            + "    /\n"
            + "(null)\n"
            + "        \\\n"
            + "        (10)\n"
            + "        /\n"
            + "    (null)\n";
        assertEquals(result, output);
    }
    
    // ----------------------------------------------------------
    /**
     * Test cases for contains helper method (Found case)
     */
    public void testContainsHelperFound() {
        Seminar sem0 = new Seminar();
        Seminar sem1 = new Seminar();
        Seminar sem4 = new Seminar();
        this.intBST.insert(3, sem0);
        this.intBST.insert(1, sem1);
        this.intBST.insert(4, sem4);
        BinarySearchTree<Integer>.TreeNode result = this.intBST
            .containsHelper(this.intBST.getRoot(), 1);
        assertNotNull(result);
        assertTrue(result.getKey() == 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test cases for contains helper method (Not found case)
     */
    public void testContainsHelperNotFound() {
        Seminar sem0 = new Seminar();
        Seminar sem1 = new Seminar();
        Seminar sem4 = new Seminar();
        this.intBST.insert(3, sem0);
        this.intBST.insert(1, sem1);
        this.intBST.insert(4, sem4);
        BinarySearchTree<Integer>.TreeNode result = this.intBST
            .containsHelper(this.intBST.getRoot(), 5);
        assertNull(result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test cases for public contains method (Found)
     */
    public void testContainsFound() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem2 = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem10 = new Seminar(
            10, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem3 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem2.id(), sem2);
        this.intBST.insert(sem10.id(), sem10);
        this.intBST.insert(sem3.id(), sem3);
        assertTrue(this.intBST.contains(sem1.id()));
    }
    
    // ----------------------------------------------------------
    /**
     * Test cases for public contains method (Not Found)
     */
    public void testContainsNotFound() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem2 = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem10 = new Seminar(
            10, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem3 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        this.intBST.insert(sem1.id(), sem1);
        this.intBST.insert(sem2.id(), sem2);
        this.intBST.insert(sem10.id(), sem10);
        assertFalse(this.intBST.contains(sem3.id()));
    }
    
}
