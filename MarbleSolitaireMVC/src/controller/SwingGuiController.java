package controller;

import model.MarbleSolitaireModel;
import view.MarbleSolitaireGuiView;

/**
 * SwingGuiController class for controlling user interaction with GUI
 */
public class SwingGuiController implements ControllerFeatures {
  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;
  private int fromRow, fromCol, toRow, toCol;

  /**
   * SwingGuiController which initializes the model and view and refreshes the GUI
   * @param model a MarbleSolitaireModel object
   * @param view a MarbleSolitaireGuiView object
   */
  public SwingGuiController(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    this.model = model;
    this.view = view;

    this.view.refresh();
    this.view.setFeatures(this);

    fromRow = -1;
    fromCol = -1;
    toRow = -1;
    toCol = -1;
  }

  @Override
  public void input(int row, int col) {
    if (row >= 0 && col >= 0) {
      if (fromRow == -1) {
        fromRow = row;
        fromCol = col;
        this.view.highlightCell(fromRow, fromCol);
      }
      else {
        toRow = row;
        toCol = col;

        try {
          model.move(fromRow, fromCol, toRow, toCol);

          if (model.isGameOver()) {
            this.view.renderMessage("Game Over!");
          }
        }
        catch (IllegalArgumentException e) {
          this.view.renderMessage("Invalid move");
        }
        fromRow = fromCol = toRow = toCol = -1;
        this.view.highlightCell(fromRow, fromCol);
      }
      this.view.refresh();
    }
  }
}
