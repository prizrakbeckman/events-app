package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.model.Band;
import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return this.eventRepository.findAllBy();
    }

    public void delete(Long id) {
        this.eventRepository.deleteById(id);
    }

    public Event updateEvent(Event event){
        this.eventRepository.save(event);
        return event;
    }

    public List<Event> getFilteredEvents(String query) {
    return this.eventRepository.findAllBy()
                .stream()
                .filter(event -> findMatch(event.getBands(), query).size() >0)
            .collect(Collectors.toList());
    }

    private List<Band> findMatch(Set<Band> bands, String query){
        return bands.stream()
                .filter(band -> band.getMembers()
                            .stream()
                            .filter(member -> member.getName().contains(query))
                            .findAny()
                            .isPresent())
                .collect(Collectors.toList());
    }
}
