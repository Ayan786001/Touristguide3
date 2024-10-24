package Touristguide3.service;

import Touristguide3.model.TouristAttraction;
import Touristguide3.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TouristService  {


    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    public Optional<TouristAttraction> getAttractionByName(String name) {
        return repository.getAttractionByName(name);
    }

    public void addAttraction(TouristAttraction attraction) {
        repository.addAttraction(attraction);
    }

    public boolean updateAttraction(String name, TouristAttraction updatedAttraction) {
        return repository.updateAttraction(name, updatedAttraction);
    }

    public boolean deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }
}

