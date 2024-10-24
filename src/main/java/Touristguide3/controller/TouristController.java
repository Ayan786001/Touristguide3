package Touristguide3.controller;

import Touristguide3.model.TouristAttraction;
import Touristguide3.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attractions")
public class TouristController {

    private final TouristRepository touristRepository;

    @Autowired
    public TouristController(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    @GetMapping
    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
        Optional<TouristAttraction> attraction = touristRepository.getAttractionByName(name);
        return attraction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Void> addAttraction(@RequestBody TouristAttraction attraction) {
        touristRepository.addAttraction(attraction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{name}")
    public ResponseEntity<Void> updateAttraction(@PathVariable String name, @RequestBody TouristAttraction updatedAttraction) {
        boolean updated = touristRepository.updateAttraction(name, updatedAttraction);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable String name) {
        boolean deleted = touristRepository.deleteAttraction(name);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

