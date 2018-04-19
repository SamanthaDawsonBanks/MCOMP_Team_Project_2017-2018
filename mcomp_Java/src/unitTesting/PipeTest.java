package unitTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import member.coms.Pipe;

/**
 * Test class for ensuring the communication methods between the pi and arduino operate correctly.
 * 
 * @author Ryan Shoobert 15812407
 * 
 * @version 1.0
 * @since 2018-04-17
 */
class PipeTest {


  private Pipe pipe;

  /**
   * Sets up an instance of the Pipe class for use in the testing methods
   */
  @Before
  void setup() {
    pipe = new Pipe("COM5");
  }

  @Test
  void testDecode() {
    fail("Not yet implemented");
  }

  @Test
  void testEncode() {
    fail("Not yet implemented");
  }

  /**
   * Clean up and close the serial connection once the round of testing has completed
   */
  @After
  void cleanup() {
    pipe.closePipe();
  }

}
