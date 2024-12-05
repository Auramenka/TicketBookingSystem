ALTER TABLE orders
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id)
REFERENCES users(id);

ALTER TABLE orders
ADD CONSTRAINT fk_event_id
FOREIGN KEY (event_id)
REFERENCES events(id);

ALTER TABLE orders
ADD CONSTRAINT fk_seat_seance_id
FOREIGN KEY (seat_seance_id)
REFERENCES seat_seance(id);

ALTER TABLE seances
ADD CONSTRAINT fk_event_id
FOREIGN KEY (event_id)
REFERENCES events(id);

ALTER TABLE seat_seance
ADD CONSTRAINT fk_seat_id
FOREIGN KEY (seat_id)
REFERENCES seats(id);

ALTER TABLE seat_seance
ADD CONSTRAINT fk_seance_id
FOREIGN KEY (seance_id)
REFERENCES seances(id);