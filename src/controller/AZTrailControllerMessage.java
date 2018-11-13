protected class AZTrailControllerMessage {
  private final int selection;
  private final String text;

  public AZTrailControllerMessage(int selection, String text) {
    this.selection = selection;
    this.text = text;
  }

  public int getSelection() {
    return this.selection;
  }

  public String getText() {
    return this.text;
  }
}
