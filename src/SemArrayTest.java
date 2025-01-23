import static org.junit.Assert.assertNotEquals;
import student.TestCase;

/**
 * Test cases for self build seminar array list
 * 
 * @author Guann-Luen Chen
 * @version 2025.09.25
 */
public class SemArrayTest extends TestCase {
    // ~ Fields ......................................................
    //
    // ----------------------------------------------------------
    private SemArray testArray;
    
    // ~ Constructors ................................................
    //
    // ----------------------------------------------------------
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        this.testArray = new SemArray(2);
    }
    
    // ~ Public Method ...............................................
    //
    // ----------------------------------------------------------
    /**
     * Test case for array instantiation
     */
    public void testInit() {
        assertEquals(this.testArray.getSize(), 0);
        assertEquals(this.testArray.getCapacity(), 2);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for no args instantiation
     */
    public void testInitNoArgs() {
        this.testArray = new SemArray();
        assertNull(this.testArray.getData());
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for array resizing
     */
    public void testResize() {
        this.testArray.setCapacity(2);
        this.testArray.addTail(new Seminar());
        assertEquals(this.testArray.getSize(), 1);
        this.testArray.addTail(new Seminar());
        assertEquals(this.testArray.getSize(), 2);
        this.testArray.addTail(new Seminar());
        assertEquals(this.testArray.getCapacity(), 4);
    }
    
    // ----------------------------------------------------------
    /**
     * Test elements after resizing
     */
    public void testElementsAfterResize() {
        this.testArray.setCapacity(2);
        Seminar[] oldArr = this.testArray.getData();
        this.testArray.addTail(new Seminar());
        this.testArray.addTail(new Seminar());
        this.testArray.addTail(new Seminar());
        Seminar[] newArr = this.testArray.getData();
        assertEquals(oldArr[0], newArr[0]);
        assertEquals(oldArr[1], newArr[1]);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for not triggering resizing
     */
    public void testWithoutResize() {
        this.testArray.setCapacity(2);
        this.testArray.addTail(new Seminar());
        assertEquals(this.testArray.getSize(), 1);
        this.testArray.addTail(new Seminar());
        assertEquals(this.testArray.getSize(), 2);
        assertEquals(this.testArray.getCapacity(), 2);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for getting empty size
     */
    public void testEmptySize() {
        assertEquals(this.testArray.getSize(), 0);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for insertion
     */
    public void testInsertSize() {
        this.testArray.addTail(new Seminar());
        assertEquals(this.testArray.getSize(), 1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for result of insertion
     */
    public void testInsertBool() {
        assertTrue(this.testArray.addTail(new Seminar()));
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for array getter
     */
    public void testArrayGetter() {
        Seminar sem1 = new Seminar();
        this.testArray.addTail(sem1);
        Seminar[] arr = this.testArray.getData();
        assertNotNull(arr);
        assertEquals(sem1, arr[0]);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for array setter
     */
    public void testArraySetter() {
        Seminar[] arr = new Seminar[10];
        this.testArray.setData(arr);
        assertEquals(this.testArray.getData().length, 10);
        assertEquals(this.testArray.getData(), arr);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for size setter
     */
    public void testSetSize() {
        this.testArray.setSize(10);
        assertEquals(this.testArray.getSize(), 10);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for capacity setter
     */
    public void testSetCapacity() {
        this.testArray.setCapacity(5);
        assertEquals(this.testArray.getCapacity(), 5);
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for checking if array is empty
     */
    public void testIsEmpty() {
        assertTrue(this.testArray.isEmpty());
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for checking if array is not empty
     */
    public void testNonEmpty() {
        this.testArray.addTail(new Seminar());
        assertFalse(this.testArray.isEmpty());
        assertNotNull(this.testArray.getAt(0));
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for get method when empty
     */
    public void testGetEmpty() {
        assertNull(this.testArray.getAt(0));
    }
     
    // ----------------------------------------------------------
    /**
     * Test case for get method when index exceed size
     */
    public void testGetIndexOutOfBounds() {
        this.testArray.addTail(new Seminar());
        assertNull(this.testArray.getAt(1));
        assertNull(this.testArray.getAt(2));
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for get method when index is negative
     */
    public void testGetIndexNegative() {
        this.testArray.addTail(new Seminar());
        assertNull(this.testArray.getAt(-1));
        assertNotNull(this.testArray.getAt(0));
    }
    
    // ----------------------------------------------------------
    /**
     * Test case for getting element normally
     */
    public void testGetNormal() {
        Seminar sem1 = new Seminar();
        Seminar sem2 = new Seminar();
        this.testArray.addTail(sem1);
        this.testArray.addTail(sem2);
        assertNotNull(this.testArray.getAt(0));
        assertNotNull(this.testArray.getAt(1));
        assertNull(this.testArray.getAt(2));
        Seminar result1 = this.testArray.getAt(0);
        Seminar result2 = this.testArray.getAt(1);
        assertEquals(result1, sem1);
        assertEquals(result2, sem2);
        assertNotEquals(result1, sem2);
    }
    
    // ----------------------------------------------------------
    /**
     * Test inserting null
     */
    public void testInsertNull() {
        this.testArray.addTail(null);
        Seminar result = this.testArray.getAt(0);
        assertNull(result);
    }
    
    // ----------------------------------------------------------
    /**
     * Test insert in order empty
     */
    public void testInOrderEmptyList() {
        Seminar sem1 = new Seminar();
        assertTrue(this.testArray.addInOrder(sem1));
        assertEquals(this.testArray.getSize(), 1);
        assertEquals(this.testArray.getAt(0), sem1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test insert in order normal
     */
    public void testInOrderNormal() {
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
        
        this.testArray.addInOrder(sem4);
        this.testArray.addInOrder(sem1);
        this.testArray.addInOrder(sem0);
        
        assertEquals(sem0, this.testArray.getAt(0));
        assertEquals(sem1, this.testArray.getAt(1));
        assertEquals(sem4, this.testArray.getAt(2));
        assertEquals(this.testArray.getSize(), 3);
    }
    
    // ----------------------------------------------------------
    /**
     * Test insert in order with resize
     */
    public void testInOrderResize() {
        this.testArray.setCapacity(2);
        this.testArray.addInOrder(new Seminar());
        assertEquals(this.testArray.getSize(), 1);
        this.testArray.addInOrder(new Seminar());
        assertEquals(this.testArray.getSize(), 2);
        this.testArray.addInOrder(new Seminar());
        assertEquals(this.testArray.getCapacity(), 4);
    }
    
    // ----------------------------------------------------------
    /**
     * Test remove existing seminar object
     */
    public void testRemoveExisting() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem0 = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        this.testArray.addInOrder(sem0);
        this.testArray.addInOrder(sem1);
        
        assertTrue(this.testArray.remove(sem0));
        assertEquals(this.testArray.getSize(), 1);
        assertEquals(this.testArray.getAt(0), sem1);
        
    }
    
    // ----------------------------------------------------------
    /**
     * Test remove non existing seminar object
     */
    public void testRemoveNonExisting() {
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
        
        this.testArray.addInOrder(sem0);
        this.testArray.addInOrder(sem1);
        
        assertFalse(this.testArray.remove(sem4));
        assertEquals(this.testArray.getSize(), 2);
        assertEquals(this.testArray.getAt(0), sem0);
        assertEquals(this.testArray.getAt(1), sem1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test remove last object
     */
    public void testRemoveLast() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        
        Seminar sem0 = new Seminar(
            0, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        Seminar sem1 = new Seminar(
            1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, 
            "This is a great seminar");
        this.testArray.addInOrder(sem1);
        this.testArray.addInOrder(sem0);
        
        assertTrue(this.testArray.remove(sem0));
        assertEquals(this.testArray.getSize(), 1);
        assertEquals(this.testArray.getAt(0), sem1);
    }
    
    // ----------------------------------------------------------
    /**
     * Test remove first object
     */
    public void testRemoveFirst() {
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
        
        
        this.testArray.addTail(sem4);
        this.testArray.addTail(sem1);
        this.testArray.addTail(sem0);
        
        assertEquals(this.testArray.getAt(1), sem1);
        assertEquals(this.testArray.getAt(2), sem0);
        
        assertTrue(this.testArray.remove(sem4));
        assertEquals(this.testArray.getSize(), 2);
        assertEquals(this.testArray.getAt(0), sem1);
        assertEquals(this.testArray.getAt(1), sem0);
        assertNull(this.testArray.getAt(2));
    }
    
    // ----------------------------------------------------------
    /**
     * Test remove middle
     */
    public void testRemoveMiddle() {
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
        
        
        this.testArray.addTail(sem4);
        this.testArray.addTail(sem1);
        this.testArray.addTail(sem0);
        
        assertEquals(this.testArray.getAt(0), sem4);
        assertEquals(this.testArray.getAt(1), sem1);
        assertEquals(this.testArray.getAt(2), sem0);
        
        assertTrue(this.testArray.remove(sem1));
        assertEquals(this.testArray.getSize(), 2);
        assertEquals(this.testArray.getAt(0), sem4);
        assertEquals(this.testArray.getAt(1), sem0);
        assertNull(this.testArray.getAt(2));
    }
}
