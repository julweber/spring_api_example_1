package training.concepts.record.representers;

import training.concepts.record.Record;
import training.concepts.record.representers.ShortRepresenter;
import training.concepts.application.Representer;

import java.util.List;
import java.util.ArrayList;

public class ListRepresenter extends Representer {
  protected List<ShortRepresenter> resources;

  public ListRepresenter(List<Record> records) {
    this.resources = convertToRepresentedList(records);
  }

  public Integer getCount() {
    return resources.size();
  }

  public List<ShortRepresenter> getResources() {
    return this.resources;
  }

  // helper method for initializing the representer list
  private List convertToRepresentedList(List<Record> inputList) {
    List<ShortRepresenter> list = new ArrayList<ShortRepresenter>();

    for (Record rec : inputList) {
      ShortRepresenter rep = new ShortRepresenter(rec);
      list.add(rep);
    }
    return list;
  }
}
