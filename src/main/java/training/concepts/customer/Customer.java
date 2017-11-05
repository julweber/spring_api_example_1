package training.concepts.customer;

import training.concepts.record.Record;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Customer {

  @Id
  @SequenceGenerator(name = "seq_customers", sequenceName = "seq_customers")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_customers")
  private Long id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private Integer rank;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private Set<Record> records;

  protected Customer() {}

  public Customer(String email, String firstName, String lastName) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Customer(String email, String firstName, String lastName,
    String password) {
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      this.rank = 0;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getRank() {
    return this.rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }

  public Set<Record> getRecords() {
    return records;
  }

  public void setRecords(Set<Record> records) {
      this.records = records;
  }

  @Override
  public String toString() {
      return String.format(
              "Customer[id=%d, email='%s', firstName='%s', lastName='%s', rank='%d']",
              id, email, firstName, lastName, rank);
  }

}
