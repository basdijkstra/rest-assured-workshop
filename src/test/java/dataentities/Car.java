package dataentities;

public class Car {

    private String make;
    private String model;
    private int modelYear;

    public Car() {

        make = "";
        model = "";
        modelYear = 0;
    }

    public Car(String make, String model, int modelYear) {

        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
    }

    public void setMake(String make) { this.make = make; }

    public void setModel(String model) { this.model = model; }

    public void setModelYear(int modelYear) { this.modelYear = modelYear; }

    public String getMake() { return this.make; }

    public String getModel() { return this.model; }

    public int getModelYear() { return this.modelYear; }
}
