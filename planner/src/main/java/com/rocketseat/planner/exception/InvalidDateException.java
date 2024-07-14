package com.rocketseat.planner.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message){
        super(message);
    }

    public static void validateTripDate(String start_at, String ends_at) throws InvalidDateException {
        LocalDateTime start = LocalDateTime.parse(start_at, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(ends_at, DateTimeFormatter.ISO_DATE_TIME);

        if (start.isAfter(end)) {
            throw new InvalidDateException("Invalid date error. Start date cannot be after end date.");
        }

    }

    public static void validateActivityDate(String occurs_at,LocalDateTime starts_at,LocalDateTime ends_at) throws InvalidDateException {
        LocalDateTime occursAt = LocalDateTime.parse(occurs_at,DateTimeFormatter.ISO_DATE_TIME);

        if(occursAt.isBefore(starts_at) || occursAt.isAfter(ends_at)){
            throw new InvalidDateException("Invalid date for activity.Please insert a date that match with the event date.");
        }
    }

}
