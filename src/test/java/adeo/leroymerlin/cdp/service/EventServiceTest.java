package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.model.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class EventServiceTest {

    @Mock
    private EventService eventService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEvents_withNoParameters_listNotEmpty(){
        //when
        when(this.eventService.getEvents()).thenReturn(createListEvents());

        List<Event> events = this.eventService.getEvents();

        //then
        assertTrue(events.size()>0);
    }

    private List<Event> createListEvents() {
        return Arrays.asList(Event.builder()
                .id(1L)
                .comment("test")
                .title("title")
                .build());
    }

}
