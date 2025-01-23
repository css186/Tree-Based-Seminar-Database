/**
 * Self build array for storing seminar object
 * 
 * @author Guann-Luen Chen
 * @version 2025.09.25
 */
public class SemArray {
    // ~ Fields.....................................................
    //
    // ----------------------------------------------------------
    private Seminar[] data;
    private int size;
    private int capacity;
    
    // ~ Constructor.................................................
    //
    // ----------------------------------------------------------
    /**
     * No args constructor
     */
    public SemArray() {
        
    }
    // ----------------------------------------------------------
    /**
     * Instantiate the array with size 0 and input capacity
     * @param capacity
     *        input capacity
     */
    public SemArray(int capacity) {
        this.data = new Seminar[capacity];
        this.capacity = capacity;
        this.size = 0;
    }
    
    // ~ public method...............................................
    //
    // ----------------------------------------------------------
    /**
     * method to resize the array when capacity reached
     */
    void resize() {
        this.capacity *= 2;
        Seminar[] newData = new Seminar[this.capacity];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
    }

    // ----------------------------------------------------------
    /**
     * insert seminar object at the end of array
     * @param s
     *        Seminar object
     * @return
     *        True if added successfully
     */
    public boolean addTail(Seminar s) {
        if (this.size == this.capacity) {
            this.resize();
        }
        this.data[this.size++] = s;
        return true;
    }
    
    // ----------------------------------------------------------
    /**
     * Insert seminar object based on its ID in ascending order
     * @param s
     *        Seminar object
     * @return
     *        True if added successfully
     */
    public boolean addInOrder(Seminar s) {
        if (this.size == this.capacity) {
            this.resize();
        }
        // Search for insert position(index)
        int i;
        for (i = 0; i < this.size; i++) {
            if (s.id() < this.data[i].id()) {
                break;
            }
        }
        // Shift 1 position right for elements
        // after index i
        for (int j = this.size; j > i; j--) {
            this.data[j] = this.data[j - 1]; 
        }
        // Insert the position
        this.data[i] = s;
        this.size++;
        return true;
    }
    
    // ----------------------------------------------------------
    /**
     * Remove seminar object
     * @param s
     *        Seminar object
     * @return
     *        True if seminar is removed
     */
    public boolean remove(Seminar s) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].id() == s.id()) {
                // Shift left for elements after the one
                // needed to be removed
                for (int j = i; j < this.size - 1; j++) {
                    this.data[j] = this.data[j + 1];
                }
                // Remove the last element
                this.data[this.size - 1] = null; 
                this.size--;  
                return true;
            }
        }
        return false;
    }
    
    // ----------------------------------------------------------
    /**
     * Method to check if array is empty
     * @return
     *        true if array is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    // ----------------------------------------------------------
    /**
     * method to get object at ith index
     * @param i
     *        ith index
     * @return
     *        return the seminar object at ith index
     */
    public Seminar getAt(int i) { 
        if (i >= this.size) {
            return null;
        }
        
        if (i < 0) {
            return null;
        }
        return this.data[i];
    }
    
    // ----------------------------------------------------------
    /**
     * Getter for array
     * @return 
     *         The data
     */
    public Seminar[] getData() {
        return data;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for array
     * @param data 
     *        The data to set
     */
    public void setData(Seminar[] data) {
        this.data = data;
    }

    // ----------------------------------------------------------
    /**
     * Getter for the size
     * @return 
     *         The size
     */
    public int getSize() {
        return size;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for the size
     * @param size 
     *        The size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    // ----------------------------------------------------------
    /**
     * Getter for capacity
     * @return 
     *         The capacity
     */
    public int getCapacity() {
        return capacity;
    }
    
    // ----------------------------------------------------------
    /**
     * Setter for capacity
     * @param capacity 
     *        The capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
}
