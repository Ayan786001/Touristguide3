package Touristguide3.repository;

import Touristguide3.model.Location;
import Touristguide3.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TouristRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TouristAttraction> getAllAttractions() {
        String sql = "SELECT ta.id, ta.name, ta.description, ta.entry_fee, l.id AS location_id, l.city, l.country " +
                "FROM TouristAttraction ta JOIN Location l ON ta.location_id = l.id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new TouristAttraction(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("entry_fee"),
                new Location(rs.getInt("location_id"), rs.getString("city"), rs.getString("country"))
        ));
    }

    public Optional<TouristAttraction> getAttractionByName(String name) {
        String sql = "SELECT ta.id, ta.name, ta.description, ta.entry_fee, l.id AS location_id, l.city, l.country " +
                "FROM TouristAttraction ta JOIN Location l ON ta.location_id = l.id WHERE ta.name = ?";
        return jdbcTemplate.query(sql, new Object[]{name}, (rs, rowNum) -> new TouristAttraction(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("entry_fee"),
                new Location(rs.getInt("location_id"), rs.getString("city"), rs.getString("country"))
        )).stream().findFirst();
    }

    public void addAttraction(TouristAttraction attraction) {
        String sql = "INSERT INTO TouristAttraction (name, description, entry_fee, location_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getEntryFee(), attraction.getId());
    }

    public boolean updateAttraction(String name, TouristAttraction updatedAttraction) {
        String sql = "UPDATE TouristAttraction SET description = ?, entry_fee = ?, location_id = ? WHERE name = ?";
        int rowsAffected = jdbcTemplate.update(sql, updatedAttraction.getDescription(), updatedAttraction.getEntryFee(), updatedAttraction.getId(), name);
        return rowsAffected > 0;
    }

    public boolean deleteAttraction(String name) {
        String sql = "DELETE FROM TouristAttraction WHERE name = ?";
        int rowsAffected = jdbcTemplate.update(sql, name);
        return rowsAffected > 0;
    }
}

