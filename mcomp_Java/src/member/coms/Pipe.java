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

  public Pipe() {
    // TODO Constructor/setup related code
    //Set up serial connection bits
  }

  /**
   * Checks that status of the serial buffer
   * 
   * @return 0 if the serial is empty; > 0 if there is still data waiting to be processed
   */
  public int available() {
    return 0;
  }

  public void send() {

    // at some point in the process
    //this.encode(/* some method, some data */);

  }

  public void read() {

    // at some point in the process
    //this.encode(/* some method, some data */);

  }


  // There is an argument to suggest that this could include the encode/decode stuff as it is the
  // only known usage of it as of yet. Probably a 'SOLID' discussion here. I really need to revise
  // this.

  // Will also for now be private as no need for outside access??

  private String encode(String input) {
    Encoder e = null;
    
    byte[] meh = input.getBytes();
    
    return e.encodeToString(meh);
  }

  private String decode(byte[] input) {
    Decoder de;
    return "decoded data";
  }
}
