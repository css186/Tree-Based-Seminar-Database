// -------------------------------------------------------------------------

/**
 * {Project Description Here}
 */

/**
 * The class containing the main method.
 *
 * @author Guann-Luen Chen
 * @version 2024.09.26
 */

// On my honor:
// - I have not used source code obtained from another current or
//   former student, or any other unauthorized source, either
//   modified or unmodified.
//
// - All source code and documentation used in my program is
//   either my original work, or was derived by me from the
//   source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
//   anyone other than my partner (in the case of a joint
//   submission), instructor, ACM/UPE tutors or the TAs assigned
//   to this course. I understand that I may discuss the concepts
//   of this program with other students, and that another student
//   may help me debug my program so long as neither of us writes
//   anything during the discussion or modifies any computer file
//   during the discussion. I have violated neither the spirit nor
//   letter of this restriction.


public class SemSearch {
    /**
     * @param args
     *     Command line parameters
     */
    public static void main(String[] args) {
        // This is the main file for the program.
        String worldSize = args[0];
        int intWorldSize = Integer.parseInt(worldSize);
        String file = args[1];
        Controller controller = new Controller(intWorldSize);
        CommandProcessor cmp = new CommandProcessor(file, 
            controller, intWorldSize);
        
        try {
            cmp.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Test out code
//         String file = "./solutionTestData/P2_testInput.txt";
//         Controller controller = new Controller(128);
//         CommandProcessor cp = new CommandProcessor(file, controller, 128);
//        
//         try {
//            cp.execute();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
