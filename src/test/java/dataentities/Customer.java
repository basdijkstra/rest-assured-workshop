package dataentities;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String ssn;

    public Customer() {
        // This constructor is only added for deserialization purposes
        // It should not be called directly
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = new Address("Main Street 123", "Nothingville", "TX", "34543");
        this.phoneNumber = "+1 800 555-5555";
        this.ssn = "123 456 789";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
