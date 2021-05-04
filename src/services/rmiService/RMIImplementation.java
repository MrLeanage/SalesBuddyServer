package services.rmiService;


import javafx.collections.ObservableList;
import model.*;
import services.daoServices.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIImplementation extends UnicastRemoteObject implements RMIInterface {

    public RMIImplementation() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = -3763231206310448L;

    @Override
    public ObservableList<Book> getAllAvailableBooks() throws RemoteException {
        BookService bookService = new BookService();
        return bookService.loadAllData();
    }

    @Override
    public boolean giveFeedback(Feedback feedback) throws RemoteException {
        FeedbackService feedbackService = new FeedbackService();
        return feedbackService.insertFeedback(feedback);
    }

    @Override
    public boolean requestBook(Request request) throws RemoteException {
        RequestService requestService = new RequestService();
        return requestService.insertRequest(request);
    }

    @Override
    public About getShopInformation() throws RemoteException {
        AboutService aboutService = new AboutService();
        return aboutService.getBookshopInformation();
    }

    @Override
    public ArrayList<Book> getAllBooks() throws RemoteException {
        BookService bookService = new BookService();
        return new ArrayList<Book>(bookService.loadAllData());
    }

    @Override
    public boolean addBookView(Integer id) throws RemoteException {
        BookService bookService = new BookService();
        return bookService.addBookView(id);
    }

    @Override
    public User loadSpecificUser(String userName) throws RemoteException {
        UserService userService = new UserService();
        return userService.loadSpecificUser(userName);
    }
}
