package training.concepts.customer.representers;

import training.concepts.customer.Customer;
import training.concepts.customer.representers.ShortRepresenter;
import training.concepts.application.Representer;

import java.util.List;
import java.util.ArrayList;

public class ListRepresenter extends Representer {
  protected List<ShortRepresenter> resources;

  public ListRepresenter(List<Customer> customers) {
    this.resources = convertToRepresentedList(customers);
  }

  public Integer getCount() {
    return resources.size();
  }

  public List<ShortRepresenter> getResources() {
    return this.resources;
  }

  // helper method for initializing the representer list
  private List convertToRepresentedList(List<Customer> inputList) {
    List<ShortRepresenter> list = new ArrayList<ShortRepresenter>();

    for (Customer cust : inputList) {
      ShortRepresenter rep = new ShortRepresenter(cust);
      list.add(rep);
    }
    return list;
  }
}
