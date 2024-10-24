package Touristguide3.model;

public class TouristAttraction { private int id;
    private String name;
    private String description;
    private double entryFee;
    private Location location;

    public TouristAttraction(int id, String name, String description, double entryFee, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.entryFee = entryFee;
        this.location = location;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {

    }


}
