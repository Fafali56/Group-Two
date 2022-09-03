package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private Button btnCustomerDetails;
    @FXML
    private Label lable_welcome;
    @FXML
    private Button logout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    DBUtils.changeScene(actionEvent,"SignIn.fxml","Login", null);
                }
        });


    }

    public void setUserInformation(String User_name){
        lable_welcome.setText("Welcome To Sky Pharmacy " +User_name + "!");
    }




    public void handle(ActionEvent event) {
            DBUtils.changeScene(event,"SignIn.fxml","Log in",null);
    }




    public void goToCustomers(ActionEvent event) {
        DBUtils.changeScene(event,"CustomerDetails.fxml","Customer Details",null);
    }

    public void goToMedicine(ActionEvent event) {
        DBUtils.changeScene(event,"MedicineDetails.fxml","Medicine Details",null);
    }

    public void goToBilling(ActionEvent event) {
        DBUtils.changeScene(event,"Bill.fxml","Billing Details",null);
    }
}
