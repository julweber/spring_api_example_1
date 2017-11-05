package training.concepts.record.representers;

import training.concepts.record.Record;
import training.concepts.application.Representer;

public class ShortRepresenter extends Representer {

  protected Record record;

  protected ShortRepresenter() {}

  public ShortRepresenter(Record record) {
    this.record = record;
  }

  public Long getId() {
    if (this.record == null) {
      return null;
    }
    return this.record.getId();
  }

  public String getTitle() {
    if (this.record == null) {
      return null;
    }
    return this.record.getTitle();
  }

  public String getArtist() {
    if (this.record == null) {
      return null;
    }
    return this.record.getArtist();
  }

  public String getGenre() {
    if (this.record == null) {
      return null;
    }
    return this.record.getGenre();
  }


}
