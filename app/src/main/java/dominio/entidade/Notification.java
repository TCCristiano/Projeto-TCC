package dominio.entidade;

import dominio.entidade.Message;

public class Notification extends Message {

  private String fromName;

  public String getFromName() {
    return fromName;
  }

  public void setFromName(String fromName) {
    this.fromName = fromName;
  }

}
