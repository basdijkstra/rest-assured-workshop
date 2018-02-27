package dataentities;

public class Address {

    private String street;
    private int houseNumber;
    private int zipCode;
    private String city;

    public Address() {
    }

    public Address(String street, int houseNumber, int zipCode, String city) {

        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() { return this.street; }

    public int getHouseNumber() { return this.houseNumber; }

    public int getZipCode() { return this.zipCode; }

    public String getCity() { return this.city; }

    public void setStreet(String street) { this.street = street; }

    public void setHouseNumber(int houseNumber) { this.houseNumber = houseNumber; }

    public void setZipCode(int zipCode) { this.zipCode = zipCode; }

    public void setCity(String city) { this.city = city; }
}
