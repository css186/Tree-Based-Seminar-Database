import java.util.Scanner;
import java.io.File;

// -------------------------------------------------------------------------
/**
 * This is a class that can read in command line input and parse text file
 * 
 * Source Code Citation:
 * 
 * Title: Unknown
 * Author: OpenDSA Project Contributors
 * Date: Last updated on Aug. 14, 2024
 * Code Version: Unknown
 * Availability: Module 2.7. Reading Input (from Files or Otherwise)
 * Note: The structure and detailed implementation all came from the
 * cited source code. Some revision was implemented based on project
 * specific specs and goals.
 * 
 * @author Guann-Luen Chen
 * @version 2024.10.15
 */

public class CommandProcessor {
    // ~ Fields ................................................................
    //
    // ----------------------------------------------------------
    private String file;
    private Controller controller;
    private int worldSize;
    
    // ~ Constructors ..........................................................
    //
    // ----------------------------------------------------------
    /**
     * Instantiate command line processor to handle text file
     * @param file
     *        File path
     * @param controller
     *        Controller object
     * @param worldSize
     *        Initial size
     */
    public CommandProcessor(String file, Controller controller, int worldSize) {
        this.file = file;
        this.controller = controller;
        this.setWorldSize(worldSize);
    }
    
    // ~ Public Method ........................................................
    //
    // ----------------------------------------------------------
    /**
     * Method to directly run the parsing function
     * @throws Exception 
     *         Throw out exception
     */
    public void execute() throws Exception {
        this.parseFile(this.file);      
    }
    
    // ----------------------------------------------------------
    /**
     * Method to parse input text file and give instruction to controller
     * based on the content of the file
     * 
     * @param filename
     *        Input file
     */
    public void parseFile(String filename) throws Exception {
        // Prepare output message
        String msg;
        try (Scanner sc = new Scanner(new File(filename))) {

            while (sc.hasNext()) {
                String cmd = sc.next().trim();
                String nextToken;

                switch (cmd) {
                    case "insert":
                        int id = Integer.parseInt(sc.next().trim());
                        // Skip to next line
                        sc.nextLine();
                        
                        String title = sc.nextLine().trim();
                        String date = sc.next().trim();
                        int duration = Integer.parseInt(sc.next().trim());
                        short x = Short.parseShort(sc.next().trim());
                        short y = Short.parseShort(sc.next().trim());
                        int cost = Integer.parseInt(sc.next().trim());
                        
                        // Skip the remaining newline after the cost
                        sc.nextLine();
                        
                        // Parse the next lines for tags and description
                        String tags = sc.nextLine().trim();
                        // Split spaces using regex
                        String[] keywords = tags.split("\\s+");
                        String description = sc.nextLine().trim();
                        
                        // Create a new seminar object
                        Seminar newSeminar = new Seminar(id, title, date, 
                            duration, x, y, cost, keywords, description);
                        
                        // Invoke controller's insert method
                        msg = this.controller.insert(newSeminar);
                        System.out.println(msg);
                        
                        break;

                    case "print":
                        nextToken = sc.next().trim();
                        switch (nextToken) {
                            case "ID":
                                msg = this.controller.print(nextToken);
                                System.out.println(msg);
                                break;
                            case "date":
                                msg = this.controller.print(nextToken);
                                System.out.println(msg);
                                break;
                            case "keyword":
                                msg = this.controller.print(nextToken);
                                System.out.println(msg);
                                break;
                            case "cost":
                                msg = this.controller.print(nextToken);
                                System.out.println(msg);
                                break;
                            case "location":
                                this.controller.print(nextToken);
                                break;
                            default:
                                System.out.println("Invalid Token");
                                break;
                        }
                        break;

                    case "search":
                        nextToken = sc.next().trim();
                        String searchKey;
                        switch (nextToken) {
                            case "ID":
                                searchKey = sc.next().trim();
                                msg = this.controller
                                    .search(nextToken, searchKey);
                                System.out.println(msg);
                                break;
                            case "keyword":
                                searchKey = sc.next().trim();
                                msg = this.controller
                                    .search(nextToken, searchKey);
                                System.out.println(msg);
                                break;
                            case "cost":
                                String lowerBound = sc.next().trim();
                                String higherBound = sc.next().trim();
                                msg = this.controller
                                    .search(nextToken, lowerBound, higherBound);
                                System.out.println(msg);
                                break;
                            case "date":
                                lowerBound = sc.next().trim();
                                higherBound = sc.next().trim();
                                msg = this.controller
                                    .search(nextToken, lowerBound, higherBound);
                                System.out.println(msg);
                                break;
                            case "location":
                                int xCord = Integer.parseInt(sc.next().trim());
                                int yCord = Integer.parseInt(sc.next().trim());
                                int radius = Integer.parseInt(sc.next().trim());
                                
                                // BinTree
                                this.controller.searchBinTree(
                                    xCord, 
                                    yCord, 
                                    radius);
                                
                                break;
                            default:
                                System.out.println("Invalid token");
                                break;
                        }
                        break;

                    case "delete":
                        nextToken = sc.next().trim();
                        msg = this.controller.delete("ID", nextToken);
                        System.out.println(msg);
                        break;

                    default:
                        System.out.println("Invalid token");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------   
    /**
     * Getter to get the name of the file
     * @return
     *         File name
     */
    public String getFile() {
        return file;
    }
    
    // ----------------------------------------------------------   
    /**
     * Setter to set name of the file
     * @param file
     *        File name
     */
    public void setFile(String file) {
        this.file = file;
    }
    
    // ----------------------------------------------------------   
    /**
     * Getter for world size
     * @return
     *        world size
     */
    public int getWorldSize() {
        return worldSize;
    }
    
    // ----------------------------------------------------------   
    /**
     * Setter for world size
     * @param worldSize
     *        World Size to be set
     */
    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
    }
    
    // ----------------------------------------------------------   
    /**
     * Getter for controller
     * @return 
     *         the controller
     */
    public Controller getController() {
        return controller;
    }

    // ----------------------------------------------------------   
    
    /**
     * Setter for controller
     * @param controller 
     *        the controller to set
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
}
