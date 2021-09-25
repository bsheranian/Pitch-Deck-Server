package service;

import Utils.DeckManipulator;
import Utils.PowerPointStrategy;
import dao.SlideTemplateDAO;
import model.Deck;
import model.Slide;
import org.apache.poi.xslf.usermodel.*;
import request.CreateTeamSlideRequest;
import request.EmailRequest;

import java.io.FileInputStream;



public class CreateTeamSlideService extends ServiceTemplate<CreateTeamSlideRequest, Void> {

    private String filePath = "/Users/sheranian/Desktop/Untitled presentation.pptx";

    @Override
    public Void doRequest(CreateTeamSlideRequest request) {

        DeckManipulator deckManipulator = new DeckManipulator(new PowerPointStrategy());

        deckManipulator.createDeck();

        try {
            Deck importedDeck = new SlideTemplateDAO().getDeck();
            DeckManipulator importedDeckManipulator = new DeckManipulator(new PowerPointStrategy());
            importedDeckManipulator.setDeck(importedDeck);
            Slide importedSlide = importedDeckManipulator.getSlide(0);
            deckManipulator.appendSlide(importedSlide);

        } catch (Exception e) {

        }

        new EmailService().doRequest(new EmailRequest());

//        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
//
//        XSLFSlideLayout layout
//                = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
//        XSLFSlide slide = ppt.createSlide(layout);

//        XSLFTextShape titleShape = slide.getPlaceholder(0);
//        XSLFTextShape contentShape = slide.getPlaceholder(1);
//
//        for (XSLFShape shape : slide.getShapes()) {
//            if (shape instanceof XSLFAutoShape) {
//                // this is a template placeholder
//            }
//        }




            // get slide from existing PPT and paste it into current PPT

//            ppt.createSlide().importContent(slide1);

            // save current PTT



        return null;
    }
}
