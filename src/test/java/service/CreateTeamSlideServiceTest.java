package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import request.CreateTeamSlideRequest;
import result.Result;

import static org.junit.jupiter.api.Assertions.*;


class CreateTeamSlideServiceTest {

    private static CreateTeamSlideRequest request;

    @BeforeAll
    public static void setup() {
        request = new CreateTeamSlideRequest();
        request.setClientEmail("bsheranian1@gmail.com");
        request.setClientName("Nate Wallace");
        request.setNumTeamMembers(3);
    }


    @Test
    public void createTeamSlideTest() {
        Result result = new CreateTeamSlideService().doRequest(request);
        assertTrue(result.isSuccess());

    }


}