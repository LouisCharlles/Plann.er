CREATE TABLE participant(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name varchar(255) not null,
    email varchar(255) not null,
    is_confirmed Boolean not null,
    trip_id UUID,
    FOREIGN KEY(trip_id) REFERENCES trips(id) ON DELETE CASCADE
);