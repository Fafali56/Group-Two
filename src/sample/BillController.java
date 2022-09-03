package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BillController implements Initializable{
    @FXML
    private Button butcalculateBill;
    @FXML
    private TableView<Medicine> tableMedicine;
    @FXML
    private TableColumn<Medicine, String> description;
    @FXML
    private TableColumn<Medicine, String> fab_date;
    @FXML
    private TableColumn<Medicine, String> quantity;
    @FXML
    private TableColumn<Medicine, String> ex_date;
    @FXML
    private TableColumn<Medicine, String> company;
    @FXML
    private TableColumn<Medicine, String> price;
    @FXML
    private TableColumn<Medicine, String> medicineName;
    @FXML
    private TableColumn<Medicine, String> medicineID;
    @FXML
    private TextField medName;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TextField txtBillingId;
    @FXML
    private TextArea txtDisplay;

    public void goToBilling(ActionEvent event) {
        DBUtils.changeScene(event,"HomePage.fxml","Home Page",null);
    }


    public void setTBCol() {
        medicineID.setCellValueFactory(new PropertyValueFactory("Medicine_ID"));
        medicineName.setCellValueFactory(new PropertyValueFactory("Medicine_Name"));
        price.setCellValueFactory(new PropertyValueFactory("Medicine_Price"));
        company.setCellValueFactory(new PropertyValueFactory("Company"));
        ex_date.setCellValueFactory(new PropertyValueFactory("Expiring_Date"));
        quantity.setCellValueFactory(new PropertyValueFactory("Quantity"));
        fab_date.setCellValueFactory(new PropertyValueFactory("Fab_Date"));
        description.setCellValueFactory(new PropertyValueFactory("Description"));
    }

    private Medicine medicine;
    public void getTabelValue(){
        medicine = tableMedicine.getSelectionModel().getSelectedItem();
        medName.setText(medicine.getMedicine_Name());
    }

    public void calculateBill(){
        try{
            double result = 0;
            if(txtQuantity.getText() != null) {
            result = Double.parseDouble(txtQuantity.getText()) * Double.parseDouble(medicine.getMedicine_Price());
        }
                txtDisplay.setText("" +
                "       \t\t\t\t*******WELCOME TO SKY PHARMACY********\n" +
                "Billing Id: "+txtBillingId.getText()+"\n" +
                "Medecine Name: "+medicine.getMedicine_Name()+"\n" +
                "Item Quantity: "+txtQuantity.getText()+"\n" +
                "Total Price: "+result+"\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTBCol();

        try {
            tableMedicine.setItems(new DBUtils().displayMedicine());
            butcalculateBill.setOnAction(e -> {
                if(!medName.getText().trim().isEmpty()&& !txtQuantity.getText().trim().isEmpty()&& !txtBillingId.getText().trim().isEmpty()){
                    DBUtils.calculateBill(e, Integer.parseInt(txtBillingId.getText()),medName.getText(),txtQuantity.getText());
                    calculateBill();
                }else{
                    System.out.println("Please fill in all information to bill");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to bill");
                    alert.show();
                }
            });
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }


}
