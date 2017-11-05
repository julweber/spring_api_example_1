package training.api.customer;

import training.Application;
import training.api.BaseController;
import training.concepts.customer.operations.Create;
import training.concepts.customer.operations.Show;
import training.concepts.customer.operations.Delete;
import training.concepts.customer.operations.Update;
import training.concepts.customer.operations.ListAll;
import training.concepts.customer.Customer;
import training.concepts.application.Representer;
import training.concepts.customer.representers.FullRepresenter;
import training.concepts.customer.representers.ShortRepresenter;

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
public class CustomerControllerV1 extends BaseController {

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

  // GET a list of customers -> customer.ListAll operation
  @RequestMapping(value="/v1/customers", method = RequestMethod.GET,
    produces = "application/json")
  public ResponseEntity<?> list() {
    Map<String, Object> payload = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    payload.put("params", params);

    Map<String, Object> result = listOperation.run(payload);
    Representer list = (Representer) result.get("representer");

    ResponseEntity entity = new ResponseEntity<Representer>(list,
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

  // POST new customer -> customer.Create operation
  @RequestMapping(value = "/v1/customers", method = RequestMethod.POST,
    produces = "application/json", consumes = "application/json")
  public ResponseEntity<?> create(@RequestBody Customer customer) {
    Map<String, Object> payload = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("customer", customer);
    payload.put("params", params);

    Map<String, Object> result = createOperation.run(payload);
    Representer rep = (Representer) result.get("representer");
    ResponseEntity entity = new ResponseEntity<Representer>(rep,
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

  // UPDATE (PUT) single customer -> customer.Update operation
  @RequestMapping(value="/v1/customers/{customerId}", method = RequestMethod.PUT,
    produces = "application/json", consumes = "application/json")
  public ResponseEntity<?> update(@PathVariable("customerId") Long id, @RequestBody Customer customer) {
    Map<String, Object> payload = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("customerId", id);
    params.put("customer", customer);
    payload.put("params", params);

    Map<String, Object> result = updateOperation.run(payload);
    Representer rep = (Representer) result.get("representer");
    ResponseEntity entity = new ResponseEntity<Representer>(rep,
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }


  // GET single customer -> customer.Show operation
  @RequestMapping(value="/v1/customers/{customerId}", method = RequestMethod.GET,
    produces = "application/json")
  public ResponseEntity<?> get(@PathVariable("customerId") Long id) {
    Map<String, Object> payload = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("customerId", id);
    payload.put("params", params);

    Map<String, Object> result = showOperation.run(payload);
    Representer rep = (Representer) result.get("representer");
    ResponseEntity entity = new ResponseEntity<Representer>(rep,
      (HttpStatus) result.get("httpStatus"));
    return entity;
  }

  // DELETE single customer -> customer.Delete operation
  @RequestMapping(value="/v1/customers/{customerId}", method = RequestMethod.DELETE,
    produces = "application/json")
  public ResponseEntity<Void> delete(@PathVariable("customerId") Long id) {
    Map<String, Object> payload = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("customerId", id);
    payload.put("params", params);

    Map<String, Object> result = deleteOperation.run(payload);
    Representer rep = (Representer) result.get("representer");
    ResponseEntity entity = new ResponseEntity<Representer>(rep, (HttpStatus) result.get("httpStatus"));
    return entity;
  }

}
