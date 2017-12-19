package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Serverable extends Remote {

  int add(int a, int b) throws RemoteException;
  
}
