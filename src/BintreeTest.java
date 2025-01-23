import student.TestCase;

/**
 * Test cases for Bintree class and all kinds of Bintree nodes
 * 
 * @author Guann-Luen Chen
 * @version 2025.10.15
 */
public class BintreeTest extends TestCase {
    // ~ Fields ......................................................
    //
    // ----------------------------------------------------------
    private Bintree tree;
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
        this.tree = new Bintree(128, 128);
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
     * Test case for instantiation
     */
    public void testInit() {
        assertEquals(this.tree.getTopLeftX(), 0);
        assertEquals(this.tree.getBotRightX(), 127);
        assertEquals(this.tree.getTopLeftY(), 0);
        assertEquals(this.tree.getBotRightY(), 127);
        assertEquals(this.tree.getRoot(), EmptyNode.getEmptyNode());
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for printing empty tree
     */
    public void testEmptyTree() {
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertEquals(output, "(E)\n");
    }
    
    // ----------------------------------------------------------
    /**
     * Test single insertion
     */
    public void testSingleInsertion() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem0 = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        
        this.tree.insert(sem0);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertEquals(output, "(Leaf with 1 objects: 0)\n");
    }
    
    // ----------------------------------------------------------
    /**
     * Test multiple insertion
     */
    public void testMultipleInsertion() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)10, (short)100, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)100, (short)100, 125, keywords, 
            "This is a great seminar");
        
        this.tree.insert(semA);
        this.tree.insert(semB);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertTrue(output.contains("(Leaf with 1 objects: 0)"));
        assertTrue(output.contains("(Leaf with 1 objects: 1)"));
        assertTrue(output.contains("(I)"));
    }
    
    // ----------------------------------------------------------
    /**
     * Test split on x axis
     */
    public void testSplitXAxis() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)63, (short)100, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)64, (short)100, 125, keywords, 
            "This is a great seminar");
        
        this.tree.insert(semA);
        this.tree.insert(semB);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertTrue(output.contains("(Leaf with 1 objects: 0)"));
        assertTrue(output.contains("(Leaf with 1 objects: 1)"));
        assertTrue(output.contains("(I)"));
    }
    
    // ----------------------------------------------------------
    /**
     * Test split on y axis
     */
    public void testSplitYAxis() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)50, (short)63, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)50, (short)64, 125, keywords, 
            "This is a great seminar");
        
        this.tree.insert(semA);
        this.tree.insert(semB);
        this.tree.printTree();
        String output = systemOut().getHistory();
        String expect = "        (I)\n"
            + "    (E)\n"
            + "    (I)\n"
            + "(Leaf with 1 objects: 1)\n"
            + "(Leaf with 1 objects: 0)\n";
        assertEquals(output, expect);

    }
    
    // ----------------------------------------------------------
    /**
     * Test split on both axis
     */
    public void testSplitXYAxis() {
        this.tree.setBotRightX(255);
        this.tree.setBotRightY(255);
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)130, (short)120, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)90, (short)200, 125, keywords, 
            "This is a great seminar");
        
        Seminar semC = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)30, (short)154, 125, keywords, 
            "This is a great seminar");
        
        this.tree.insert(semA);
        this.tree.insert(semB);
        this.tree.insert(semC);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertTrue(output.contains("(E)"));
        
        String expect = "            (I)\n"
            + "        (Leaf with 1 objects: 0)\n"
            + "        (I)\n"
            + "    (I)\n"
            + "(Leaf with 1 objects: 1)\n"
            + "(Leaf with 1 objects: 2)\n"
            + "    (E)\n";
        assertEquals(output, expect);

    }
    
    // ----------------------------------------------------------
    /**
     * Test to match sample output
     */
    public void testSampleOutput() {
        this.tree.insert(this.sem1);
        this.tree.insert(this.sem2);
        this.tree.insert(this.sem10);
        this.tree.insert(this.sem3);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertTrue(output.contains("(Leaf with 1 objects: 10)"));
        assertTrue(output.contains("(Leaf with 2 objects: 1 2)"));
        assertTrue(output.contains("(Leaf with 1 objects: 3)"));
        assertTrue(output.contains("(E)"));
        String expect = "                            (I)\n"
            + "                        (E)\n"
            + "                        (I)\n"
            + "                    (E)\n"
            + "                    (I)\n"
            + "                (E)\n"
            + "                (I)\n"
            + "            (E)\n"
            + "            (I)\n"
            + "        (Leaf with 1 objects: 10)\n"
            + "        (I)\n"
            + "    (E)\n"
            + "    (I)\n"
            + "(Leaf with 2 objects: 1 2)\n"
            + "(Leaf with 1 objects: 3)\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search with small radius
     */
    public void testSampleSearchSmallRadius() {
        this.tree.insert(this.sem1);
        this.tree.insert(this.sem2);
        this.tree.insert(this.sem10);
        this.tree.insert(this.sem3);
        this.tree.search(-1, 0, 1);
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("-1"));
        assertTrue(output.contains("3"));
        assertTrue(output.contains("8"));
        
        String expect = "Seminars within 1 units of -1, 0:\n"
            + "Found a record with key value 3 at 0, 0\n"
            + "8 nodes visited in this search\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search with large radius
     */
    public void testSampleSearchLargeRadius() {
        this.tree.insert(this.sem1);
        this.tree.insert(this.sem2);
        this.tree.insert(this.sem10);
        this.tree.insert(this.sem3);
        this.tree.search(-1, 0, 2000);
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("-1"));
        assertTrue(output.contains("3"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
        assertTrue(output.contains("10"));
        assertTrue(output.contains("15"));
        
        String expect = "Seminars within 2000 units of -1, 0:\n"
            + "Found a record with key value 3 at 0, 0\n"
            + "Found a record with key value 1 at 10, 10\n"
            + "Found a record with key value 2 at 10, 10\n"
            + "Found a record with key value 10 at 30, 10\n"
            + "15 nodes visited in this search\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search with radius equals 0
     */
    public void testSampleSearchZeroRadius() {
        this.tree.insert(this.sem1);
        this.tree.insert(this.sem2);
        this.tree.insert(this.sem10);
        this.tree.insert(this.sem3);
        this.tree.search(10, 10, 0);
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
        assertTrue(output.contains("8"));
        
        String expect = "Seminars within 0 units of 10, 10:\n"
            + "Found a record with key value 1 at 10, 10\n"
            + "Found a record with key value 2 at 10, 10\n"
            + "8 nodes visited in this search\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search not found
     */
    public void testSampleSearchNotFound() {
        this.tree.insert(this.sem1);
        this.tree.insert(this.sem2);
        this.tree.insert(this.sem10);
        this.tree.insert(this.sem3);
        this.tree.search(11, 11, 0);
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("8"));
        
        String expect = "Seminars within 0 units of 11, 11:\n"
            + "8 nodes visited in this search\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test less node visited
     */
    public void testSampleSearchLessNodeVisited() {
        this.tree.insert(this.sem1);
        this.tree.insert(this.sem2);
        this.tree.insert(this.sem10);
        this.tree.insert(this.sem3);
        this.tree.search(10, 10, 20);
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("11"));
        
        String expect = "Seminars within 20 units of 10, 10:\n"
            + "Found a record with key value 3 at 0, 0\n"
            + "Found a record with key value 1 at 10, 10\n"
            + "Found a record with key value 2 at 10, 10\n"
            + "Found a record with key value 10 at 30, 10\n"
            + "11 nodes visited in this search\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test sample delete
     */
    public void testSampleDelete() {
        this.tree.insert(this.sem1);
        this.tree.insert(this.sem2);
        this.tree.insert(this.sem10);
        this.tree.insert(this.sem3);
        this.tree.delete(this.sem1);
        this.tree.printTree();
        String output = systemOut().getHistory();
        String expect = "                            (I)\n"
            + "                        (E)\n"
            + "                        (I)\n"
            + "                    (E)\n"
            + "                    (I)\n"
            + "                (E)\n"
            + "                (I)\n"
            + "            (E)\n"
            + "            (I)\n"
            + "        (Leaf with 1 objects: 10)\n"
            + "        (I)\n"
            + "    (E)\n"
            + "    (I)\n"
            + "(Leaf with 1 objects: 2)\n"
            + "(Leaf with 1 objects: 3)\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test deleting non existing
     */
    public void testDeleteNonExisting() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)10, (short)10, 125, keywords, 
            "This is a great seminar");
        
        Seminar semB = new Seminar(
            10, "Seminar Title", "2405231000", 75,
            (short)5, (short)5, 125, keywords, 
            "This is a great seminar");
        
        this.tree.insert(semA);
        this.tree.delete(semB);
        this.tree.search(0, 0, 20);
        String output = systemOut().getHistory();
        String expect = "Seminars within 20 units of 0, 0:\n"
            + "Found a record with key value 1 at 10, 10\n"
            + "1 nodes visited in this search\n";
        assertFalse(output.contains("5"));
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test depth calculation
     */
    public void testComputeDepth() {
        assertEquals(this.tree.getRoot().computeDepth(), 0);
        this.tree.insert(this.sem1);
        assertEquals(this.tree.getRoot().computeDepth(), 0);
        this.tree.insert(this.sem2);
        assertEquals(this.tree.getRoot().computeDepth(), 0);
        this.tree.insert(this.sem10);
        assertEquals(this.tree.getRoot().computeDepth(), 5);
        this.tree.insert(this.sem3);
        assertEquals(this.tree.getRoot().computeDepth(), 7);
    }
    
    // ----------------------------------------------------------
    /**
     * Test delete empty tree
     */
    public void testDeleteEmpty() {
        this.tree.delete(this.sem1);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertNotNull(output);
        assertEquals(output, "(E)\n");
    }
    
    // ----------------------------------------------------------
    /**
     * Test delete leaf node
     */
    public void testDeleteLeaf() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)63, (short)100, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)64, (short)100, 125, keywords, 
            "This is a great seminar");
        
        this.tree.insert(semA);
        this.tree.insert(semB);
        this.tree.delete(semA);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertNotNull(output);
        assertEquals(output, "(Leaf with 1 objects: 1)\n");
    }
    
    // ----------------------------------------------------------
    /**
     * Test delete two leaf nodes
     */
    public void testDeleteTwoLeaf() {
        this.tree.insert(this.sem1);
        this.tree.insert(this.sem2);
        this.tree.insert(this.sem10);
        this.tree.insert(this.sem3);
        this.tree.delete(this.sem1);
        this.tree.delete(this.sem2);
        this.tree.delete(this.sem3);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertNotNull(output);
        assertEquals(output, "(Leaf with 1 objects: 10)\n");
    }
    
    // ----------------------------------------------------------
    /**
     * Test split on Y axis and delete
     */
    public void testYAxisDelete() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)50, (short)10, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)50, (short)70, 125, keywords, 
            "This is a great seminar");
        Seminar semC = new Seminar(
            2, "Seminar Title", "2405231000", 75,
            (short)50, (short)90, 125, keywords, 
            "This is a great seminar");
        this.tree.insert(semA);
        this.tree.insert(semB);
        this.tree.insert(semC);
        assertEquals(this.tree.getRoot().computeDepth(), 6);
        this.tree.delete(semB);
        assertEquals(this.tree.getRoot().computeDepth(), 2);
        this.tree.printTree();
        String output = systemOut().getHistory();
        assertNotNull(output);
        
        String expect = "        (I)\n"
            + "    (E)\n"
            + "    (I)\n"
            + "(Leaf with 1 objects: 2)\n"
            + "(Leaf with 1 objects: 0)\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search on boundary
     */
    public void testSearchOnBoundary1() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)50, (short)50, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)60, (short)60, 125, keywords, 
            "This is a great seminar");
        this.tree.insert(semA);
        this.tree.insert(semB);
        this.tree.search(50, 50, 10);
        String output = systemOut().getHistory();
        String expect = "Seminars within 10 units of 50, 50:\n"
            + "Found a record with key value 0 at 50, 50\n"
            + "11 nodes visited in this search\n";
        assertEquals(output, expect);
    }
    
    // ----------------------------------------------------------
    /**
     * Test search with box out of bounds
     */
    public void testOutofBoundsBox() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)50, (short)50, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)60, (short)50, 125, keywords, 
            "This is a great seminar");
        this.tree.insert(semA);
        this.tree.insert(semB);
        this.tree.search(50, -50, 20);
        String output = systemOut().getHistory();
        assertTrue(output.contains("1 nodes visited in this search"));
        this.tree.search(-50, 50, 20);
        output = systemOut().getHistory();
        assertTrue(output.contains("1 nodes visited in this search"));
        this.tree.search(178, 50, 20);
        output = systemOut().getHistory();
        assertTrue(output.contains("1 nodes visited in this search"));
        this.tree.search(50, 178, 20);
        output = systemOut().getHistory();
        assertTrue(output.contains("1 nodes visited in this search"));
    }
    
    // ----------------------------------------------------------
    /**
     * Test search on boundary
     */
    public void testSearchOnBoundary2() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar semA = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)63, (short)63, 125, keywords, 
            "This is a great seminar");
        Seminar semB = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)64, (short)64, 125, keywords, 
            "This is a great seminar");
        this.tree.insert(semA);
        this.tree.insert(semB);
        
        this.tree.search(-1, -1, 1);
        String output = systemOut().getHistory();
        assertTrue(output.contains("2 nodes visited in this search"));
        this.tree.search(128, -1, 1);
        output = systemOut().getHistory();
        assertTrue(output.contains("2 nodes visited in this search"));
    }
    
}
