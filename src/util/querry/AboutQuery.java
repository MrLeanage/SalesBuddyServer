package util.querry;

public class AboutQuery {
    public static final String LOAD_LIBRARY_INFO = "SELECT * FROM about";
    public static final String INSERT_LIBRARY_INFO = "INSERT INTO about (aBookshopName, aBookshopAddress, aContactPerson, aContactDesignation, aContactEmail, aBookshopNumber) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_LIBRARY_INFO = "UPDATE about SET aBookshopName = ?, aBookshopAddress = ?, aContactPerson = ?, aContactDesignation = ?, aContactEmail = ?, aBookshopNumber = ? WHERE aInfoID = ?";
}
