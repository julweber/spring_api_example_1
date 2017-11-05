package training.concepts.record.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.record.Record;
import training.concepts.record.RecordRepository;
import training.concepts.record.representers.ListRepresenter;
import training.concepts.application.ErrorRepresenter;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

@Service
public class RecordListCustomer implements OperationInterface {

  // As you can see here the implementation is also similar to the according customer operation
  @Autowired
  private RecordRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    Map params = (Map) payload.get("params");
    Long customerId = (Long) params.get("customerId");

    if (customerId == null) {
      ErrorRepresenter errorRepresenter = new ErrorRepresenter("NO_CUSTOMERID",
        "No CustomerId provided!");
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("representer", errorRepresenter);
      payload.put("model", null);
      return payload;
    }

    Iterable<Record> all = repository.findByCustomerId(customerId);
    List<Record> model = Lists.newArrayList(all);
    Application.logger.info("Found Records: {}", model);
    ListRepresenter rep = new ListRepresenter(model);

    payload.put("httpStatus", HttpStatus.OK);
    payload.put("model", model);
    payload.put("representer", rep);
    return payload;
  }
}
