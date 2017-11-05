package training.concepts.record.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.record.Record;
import training.concepts.record.RecordRepository;
import training.concepts.record.representers.ListRepresenter;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

// As bean injection is a little stupid and only checks the class name without package
// We need to prefix this class with Record, even if it is within the record package
// FIXME: Find a way to use class names multiple times in different packages as beans to clean this naming up

@Service
public class RecordListAll implements OperationInterface {

  // As you can see here the implementation is also similar to the according customer operation
  @Autowired
  private RecordRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    Iterable<Record> all = repository.findAll();
    List<Record> model = Lists.newArrayList(all);
    Application.logger.info("Found Records: {}", model);
    ListRepresenter rep = new ListRepresenter(model);

    payload.put("httpStatus", HttpStatus.OK);
    payload.put("model", model);
    payload.put("representer", rep);
    return payload;
  }
}
