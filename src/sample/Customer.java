package sample;

public class Customer {
    private String customer_ID;
    private String customer_Name;
    private String customer_Address;
    private String customer_Gender;
    private String customer_Contact;
    private String customer_Email;
    private String customer_Age;
    private String customer_ItemPurchase;
    private String customer_Quantity;

    public Customer() {
    }

    public Customer(String customer_ID, String customer_Name, String customer_Address, String customer_Gender,
                    String customer_Contact, String customer_Email, String customer_Age, String customer_ItemPurchase,
                    String customer_Quantity) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.customer_Address = customer_Address;
        this.customer_Gender = customer_Gender;
        this.customer_Contact = customer_Contact;
        this.customer_Email = customer_Email;
        this.customer_Age = customer_Age;
        this.customer_ItemPurchase = customer_ItemPurchase;
        this.customer_Quantity = customer_Quantity;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getCustomer_Address() {
        return customer_Address;
    }

    public void setCustomer_Address(String customer_Address) {
        this.customer_Address = customer_Address;
    }

    public String getCustomer_Gender() {
        return customer_Gender;
    }

    public void setCustomer_Gender(String customer_Gender) {
        this.customer_Gender = customer_Gender;
    }

    public String getCustomer_Contact() {
        return customer_Contact;
    }

    public void setCustomer_Contact(String customer_Contact) {
        this.customer_Contact = customer_Contact;
    }

    public String getCustomer_Email() {
        return customer_Email;
    }

    public void setCustomer_Email(String customer_Email) {
        this.customer_Email = customer_Email;
    }

    public String getCustomer_Age() {
        return customer_Age;
    }

    public void setCustomer_Age(String customer_Age) {
        this.customer_Age = customer_Age;
    }

    public String getCustomer_ItemPurchase() {
        return customer_ItemPurchase;
    }

    public void setCustomer_ItemPurchase(String customer_ItemPurchase) {
        this.customer_ItemPurchase = customer_ItemPurchase;
    }

    public String getCustomer_Quantity() {
        return customer_Quantity;
    }

    public void setCustomer_Quantity(String customer_Quantity) {
        this.customer_Quantity = customer_Quantity;
    }
}

