package common;

import java.util.ArrayList;

/**
 * 
 * @author Stephen Pope 15836791
 *
 *	A test class for a Herd.
 *
 *	In order to exist, a Herd must have 1 member when it is created.
 *	Once created, a Herd must nominate a Leader. This will always 
 *	result in the Founding member beginning as the leader.
 *
 *	A Herd holds lists containing all members and lists for members with
 *	certain abilities.
 *	
 */

public class Herd {
	private String herdID;
	private Member theLeader;
	private ArrayList <Member> herdMembers;
	private ArrayList <Member> herdDrivers;
	private ArrayList <Member> herdSensors;
	private ArrayList <Member> herdProcessors;
	private ArrayList <Member> herdViewers;
	private Member herdDestSetter;

	/**
	 * The Herd constructor.
	 * 
	 * A new Herd is handed its founding member.
	 * When storage has been allocated, the founding
	 * member is queried for its abilities. It will
	 * then populate the appropriate lists. An election
	 * is then called.
	 * 
	 * @param A Member to be the founder of the Herd.
	 * @return A new Herd object.
	 */
	public Herd (Member a) {
		//Storage Initialisation
		herdID = "newHerd"; //TODO Needs to be a randomly generated name
		herdMembers = new ArrayList<Member>();
		herdDrivers = new ArrayList<Member>();
		herdSensors = new ArrayList<Member>();
		herdProcessors = new ArrayList<Member>();
		herdViewers = new ArrayList<Member>();

		//Ability Querying
		herdMembers.add(a);
		for(String b : a.getAbilities()) {
			switch (b) {
			case "Driver":
				herdDrivers.add(a);
				break;
			case "Processor":
				herdProcessors.add(a);
				break;
			case "Sensor":
				herdSensors.add(a);
				break;
			case "Viewer":
				herdViewers.add(a);
				break;
			case "DestSetter":
				herdDestSetter = a;
				break;
			}
		}
		//election
		theLeader = a;
	}

	/**
	 * The nomination process.
	 * The Leader variable is populated by the Member Object of the Leader.
	 * The election process picks the oldest member in the Herd.
	 * 
	 * @return The leader of the Herd.
	 */
	public Member nominateLeader() {
		//Obviously the data type and nomination process needs refining!
		//TODO Implement a more robust election system, i.e check list has members
		theLeader = herdMembers.get(0);
		return theLeader;
	} 


	/*
	 * This is where requests to join the Herd are handled. In the
	 * full implementation, a Herd can only be "merged" with another,
	 * so this method will either change signature or be deprecated.
	 */
	
	/**
	 * A member requests to join the Herd by calling this method. The
	 * Herd will request a Public Key and Abilities List. If the Public
	 * Key is a valid Public Key then the Herd will respond with the herdMembers
	 * List.
	 * 
	 * @param The Member object requesting to join the Herd.
	 * @return The list of all current members in the Herd.
	 */
	public ArrayList<Member> requestJoin(Member aspiringMember){
		//TODO Find a test to validate the Key of a Member/Herd
		herdMembers.add(aspiringMember);
		for(String ability: aspiringMember.getAbilities()) {
			switch (ability) {
			case "Driver":
				herdDrivers.add(aspiringMember);
				break;
			case "Proccessor":
				herdProcessors.add(aspiringMember);
				break;
			case "Sensor":
				herdSensors.add(aspiringMember);
				break;
			case "Viewer":
				herdViewers.add(aspiringMember);
				break;
			case "DestSetter":
				//TODO Need code to check the old destSetter and remove it if it only has the one ability.
				if(herdDestSetter != null) {
					if(herdDestSetter.getAbilities().size() > 1) {
						herdDestSetter.getAbilities().remove("DestSetter");
					}
					else {
						herdDestSetter.leaveHerd(this);
					}
				}
				herdDestSetter = aspiringMember;
				break;
			}
		}
		return herdMembers;
	}

	/**
	 * This where requests to leave the Herd are handled. In order to leave,
	 * a member must be removed from the Herds lists of specialists. All remaining
	 * members must be notified of the new state of the Herd. If the leaving member
	 * is a Leader, then an election must be held.
	 * 
	 * @param The member object leaving the Herd.
	 * @return The list of remaining members.
	 */
	public ArrayList<Member> requestLeave(Member leavingMember){
		/*TODO Consider the return type.
		 * If a member leaves, do they need returned the state of the herd?
		 * Perhaps this should be a void, or return an enum. 
		 */
		/*TODO Implement a test for leave.
		 * If a member is the last Herd member, should the Herd be destroyed?
		 * What happens to it's discoveries about the surrounding world? 
		 */
		herdMembers.remove(leavingMember);
		for(String b : leavingMember.getAbilities()) {
			switch (b) {
			case "Driver":
				herdDrivers.remove(leavingMember);
				break;
			case "Processor":
				herdProcessors.add(leavingMember);
				break;
			case "Sensor":
				herdSensors.add(leavingMember);
				break;
			case "Viewer":
				herdViewers.add(leavingMember);
				break;
			case "DestSetter":
				herdDestSetter = null;
				break;
			}
		}
		return herdMembers;
	}
	
	//TODO Finish this
	//	public ArrayList<String> publishMembers() {
	//		for(String a: herdMembers){
	//		//TODO for each member in the herdMembers list publish the new list to them
	//		}
	//	}
	
	/**
	 * Retrieves the unique ID of the Herd.
	 * 
	 * @return The herd ID.
	 */
	public String getHerdID () {
		return herdID;
	}

	/**
	 * Retrieve the list of all Members of the Herd.
	 * 
	 * @return The list of Herd Members.
	 */
	public ArrayList<Member> getMembers(){
		return herdMembers;
	}

	/**
	 * Retrieve a single member from the List
	 * 
	 * @param The Public Key of a member in the Herd.
	 * @return The Member object if it exists, null if not.
	 */
	public Member getMember(String theKey) {
		for(Member a: herdMembers) {
			if(a.getPublicKey().equals(theKey)) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Retrieve a list of all the Herds Drive-Capable Members.
	 * 
	 * @return The list of Drivers.
	 */
	public ArrayList<Member> getDrivers() {
		return herdDrivers;
	}
	
	/**
	 * Retrieve a list of all the Herds Map-Processing-Capable Members.
	 * 
	 * @return The list of Processors.
	 */
	public ArrayList<Member> getProcessors(){
		return herdProcessors;
	}
	
	/**
	 * Retrieve a list of all the Herds Sensor-Equipped Members.
	 * 
	 * @return The list of Sensors.
	 */
	public ArrayList<Member> getSensors(){
		return herdSensors;
	}
	
	/**
	 * Retrieve a list of all the Herds GUI-Capable Members.
	 * 
	 * @return The list of Viewers.
	 */
	public ArrayList<Member> getViewers(){
		return herdViewers;
	}
	
	/**
	 * Retrieve the Member of the Herd responsible for setting destinations.
	 * 
	 * @return The Destination Setter.
	 */
	public Member getDestSetter(){
		return herdDestSetter;
	}
}
