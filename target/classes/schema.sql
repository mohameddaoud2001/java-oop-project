CREATE TABLE data_entries (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    data_name VARCHAR(255) NOT NULL,
    value FLOAT(53) NOT NULL
);