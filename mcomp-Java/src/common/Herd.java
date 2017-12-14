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
	private Member herdDestSetters;

	/*
	 * The Herd constructor.
	 * 
	 * A new Herd is handed its founding member.
	 * When storage has been allocated, the founding
	 * member is queried for its abilities. It will
	 * then populate the appropriate lists. An election
	 * is then called.
	 */
	public Herd (Member a) {
		//Storage Initialisation
		herdID = "newHerd"; //Needs to be a randomly generated name
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
				herdDestSetters = a;
				break;
			}
		}
		//election
		theLeader = a;
	}
	
	/*
	 * The nomination process. The Leader variable is populated
	 * by the Member Object of the Leader.
	 */
	public Member nominateLeader() {
		//Obviously the data type and nomination process needs refining!
		theLeader = herdMembers.get(0);
		return theLeader;
	}
	
	
	/*
	 * This is where requests to join the Herd are handled. In the
	 * full implementation, a Herd can only be "merged" with another,
	 * so this method will either change signature or be deprecated.
	 * 
	 * A member requests to join the Herd by calling this method. The
	 * Herd will request a Public Key and Abilities List. If the Public
	 * Key is a valid Public Key (Maybe send a file and see if it returns
	 * the correct answer?) Then the Herd will respond with the herdMembers
	 * List. Spreading the List like this should prevent Herd collapse in
	 * the event that the Leader dies/disappears.
	 */
	public ArrayList<Member> requestJoin(Member aspiringMember){
		//TODO Find a test to validate the Key of a member
		//TODO Populate lists based on ability
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
				herdDestSetters.getAbilities().remove("W");
				herdDestSetters.leaveHerd(this);
				herdDestSetters = aspiringMember;
				break;
			}
		}
		return herdMembers;
	}
	
	/*
	 * This where requests to leave the Herd are handled. In order to leave,
	 * a member must removed from the Herds lists of specialists. All remaining
	 * members must be notified of the new state of the Herd. If the leaving member
	 * is a Leader, then an election must be held.
	 * 
	 * A debate needs to be about transferring leadership, and what gets copied over.
	 */
	public ArrayList<Member> requestLeave(Member leavingMember){
		//TODO There is 100% a smarter way to remove a member from the lists
		String leaver = leavingMember.getPublicKey();
		for(Member a: herdMembers) {
			if(a.getPublicKey().equals(leaver)) {
				herdMembers.remove(leavingMember);
			}
		}
		for(Member a: herdDrivers) {
			if(a.getPublicKey().equals(leaver)) {
				herdDrivers.remove(leavingMember);
			}
		}
		for(Member a: herdSensors) {
			if(a.getPublicKey().equals(leaver)) {
				herdSensors.remove(leavingMember);
			}
		}
		for(Member a: herdProcessors) {
			if(a.getPublicKey().equals(leaver)) {
				herdProcessors.remove(leavingMember);
			}
		}
		for(Member a: herdViewers) {
			if(a.getPublicKey().equals(leaver)) {
				herdViewers.remove(leavingMember);
			}
		}
		if(herdDestSetters != null) {
			if (herdDestSetters.getPublicKey().equals(leaver)) {
				herdDestSetters = null;
			}
		}
		if(theLeader.getPublicKey().equals(leavingMember.getPublicKey())){
		   nominateLeader();
		}
		return herdMembers;
	}
	
//	public ArrayList<String> publishMembers() {
//		for(String a: herdMembers){
//		//TODO for each member in the herdMembers list publish the new list to them
//		}
//	}
	
	public String getHerdID () {
		return herdID;
	}
	
	/*
	 * Retrieve the list of all Members of the Herd
	 */
	public ArrayList<Member> getMembers(){
		return herdMembers;
	}
	
	/*
	 * Retrieve a single member from the List
	 */
	public Member getMember(String theKey) {
		for(Member a: herdMembers) {
			if(a.getPublicKey().equals(theKey)) {
				return a;
			}
		}
		return null;
	}
	
	public ArrayList<Member> getDrivers() {
		return herdDrivers;
	}
	
	public ArrayList<Member> getProcessors(){
		return herdProcessors;
	}
	
	public ArrayList<Member> getSensors(){
		return herdSensors;
	}
	
	public ArrayList<Member> getViewers(){
		return herdViewers;
	}
	
	public Member getDestSetter(){
		return herdDestSetters;
	}
}
