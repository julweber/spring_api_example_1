package training.concepts.record.json;

// This class is used for reading json input from requests and mapping them to this POJO
// It provides the interface for a POST request to create a record
public class RecordJson {
  private Long id;
  private String artist;
  private String title;
  private String format;
  private String genre;
  private Long customerId;

  protected RecordJson() {}

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getArtist() {
    return this.artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFormat() {
    return this.format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getGenre() {
    return this.genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }
}
