package common.objects;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;
import common.datatypes.Ability;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.path.Path;
import common.interfaces.Bossable;
import common.interfaces.Directable;
import common.interfaces.Drawable;
import common.interfaces.Driveable;
import common.interfaces.LSenseable;
import common.interfaces.Leadable;
import common.interfaces.Membership;
import common.interfaces.Notifiable;
import common.interfaces.Promotable;
import common.interfaces.Rmiable;
import common.interfaces.Transferable;
import java.security.Key;

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

public class Member implements LSenseable, Driveable, Drawable, Directable, Bossable, Transferable, Promotable, Notifiable {
	private ArrayList<Ability> abilities;
	private ArrayList <Member> herdMembers;
	private Key myPublicKey;
	private Key myPrivateKey;
	private Key leaderPublicKey;

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

		for(Ability a : can) {
			switch (a) {
			case PROCESSOR:
				abilities.add(a);
				break;
			case DRIVER:
				abilities.add(a);
				break;
			case SENSOR:
				abilities.add(a);
				break;
			case VIEWER:
				abilities.add(a);
				break;
			case DEST_SETTER:
				abilities.add(a);
				break;
			default: break;
			}
		}		
	}


	/**
	 * Retrieves a list of the Members abilities.
	 * 
	 * @return The ability list.
	 */
	@Override
	public ArrayList<Ability> getAbilities(){
		return abilities;
	}

	/**
	 * Sets the value of the Leaders publicKey when handed by a leader.
	 * 
	 * @param The Public key of a leader.
	 */
	
	@Override
	public boolean importLeaderKey(Key pk) {
		if (isValidKey(pk)) {
			leaderPublicKey = pk;
			return true;
		}
		else return false;		
	}


	/**
	 * Retrieves the Public Encryption Key of the Member.
	 * 
	 * @return The Public Key.
	 */
	public Key getPublicKey() {
		return myPublicKey; //TODO this needs to be of type Key once I work out ciphers.
	}
	
	/**
	 * Validates that the provided Public Key given by a leader
	 * can be used to securely communicate with the leader.
	 * 
	 * @param pk
	 * @return True if key is valid, false if not.
	 */
	private boolean isValidKey(Key pk) {
		// TODO RMI call leader, encrpyt String hello world to Leader, if leader returns Hello World then true.
		return false;
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	public void startLeader() {
	  //start RMI registry
	  Registry r = LocateRegistry.createRegistry(1111);
	  
	  //start leader process
	  Leader leader = new Leader(1111, "HerdLeader");
	}


  @Override
  public void notifyOfChange() {
    // TODO Auto-generated method stub
    
  }


  @Override
  public Leader becomeLeader() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public Map processMapLump(Map m) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public Path processPathLump(Path p) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public boolean setDestination(Waypoint w) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public Waypoint drive(Waypoint w) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public void lSense() {
    // TODO Auto-generated method stub
    
  }
}

