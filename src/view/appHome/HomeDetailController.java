package view.appHome;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.*;
import services.daoServices.BookService;
import services.daoServices.FeedbackService;
import services.daoServices.RequestService;
import services.navigation.MenuNavigation;
import services.navigation.NavigationInterface;
import util.utility.MenuBarController;
import util.utility.UtilityMethod;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class HomeDetailController implements Initializable {

    @FXML
    private BarChart<String, Integer> viewBarChart;

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private ComboBox<String> dateSortComboBox;

    @FXML
    private ImageView greyRequestBell;

    @FXML
    private ImageView redRequestBell;

    @FXML
    private Label requestNotificationLabel;

    @FXML
    private Label newFeedbackLabel;

    @FXML
    private ImageView greyFeedbackbell;

    @FXML
    private ImageView redFeedbackbell;

    @FXML
    private Label feedbackNotificationLabel;

    private NavigationInterface navigationInterface = new MenuNavigation();

    private String datePeriod;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePeriod = UtilityMethod.getYearMonth(String.valueOf(LocalDate.now()));

        setDateSortComboBoxData();
        setChartData();
        setNotificationData();
    }

    @FXML
    private void loadRequestItem(ActionEvent actionEvent) {
        navigationInterface.loadRequestHome(detailAnchorPane);
        MenuBarController.setMenuNumber(2);
        navigationInterface.getDesktopController().setStyle();
    }

    @FXML
    private void loadFeedback(ActionEvent actionEvent) {
        navigationInterface.loadFeedbackHome(detailAnchorPane);
        MenuBarController.setMenuNumber(3);
        navigationInterface.getDesktopController().setStyle();
    }

    private void setChartData() {


        LinkedList<ChartData> chartDataList = getBookViews(datePeriod);
        viewBarChart.getData().clear();
        XYChart.Series bookSeries = new XYChart.Series<>();
        bookSeries.setName("Book Names");
        for (ChartData chartData : chartDataList) {
            if (chartData.getChartValue() != 0) {
                if (chartData.getChartDate().equals(datePeriod)) {
                    bookSeries.getData().add(new XYChart.Data<>(chartData.getChartCategory(), chartData.getChartValue()));
                } else
                    bookSeries.getData().add(new XYChart.Data<>(chartData.getChartCategory(), chartData.getChartValue()));
            }
        }
        viewBarChart.getData().add(bookSeries);
    }

    private void setDateSortComboBoxData() {
        ObservableList<String> unSortedList = FXCollections.observableArrayList();
        for (ChartData chartData : getBookViews("All Views")) {
            unSortedList.add(chartData.getChartDate());
        }
        ObservableList<String> comboBoxList = UtilityMethod.removeStringDuplicates(unSortedList);
        comboBoxList.add("All Views");
        dateSortComboBox.setValue(datePeriod);
        dateSortComboBox.setItems(comboBoxList);
    }

    private LinkedList<ChartData> getBookViews(String period) {
        BookService bookService = new BookService();
        ObservableList<BookView> bookViewObservableList = bookService.loadAllBookViews();
        ObservableList<Book> bookObservableList = bookService.loadAllData();
        LinkedList<ChartData> chartDataList = new LinkedList<>();

        if (period.equals("All Views")) {
            for (Book book : bookObservableList) {
                Integer totalCounter = 0;
                for (BookView bookView : bookViewObservableList) {
                    if (UtilityMethod.seperateID(book.getbID()) == bookView.getViewBookID()) {
                        totalCounter++;
                    }
                }
                for (BookView bookView : bookViewObservableList) {
                    if (UtilityMethod.seperateID(book.getbID()) == bookView.getViewBookID()) {
                        chartDataList.add(new ChartData(book.getbTitle(), totalCounter, UtilityMethod.getYearMonth(bookView.getViewDate())));
                    }
                }


            }
        } else {
            for (Book book : bookObservableList) {
                Integer monthCounter = 0;
                for (BookView bookView : bookViewObservableList) {
                    if (UtilityMethod.seperateID(book.getbID()) == bookView.getViewBookID() && UtilityMethod.getYearMonth(bookView.getViewDate()).equals(datePeriod)) {
                        monthCounter++;
                    }
                }
                chartDataList.add(new ChartData(book.getbTitle(), monthCounter, period));
            }
        }


        return chartDataList;
    }

    @FXML
    private void sortChartData() {
        datePeriod = dateSortComboBox.getValue();
        setChartData();
    }

    private void setNotificationData() {
        FeedbackService feedbackService = new FeedbackService();
        ObservableList<Feedback> feedbackObservableList = feedbackService.loadAllFeedbacks();

        RequestService requestService = new RequestService();
        ObservableList<Request> requestObservableList = requestService.loadAllRequest();

        int feedbackCounter = 0;
        for (Feedback feedback : feedbackObservableList) {
            if (feedback.getfStatus().equals("Pending")) {
                feedbackCounter++;
            }
        }

        int requestCounter = 0;
        for (Request request : requestObservableList) {
            if (request.getrStatus().equals("Pending")) {
                requestCounter++;
            }
        }

        if (feedbackCounter == 0) {
            greyFeedbackbell.setVisible(true);
            redFeedbackbell.setVisible(false);
            feedbackNotificationLabel.setVisible(false);
        } else {
            greyFeedbackbell.setVisible(false);
            redFeedbackbell.setVisible(true);
            feedbackNotificationLabel.setVisible(true);
            feedbackNotificationLabel.setText(String.valueOf(feedbackCounter));
        }

        if (requestCounter == 0) {
            greyRequestBell.setVisible(true);
            redRequestBell.setVisible(false);
            requestNotificationLabel.setVisible(false);
        } else {
            greyRequestBell.setVisible(false);
            redRequestBell.setVisible(true);
            requestNotificationLabel.setVisible(true);
            requestNotificationLabel.setText(String.valueOf(requestCounter));
        }
    }

}
