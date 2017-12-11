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
	private ArrayList <Member> herdDestSetters;

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
		herdMembers = new ArrayList<Member>();
		herdDrivers = new ArrayList<Member>();
		herdSensors = new ArrayList<Member>();
		herdProcessors = new ArrayList<Member>();
		herdViewers = new ArrayList<Member>();
		herdDestSetters = new ArrayList<Member>();

		//Ability Querying
		herdMembers.add(a);
		for(String b : a.getAbilities()) {
			switch (b) {
			case "D":
				herdDrivers.add(a);
				break;
			case "P":
				herdProcessors.add(a);
				break;
			case "S":
				herdSensors.add(a);
				break;
			case "V":
				herdViewers.add(a);
				break;
			case "W":
				herdDestSetters.add(a);
				break;
			}
		}
		//election
		firstTimeElection(a);
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
	
	public Member firstTimeElection(Member founder) {
		theLeader = founder;
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
		for(Member a : herdDestSetters) {
			if(a.getPublicKey().equals(leaver)) {
				herdDestSetters.remove(leavingMember);
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
	
	public ArrayList<Member> getMembers(){
		return herdMembers;
	}
}
