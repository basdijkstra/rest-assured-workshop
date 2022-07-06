package dataentities;

public class Account {

    private int id;
    private String type;
    private String balance;

    public Account() {

    }

    public Account(String type) {
        this.id = 87654;
        this.type = type;
        this.balance = "0";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
