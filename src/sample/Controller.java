package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.Observable;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField textfield_id;

    @FXML
    private TextField textfield_title;

    @FXML
    private TextField textfield_author;

    @FXML
    private TextField textfield_year;

    @FXML
    private TextField textfield_pages;

    @FXML
    private TableView<Books> tvbooks;

    @FXML
    private TableColumn<Books, Integer> colid;

    @FXML
    private TableColumn<Books, String> coltitle;

    @FXML
    private TableColumn<Books, String> colauthor;

    @FXML
    private TableColumn<Books, Integer> colyear;

    @FXML
    private TableColumn<Books, Integer> colpages;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public Connection getConnection(){
        Connection connection;
        try {
            connection= DriverManager.getConnection("jdbc:mysql:localhost:8889/library","root","root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("error");
        }
            return null;
    }
    public ObservableList<Books> getBookslist() {
        ObservableList<Books> booksList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM BOOKS";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Books books;
            while (resultSet.next()) {
                books = new Books(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("pages"));
                booksList.add(books);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return booksList;
    }

    public void showBooks(){
        ObservableList<Books> list =  getBookslist();
        colid.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
        coltitle.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
        colauthor.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        colyear.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
        colpages.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));
    }
}
