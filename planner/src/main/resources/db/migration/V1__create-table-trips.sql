CREATE TABLE trips(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    destination varchar(255) NOT NULL,
    starts_at TIMESTAMP not null,
    ends_at TIMESTAMP not null,
    is_confirmed BOOLEAN not null,
    owner_name varchar(255) not null,
    owner_email varchar(255) not null
);