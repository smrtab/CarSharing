package carsharing.data.entities;

public class Customer {

    private long id;

    private long rented_car_id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRentedCarId() {
        return rented_car_id;
    }

    public void setRentedCarId(long rented_car_id) {
        this.rented_car_id = rented_car_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
