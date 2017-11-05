package training.concepts.record.representers;

import training.concepts.record.Record;

// FullRepresenters derives from ShortRepresenter to have all fields of the short representation
// plus the fields for a full representation of a Customer
public class FullRepresenter extends ShortRepresenter {

  protected FullRepresenter() {}

  public FullRepresenter(Record record) {
    this.record = record;
  }

  public String getFormat() {
    if (this.record == null) {
      return null;
    }
    return this.record.getGenre();
  }

  public Long getCustomerId() {
    if (this.record == null) {
      return null;
    }
    return this.record.getCustomer().getId();
  }

}
