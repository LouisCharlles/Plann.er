package com.rocketseat.planner.activity;


import com.rocketseat.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository repository;

    public ActivityResponse registerActivity(ActivityRequestPayload payload, Trip trip){
        Activity newActivity = new Activity(payload.title(), payload.occurs_at(),trip);
        this.repository.save(newActivity);
        return new ActivityResponse(newActivity.getId());
    }

    public List<ActivityData> getAllActivitiesFromEvent(UUID id){
        return this.repository.findByTripId(id).stream()
                .map(activity -> new com.rocketseat.planner.activity.ActivityData(activity.getId(),activity.getTitle(),activity.getOccursAt()))
                .toList();
    }
}
