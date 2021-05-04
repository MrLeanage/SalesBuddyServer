package view.storeManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Book;
import services.daoServices.BookService;
import util.utility.AlertPopUp;
import util.validation.DataValidation;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class BookDetailController implements Initializable {

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, Integer> bIDColumn;

    @FXML
    private TableColumn<Book, String> bISBNColumn;

    @FXML
    private TableColumn<Book, String> bTitleColumn;

    @FXML
    private TableColumn<Book, String> bAuthorColumn;

    @FXML
    private TableColumn<Book, String> bDescriptionColumn;

    @FXML
    private TableColumn<Book, String> bLanguageColumn;

    @FXML
    private TableColumn<Book, String> bPublisherYearColumn;

    @FXML
    private TableColumn<Book, String> bAvailabilityColumn;

    @FXML
    private TextField searchTextField;

    @FXML
    private TextField ISBNTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField languageTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField publishYearTextField;

    @FXML
    private ChoiceBox<String> availabilityChoiceBox;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private Label publisherYearLabel;

    private static Book selectedBook = null;

    private ObservableList<String> availableChoiceBoxList = FXCollections.observableArrayList("Available", "Not Available");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        searchTable();
        availabilityChoiceBox.setValue("Available");
        availabilityChoiceBox.setItems(availableChoiceBoxList);
    }

    private void loadData() {
        BookService bookService = new BookService();
        ObservableList<Book> bookObservableList = bookService.loadAllData();

        bIDColumn.setCellValueFactory(new PropertyValueFactory<>("bID"));
        bISBNColumn.setCellValueFactory(new PropertyValueFactory<>("bISBN"));
        bTitleColumn.setCellValueFactory(new PropertyValueFactory<>("bTitle"));
        bAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bAuthor"));
        bDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("bDescription"));
        bLanguageColumn.setCellValueFactory(new PropertyValueFactory<>("bLanguage"));
        bPublisherYearColumn.setCellValueFactory(new PropertyValueFactory<>("bPublishYear"));
        bAvailabilityColumn.setCellValueFactory(new PropertyValueFactory<>("bAvailability"));

        bookTable.setItems(null);
        bookTable.setItems(bookObservableList);
    }

    @FXML
    private void addBook(ActionEvent event) {
        clearLabels();
        if (validateData()) {
            Book book = new Book();
            BookService bookService = new BookService();

            book.setbISBN(ISBNTextField.getText());
            book.setbTitle(titleTextField.getText());
            book.setbAuthor(authorTextField.getText());
            book.setbDescription(descriptionTextArea.getText());
            book.setbLanguage(languageTextField.getText());
            book.setbPublishYear(publishYearTextField.getText());
            book.setbAvailability(String.valueOf(availabilityChoiceBox.getValue()));

            if (bookService.insertData(book)) {
                AlertPopUp.insertSuccesfully("Book info");
                refreshData();
            } else
                AlertPopUp.insertionFailed("Book Info");
        } else
            validationMessage();
    }

    @FXML
    private void setSelectedDataToFields(MouseEvent event) {
        try {
            selectedBook = bookTable.getSelectionModel().getSelectedItem();
            ISBNTextField.setText(selectedBook.getbISBN());
            titleTextField.setText(selectedBook.getbTitle());
            authorTextField.setText(selectedBook.getbAuthor());
            descriptionTextArea.setText(selectedBook.getbDescription());
            languageTextField.setText(selectedBook.getbLanguage());
            publishYearTextField.setText(selectedBook.getbPublishYear());
            availabilityChoiceBox.setValue(selectedBook.getbAvailability());
        } catch (Exception exception) {

        }

    }

    @FXML
    private void updateBook(ActionEvent event) {
        clearLabels();
        if (validateData()) {
            Book book = new Book();
            BookService bookService = new BookService();

            book.setbID(selectedBook.getbID());
            book.setbISBN(ISBNTextField.getText());
            book.setbTitle(titleTextField.getText());
            book.setbAuthor(authorTextField.getText());
            book.setbDescription(descriptionTextArea.getText());
            book.setbLanguage(languageTextField.getText());
            book.setbPublishYear(publishYearTextField.getText());
            book.setbAvailability(String.valueOf(availabilityChoiceBox.getValue()));

            if (bookService.updateBook(book)) {
                AlertPopUp.updateSuccesfully("Book info");
                refreshData();
            } else
                AlertPopUp.updateFailed("Book Info");
        } else
            validationMessage();
    }

    @FXML
    private void deleteBook(ActionEvent event) {
        if (selectedBook.getbID() != null) {
            Optional<ButtonType> action = AlertPopUp.deleteConfirmation("Book");
            if (action.get() == ButtonType.OK) {
                BookService bookService = new BookService();
                if (bookService.deleteBook(selectedBook.getbID())) {
                    AlertPopUp.deleteSuccesfull("Book");
                    refreshData();
                    clearLabels();
                } else {
                    AlertPopUp.deleteFailed("Book");
                }
            }
        }
    }

    private void refreshData() {
        loadData();
        searchTable();
        clearFields();
    }

    public void searchTable() {

        BookService bookService = new BookService();

        SortedList<Book> sortedData = bookService.searchTable(searchTextField);

        //binding the SortedList to TableView
        sortedData.comparatorProperty().bind(bookTable.comparatorProperty());
        //adding sorted and filtered data to the table
        bookTable.setItems(sortedData);
    }

    private boolean validateData() {
        return DataValidation.TextFieldNotEmpty(ISBNTextField.getText())
                && DataValidation.TextFieldNotEmpty(titleTextField.getText())
                && DataValidation.TextFieldNotEmpty(authorTextField.getText())
                && DataValidation.TextFieldNotEmpty(descriptionTextArea.getText())
                && DataValidation.TextFieldNotEmpty(languageTextField.getText())
                && DataValidation.TextFieldNotEmpty(publishYearTextField.getText())

                && DataValidation.isValidMaximumLength(ISBNTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(titleTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(authorTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(descriptionTextArea.getText(), 400)
                && DataValidation.isValidMaximumLength(languageTextField.getText(), 20)
                && DataValidation.isValidMaximumLength(publishYearTextField.getText(), 45);
    }

    private void validationMessage() {
        if (!(DataValidation.TextFieldNotEmpty(ISBNTextField.getText())
                && DataValidation.TextFieldNotEmpty(titleTextField.getText())
                && DataValidation.TextFieldNotEmpty(authorTextField.getText())
                && DataValidation.TextFieldNotEmpty(descriptionTextArea.getText())
                && DataValidation.TextFieldNotEmpty(languageTextField.getText())
                && DataValidation.TextFieldNotEmpty(publishYearTextField.getText()))) {
            DataValidation.TextFieldNotEmpty(ISBNTextField.getText(), isbnLabel, "ISBN Field Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(titleTextField.getText(), titleLabel, "Book Name Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(authorTextField.getText(), authorLabel, "Author Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(descriptionTextArea.getText(), descriptionLabel, "Book Description Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(languageTextField.getText(), languageLabel, "Book Language Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(publishYearTextField.getText(), publisherYearLabel, "Book Publisher & Year Cannot be Empty!");
        }
        if (!(DataValidation.isValidMaximumLength(ISBNTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(titleTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(authorTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(descriptionTextArea.getText(), 400)
                && DataValidation.isValidMaximumLength(languageTextField.getText(), 20)
                && DataValidation.isValidMaximumLength(publishYearTextField.getText(), 45))) {
            DataValidation.isValidMaximumLength(ISBNTextField.getText(), 13, isbnLabel, "Field Length exceeded, Limit 45");
            DataValidation.isValidMaximumLength(titleTextField.getText(), 45, titleLabel, "Field Length exceeded, Limit 45");
            DataValidation.isValidMaximumLength(authorTextField.getText(), 45, authorLabel, "Field Length exceeded, Limit 45");
            DataValidation.isValidMaximumLength(descriptionTextArea.getText(), 400, descriptionLabel, "Field Length exceeded, Limit 400");
            DataValidation.isValidMaximumLength(languageTextField.getText(), 45, languageLabel, "Field Length exceeded, Limit 45");
            DataValidation.isValidMaximumLength(publishYearTextField.getText(), 45, publisherYearLabel, "Field Length exceeded, Limit 45");
        }
    }

    @FXML
    private void clearLabels() {
        isbnLabel.setText("");
        bTitleColumn.setText("");
        authorLabel.setText("");
        descriptionLabel.setText("");
        languageLabel.setText("");
        publisherYearLabel.setText("");
    }

    @FXML
    private void clearFields() {
        ISBNTextField.setText("");
        titleTextField.setText("");
        authorTextField.setText("");
        descriptionTextArea.setText("");
        languageTextField.setText("");
        publishYearTextField.setText("");
    }

}
