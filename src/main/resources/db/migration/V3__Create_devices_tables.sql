CREATE TABLE IF NOT EXISTS devices
(
    id              INTEGER AUTO_INCREMENT,
    current_task_id INTEGER,
    active_token    VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT device_task_fk FOREIGN KEY (current_task_id) REFERENCES tasks (id)
);

CREATE TABLE IF NOT EXISTS user_devices
(
    user_id   INTEGER NOT NULL,
    device_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, device_id),
    CONSTRAINT user_devices_user_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT user_devices_device_fk FOREIGN KEY (device_id) REFERENCES devices (id)
);

CREATE TABLE IF NOT EXISTS device_ping
(
    device_id     INTEGER NOT NULL,
    last_activity DATETIME,
    longitude     FLOAT,
    latitude      FLOAT,
    wifi_bssid    VARCHAR(255),
    lan_mac       VARCHAR(255),
    lan_id        VARCHAR(255),
    bluetooth_mac VARCHAR(255),
    CONSTRAINT device_ping_device_fk FOREIGN KEY (device_id) REFERENCES devices (id)
);