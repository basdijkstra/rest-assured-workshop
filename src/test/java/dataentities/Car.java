package dataentities;

import lombok.Data;

@Data
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
}
