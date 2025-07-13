CREATE TABLE IF NOT EXISTS weather (
    city TEXT PRIMARY KEY,
    temperature INT NOT NULL
);

INSERT INTO weather(city, temperature) VALUES ('Moscow', 20) ON CONFLICT DO NOTHING;