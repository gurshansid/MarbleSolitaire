package view;

import controller.SwingGuiController;
import model.EnglishSolitaireModel;
import model.MarbleSolitaireModel;

/**
 * The EnglishSolitaireGUIMain program
 */
public class EnglishSolitaireGUIMain {

  public static void main(String[] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    SwingGuiController controller = new SwingGuiController(model, view);
  }
}
