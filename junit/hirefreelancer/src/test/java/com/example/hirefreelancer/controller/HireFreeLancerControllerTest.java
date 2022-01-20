package com.example.hirefreelancer.controller;

import com.example.hirefreelancer.services.HiringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HireFreeLancerControllerTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @MockBean
    private HiringService hiringService;
    //Injecting a MockBean to controller

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getMainPage() throws Exception {

        String expected = "Hi. We have 3 available freelancers";

        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        String actual = response.getBody();


        assertEquals(expected, actual);
    }


    @Test
    public void anotherPAge() throws Exception {

        when(hiringService.getAvailableFreelancerExperienceYearsCount()).thenReturn(8);
        int expected=8;
        int available=hiringService.getAvailableFreelancerExperienceYearsCount();

        assertEquals(expected, available);
    }

}
