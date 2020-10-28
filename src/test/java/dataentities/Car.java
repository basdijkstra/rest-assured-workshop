package dataentities;

public class Car {

    private String make;
    private String model;
    private int modelYear;

    public Car() {
    }

    public Car(String make, String model, int modelYear) {

        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }
}
