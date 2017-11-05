package training.api.record;

import training.Application;
import training.api.BaseController;
import training.concepts.record.operations.RecordListAll;

import training.concepts.record.Record;
import training.concepts.application.Representer;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class RecordControllerV1 extends BaseController {

  @Autowired
  private RecordListAll listOperation;

  // This is almost identical to the CustomerController implementation of the list Endpoints
  // The logic is encapsulated in the record.ListAll operation

  // GET a list of records -> record.ListAll operation
  @RequestMapping(value="/v1/records", method = RequestMethod.GET,
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

}
