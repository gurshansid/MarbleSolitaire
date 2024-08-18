package view;

import javax.swing.*;
import controller.ControllerFeatures;

/**
 * This interface represents a GUI view for the game of marble solitaire.
 */
public interface MarbleSolitaireGuiView {
  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated and therefore it must be redrawn.
   */
  void refresh();

  /**
   * Display a message in a suitable area of the GUI.
   * @param message the message to be displayed
   */
  void renderMessage(String message);

  /**
   * Sets the features to the given features
   * @param features the features of the controller
   */
  void setFeatures(ControllerFeatures features);

  /**
   * Highlight a particular cell in the drawing of the board
   * @param row the row of the cell to be highlighted
   * @param col the column of the cell to be highlighted
   */
  void highlightCell(int row, int col);

  /**
   * Resets the highlight row and column to -1
   */
  void resetHighlight();

  /**
   * Gets the score label
   * @returns the score label
   */
  JLabel getScoreLabel();
}
