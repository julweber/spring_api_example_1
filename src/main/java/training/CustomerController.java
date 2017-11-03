package training;

import training.Application;
import training.concepts.customer.operations.Create;
import training.concepts.customer.operations.Show;
import training.concepts.customer.Customer;

// remove later
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

// import get method
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CustomerController {

  @Autowired
  private Create createOperation;

  @Autowired
  private Show showOperation;

  @RequestMapping(value="/customers/{customerId}", method = RequestMethod.GET)
  public Customer get(@PathVariable("customerId") Long id) {
    Map<String, Object> payload = new HashMap();
    Map<String, Object> params = new HashMap();
    params.put("customerId", id);
    payload.put("params", params);

    Map result = showOperation.run(payload);
    Application.logger.info("Operation result: {}", result);
    Customer customer = (Customer) result.get("model");
    Application.logger.info("Customer result: {}", customer);
    return customer;
  }

  // @RequestMapping(value = "/customers", method = RequestMethod.GET)
  // public Customer customer() {
  //   // example operation usage
  //   Application.logger.info("Calling example operation customer.Create");
  //   Map<String, Object> payload = new HashMap();
  //   Map<String, Object> params = new HashMap();
  //   params.put("email", "test@example.com");
  //   payload.put("params", params);
  //
  //   Map result = createOperation.run(payload);
  //   Customer createdCustomer = (Customer) result.get("model");
  //
  //   Application.logger.info(createdCustomer.toString());
  //   Application.logger.info("");
  //   return createdCustomer;
  // }
}
