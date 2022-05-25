package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.AdeoLeroyMerlinCDPRecruitmentApplication;
import adeo.leroymerlin.cdp.model.Event;
import adeo.leroymerlin.cdp.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdeoLeroyMerlinCDPRecruitmentApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class EventControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private EventService eventService;

    @Before
    public void setUp(){
    }

    @Test
    public void whenGetEvents_thenReturnNotEmptyList_thenStatus200() throws Exception {
        List<Event> events = createListEvents();
        this.eventService =  org.mockito.Mockito.mock(EventService.class);
        given(this.eventService.getEvents()).willReturn(events);

        this.mvc.perform(get("/api/events/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    private List<Event> createListEvents() {
        return Arrays.asList(Event.builder()
                .id(1L)
                .comment("test")
                .title("title")
                .build());
    }


}
