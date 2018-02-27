package dataentities;

public class Car {

    private String make;
    private String model;
    private int year;

    public Car() {

        make = "";
        model = "";
        year = 0;
    }

    public Car(String make, String model, int year) {

        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void setMake(String make) { this.make = make; }

    public void setModel(String model) { this.model = model; }

    public void setYear(int year) { this.year = year; }

    public String getMake() { return this.make; }

    public String getModel() { return this.model; }

    public int getYear() { return this.year; }
}
