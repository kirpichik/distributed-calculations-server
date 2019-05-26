CREATE TABLE IF NOT EXISTS task_results
(
    id           INTEGER AUTO_INCREMENT,
    task_id      INTEGER      NOT NULL,
    device_id    INTEGER      NOT NULL,
    status       VARCHAR(255) NOT NULL,
    date         DATETIME     NOT NULL,
    value_int    INTEGER,
    value_string VARCHAR(255),
    value_float  FLOAT,
    PRIMARY KEY (id),
    CONSTRAINT result_task_fk FOREIGN KEY (task_id) REFERENCES tasks (id),
    CONSTRAINT result_device_fk FOREIGN KEY (device_id) REFERENCES devices (id)
);

CREATE TABLE IF NOT EXISTS result_files
(
    result_id INTEGER      NOT NULL,
    filename  VARCHAR(255) NOT NULL,
    PRIMARY KEY (result_id, filename),
    CONSTRAINT task_result_fk FOREIGN KEY (result_id) REFERENCES task_results (id)
);