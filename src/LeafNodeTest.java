import student.TestCase;

/**
 * Test cases for leaf node
 * 
 * @author Guann-Luen Chen
 * @version 2025.10.15
 */
public class LeafNodeTest extends TestCase {
    // ~ Fields ......................................................
    //
    // ----------------------------------------------------------
    private LeafNode leaf;
    private Seminar sem1;
    private Seminar sem2;
    private Seminar sem10;
    
    // ~ Constructors ................................................
    //
    // ----------------------------------------------------------
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        this.sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)10, (short)10, 125, keywords, 
            "This is a great seminar");
        this.sem2 = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)10, (short)10, 125, keywords, 
            "This is a great seminar");
        this.sem10 = new Seminar(
            10, "Seminar Title", "2405231000", 75,
            (short)30, (short)10, 125, keywords, 
            "This is a great seminar");
        this.leaf = new LeafNode(this.sem1);
    }
    // ~ Public Method ...............................................
    //
    // ----------------------------------------------------------
    /**
     * Test insert same location
     */
    public void testInsertSameLocation() {
        this.leaf.insert(this.sem2, 0, 128, 0, 128);
        assertFalse(this.leaf.getSemArray().isEmpty());
        assertEquals(this.leaf.getSemArray().getSize(), 2);
    }
    
    // ----------------------------------------------------------
    /**
     * Test insert normal
     */
    public void testInsertNormal() {
        this.leaf = new LeafNode(this.sem1);
        BintreeNode node = this.leaf.insert(this.sem10, 0, 128, 0, 128);
        assertFalse(this.leaf.getSemArray().isEmpty());
        assertTrue(this.leaf.getSemArray().getSize() == 1);
        assertEquals(this.leaf.getSemArray().getAt(0), this.sem1);
        assertTrue(node instanceof InternalNode);
    }
    // ----------------------------------------------------------
    /**
     * Test delete normal
     */
    public void testDeleteNormal() {
        this.leaf = new LeafNode(this.sem1);
        BintreeNode node = this.leaf.delete(this.sem1, 0, 128, 0, 128);
        assertNotNull(node);
        assertTrue(node instanceof EmptyNode);
    }
    
    // ----------------------------------------------------------
    /**
     * Test delete same location
     */
    public void testDeleteSameLocation() {
        this.leaf.insert(this.sem2, 0, 128, 0, 128);
        BintreeNode node = this.leaf.delete(this.sem1, 0, 128, 0, 128);
        assertNotNull(node);
        assertFalse(node instanceof EmptyNode);
        assertTrue(node instanceof LeafNode);
    }
    
    // ----------------------------------------------------------
    /**
     * Test splitting node
     */
    public void testSplitNode() {
        BintreeNode node = this.leaf.insert(this.sem10, 0, 128, 0, 128);
        assertNotNull(node);
        assertTrue(node instanceof InternalNode);
        assertFalse(node.computeDepth() == 1);
        assertEquals(node.computeDepth(), 5);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search
     */
    public void testSearch() {
        SemArray semFound = new SemArray(10);
        int[] visitedCount = {0};

        this.leaf.search(10, 10, 0, 128, 0, 128, 20, 
            semFound, visitedCount);
        assertEquals(semFound.getSize(), 1);
        assertEquals(semFound.getAt(0).id(), 1);
        assertEquals(visitedCount[0], 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search not found
     */
    public void testSearchOutsideRange() {
        SemArray semFound = new SemArray(10);
        int[] visitedCount = {0};

        this.leaf.search(50, 50, 0, 128, 0, 128, 5, 
            semFound, visitedCount);

        assertEquals(semFound.getSize(), 0);
        assertEquals(visitedCount[0], 1); 

    }
    
    // ----------------------------------------------------------
    /**
     * Test print
     */
    public void testPrintLeafNode() {
        this.leaf.print(2, 0);
        String output = systemOut().getHistory();
        assertNotNull(output);
        assertEquals(output, 
            "        (Leaf with 1 objects: 1)\n");
    }
    
    // ----------------------------------------------------------
    /**
     * Test compute depth
     */
    public void testComputeDepth() {
        assertEquals(this.leaf.computeDepth(), 0);
    }
}
