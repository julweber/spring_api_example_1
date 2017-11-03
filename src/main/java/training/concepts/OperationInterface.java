package training.concepts;

import java.util.Map;
import java.util.HashMap;

// this interface is used to implement operations within context
// an operation encapsulates a logical execution step list
// operations are only initialized and the executed with the run method
// operations deliver back a hash with processed results
public interface OperationInterface {
  public Map<String, Object> run(Map<String, Object> payload);
}
