package training.concepts.record;

import training.concepts.record.Record;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// use the imports below to formulate custom sql queries
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {

  public List<Record> findById(Long id);
  public List<Record> findByArtist(String artist);

  @Query("from Record r where r.genre = :genre")
  public List<Record> findByGenre(@Param("genre") Integer genre);

  // @Query("from Record r where r.customer_id = :customerId")
  public List<Record> findByCustomerId(Long customerId);

}
