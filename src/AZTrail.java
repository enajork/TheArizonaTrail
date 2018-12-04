import javafx.application.Application;
import view.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class AZTrail {
  /**
   * Main entry point for The Arizona Trail
   * Constructs the javafx application from AZTrailView
   * and launches it
   * @param args command line arguments
   */
  public static void main(String[] args) {
    Application.launch(AZTrailView.class, args);
  }
}
