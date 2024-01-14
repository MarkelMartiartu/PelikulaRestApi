package dambi.pelikularest.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import dambi.pelikularest.model.Pelikula;

@Repository
public interface PelikulaRepository {
    List<Pelikula> getPelikula();

    Pelikula getPelikulaById(Integer id);

    List<Pelikula> getPelikulaByGenre(String genre);

    Pelikula addPelikula(Pelikula pelikula);

    Pelikula updatePelikula(Integer id, Pelikula pelikula);

    Pelikula save(Pelikula pelikula);

    Pelikula deletePelikula(Integer id);
}
