package common;

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
		//TODO Will need access to notify the Leader
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
		herdID = newID;
	}
}

