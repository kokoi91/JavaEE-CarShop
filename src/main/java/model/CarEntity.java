package model;

public class CarEntity {
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear_() {
        return year_;
    }

    public void setYear_(int year_) {
        this.year_ = year_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBought() {
        return dateOfBought;
    }

    public void setDateOfBought(String dateOfBought) {
        this.dateOfBought = dateOfBought;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    private int carId;
    private String model;
    private int year_;
    private String name;
    private String dateOfBought;
    private int ownerId;

}
