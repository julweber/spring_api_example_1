package training.concepts.record;

import training.concepts.customer.Customer;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class Record {

  @Id
  @SequenceGenerator(name = "seq_records", sequenceName = "seq_records")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_records")
  private Long id;
  private String artist;
  private String title;
  private String format;
  private String genre;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName="id")
  private Customer customer;

  protected Record() {}

  public Record(String title) {
    this.title = title;
  }

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

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
      return String.format(
              "Record[id=%d, artist='%s', title='%s', format='%s', genre='%s']",
              id, artist, title, format, genre);
  }

}
