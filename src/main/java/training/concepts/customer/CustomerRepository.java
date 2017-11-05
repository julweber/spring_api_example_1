package training.concepts.customer;

import training.concepts.customer.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// use the imports below to formulate custom sql queries
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  @Query("from Customer c where c.rank > :rank")
  public List<Customer> withRankHigherAs(@Param("rank") Integer rank);

  List<Customer> findById(Long id);
  List<Customer> findByEmail(String email);

}
