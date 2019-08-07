package com.monsanto.interview.FortuneCookieGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.*;


import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebTestContextBootstrapper;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class FortuneCookieTest {

    private MockMvc mock;

    @Autowired
    private FortuneCookieBuilder fortuneCookieBuilder;

    @InjectMocks
    private FortuneCookieController fortuneCookieController;

    @Before
    public void setup(){
        mock = MockMvcBuilders.standaloneSetup(fortuneCookieController).build();
    }

    @Test
    public void fortuneCookieController_shouldReturnNewCookie() throws Exception {
        MvcResult firstResponse = mock.perform(
                get("/generateFortuneCookie?client=Barney&company=SuperStore"))
                .andReturn();
        MvcResult secondResponse = mock.perform(
                get("/generateFortuneCookie?client=Barney&company=SuperStore"))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();

        FortuneCookie fc1 = mapper.readValue(
                firstResponse.getResponse().getContentAsString(), FortuneCookie.class);
        FortuneCookie fc2 = mapper.readValue(
                secondResponse.getResponse().getContentAsString(), FortuneCookie.class);

        Assert.assertNotEquals(fc1,fc2);
    }

}
