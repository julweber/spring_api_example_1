package training.concepts;

import java.util.Map;
import java.util.HashMap;


public interface OperationInterface {
  public Map<String, Object> run(Map<String, Object> payload);
}
