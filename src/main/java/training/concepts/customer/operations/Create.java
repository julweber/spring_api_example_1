package training.concepts.customer.operations;

import training.concepts.OperationInterface;
import training.concepts.customer.Customer;
import training.concepts.customer.CustomerRepository;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;


@Service
public class Create implements OperationInterface {

  @Autowired
  private CustomerRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Map params = (Map) payload.get("params");
    String email = (String) params.get("email");
    Customer model = new Customer(email, null, null);

    repository.save(model);

    payload.put("httpStatus", 200);
    payload.put("model", model);
    payload.put("json", "{}");
    return payload;
  }
}
