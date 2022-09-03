package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String User_Name) {
        Parent root = null;
        if (User_Name != null) {
            try {

                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                HomePageController homePageController = loader.getController();
                homePageController.setUserInformation(User_Name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void saveSignUpDetails(ActionEvent event, String User_Name, String Password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExits = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sky_pharmacy_management", "root", "Phaphaly@11");
            psCheckUserExits = connection.prepareStatement("SELECT * FROM login_table WHERE User_Name = ?");
            psCheckUserExits.setString(1, User_Name);
            resultSet = psCheckUserExits.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO login_table (User_Name,Password) VALUES(?,?)");
                psInsert.setString(1, User_Name);
                psInsert.setString(2, Password);
                psInsert.executeUpdate();

                changeScene(event, "HomePage.fxml", "Welcome", User_Name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExits != null) {
                try {
                    psCheckUserExits.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void handle(ActionEvent event, String User_Name, String Password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sky_pharmacy_management", "root", "Phaphaly@11");
            preparedStatement = connection.prepareStatement("SELECT Password FROM login_table WHERE User_Name =?");
            preparedStatement.setString(1, User_Name);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievePassword = resultSet.getString("Password");
                    if (retrievePassword.equals(Password)) {
                        changeScene(event, "HomePage.fxml", "welcome", User_Name);
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("the provided credentials are not correct");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //

    public int saveCustomer(Customer customer) throws SQLException {
        Connection connection = null;
        PreparedStatement pst;
        ResultSet resultSet = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sky_pharmacy_management", "root", "Phaphaly@11");
        pst = connection.prepareStatement("INSERT INTO customer_table VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pst.setString(1, customer.getCustomer_ID());
        pst.setString(2, customer.getCustomer_Name());
        pst.setString(3, customer.getCustomer_Address());
        pst.setString(4, customer.getCustomer_Gender());
        pst.setString(5, customer.getCustomer_Contact());
        pst.setString(6, customer.getCustomer_Email());
        pst.setString(7, customer.getCustomer_Age());
        pst.setString(8, customer.getCustomer_ItemPurchase());
        pst.setString(9, customer.getCustomer_Quantity());

        return pst.executeUpdate();
    }

    public ObservableList<Customer> displayCustomer() throws SQLException {
        Connection connection = null;
        PreparedStatement pst;
        ResultSet resultSet = null;
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sky_pharmacy_management", "root", "Phaphaly@11");
        pst = connection.prepareStatement("SELECT * FROM customer_table;");
        resultSet = pst.executeQuery();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setCustomer_ID(resultSet.getString("Customer_ID"));
            customer.setCustomer_Name(resultSet.getString("Customer_FullName"));
            customer.setCustomer_Address(resultSet.getString("Customer_Address"));
            customer.setCustomer_Gender(resultSet.getString("Customer_Gender"));
            customer.setCustomer_Contact(resultSet.getString("Customer_Contact"));
            customer.setCustomer_Email(resultSet.getString("Customer_Email"));
            customer.setCustomer_Age(resultSet.getString("Customer_Age"));
            customer.setCustomer_ItemPurchase(resultSet.getString("Item_Purchase"));
            customer.setCustomer_Quantity(resultSet.getString("Quantity"));

            customers.add(customer);
        }
        return customers;
    }

    //medicine
    public int SaveMedicine(Medicine medicine) throws SQLException {
        Connection connection = null;
        PreparedStatement pst;
        ResultSet resultSet = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sky_pharmacy_management", "root", "Phaphaly@11");
        pst = connection.prepareStatement("INSERT INTO medicine_table VALUE(?,?,?,?,?,?,?,?)");
        pst.setString(1, medicine.getMedicine_ID());
        pst.setString(2, medicine.getMedicine_Name());
        pst.setString(3, medicine.getMedicine_Price());
        pst.setString(4, medicine.getCompany());
        pst.setString(5, medicine.getExpiring_Date());
        pst.setString(6, medicine.getQuantity());
        pst.setString(7, medicine.getFab_Date());
        pst.setString(8, medicine.getDescription());

        return pst.executeUpdate();
    }

    public ObservableList<Medicine> displayMedicine() throws SQLException {
        Connection connection = null;
        PreparedStatement pst;
        ResultSet resultSet = null;
        ObservableList<Medicine> medicines = FXCollections.observableArrayList();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sky_pharmacy_management", "root", "Phaphaly@11");
        pst = connection.prepareStatement("SELECT * FROM medicine_table;");
        resultSet = pst.executeQuery();
        while (resultSet.next()) {
            Medicine medicine = new Medicine();
            medicine.setMedicine_ID(resultSet.getString("Medicine_ID"));
            medicine.setMedicine_Name(resultSet.getString("Medicine_Name"));
            medicine.setMedicine_Price(resultSet.getString("Medicine_Price"));
            medicine.setCompany(resultSet.getString("Company"));
            medicine.setExpiring_Date(resultSet.getString("Expiring_Date"));
            medicine.setQuantity(resultSet.getString("Quantity"));
            medicine.setFab_Date(resultSet.getString("Fab_Date"));
            medicine.setDescription(resultSet.getString("Description"));

            medicines.add(medicine);

        }
        return medicines;
    }

    //Calculate bill
    public static void calculateBill(ActionEvent event,int Bill_ID,String Medicine_Name,String Quantity){
        Connection connection=null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckBilling_IDExists=null;
        ResultSet resultSet=null;

        try {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sky_pharmacy_management","root","Phaphaly@11");
            psCheckBilling_IDExists=connection.prepareStatement("SELECT * FROM billing_table WHERE Bill_ID=?");
            psCheckBilling_IDExists.setInt(1,Bill_ID);
            resultSet=psCheckBilling_IDExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Bill ID Already Exist!");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You Cannot Use This Bill ID Again.");
                alert.show();
            }
            else {
                psInsert=connection.prepareStatement("INSERT INTO billing_table(Bill_ID,Medicine_Name,Quantity) VALUES(?,?,?)");
                psInsert.setInt(1,Bill_ID);
                psInsert.setString(2,Medicine_Name);
                psInsert.setString(3,Quantity);
                psInsert.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }




}
