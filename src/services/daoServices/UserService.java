package services.daoServices;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import util.dbConnection.DBConnection;
import util.querry.UserQuerry;
import util.utility.AlertPopUp;
import util.utility.DataEncryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public ObservableList<User> loadAllUserData() {
        ObservableList<User> userObservableList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getDBConnection();

        try {
            ResultSet resultSet = conn.createStatement().executeQuery(UserQuerry.LOAD_ALL_USER_DATA);
            while (resultSet.next()) {
                userObservableList.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }

        } catch (SQLException sqlException) {
            AlertPopUp.sqlQueryError(sqlException);
        }
        return userObservableList;
    }

    public User loadSpecificUser(String email) {
        User user = null;
        Connection conn = DBConnection.getDBConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(UserQuerry.LOAD_SPECIFIC_USER_DATA);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }

        } catch (SQLException sqlException) {
            AlertPopUp.sqlQueryError(sqlException);
        }
        return user;
    }

    public boolean insertUserData(User user) {
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement psUser = null;
        try {

            psUser = conn.prepareStatement(UserQuerry.INSERT_USER_DATA);

            psUser.setString(1, user.getuEmmail());
            psUser.setString(2, user.getuName());
            psUser.setString(3, DataEncryption.passwordEncryption(user.getuPassword()));
            psUser.setString(4, user.getuType());
            psUser.execute();
            return true;

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public boolean updateUserData(User user) {
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement psUser = null;
        try {

            psUser = conn.prepareStatement(UserQuerry.UPDATE_USER_DATA);

            psUser.setString(1, user.getuName());
            psUser.setString(2, user.getuType());
            psUser.setString(3, user.getuEmmail());
            psUser.execute();
            return true;

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public boolean updateUserPassword(User user) {
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement psUser = null;
        try {

            psUser = conn.prepareStatement(UserQuerry.UPDATE_USER_PASSWORD);

            psUser.setString(1, DataEncryption.passwordEncryption(user.getuPassword()));
            psUser.setString(2, user.getuEmmail());
            psUser.execute();
            return true;

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public boolean deleteUserData(String email) {
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement psUser = null;
        try {

            psUser = conn.prepareStatement(UserQuerry.DELETE_USER_DATA);

            psUser.setString(1, email);
            psUser.execute();
            return true;

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

}
