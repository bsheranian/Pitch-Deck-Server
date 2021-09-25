package model;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.io.FileOutputStream;

public class PowerPointDeck implements Deck {

    private XMLSlideShow deck;

    public PowerPointDeck(XMLSlideShow deck) {
        this.deck = deck;
    }

    public XMLSlideShow getDeck() {
        return deck;
    }

    @Override
    public void saveDeckToFile(String filename) {
        try {
            FileOutputStream out = new FileOutputStream(filename);
            deck.write(out);
            out.close();
        } catch (Exception e) {

        }
    }
}
