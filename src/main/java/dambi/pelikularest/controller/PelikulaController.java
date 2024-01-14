package dambi.pelikularest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoWriteException;

import dambi.pelikularest.model.Pelikula;
import dambi.pelikularest.repositorio.PelikulaRepository;

@RestController
@RequestMapping("api/pelikula")
public class PelikulaController {

    @Autowired
    private PelikulaRepository pelikulaRepositorio;

    @GetMapping
    public List<Pelikula> getPelikula() {
        return pelikulaRepositorio.getPelikula();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelikula> getPelikulaById(@PathVariable Integer id) {
        Pelikula pelikula = pelikulaRepositorio.getPelikulaById(id);

        if (pelikula != null) {
            return new ResponseEntity<>(pelikula, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Pelikula>> getPelikulaByGenre(@PathVariable String genre) {
        List<Pelikula> pelikulaList = pelikulaRepositorio.getPelikulaByGenre(genre);

        if (!pelikulaList.isEmpty()) {
            return ResponseEntity.ok(pelikulaList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Pelikula> addPelikula(@RequestBody Pelikula addPelikula) {
        Pelikula addedPelikula = pelikulaRepositorio.addPelikula(addPelikula);

        if (addedPelikula != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedPelikula);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePelikula(@PathVariable Integer id, @RequestBody Pelikula updatedPelikula) {
        try {
            Pelikula existingPelikula = pelikulaRepositorio.getPelikulaById(id);

            if (existingPelikula != null) {
                existingPelikula.setTitle(updatedPelikula.getTitle());
                existingPelikula.setExtract(updatedPelikula.getExtract());
                existingPelikula.setYear(updatedPelikula.getYear());

                Pelikula savedPelikula = pelikulaRepositorio.save(existingPelikula);
                return ResponseEntity.ok(savedPelikula);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MongoWriteException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ezin izan da eguneratu: " + e.getMessage());
        }

    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deletePelikula(@PathVariable Integer id) {
        try {
            pelikulaRepositorio.deletePelikula(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
