package training.concepts.customer.operations;

import training.concepts.OperationInterface;
import training.concepts.customer.Customer;
import training.concepts.customer.CustomerRepository;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


@Service
public class Create implements OperationInterface {

  @Autowired
  private CustomerRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Map params = (Map) payload.get("params");
    Customer model = (Customer) params.get("customer");

    if (model.getPassword() == null) {
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      // TODO: add error response
      return payload;
    }

    if (!repository.findByEmail(model.getEmail()).isEmpty() ) {
      // TODO: add error response
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      return payload;
    }

    model.setRank(0);
    repository.save(model);
    payload.put("httpStatus", HttpStatus.CREATED);
    payload.put("model", model);

    return payload;
  }
}
