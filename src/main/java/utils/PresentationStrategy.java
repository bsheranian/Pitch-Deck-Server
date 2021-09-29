package utils;

import model.Slide;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface PresentationStrategy {

    void createPresentation();

    void importPresentationFromInputStream(InputStream inputStream) throws IOException;

    void appendBlankSlide();

    void appendSlide(Slide slide);

    Slide getSlide(int postion);

    Dimension getPageSize();

    void setPageSize(Dimension dimension);

    File saveToFile(String filePath) throws IOException;

    byte[] toByteArray() throws IOException;


}
