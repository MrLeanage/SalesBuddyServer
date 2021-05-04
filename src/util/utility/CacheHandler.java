package util.utility;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import services.rmiService.RMIServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CacheHandler {
    private static final String path = "../SalesBuddyServer/system/config.json";
    private static final String serverName = "Service Name";
    private static final String serverPort = "Port Number";
    private static final String serverStatus = "Server Status";

    public static void createConfig() {
        File configFile = new File(path);
        RMIServer rmiServer = new RMIServer();
        rmiServer.setServerName("SalesBuddy");
        rmiServer.setPortNumber("1099");
        rmiServer.setServerStatus("off");
        if (!configFile.exists() && !configFile.isDirectory()) {
            setServerInfo(rmiServer);
        }
    }

    public static boolean setServerInfo(RMIServer rmiServer) {

        //Creating a JSON Object object
        JSONObject jsonObject = new JSONObject();
        try {
            //Udating Existing key-value pairs into the json object
            if (!rmiServer.getServerStatus().equals("default"))
                jsonObject.put(serverStatus, rmiServer.getServerStatus());
            if (!rmiServer.getServerName().equals("default"))
                jsonObject.put(serverName, rmiServer.getServerName());
            if (!rmiServer.getPortNumber().equals("default"))
                jsonObject.put(serverPort, rmiServer.getPortNumber());

            FileWriter file = new FileWriter(path);
            file.write(jsonObject.toJSONString());
            file.close();
            return true;
        } catch (IOException exception) {
            AlertPopUp.specificGeneralError(exception, "CacheHandler.setServerStatus()");
            return false;
        }
    }

    public static RMIServer getServerInfo() {

        RMIServer rmiServer = new RMIServer();
        JSONParser parserReader = new JSONParser();
        try {
            Object obj = parserReader.parse(new FileReader(path));
            JSONObject jsonReadObject = (JSONObject) obj;
            rmiServer.setServerStatus((String) jsonReadObject.get(serverStatus));
            rmiServer.setServerName((String) jsonReadObject.get(serverName));
            rmiServer.setPortNumber(String.valueOf(jsonReadObject.get(serverPort)));
        } catch (Exception exception) {
            AlertPopUp.specificGeneralError(exception, "CacheHandler.getServerStatus");
        }
        return rmiServer;
    }
}
