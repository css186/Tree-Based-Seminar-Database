import student.TestCase;

/**
 * Test cases for Empty Node
 * 
 * @author Guann-Luen Chen
 * @version 2025.10.15
 */
public class EmptyNodeTest extends TestCase {
    // ~ Fields ......................................................
    //
    // ----------------------------------------------------------
    private EmptyNode emptyNode;
    private Seminar sem1;
    
    // ~ Constructors ................................................
    //
    // ----------------------------------------------------------
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        this.emptyNode = EmptyNode.getEmptyNode();
        String[] keywords = {"Good", "Bad", "Ugly"};
        this.sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)10, (short)10, 125, keywords, 
            "This is a great seminar");
    }
    
    // ~ Public Method ...............................................
    //
    // ----------------------------------------------------------
    /**
     * Test Flyweight design pattern
     */
    public void testFlyweight() {
        EmptyNode anotherEmptyNode = EmptyNode.getEmptyNode();
        assertSame(this.emptyNode, anotherEmptyNode);
    }
    
    // ----------------------------------------------------------
    /**
     * Test insert, empty node to leaf node
     */
    public void testInsert() {
        BintreeNode node = this.emptyNode.insert(sem1, 0, 128, 0, 128);
        assertTrue(node instanceof LeafNode);
    }
    
    // ----------------------------------------------------------
    /**
     * Test delete
     */
    public void testDeleteEmptyNode() {
        BintreeNode node = this.emptyNode.delete(sem1, 0, 128, 0, 128);
        assertSame(node, this.emptyNode);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search
     */
    public void testSearch() {
        SemArray semArray = new SemArray(5);
        int[] visitedCount = {0};
        emptyNode.search(50, 50, 0, 128, 0, 128, 20, semArray, visitedCount);
        
        assertEquals(1, visitedCount[0]);  
        assertEquals(0, semArray.getSize());
    }
    
    // ----------------------------------------------------------
    /**
     * Test print
     */
    public void testPrint() {
        this.emptyNode.print(0, 0);
        String output = systemOut().getHistory();
        assertNotNull(output);
        assertEquals(output, "(E)\n");
    }
    
    // ----------------------------------------------------------
    /**
     * Test compute depth
     */
    public void testComputeDepth() {
        assertNotNull(this.emptyNode.computeDepth());
        assertTrue(this.emptyNode.computeDepth() == 0);
    }
    
}
