package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.ControllerFeatures;
import model.MarbleSolitaireModelState;

/**
 * The BoardPanel class
 */
public class BoardPanel extends JPanel implements IBoardPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX,originY;
  private ControllerFeatures features;
  private int highlightRow, highlightCol;

  /**
   * BoardPanel constructor which initializes the model state as well the initial state of the game
   * board
   * @param state a MarbleSolitaireModelState object
   * @throws IllegalStateException if the class is unable to successfully read input or transmit
   * output
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    this.highlightRow = highlightCol - 1;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
                      , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize() * cellDimension / 2);

    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        switch (this.modelState.getSlotAt(i,j)) {
          case Invalid:
            g.drawImage(blankSlot, originX + i * cellDimension, originY + j * cellDimension, null);
            break;
          case Marble:
            g.drawImage(marbleSlot, originX + i * cellDimension, originY + j * cellDimension, null);
            break;
          case Empty:
            g.drawImage(emptySlot, originX + i * cellDimension, originY + j * cellDimension, null);
            break;
        }
      }
    }
    if ((highlightRow != -1) && (highlightCol != -1)) {
      g.setColor(Color.black);
      g.drawRect(originX + highlightRow * cellDimension,
              originY + highlightCol * cellDimension,
              cellDimension,
              cellDimension);
    }
  }

  @Override
  public void setHighlight(int row, int col) {
    this.highlightRow = row;
    this.highlightCol = col;
  }

  @Override
  public int getHighlightRow() {
    return highlightRow;
  }

  @Override
  public int getHighlightCol() {
    return highlightCol;
  }

  @Override
  public void resetHighlight() {
    this.highlightRow = -1;
    this.highlightCol = -1;
  }

  @Override
  public void setFeatures(ControllerFeatures features) {
    this.features = features;
    this.addMouseListener(new BoardMouseListener());
  }

  /**
   * BoardMouseListener class for listening to mouse clicking of user
   */
  private class BoardMouseListener extends MouseAdapter {

    /**
     * Listens to the mouse clicking of the user and inputs the row and column to teh controller
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      int row = (e.getX() - originX) / BoardPanel.this.cellDimension;
      int col = (e.getY() - originY) / BoardPanel.this.cellDimension;
      BoardPanel.this.features.input(row, col);
    }
  }
}
