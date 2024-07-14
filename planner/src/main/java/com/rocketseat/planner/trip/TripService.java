package com.rocketseat.planner.trip;

import com.rocketseat.planner.exception.InvalidDateException;
import com.rocketseat.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository repository;

    @Autowired
    private ParticipantService participantService;

    public ResponseEntity<TripCreateResponse> registerNewTrip(TripRequestPayload payload){
        InvalidDateException.validateTripDate(payload.starts_at(), payload.ends_at());

        Trip newTrip = new Trip(payload);

        this.repository.save(newTrip);

        participantService.registerParticipantsToEvent(payload.emails_to_invite(), newTrip);

        return ResponseEntity.ok(new TripCreateResponse(newTrip.getId()));
    }

    public ResponseEntity<Trip> getTripDetails(UUID id) {
        Optional<Trip> trip = this.repository.findById(id);

        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Trip> updateTrip(UUID id, TripRequestPayload payload){
        Optional<Trip> trip = this.repository.findById(id);

        if (trip.isPresent()) {
            Trip rawTrip = trip.get();
            rawTrip.setDestination(payload.destination());
            rawTrip.setStartsAt(LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
            rawTrip.setEndsAt(LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME));

            this.repository.save(rawTrip);
            return ResponseEntity.ok(rawTrip);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Trip> confirmTrip(UUID id) {
        Optional<Trip> trip = this.repository.findById(id);

        if (trip.isPresent()) {
            Trip rawTrip = trip.get();
            rawTrip.setIsConfirmed(true);

            this.repository.save(rawTrip);
            this.participantService.triggerConfirmationEmailToParticipants(id);
            return ResponseEntity.ok(rawTrip);
        }

        return ResponseEntity.notFound().build();
    }
}
