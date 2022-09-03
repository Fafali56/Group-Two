package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerDetailsController implements Initializable {
    @FXML
    private TextField txtquantity;
    @FXML
    private TextField txtItem;
    @FXML
    private TextField txtCusAge;
    @FXML
    private TextField txtCusEmail;
    @FXML
    private TextField txtCusContact;
    @FXML
    private TextField txtCusGender;
    @FXML
    private TextField txtCusAddress;
    @FXML
    private TextField txtCusName;
    @FXML
    private TextField txtCusID;
    @FXML
    private TableColumn<Customer,String> quantity;
    @FXML
    private TableColumn<Customer,String> itemPurchase;
    @FXML
    private TableColumn<Customer,String> customerAge;
    @FXML
    private TableColumn<Customer,String> customerEmail;
    @FXML
    private TableColumn<Customer,String> customerContact;
    @FXML
    private TableColumn<Customer,String> customerGender;
    @FXML
    private TableColumn<Customer,String> customerAddress;
    @FXML
    private TableColumn<Customer,String> customerName;
    @FXML
    private TableColumn<Customer,String> customerID;
    @FXML
    private TableView<Customer> tableCustomer;



    public void goToCustomer(ActionEvent event) {
        DBUtils.changeScene(event,"HomePage.fxml","Home Page",null);
    }


//
    private ObservableList<Customer>customer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTblCol();
        try {
            tableCustomer.setItems(new DBUtils().displayCustomer());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setTblCol(){
        customerID.setCellValueFactory(new PropertyValueFactory("Customer_ID"));
        customerName.setCellValueFactory(new PropertyValueFactory("Customer_Name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory("Customer_Address"));
        customerGender.setCellValueFactory(new PropertyValueFactory("Customer_Gender"));
        customerContact.setCellValueFactory(new PropertyValueFactory("Customer_Contact"));
        customerEmail.setCellValueFactory(new PropertyValueFactory("Customer_Email"));
        customerAge.setCellValueFactory(new PropertyValueFactory("Customer_Age"));
        itemPurchase.setCellValueFactory(new PropertyValueFactory("Customer_ItemPurchase"));
        quantity.setCellValueFactory(new PropertyValueFactory("Customer_Quantity"));
    }

    public void addCustomer(ActionEvent event) throws SQLException {

    Customer customer =  new Customer(txtCusID.getText(),txtCusName.getText(),txtCusAddress.getText(),
                        txtCusGender.getText(),txtCusContact.getText(),txtCusEmail.getText(),
                        txtCusAge.getText(),txtItem.getText(),txtquantity.getText() );
        if (new DBUtils().saveCustomer(customer) == 1){
            tableCustomer.setItems(new DBUtils().displayCustomer());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Customer Saved Successful");
            alert.setHeaderText(null);
            alert.showAndWait();
        }


    }
}