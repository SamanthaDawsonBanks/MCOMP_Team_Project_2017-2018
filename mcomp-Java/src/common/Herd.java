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
 *	A Herd holds lists for all members and lists for members with
 *	certain abilities. The Lists will eventually hold the Public Key
 *	for that member as a unique identifier.
 *	
 */

public class Herd {
	private String herdID;
	private String theLeader;
	private ArrayList <String> herdMembers;
	private ArrayList <String> herdDrivers;
	private ArrayList <String> herdSensors;
	private ArrayList <String> herdProcessors;
	private ArrayList <String> herdViewers;
	private ArrayList <String> herdDestSetters;

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
		herdID = "newHerd";
		herdMembers = new ArrayList<String>();
		herdDrivers = new ArrayList<String>();
		herdSensors = new ArrayList<String>();
		herdProcessors = new ArrayList<String>();
		herdViewers = new ArrayList<String>();
		herdDestSetters = new ArrayList<String>();

		//Ability Querying
		for(String b : a.getAbilities()) {
			switch (b) {
			case "D":
				herdDrivers.add(a.getPublicKey());
				break;
			case "P":
				herdProcessors.add(a.getPublicKey());
				break;
			case "S":
				herdSensors.add(a.getPublicKey());
				break;
			case "V":
				herdViewers.add(a.getPublicKey());
				break;
			case "W":
				herdDestSetters.add(a.getPublicKey());
				break;
			}
		}
		//election
		nominateLeader();
	}
	
	/*
	 * The nomination process. The Leader variable is populated
	 * by the Public Key of the elected leader.
	 */
	public String nominateLeader() {
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
	public ArrayList<String> requestJoin(Member aspiringMember){
		//TODO Find a test to validate the Key of a member
		return herdMembers;
		//Else return empty ArrayList
	}
	
	/*
	 * This where requests to leave the Herd are handled. In order to leave,
	 * a member must removed from the Herds lists of specialists. All remaining
	 * members must be notified of the new state of the Herd. If the leaving member
	 * is a Leader, then an election must be held.
	 * 
	 * A debate needs to be about transferring leadership, and what gets copied over.
	 */
	public ArrayList<String> requestLeave(Member leavingMember){
		//TODO There is 100% a smarter way to remove a member from the lists
		String leaver = leavingMember.getPublicKey();
		for(String id: herdMembers) {
			if(id.equals(leaver)) {
				herdMembers.remove(leaver);
			}
		}
		for(String id: herdDrivers) {
			if(id.equals(leaver)) {
				herdDrivers.remove(leaver);
			}
		}
		for(String id: herdSensors) {
			if(id.equals(leaver)) {
				herdSensors.remove(leaver);
			}
		}
		for(String id: herdProcessors) {
			if(id.equals(leaver)) {
				herdProcessors.remove(leaver);
			}
		}
		for(String id: herdViewers) {
			if(id.equals(leaver)) {
				herdViewers.remove(leaver);
			}
		}
		for(String id: herdDestSetters) {
			if(id.equals(leaver)) {
				herdDestSetters.remove(leaver);
			}
		}
		if(theLeader.equals(leavingMember.getPublicKey())){
		   nominateLeader();
		}
		return herdMembers;
	}
	
//	public ArrayList<String> publishMembers() {
//		for(String a: herdMembers){
//		//TODO for each member in the herdMembers list publish the new list to them
//		}
//	}
}
