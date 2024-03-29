package training;

// application configuration from environment variables: https://12factor.net/config

public class Configuration {

  public final Integer RESULTS_PER_PAGE = (System.getenv("RESULTS_PER_PAGE") != null) ? Integer.parseInt(System.getenv("RESULTS_PER_PAGE")) : 5;
  public final String DEFAULT_GENRE = (System.getenv("DEFAULT_GENRE") != null) ? System.getenv("DEFAULT_GENRE") : "fusion";
  public final String QUEUE_NAME = (System.getenv("QUEUE_NAME") != null) ? System.getenv("QUEUE_NAME") : "messages";

  public String toString() {
    String str = "";
    str += String.format("RESULTS_PER_PAGE: %d \n", RESULTS_PER_PAGE);
    str += String.format("DEFAULT_GENRE: %s \n", DEFAULT_GENRE);
    str += String.format("QUEUE_NAME: %s \n", QUEUE_NAME);
    return str;
  }
}
