CREATE TABLE IF NOT EXISTS users
(
    id               INTEGER AUTO_INCREMENT,
    username         VARCHAR(255) NOT NULL,
    password_hash    VARCHAR(255) NOT NULL,
    email            VARCHAR(255) NOT NULL,
    can_create_tasks TINYINT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_tokens
(
    user_id INTEGER,
    token   VARCHAR(255),
    last_ip VARCHAR(255),
    PRIMARY KEY (user_id, token),
    CONSTRAINT user_tokens_user_fk FOREIGN KEY (user_id) REFERENCES users (id)
);