package common.interfaces;

import java.util.ArrayList;
import common.objects.Member;

/**
 * An object is joinable if it has the ability to accept new Members to its Collection (typically
 * Herd)
 *
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.objects.Member
 * @see common.objects.Herd
 * @see common.objects.Leader
 *
 */

public interface Joinable {

  /**
   * Server side of the client (member) / server (leader-herd) connection used advise the herd that
   * the member is joining
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param aspiringMember UID (by object ref) of the member (typically 'self') that is joining
   * 
   * @return The members group plus the joining member
   */
  public ArrayList<RemoteMember> requestJoin(RemoteMember aspiringMember);

  public ArrayList<RemoteView> requestJoin(RemoteView aspiringView);

  /**
   * Server side of the client (member) / server (leader-herd) connection used advise the herd that
   * the member is leaving
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param leavingMember UID (by object ref) of the member (typically 'self') that is leaving
   * 
   * @return The members group minus the leaving member
   */
  public ArrayList<RemoteMember> requestLeave(RemoteMember leavingMember);

  public ArrayList<RemoteView> requestLeave(RemoteView leavingView);

  /**
   * Access method for getting the member that has the ability DestSDEST_SETTERetter
   * 
   * @see common.datatypes.Ability
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Member that is the designated DEST_SETTER
   */
  public Directable getDestSetter();

  /**
   * Access method for getting a collection of members that have the ability VIEWER
   * 
   * @see common.datatypes.Ability
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Collection of Members that are designated VIEWERs
   */
  public ArrayList<Notifiable> getViewers();

  /**
   * Access method for getting a collection of members that have the ability SENSOR
   * 
   * @see common.datatypes.Ability
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Collection of Members that are designated SENSORs
   */
  public ArrayList<LSenseable> getSensors();

  /**
   * Access method for getting a collection of members that have the ability VIEWER
   * 
   * @see common.datatypes.Ability
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Collection of Members that are designated VIEWERs
   */
  public ArrayList<Bossable> getProcessors();

  /**
   * Access method for getting a collection of members that have the ability PROCESSOR
   * 
   * @see common.datatypes.Ability
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Collection of Members that are designated PROCESSORs
   */
  public ArrayList<Driveable> getDrivers();

  /**
   * Access method for getting a collection of all members
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Collection of all Members
   */
  public ArrayList<RemoteMember> getMembers();

//  /**
//   * Access method for getting a single member by UID
//   * 
//   * @see common.objects.Member
//   * @see common.objects.Herd
//   * @see common.objects.Leader
//   *
//   * @param theKey UID for the Member in the form of a Cryptographic public key
//   *
//   * @return Single Member by UID
//   */
//  public RemoteMember getMember(String theKey);
//
  /**
   * Access method for getting the UID / HerdID for the herd
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return HerdSingle Member by UID
   */
  public String getHerdID();

  public ArrayList<RemoteView> getViews();

  public RemoteLeader getLeader();

}
