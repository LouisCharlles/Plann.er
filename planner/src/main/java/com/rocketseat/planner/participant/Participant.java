package com.rocketseat.planner.participant;


import com.rocketseat.planner.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
@Table(name = "participant")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "is_confirmed",nullable = false)
    private Boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "trip_id",nullable = false)
    private Trip trip;

    public Participant(String email, Trip tripId){
        this.email = email;
        this.trip = tripId;
        this.isConfirmed = false;
        this.name = "";
    }
}
