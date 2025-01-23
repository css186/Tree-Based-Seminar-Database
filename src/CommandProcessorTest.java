import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * Test cases for Command Processor
 * 
 * @author Guann-Luen Chen
 * @version 2024.10.15
 */

public class CommandProcessorTest extends TestCase {
    // ~ Fields ................................................................
    //
    // ----------------------------------------------------------
    private CommandProcessor testProcessor;
    private Controller controller;
    private String inputPath;
    private String outputPath;
    
    // ~ Set up ............................................................
    //
    /**
     * Sets up the tests that follow. In general, used for initialization
     * 
     */
    public void setUp() {
        this.inputPath = "./solutionTestData/P2_sampleInput.txt";
        this.outputPath = "./solutionTestData/P2_sampleOutput.txt";
        this.controller = new Controller(128);
        this.testProcessor = new CommandProcessor(
            this.inputPath, 
            this.controller, 
            128);

    }
 
    // ~ Test Method ........................................................
    //
    // ----------------------------------------------------------
    /**
     * Test input case 1
     * @throws Exception
     *         Throw Exception if file does not exist 
     */
    public void testSample() throws Exception {
        
        assertNotNull(this.testProcessor.getController());
        assertNotNull(this.controller);
        
        this.testProcessor.parseFile(inputPath);
        String outputContent = new String(Files.readAllBytes(
            Paths.get(this.outputPath)), StandardCharsets.UTF_8);
        String outputHistory = systemOut().getHistory();
        assertFuzzyEquals(outputContent, outputHistory);
    }
    
}
