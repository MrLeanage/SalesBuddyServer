package services.rmiService;


import javafx.collections.ObservableList;
import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIInterface extends Remote {

    public ObservableList<Book> getAllAvailableBooks() throws RemoteException;

    public boolean giveFeedback(Feedback feedback) throws RemoteException;

    public boolean requestBook(Request request) throws RemoteException;

    public About getShopInformation() throws RemoteException;

    public ArrayList<Book> getAllBooks() throws RemoteException;

    public boolean addBookView(Integer id) throws RemoteException;

    public User loadSpecificUser(String userName) throws RemoteException;
}
