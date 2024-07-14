CREATE TABLE activities(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    title varchar(255) not null,
    occurs_at TIMESTAMP not null,
    trip_id UUID,
    FOREIGN KEY(trip_id) REFERENCES trips(id) ON DELETE CASCADE
);