package com.qrbats.qrbats.functionalities.eventcreation.services.impl;

import com.qrbats.qrbats.entity.event.Event;
import com.qrbats.qrbats.entity.event.EventRepository;
import com.qrbats.qrbats.entity.event.EventRole;
import com.qrbats.qrbats.entity.module.ModuleRepository;
import com.qrbats.qrbats.functionalities.eventcreation.dto.RegisterEventRequest;
import com.qrbats.qrbats.functionalities.eventcreation.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository repository;
    private final ModuleRepository moduleRepository;

    @Override
    public Event createEvent(RegisterEventRequest request){
        Optional<Event> existEvent;
        Event event;
        if(request.getEventId() != null){
            existEvent = repository.findById(request.getEventId());
        }else {
            existEvent = repository
                    .findByEventNameAndEventDateAndEventTimeAndEventModuleCode(
                    request.getEventName(),request.getEventDate(),request.getEventTime(),request.getEventModuleName()
            );
        }
        event = existEvent.orElseGet(Event::new);
        event.setEventName(request.getEventName());
        event.setEventDate(request.getEventDate());
        event.setEventValidDate(request.getEventValidDate());
        event.setEventTime(request.getEventTime());
        event.setEventEndTime(request.getEventEndTime());
        event.setEventVenue(request.getEventVenue());
        event.setEventRole(EventRole.valueOf(request.getEventRole()));
        event.setEventAssignedUserId(request.getEventAssignedUserId());
        if(request.getEventModuleName() != null){
            event.setEventModuleCode(request.getEventModuleName());
        }else {
            event.setEventModuleCode(null);
        }

        return repository.save(event);
    }

    public Optional<Event> findAlreadyExistEvent(RegisterEventRequest request){
       return repository.findByEventNameAndEventDateAndEventTimeAndEventModuleCode(
                request.getEventName(),request.getEventDate(),request.getEventTime(),request.getEventModuleName()
        );
    }

    @Override
    public List<Event> getAllEventByModuleCode(String moduleCode) {
        Optional<List<Event>> eventList = repository.findAllByEventModuleCode(moduleCode);
        if (eventList.isPresent()){
            return eventList.get();
        }else {
            throw new RuntimeException("Given module code has no events.");
        }
    }

    @Override
    public List<Event> getAllEvent() {
        return repository.findAll();
    }

    @Override
    public void deleteEvent(Integer id) {
        repository.deleteById(id);
    }

}
