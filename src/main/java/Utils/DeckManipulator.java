package Utils;

import model.Deck;
import model.Slide;

public class DeckManipulator {

    private DeckStrategy deckStrategy;

    public void setDeck(Deck deck) {
        deckStrategy.setDeck(deck);
    }

    public DeckManipulator(DeckStrategy deckStrategy) {
        this.deckStrategy = deckStrategy;
    }

    public Deck createDeck() {
        return deckStrategy.createDeck();
    }

    public void appendBlankSlide() {
        deckStrategy.appendBlankSlide();
    }

    public void appendSlide(Slide slide) {
        deckStrategy.appendSlide(slide);
    }

    public Slide getSlide(int postion) {
        return deckStrategy.getSlide(postion);
    }

}
