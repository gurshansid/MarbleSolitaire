package model;

import java.io.InputStreamReader;
import controller.MarbleSolitaireController;
import controller.MarbleSolitaireControllerImpl;
import view.EuropeanSolitaireTextView;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;

/**
 * The main program
 */
public class EnglishSolitaireMainProgram {
  public static void main(String[] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    EuropeanSolitaireModel eModel = new EuropeanSolitaireModel();
    EuropeanSolitaireTextView eView = new EuropeanSolitaireTextView(eModel);
    Readable readable = new InputStreamReader(System.in);
    MarbleSolitaireController msc = new MarbleSolitaireControllerImpl(eModel, eView, readable);
    msc.playGame();
  }
}
