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

  public boolean acceptMember(Member m);// TODO leader info? Is this interface badly specified?

  public boolean notifyJoin();// TODO same as above, notify the leader of a join?

  public boolean removeMember(Member m); // may need other uid

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
  Directable getDestSetter();

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
  ArrayList<Notifiable> getViewers();

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
  ArrayList<LSenseable> getSensors();

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
  ArrayList<Bossable> getProcessors();

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
  ArrayList<Driveable> getDrivers();

  /**
   * Access method for getting a collection of all members
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Collection of all Members
   */
  ArrayList<RemoteMember> getMembers();

  /**
   * Access method for getting a single member by UID
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param theKey UID for the Member in the form of a Cryptographic public key 
   *
   * @return Single Member by UID
   */
  RemoteMember getMember(String theKey);

  /**
   * Access method for getting the UID / HerdID for the herd
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return HerdSingle Member by UID
   */
  String getHerdID();

  /**
   * Server side of the client (member) / server (leader-herd) connection used advise the herd that the member is leaving
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param leavingMember UID (by object ref) of the member (typically 'self') that is leaving
   * 
   * @return The members group minus the leaving member
   */
  ArrayList<RemoteMember> requestLeave(RemoteMember leavingMember);

  /**
   * Server side of the client (member) / server (leader-herd) connection used advise the herd that the member is joining
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param aspiringMember UID (by object ref) of the member (typically 'self') that is joining
   * 
   * @return The members group plus the joining member
   */
  ArrayList<RemoteMember> requestJoin(RemoteMember aspiringMember);
}
