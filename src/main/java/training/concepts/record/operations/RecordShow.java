package training.concepts.record.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.record.Record;
import training.concepts.record.RecordRepository;
import training.concepts.record.representers.FullRepresenter;
import training.concepts.application.ErrorRepresenter;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


@Service
public class RecordShow implements OperationInterface {

  @Autowired
  private RecordRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    // fetch an individual customer by ID
    Map params = (Map) payload.get("params");
    Long recordId = (Long) params.get("recordId");

    Record model = repository.findOne(recordId);

    Application.logger.info("Found Record: {}", model);

    // Set representer, httpstatus and model according to result
    if (model == null) {
      Application.logger.info("Could not find record with id: {}", recordId);
      ErrorRepresenter errorRepresenter = new ErrorRepresenter("NOT_FOUND",
        String.format("The record with id: %d could not be found!", recordId));
      payload.put("httpStatus", HttpStatus.NOT_FOUND);
      payload.put("representer", errorRepresenter);
      payload.put("model", null);
      return payload;
    } else {
      payload.put("httpStatus", HttpStatus.OK);
      FullRepresenter rep = new FullRepresenter(model);
      payload.put("representer", rep);
      payload.put("model", model);
      return payload;
    }

  }
}
