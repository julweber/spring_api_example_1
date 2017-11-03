package training.concepts.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String email;
  private String firstName;
  private String lastName;

  protected Customer() {}

  public Customer(String email, String firstName, String lastName) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // TODO: separate representation from model -> CustomerRepresenter
  public Long getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.firstName;
  }

  @Override
  public String toString() {
      return String.format(
              "Customer[id=%d, email='%s', firstName='%s', lastName='%s']",
              id, email, firstName, lastName);
  }

}
