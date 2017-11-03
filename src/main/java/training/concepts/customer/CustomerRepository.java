package training.concepts.customer;

import training.concepts.customer.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findById(Long id);
  List<Customer> findByEmail(String email);

}
