package dambi.pelikularest.model;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

import dambi.pelikularest.repositorio.PelikulaRepository;
import jakarta.annotation.PostConstruct;

@Repository
public class MongoDBPelikulaRepository implements PelikulaRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    @Autowired
    private MongoClient client;
    private MongoCollection<Pelikula> pelikulaCollection;

    @PostConstruct
    void init() {
        pelikulaCollection = client.getDatabase("pelikulak").getCollection("40spelikulak", Pelikula.class);
    }

    @Override
    public List<Pelikula> getPelikula() {
        return pelikulaCollection.find().sort(Sorts.descending("year")).limit(100).into(new ArrayList<>());
    }

    @Override
    public Pelikula getPelikulaById(Integer id) {
        return pelikulaCollection.find(eq("_id", id)).first();
    }

    // Genero bateko pelikulak itzultzen ditu,
    // baliteke pelikula bakar bat hainbat generotakoa izatea.
    @Override
    public List<Pelikula> getPelikulaByGenre(String genre) {
        List<Pelikula> pelikulaList = new ArrayList<>();

        pelikulaCollection.find(in("genres", genre)).into(pelikulaList);

        return pelikulaList;
    }

    @Override
    public Pelikula addPelikula(Pelikula pelikula) {
        pelikulaCollection.insertOne(pelikula);

        return pelikula;
    }

    @Override
    public Pelikula updatePelikula(Integer id, Pelikula pelikula) {
        pelikulaCollection.replaceOne(eq("id", pelikula.getId()), pelikula);
        return pelikula;
    }

    @Override
    public Pelikula save(Pelikula pelikula) {
        pelikulaCollection.insertOne(pelikula);
        return pelikula;
    }

    @Override
    public Pelikula deletePelikula(Integer id) {
        Pelikula deletedPelikula = pelikulaCollection.findOneAndDelete(eq("id", id));

        if (deletedPelikula != null) {
            return deletedPelikula;
        } else {
            throw new EmptyResultDataAccessException("Not found", 1);
        }
    }

}
