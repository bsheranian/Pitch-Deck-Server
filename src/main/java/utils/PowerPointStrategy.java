package utils;

import model.Slide;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;

import java.awt.*;
import java.io.*;

public class PowerPointStrategy implements PresentationStrategy {

    private XMLSlideShow presentation;

    static public class PowerPointSlide implements Slide {
        private XSLFSlide slide;
        public PowerPointSlide(XSLFSlide slide) {
            this.slide = slide;
        }
        public void setSlide(XSLFSlide slide) {
            this.slide = slide;
        }
        public XSLFSlide getSlide() {
            return slide;
        }
        public XSLFSlideMaster getSlideMaster() {
            return slide.getSlideMaster();
        }
        public XSLFSlideLayout getSlideLayout() {
            return slide.getSlideLayout();
        }
    }

    public PowerPointStrategy() {}

    public PowerPointStrategy(XMLSlideShow presentation) {
        this.presentation = presentation;
    }




    @Override
    public void createPresentation() {
        presentation = new XMLSlideShow();
    }

    @Override
    public void importPresentationFromInputStream(InputStream inputStream) throws IOException {
        presentation = new XMLSlideShow(inputStream);
    }


    @Override
    public void appendBlankSlide() {
        presentation.createSlide();
    }

    @Override
    public void appendSlide(Slide slide) {
        if (!(slide instanceof PowerPointSlide)) {
            throw new RuntimeException();
        } else {
            PowerPointSlide powerPointSlide = (PowerPointSlide) slide;
            XSLFSlide newSlide = presentation.createSlide();
            newSlide.importContent(powerPointSlide.getSlide());
            newSlide.getSlideLayout().importContent(powerPointSlide.getSlideLayout());
        }
    }

    @Override
    public Slide getSlide(int position) {
        return new PowerPointSlide(presentation.getSlides().get(position));
    }


    @Override
    public Dimension getPageSize() {
        return presentation.getPageSize();
    }

    @Override
    public void setPageSize(Dimension dimension) {
        presentation.setPageSize(dimension);
    }


    @Override
    public File saveToFile(String filePath) throws IOException {

        File file = new File(filePath);
        FileOutputStream out = new FileOutputStream(file);
        presentation.write(out);
        out.close();

        System.out.println("Successfully saved file to " + filePath);
        return file;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        presentation.write(out);
        out.close();
        return out.toByteArray();
    }
}
