package training.concepts.application;

// Representer for error messages as json responses
public class ErrorRepresenter extends Representer {
  private String errorCode;
  private String errorMessage;

  protected ErrorRepresenter() {}

  public ErrorRepresenter(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }
}
