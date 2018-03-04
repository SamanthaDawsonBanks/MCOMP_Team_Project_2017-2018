package common.objects;

import java.rmi.RemoteException;
import java.util.ArrayList;
import common.datatypes.Ability;
import common.interfaces.Leadable;
import common.interfaces.Membership;
import common.interfaces.Rmiable;

/**
 * 
 * @author Stephen Pope 15836791
 * @author Ryan Shoobert (15812407)
 * 
 * A test Member Class.
 *
 * A Member must have a Public/Private Key pair.
 * A Member must have a List of abilities that are of use to the Herd.
 * A Member can Join or Leave a Herd at any time.
 * A Member must be able to become the Leader of the Herd when nominated.
 * 
 */

public class Member implements Rmiable, Membership {
	private ArrayList<Ability> abilities;
	private String herdID = null;
	private String publicKey;
	private String privateKey;
	private ArrayList<Member> herdMembers;

	/**
	 * The Constructor for a Member.
	 *
	 * A new Member will come with a list of abilities.
	 * 
	 * These abilities are parsed by the constructor and
	 * used to initialise the controllers for those abilities.
	 * 
	 * A member then initialises a Herd, becoming that Herds
	 * first member.
	 * 
	 * @param The list containing the Members abilities.
	 * @return A new Member object.
	 */

	public Member (Ability[] can) {
		abilities = new ArrayList<Ability>();
		herdMembers = new ArrayList<Member>();
		if (can.length > 0) {
			for(Ability a : can) {
				switch (a) {
				case PROCESSOR: case DRIVER: case SENSOR: case VIEWER: case DEST_SETTER:
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
	public ArrayList<Ability> getAbilities(){
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


	@Override
	public int add(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		//Leftover from RMIable testing.
		return 0;
	}


	@Override
	public boolean joinHerd(Leadable h) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean leaveHerd() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Become the leader.
	 * Handle the notification that this Member has been
	 * elected as the leader of its Herd.
	 */
	@Override
	public boolean becomeLeader() {
		// TODO Auto-generated method stub
		return false;
	}


	public void start() {
		// TODO Auto-generated method stub
		
	}
}

