package sample;

public class Billing {
    private int Bill_ID;
    private  String Medicine_Name;
    private String Quantity;

    public Billing( String medicine_Name, String quantity) {
       this.Medicine_Name = medicine_Name;
       this.Quantity = quantity;
    }

    public int getBill_ID() {
        return Bill_ID;
    }

    public void setBill_ID(int bill_ID) {
        Bill_ID = bill_ID;
    }

    public String getMedicine_Name() {
        return Medicine_Name;
    }

    public void setMedicine_Name(String medicine_Name) {
        Medicine_Name = medicine_Name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
