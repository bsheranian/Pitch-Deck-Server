package service;

import exception.DAOException;
import result.SendEmailResult;
import result.Result;
import utils.PresentationBuilder;
import utils.PowerPointStrategy;
import dao.SlideTemplateDAO;
import model.Slide;
import request.CreateTeamSlideRequest;
import request.SendEmailRequest;


import java.io.IOException;
import java.io.InputStream;


public class CreateTeamSlideService extends ServiceTemplate<CreateTeamSlideRequest, Result> {

    private final String COMPANY_EMAIL = "bsheranian1@gmail.com";
    private final String EMAIL_SUBJECT = "Here's your team slides!";
    private final String EMAIL_BODY = "Hello,\r\n"
            + "Please see the attached file for your newly created team slide.";
    private final String EMAIL_HTML = "<html>"
            + "<head></head>"
            + "<body>"
            + "<h1>Hello!</h1>"
            + "<p>Please see the attached file for a "
            + "list of customers to contact.</p>"
            + "</body>"
            + "</html>";


    @Override
    public Result doRequest(CreateTeamSlideRequest request) throws DAOException {
        System.out.println("Entering CreatTeamSlideRequest");

        PresentationBuilder masterTemplates = null;

        try {
            InputStream masterSlidesInputStream = new SlideTemplateDAO().getMasterTemplates();
            masterTemplates = new PresentationBuilder(new PowerPointStrategy());
            masterTemplates.importPresentationFromInputStream(masterSlidesInputStream);
        } catch (Exception e) {
            return new Result(false, "Could not access master slide templates: " + e.getMessage());
        }

        String filePath = String.format("/tmp/%s_TeamSlides.pptx", request.getClientName());

        PresentationBuilder pb = new PresentationBuilder(new PowerPointStrategy());

        pb.createPresentation();
        pb.setPageSize(masterTemplates.getPageSize());

        Slide template = masterTemplates.getSlide(0);
        pb.appendSlide(template);

        try {
            pb.saveToFile(filePath);
        } catch (IOException e) {
            return new Result(false, "Could not save presentation: " + e.getMessage());
        }

        SendEmailRequest sendEmailRequest = new SendEmailRequest();
        sendEmailRequest.setRecipients(new String[]{request.getClientEmail()});
        sendEmailRequest.setSender(COMPANY_EMAIL);
        sendEmailRequest.setSubject(EMAIL_SUBJECT);
        sendEmailRequest.setTextBody(EMAIL_BODY);
        sendEmailRequest.setHtmlBody(EMAIL_HTML);
        sendEmailRequest.setAttachmentFilePath(filePath);

        SendEmailResult sendEmailResult = new SendEmailService().doRequest(sendEmailRequest);

        if (sendEmailResult.isSuccess()) {
            return new Result(true, "Team slide created and sent successfully.");
        } else {
            return new Result(false, "Email failed: " + sendEmailResult.getMessage());
        }
    }
}
