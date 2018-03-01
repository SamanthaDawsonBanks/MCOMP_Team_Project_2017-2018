package common.objects;

import java.net.InetAddress;
import java.net.UnknownHostException;

import common.interfaces.Leadable;
import common.interfaces.Membership;

public class Leader implements Leadable {
	InetAddress[] addresses;
	InetAddress loopback = InetAddress.getLoopbackAddress();

	public Leader(int i, String string) {
    // TODO Auto-generated constructor stub
  }

  @Override
	public Membership nominateLeader() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Polls the host of the Leader and collects the IP address of all 
	 * reachable interfaces. If an address cannot be reached, return 
	 * the loopback address.
	 * 
	 * @return The array of IP addresses
	 */
	@Override
	public InetAddress[] getAddress() {
		try {
			addresses = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			addresses[0] = loopback; 
		}
		return addresses;
	}

	@Override
	public InetAddress publishAddress() {
		// TODO For each member in Herd, RMI the address to each Member
		return null;
	}

}
