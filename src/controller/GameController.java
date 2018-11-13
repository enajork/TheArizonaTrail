import model.*;

public class GameController {

  // may not be void return
  public void loadingMenu(int choice) {
    if (choice < 1 || choice > 6) {
      System.err.println("Invalid Menu Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        // Travel the trail
        break;
      case 2:
        // Learn about the trail
        break;
      case 3:
        // See the Arizona top 10
        break;
      case 4:
        // turn sound off
        break;
      case 5:
        // choose management option
        break;
      case 6:
        // end
        break;
    }
    // return;
  }

  // may not be void return
  public profMenu(int choice) {
    if (choice < 1 || choice > 4) {
      System.err.println("Invalid Profession Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        // banker from ***Boston
        break;
      case 2:
        // carpenter from ***Ohio
        break;
      case 3:
        // farmer from ***Illinois
        break;
      case 4:
        // find out the differences between these choices
        break;
    }
    // return;
  }

  public void leaderNameMenu(String name) {
    //
  }

  public void partyNamesMenu(String[] names) {
    //
  }

  public void monthMenu(int choice) {
    if (choice < 1 || choice > 6) {
      System.err.println("Invalid Month Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        // March
        break;
      case 2:
        // April
        break;
      case 3:
        // May
        break;
      case 4:
        // June
        break;
      case 5:
        // July
        break;
      case 6:
        // Ask for advice
        break;
    }
  }

  public void storeMenu(int choice) {
    if (choice < 1 || choice > 5) {
      System.err.println("Invalid Store Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        //
        break;
      case 2:
        //
        break;
      case 3:
        //
        break;
      case 4:
        //
        break;
      case 5:
        //
        break;
    }
  }

  public void storeSubMenu(int quantity) {
    if (quantity < 1 || quantity > 10) {
      //
    }
    // add quantity to order
  }

  // public void

  public void learn() {
    //
  }

  public void topTen() {
    //
  }

  public void sound() {
    // turn on or turn off
  }

  public void manage() {
    //
  }

  public void end() {
    // may not need
  }


}
