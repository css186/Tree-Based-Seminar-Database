import student.TestCase;

/**
 * Test cases for the Controller
 * 
 * @author Guann-Luen Chen
 * @version 2024.09.27
 */
public class ControllerTest extends TestCase {
    // ~ Fields .........................................................
    //
    // ----------------------------------------------------------
    private Controller controller;
    private Seminar sem1;
    
    // ~ Set up .........................................................
    //
    // ----------------------------------------------------------  
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        this.controller = new Controller(128);
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        sem1 = new Seminar(
            1, "Seminar 1", "2405231000", 1,
            (short)10, (short)33, 125, keywords, 
            "This is Seminar 1");
    }
    
    // ~ Test Method ...................................................
    //
    // ----------------------------------------------------------
    /**
     * Test case for controller instantiation
     * 
     */
    public void testContollerInstantiation() {
        assertTrue(this.controller.getIdTree().isEmpty());
        assertTrue(this.controller.getCostTree().isEmpty());
        assertTrue(this.controller.getDateTree().isEmpty());
        assertTrue(this.controller.getKeywordTree().isEmpty());
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for successful insertion
     */
    public void testInsertSuccess() {
        String result = this.controller.insert(this.sem1);
        String expect = 
            "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Seminar 1\n"
            + "Date: 2405231000, Length: 1, X: 10, Y: 33, Cost: 125\n"
            + "Description: This is Seminar 1\n"
            + "Keywords: Good, Bad, Ugly";
        assertNotNull(result);
        assertEquals(expect, result);
        assertTrue(result.contains(this.sem1.toString()));
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for insert duplicates
     */
    public void testInsertDuplicateId() {
        Seminar sem0 = new Seminar(
            1, "Seminar 1", "2405231000", 1,
            (short)10, (short)33, 125, new String[]{"Test"}, 
            "This is Seminar 1");
        this.controller.insert(this.sem1);
        String result = this.controller.insert(sem0);
        assertNotNull(result);
        assertEquals(result, 
            "Insert FAILED - There is already a record with ID 1");
    }
    
    // ----------------------------------------------------------
    /**
     * Test printing empty tree
     */
    public void testPrintEmpty() {
        String result = this.controller.print("ID");
        assertNotNull(result);
        assertEquals(result, "ID Tree:\nThis tree is empty");
        // test capitalization
        result = this.controller.print("date");
        assertNotNull(result);
        assertEquals(result, "Date Tree:\nThis tree is empty");
        
        result = this.controller.print("cost");
        assertNotNull(result);
        assertEquals(result, "Cost Tree:\nThis tree is empty");
    }
    
    // ----------------------------------------------------------
    /**
     * Test printing tree
     */
    public void testPrintTree() {
        this.controller.insert(this.sem1);
        String result = this.controller.print("ID");
        String expect = "ID Tree:\n"
            + "(null)\n"
            + "    \\\n"
            + "    (1)\n"
            + "    /\n"
            + "(null)\n"
            + "Number of records: 1";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test print helper int (empty)
     */
    public void testPrintHelperIntEmpty() {  
        String result = this.controller.printHelperInt(
            this.controller.getIdTree(), "");
        String expect = "This tree is empty";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test print helper int (non empty)
     */
    public void testPrintHelperIntTree() {
        this.controller.insert(this.sem1);
        String result = this.controller.printHelperInt(
            this.controller.getIdTree(), "");
        assertNotNull(result);
        assertTrue(result.contains("Number of records: 1"));
    }
    
    // ----------------------------------------------------------
    /**
     * Test print helper string (empty)
     */
    public void testPrintHelperStringEmpty() {  
        String result = this.controller.printHelperString(
            this.controller.getDateTree(), "");
        String expect = "This tree is empty";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test print helper string (non empty)
     */
    public void testPrintHelperStringTree() {
        this.controller.insert(this.sem1);
        String result = this.controller.printHelperString(
            this.controller.getDateTree(), "");
        assertNotNull(result);
        assertTrue(result.contains("Number of records: 1"));
    }
    
    // ----------------------------------------------------------
    /**
     * Test searching id
     */
    public void testSearchId() {
        this.controller.insert(this.sem1);
        String idStr = "" + this.sem1.id();
        String result = this.controller.search("ID", 
            idStr);
        String expect = "Found record with ID 1:\n"
            + "ID: 1, Title: Seminar 1\n"
            + "Date: 2405231000, Length: 1, X: 10, Y: 33, Cost: 125\n"
            + "Description: This is Seminar 1\n"
            + "Keywords: Good, Bad, Ugly";
        assertNotNull(result);
        assertEquals(expect, result);
        assertTrue(result.contains(this.sem1.toString()));
    }
    
    // ----------------------------------------------------------
    /**
     * Test searching id failed
     */
    public void testSearchIdFailed() {
        this.controller.insert(this.sem1);
        String result = this.controller.search("ID", "2");
        String expect = "Search FAILED -- There is no record with ID 2";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test searching keyword
     */
    public void testSearchKeyword() {
        this.controller.insert(this.sem1);
        String result = this.controller.search("keyword", "Good");
        String expect = "Seminars matching keyword Good:\n"
            + "ID: 1, Title: Seminar 1\n"
            + "Date: 2405231000, Length: 1, X: 10, Y: 33, Cost: 125\n"
            + "Description: This is Seminar 1\n"
            + "Keywords: Good, Bad, Ugly";
        assertNotNull(result);
        assertEquals(expect, result);
        assertTrue(result.contains(this.sem1.toString()));
    }
    
    // ----------------------------------------------------------
    /**
     * Test searching keyword failed
     */
    public void testSearchKeywordFailed() {
        String result = this.controller.search("keyword", "Bad");
        String expect = "Seminars matching keyword Bad:";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test range searching cost
     */
    public void testRangeSearchCost() {
        this.controller.insert(this.sem1);
        String result = this.controller.search("cost", "100", "150");
        String expect = "Seminars with costs in range 100 to 150:\n"
            + "ID: 1, Title: Seminar 1\n"
            + "Date: 2405231000, Length: 1, X: 10, Y: 33, Cost: 125\n"
            + "Description: This is Seminar 1\n"
            + "Keywords: Good, Bad, Ugly\n"
            + "3 nodes visited in this search";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test range searching cost failed
     */
    public void testRangeSearchCostFailed() {
        this.controller.insert(this.sem1);
        String result = this.controller.search("cost", "150", "250");
        String expect = "Seminars with costs in range 150 to 250:\n"
            + "2 nodes visited in this search";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test range searching date
     */
    public void testRangeSearchDate() {
        this.controller.insert(this.sem1);
        String result = this.controller.search("date", "0", "3");
        String expect = "Seminars with dates in range 0 to 3:\n"
            + "ID: 1, Title: Seminar 1\n"
            + "Date: 2405231000, Length: 1, X: 10, Y: 33, Cost: 125\n"
            + "Description: This is Seminar 1\n"
            + "Keywords: Good, Bad, Ugly\n"
            + "3 nodes visited in this search";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test range searching date failed
     */
    public void testRangeSearchDateFailed() {
        this.controller.insert(this.sem1);
        String result = this.controller.search("date", "0", "1");
        String expect = "Seminars with dates in range 0 to 1:\n"
            + "2 nodes visited in this search";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test deletion
     */
    public void testDeleteSuccess() {
        this.controller.insert(sem1);
        String result = this.controller.delete("ID", "1");
        String expect = "Record with ID 1 successfully "
            + "deleted from the database";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test deletion failed
     */
    public void testDeleteFailed() {
        this.controller.insert(sem1);
        String result = this.controller.delete("ID", "99");
        String expect = "Delete FAILED -- "
            + "There is no record with ID 99";
        assertNotNull(result);
        assertEquals(expect, result);
    }
    
}
