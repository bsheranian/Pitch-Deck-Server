package Utils;

import model.Deck;
import model.PowerPointDeck;
import model.Slide;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class PowerPointStrategy implements DeckStrategy {



    private XMLSlideShow deck;

    @Override
    public void setDeck(Deck deck) throws RuntimeException {
        PowerPointDeck powerPointDeck;
        if (deck instanceof PowerPointDeck) {
            powerPointDeck = (PowerPointDeck) deck;
            this.deck = powerPointDeck.getDeck();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Deck createDeck() {
        deck = new XMLSlideShow();
        return new PowerPointDeck(deck);
    }

    @Override
    public void appendBlankSlide() {
        deck.createSlide();
    }

    @Override
    public void appendSlide(Slide slide) {
        if (!(slide instanceof PowerPointSlide)) {
            throw new RuntimeException();
        }
        PowerPointSlide powerPointSlide = (PowerPointSlide) slide;
        deck.createSlide().importContent(powerPointSlide.getSlide());
    }


    @Override
    public Slide getSlide(int position) {
        return new PowerPointSlide(deck.getSlides().get(position));
    }



    static public class PowerPointSlide extends Slide {
        private XSLFSlide slide;

        public PowerPointSlide(XSLFSlide slide) {
            this.slide = slide;
        }

        public XSLFSlide getSlide() {
            return slide;
        }

        public void setSlide(XSLFSlide slide) {
            this.slide = slide;
        }
    }
}
