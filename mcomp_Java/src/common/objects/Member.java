package common.objects;

import java.util.ArrayList;

/**
 * 
 * @author Stephen Pope 15836791
 * 
 * A test Member Class.
 *
 * A Member must have a Public/Private Key pair.
 * A Member must have a List of abilities that are of use to the Herd.
 * A Member can Join or Leave a Herd at any time.
 * A Member must be able to become the Leader of the Herd when nominated.
 * 
 */

public class Member {
	private ArrayList<String> abilities;
	private String herdID = null;
	private String publicKey;
	private String privateKey;
	private ArrayList<Member> herdMembers;

	/**
	 * The Constructor for a Member.
	 * 
	 * A new Member will come with a list of abilities.
	 * These abilities will be queried by the Herd when
	 * the member initiates a Join or creates a Herd.
	 * 
	 * @param The list containing the Members abilities.
	 * @return A new Member object.
	 */

	public Member (String[] can) {
		/*TODO Change from list of Strings to enum to ensure Member 
		 *cannot be set without any valid abilities.
		 */
		abilities = new ArrayList<String>();
		herdMembers = new ArrayList<Member>();
		if (can.length > 0) {
			for(String a : can) {
				switch (a) {
				case "Driver": case "Processor": case "Sensor": case "Viewer": case "DestSetter":
					abilities.add(a);
					break;
				default: break;
				}
			}
		}
		//TODO Generate a Public/Private Key Pair using Java Crypto Library.
		publicKey = "Test";
		privateKey = "Password123";

	}
	
	/**
	 * Retrieves a list of the Members abilities.
	 * 
	 * @return The ability list.
	 */
	public ArrayList<String> getAbilities(){
		return abilities;
	}
	
	/**
	 * Retrieves the Public Encryption Key of the Member.
	 * 
	 * @return The Public Key.
	 */
	public String getPublicKey() {
		return publicKey;
	}
	
	/**
	 * Attempt to Join a herd.
	 * Sends a request to the Herd to gain membership.
	 * 
	 * @param The Herd object being joined.
	 */
	public void joinHerd(Herd h) {
		/*TODO Find a sufficient replacement for handing the
		 * Herd object to this method. We need a method that
		 * allows the Herd to "advertise" itself. The Member
		 * should be able to respond to this advertisement 
		 * when appropriate. 
		 */
		herdMembers = h.requestJoin(this);
		if (herdMembers.isEmpty() == true) {
			System.out.println("Failed to join Herd!");
		}
	}
	
	/**
	 * Attempt to leave a Herd.
	 * Sends a notification to the Herd informing it of
	 * this members intention to leave.
	 * 
	 * @param The Herd being left.
	 */
	public void leaveHerd(Herd h) {
		/*TODO Will need to replace the Herd object
		 * param with an IP or similar.
		 */
		h.requestLeave(this);
		System.out.printf("%s has left the Herd %s \n",this, h.getHerdID());
	}
	
	/**
	 * Become the leader.
	 * Handle the notification that this Member has been
	 * elected as the leader of its Herd.
	 */
	public void becomeLeader() {
		//TODO Needs to have access to Herd ID/IP?
		//TODO Write this method.
		//TODO Need to create a Leader Object?
	}
	
	/**
	 * Retrieves the HerdID of the Herd that this Member
	 * has joined.
	 * 
	 * @return The HerdID.
	 */
	public String getHerdID() {
		return herdID;
	}
	
	/**
	 * Sets the ID of the Herd this Member has joined.
	 * 
	 * @param The new HerdID.
	 */
	public void setHerdID(String newID) {
		/*TODO Write a check to ensure that the 
		 * change of ID is valid. Perhaps contact
		 * the Herd ID supplied. Perhaps ensure that
		 * the Herd is the only one able to change this.
		 */
		herdID = newID;
	}
}

