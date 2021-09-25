package Utils;

import model.Deck;
import model.Slide;

public interface DeckStrategy {

    public void setDeck(Deck deck) throws RuntimeException;

    public Deck createDeck();

    public void appendBlankSlide();

    public void appendSlide(Slide slide);

    public Slide getSlide(int postion);

}
