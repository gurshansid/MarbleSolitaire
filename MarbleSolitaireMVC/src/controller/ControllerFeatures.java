package controller;

public interface ControllerFeatures {

  /**
   * Takes in user input, update the state and view
   * @param row the row of the user's move
   * @param col the column of the user's move
   */
  void input(int row, int col);
}
