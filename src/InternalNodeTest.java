import student.TestCase;

/**
 * Test cases for leaf node
 * 
 * @author Guann-Luen Chen
 * @version 2025.10.15
 */
public class InternalNodeTest extends TestCase {
    // ~ Fields ......................................................
    //
    // ----------------------------------------------------------
    private InternalNode internal;
    private Seminar sem1;
    private Seminar sem2;
    private Seminar sem10;
    private Seminar sem3;
    
    // ~ Constructors ................................................
    //
    // ----------------------------------------------------------
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        this.internal = new InternalNode();
        
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
        
        this.sem3 = new Seminar(
            3, "Seminar Title", "2405231000", 75,
            (short)0, (short)0, 125, keywords, 
            "This is a great seminar");
    }
    
    // ~ Public Method ...............................................
    //
    // ----------------------------------------------------------
    /**
     * test initialization
     */
    public void testInit() {
        assertTrue(this.internal.getLeftChild() instanceof EmptyNode);
        assertTrue(this.internal.getRightChild() instanceof EmptyNode);
    }
    // ----------------------------------------------------------
    /**
     * test insert
     */
    public void testInsert() {
        this.internal.insert(this.sem1, 0, 128, 0, 128);
        assertTrue(this.internal.getLeftChild() instanceof LeafNode);
        assertTrue(this.internal.getRightChild() instanceof EmptyNode);
        this.internal.insert(this.sem2, 0, 128, 0, 128);
        this.internal.insert(this.sem10, 0, 128, 0, 128);
        assertTrue(this.internal.getLeftChild() instanceof InternalNode);
        assertTrue(this.internal.getRightChild() instanceof EmptyNode);
        assertEquals(this.internal.computeDepth(), 5);
    }
    
    // ----------------------------------------------------------
    /**
     * test delete normal
     */
    public void testDeleteNormal() {
        this.internal.insert(this.sem1, 0, 128, 0, 128); 
        this.internal.insert(this.sem10, 0, 128, 0, 128);
        this.internal.delete(this.sem1, 0, 128, 0, 128);
        assertEquals(this.internal.computeDepth(), 1);
        assertTrue(this.internal.getLeftChild() instanceof LeafNode);
        assertTrue(this.internal.getRightChild() instanceof EmptyNode);
    }
    
    // ----------------------------------------------------------
    /**
     * test delete same location
     */
    public void testDeleteSameLocation() {
        this.internal.insert(this.sem1, 0, 128, 0, 128); 
        this.internal.insert(this.sem2, 0, 128, 0, 128);
        assertTrue(this.internal.getLeftChild() instanceof LeafNode);
        assertTrue(this.internal.getRightChild() instanceof EmptyNode);
        this.internal.delete(this.sem2, 0, 128, 0, 128);
        assertEquals(this.internal.computeDepth(), 1);
        assertTrue(this.internal.getLeftChild() instanceof LeafNode);
        assertTrue(this.internal.getRightChild() instanceof EmptyNode);
    }
    
    // ----------------------------------------------------------
    /**
     * test search within radius
     */
    public void testSearchWithinRadius() {
        SemArray semFound = new SemArray(10);
        int[] visitedCount = {0};
        this.internal.insert(this.sem1, 0, 128, 0, 128);
        this.internal.insert(this.sem3, 0, 128, 0, 128); 
        this.internal.search(5, 5, 0, 128, 0, 128, 10, 
            semFound, visitedCount);
        assertEquals(semFound.getSize(), 2);
        assertEquals(semFound.getAt(0).id(), 3);
        assertEquals(semFound.getAt(1).id(), 1);
        assertEquals(visitedCount[0], 9);
    }
    
    // ----------------------------------------------------------
    /**
     * test search outside radius
     */
    public void testSearchOutsideRadius() {
        SemArray semFound = new SemArray(10);
        int[] visitedCount = {0};
        this.internal.insert(this.sem1, 0, 128, 0, 128);
        this.internal.insert(this.sem3, 0, 128, 0, 128); 
        this.internal.search(5, 5, 0, 128, 0, 128, 5, 
            semFound, visitedCount);
        assertEquals(semFound.getSize(), 0);
        assertEquals(visitedCount[0], 9);
    }
    
    // ----------------------------------------------------------
    /**
     * test print
     */
    public void testPrint() {
        this.internal.insert(this.sem1, 0, 128, 0, 128);
        this.internal.print(2, 0);
        String output = systemOut().getHistory();
        assertTrue(output.contains("(I)"));
    }
    
    // ----------------------------------------------------------
    /**
     * test compute depth
     */
    public void testComputeDepth() {
        this.internal.insert(this.sem3, 0, 128, 0, 128);
        this.internal.insert(this.sem10, 0, 128, 0, 128);
        assertEquals(this.internal.computeDepth(), 5);
    }

}
