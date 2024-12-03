CREATE TABLE IF NOT EXISTS events (
    id SERIAL PRIMARY KEY,
    name varchar,
    description varchar,
    date_start date,
    date_end date,
    category varchar,
    photo varchar
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username varchar NOT NULL UNIQUE,
    password varchar NOT NULL,
    email varchar NOT NULL UNIQUE,
    role varchar
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id int not null,
    event_id int not null,
    status varchar
);

CREATE TABLE IF NOT EXISTS seats (
    id SERIAL PRIMARY KEY,
    seat_number int,
    row_number int,
    is_occupied int
);

CREATE TABLE IF NOT EXISTS seances (
    id SERIAL PRIMARY KEY,
    date_start date,
    time_start time
);

CREATE TABLE IF NOT EXISTS seat_seance (
    id SERIAL PRIMARY KEY,
    seat_id int not null,
    seance_id int not null
);