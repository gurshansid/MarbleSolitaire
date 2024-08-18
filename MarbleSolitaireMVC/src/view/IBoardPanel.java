package view;

import controller.ControllerFeatures;

/**
 * The IBoardPanel interface
 */
public interface IBoardPanel {

  /**
   * Set the highlight
   * @param row the row of the user
   * @param col the column of the user
   */
  void setHighlight(int row, int col);

  /**
   * Get the highlighted row
   * @return the highlighted row
   */
  int getHighlightRow();

  /**
   * Gets the highlighted column
   * @return the highlighted column
   */
  int getHighlightCol();

  /**
   * Resets the position of the highlight row and column to -1
   */
  void resetHighlight();

  /**
   * Sets the features to the given features
   * @param features the features of the controller
   */
  void setFeatures(ControllerFeatures features);
}
