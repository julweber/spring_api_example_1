package training.api;

import training.Application;
import training.concepts.customer.operations.Create;
import training.concepts.customer.operations.Show;
import training.concepts.customer.operations.Delete;
import training.concepts.customer.operations.Update;
import training.concepts.customer.operations.ListAll;
import training.concepts.customer.Customer;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CustomerController {

  @Autowired
  private Create createOperation;

  @Autowired
  private Show showOperation;

  @Autowired
  private ListAll listOperation;

  @Autowired
  private Update updateOperation;

  @Autowired
  private Delete deleteOperation;

  // GET a list of customers
  @RequestMapping(value="customers", method = RequestMethod.GET,
    produces = "application/json")
  public ResponseEntity<?> list() {
    Map<String, Object> payload = new HashMap();
    Map<String, Object> params = new HashMap();
    payload.put("params", params);

    Map result = listOperation.run(payload);
    ResponseEntity entity = new ResponseEntity<List<Customer>>((List<Customer>) result.get("model"),
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

  // POST new customer
  @RequestMapping(value = "/customers", method = RequestMethod.POST,
    produces = "application/json", consumes = "application/json")
  public ResponseEntity<?> create(@RequestBody Customer customer) {
    Map<String, Object> payload = new HashMap();
    Map<String, Object> params = new HashMap();
    params.put("customer", customer);
    payload.put("params", params);

    Map result = createOperation.run(payload);
    ResponseEntity entity = new ResponseEntity<Customer>((Customer) result.get("model"),
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

  // UPDATE (PUT) single customer
  @RequestMapping(value="/customers/{customerId}", method = RequestMethod.PUT,
    produces = "application/json", consumes = "application/json")
  public ResponseEntity<?> update(@PathVariable("customerId") Long id, @RequestBody Customer customer) {
    Map<String, Object> payload = new HashMap();
    Map<String, Object> params = new HashMap();
    params.put("customerId", id);
    params.put("customer", customer);
    payload.put("params", params);

    Map result = updateOperation.run(payload);
    ResponseEntity entity = new ResponseEntity<Customer>((Customer) result.get("model"),
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }


  // GET single customer
  @RequestMapping(value="/customers/{customerId}", method = RequestMethod.GET,
    produces = "application/json")
  public ResponseEntity<?> get(@PathVariable("customerId") Long id) {
    Map<String, Object> payload = new HashMap();
    Map<String, Object> params = new HashMap();
    params.put("customerId", id);
    payload.put("params", params);

    Map result = showOperation.run(payload);
    ResponseEntity<Customer> entity = new ResponseEntity<Customer>((Customer) result.get("model"),
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

  // DELETE single customer
  @RequestMapping(value="/customers/{customerId}", method = RequestMethod.DELETE,
    produces = "application/json")
  public ResponseEntity<Void> delete(@PathVariable("customerId") Long id) {
    Map<String, Object> payload = new HashMap();
    Map<String, Object> params = new HashMap();
    params.put("customerId", id);
    payload.put("params", params);

    Map result = deleteOperation.run(payload);
    ResponseEntity<Void> entity = new ResponseEntity<Void>((HttpStatus) result.get("httpStatus"));
    return entity;
  }

}
