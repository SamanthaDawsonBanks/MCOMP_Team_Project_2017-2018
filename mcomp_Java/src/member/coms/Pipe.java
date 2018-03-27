package member.coms;

import java.util.Base64;
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
    //where is the buffer?
    throw new UnsupportedOperationException("This method is not implemented yet!");
  }

  public void send() {

    // at some point in the process
    //this.encode(/* some method, some data */);
    
    throw new UnsupportedOperationException("This method is not implemented yet!");
  }

  public void read() {

    // at some point in the process
    //this.encode(/* some method, some data */);

    throw new UnsupportedOperationException("This method is not implemented yet!");
  }


  // There is an argument to suggest that this could include the encode/decode stuff as it is the
  // only known usage of it as of yet. Probably a 'SOLID' discussion here. I really need to revise
  // this.

  // Will also for now be private as no need for outside access??

  /**
   * 
   * --NOT SURE THIS WILL STAY A STRING BUT FOR THE BASIC IMPLEMENTATION--
   * 
   * @param input The data to be encoded into Base64
   * @return The encoded version of the input as a string
   */
  private String encode(String input) {
    String encodedOutput = Base64.getEncoder().encodeToString(input.getBytes());
    return encodedOutput;
  }

  /**
   * 
   * @param input The incoming data to be 
   * @return
   */
  private String decode(byte[] input) {
    byte[] decodedBytes = Base64.getDecoder().decode(input);
    String decodedOutput = new String(decodedBytes);
    return decodedOutput;
  }
}
