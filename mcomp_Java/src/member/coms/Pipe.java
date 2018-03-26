package member.coms;

// encoder/decoder imports
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 
 * 
 * @author Ryan Shoobert 15812407
 */
public class Pipe {

  // There is an argument to suggest that this could include the encode/decode stuff as it is the
  // only known usage of it as of yet. Probably a 'SOLID' discussion here. I really need to revise
  // this.

  public Pipe() {
    // TODO Constructor/setup related code
  }

  /**
   * Checks that status of the serial buffer
   * 
   * @return True if the pipe is empty/available for use; False if there is still stuff to be
   *         processed
   */
  public boolean available() {
    return false;
  }

  public void send() {

  }

  public void read() {

  }
}
