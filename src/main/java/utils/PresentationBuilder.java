package utils;

import model.Slide;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PresentationBuilder {

    private PresentationStrategy presentationStrategy;

    public PresentationBuilder(PresentationStrategy presentationStrategy) {
        this.presentationStrategy = presentationStrategy;
    }

    public void createPresentation() {
        presentationStrategy.createPresentation();
    }

    public void importPresentationFromInputStream(InputStream inputStream) throws IOException {
        presentationStrategy.importPresentationFromInputStream(inputStream);
    }

    public void appendBlankSlide() {
        presentationStrategy.appendBlankSlide();
    }

    public void appendSlide(Slide slide) {
        presentationStrategy.appendSlide(slide);
    }

    public Slide getSlide(int postion) {
        return presentationStrategy.getSlide(postion);
    }

    public Dimension getPageSize() {
        return presentationStrategy.getPageSize();
    }

    public void setPageSize(Dimension dimension) {
        presentationStrategy.setPageSize(dimension);
    }

    public File saveToFile(String filePath) throws IOException {
        return presentationStrategy.saveToFile(filePath);
    }

    public byte[] presentationToByteArray() throws IOException {
        return presentationStrategy.toByteArray();
    }
}
