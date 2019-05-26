CREATE TABLE IF NOT EXISTS features
(
    id           INTEGER AUTO_INCREMENT,
    display_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS device_features
(
    device_id  INTEGER NOT NULL,
    feature_id INTEGER NOT NULL,
    value      INTEGER NOT NULL,
    PRIMARY KEY (device_id, feature_id),
    CONSTRAINT device_features_device_fk FOREIGN KEY (device_id) REFERENCES devices (id),
    CONSTRAINT device_features_feature_fk FOREIGN KEY (feature_id) REFERENCES features (id)
);

CREATE TABLE IF NOT EXISTS task_features
(
    task_id    INTEGER NOT NULL,
    feature_id INTEGER NOT NULL,
    min_value  INTEGER,
    max_value  INTEGER,
    PRIMARY KEY (task_id, feature_id),
    CONSTRAINT task_features_task_fk FOREIGN KEY (task_id) REFERENCES tasks (id),
    CONSTRAINT task_features_feature_fk FOREIGN KEY (feature_id) REFERENCES features (id)
);