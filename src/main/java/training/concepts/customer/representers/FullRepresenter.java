package training.concepts.customer.representers;

import training.concepts.customer.Customer;

// FullRepresenters derives from ShortRepresenter to have all fields of the short representation
// plus the fields for a full representation of a Customer
public class FullRepresenter extends ShortRepresenter {

  protected FullRepresenter() {}

  public FullRepresenter(Customer customer) {
    this.customer = customer;
  }

  public String getFirstName() {
    if (this.customer == null) {
      return null;
    }
    return this.getCustomer().getFirstName();
  }

  public String getLastName() {
    if (this.customer == null) {
      return null;
    }
    return this.getCustomer().getLastName();
  }

  public Integer getRank() {
    if (this.customer == null) {
      return null;
    }
    return this.getCustomer().getRank();
  }

}
