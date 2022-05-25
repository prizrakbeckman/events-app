package adeo.leroymerlin.cdp.repository;

import adeo.leroymerlin.cdp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EventRepository extends JpaRepository<Event, Long>{

    @Override
    void deleteById(Long eventId);

    List<Event> findAllBy();

}
