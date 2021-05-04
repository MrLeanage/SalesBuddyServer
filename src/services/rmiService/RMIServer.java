package services.rmiService;


import util.utility.AlertPopUp;
import util.utility.CacheHandler;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {

    private static Registry registry = null;
    private final String address = "rmi://localhost/";
    private String serverName = "default";
    private String portNumber = "1099";
    private String serverStatus = "default";
    private RMIInterface rmiInterface = null;

    public RMIServer() {
        try {
            rmiInterface = new RMIImplementation();
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }

    public boolean forceStartRMI() {
        RMIServer rmiServer = CacheHandler.getServerInfo();
        rmiServer.setServerStatus("on");
        try {
            try {
                registry = LocateRegistry.createRegistry(Integer.parseInt(getPortNumber()));
                registry.rebind(rmiServer.getServerName(), rmiInterface);
                CacheHandler.setServerInfo(rmiServer);
                return true;
            } catch (ExportException exportException) {
                return false;
            }


        } catch (RemoteException remoteException) {
            AlertPopUp.failMessage("Server Status", "Failed to start Server on port number :" + getPortNumber() + "\n Caused :" + remoteException);
            remoteException.printStackTrace();
            return false;
        }
    }

    public boolean startRMIServer() {
        RMIServer rmiServer = CacheHandler.getServerInfo();
        rmiServer.setServerStatus("on");

        try {
            try {
                registry = LocateRegistry.createRegistry(Integer.parseInt(getPortNumber()));
                registry.rebind(rmiServer.getServerName(), rmiInterface);
                AlertPopUp.successMessage("Server Status", "Server Started Successfully\nServer Port Number : " + rmiServer.getPortNumber());
                CacheHandler.setServerInfo(rmiServer);
                return true;
            } catch (ExportException exportException) {
                AlertPopUp.failMessage("Server Status", "Failed to start Server on port number :" + rmiServer.getPortNumber() + " Already in use. Try Change port Number\n Caused :" + exportException);
                return false;
            }
        } catch (RemoteException remoteException) {
            AlertPopUp.failMessage("Server Status", "Failed to start Server on port number :" + getPortNumber() + "\n Caused :" + remoteException);
            remoteException.printStackTrace();
            return false;
        }
    }

    public boolean forceStopRMIServer() {
        RMIServer rmiServer = CacheHandler.getServerInfo();
        rmiServer.setServerStatus("off");
        try {
            UnicastRemoteObject.unexportObject(registry, true);
            CacheHandler.setServerInfo(rmiServer);
            return true;
        } catch (NoSuchObjectException noSuchObjectException) {
            return true;
        }
    }

    public boolean stopRMIServer() {
        RMIServer rmiServer = CacheHandler.getServerInfo();
        rmiServer.setServerStatus("off");
        try {
            UnicastRemoteObject.unexportObject(registry, true);
            AlertPopUp.successMessage("Server Status", "Server on Port Number : " + rmiServer.getPortNumber() + "\nStopped Successfully!!");
            CacheHandler.setServerInfo(rmiServer);
            return true;
        } catch (NoSuchObjectException noSuchObjectException) {
            AlertPopUp.failMessage("Server Status", "Failed to stop Server on port number :" + rmiServer.getPortNumber() + "\nServer has been terminated by Outside\nCaused :" + noSuchObjectException);
            return false;
        }
    }
}
