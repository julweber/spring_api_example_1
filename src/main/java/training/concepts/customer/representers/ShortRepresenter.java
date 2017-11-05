package training.concepts.customer.representers;

import training.concepts.customer.Customer;
import training.concepts.application.Representer;

public class ShortRepresenter extends Representer {

  protected Customer customer;

  protected ShortRepresenter() {}

  public ShortRepresenter(Customer customer) {
    this.customer = customer;
  }

  protected Customer getCustomer() {
    return this.customer;
  }

  public Long getId() {
    if (this.customer == null) {
      return null;
    }
    return this.getCustomer().getId();
  }

  public String getEmail() {
    if (this.customer == null) {
      return null;
    }
    return this.getCustomer().getEmail();
  }

}
