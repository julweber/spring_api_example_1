package training.concepts.customer.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.customer.Customer;
import training.concepts.customer.CustomerRepository;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


@Service
public class ListAll implements OperationInterface {

  @Autowired
  private CustomerRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    Iterable<Customer> all = repository.findAll();
    List<Customer> model = Lists.newArrayList(all);
    Application.logger.info("Found Customers: {}", model);

    payload.put("httpStatus", HttpStatus.OK);
    payload.put("model", model);
    return payload;
  }
}
