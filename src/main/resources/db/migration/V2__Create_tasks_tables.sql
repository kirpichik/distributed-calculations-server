CREATE TABLE IF NOT EXISTS tasks
(
    id           INTEGER AUTO_INCREMENT,
    owner_id     INTEGER      NOT NULL,
    display_name VARCHAR(255) NOT NULL,
    description  VARCHAR(4096),
    status       VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT task_user_fk FOREIGN KEY (owner_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS task_files
(
    task_id  INTEGER      NOT NULL,
    filename VARCHAR(255) NOT NULL,
    PRIMARY KEY (task_id, filename),
    CONSTRAINT file_task_fk FOREIGN KEY (task_id) REFERENCES tasks (id)
);

CREATE TABLE IF NOT EXISTS united_tasks
(
    task_id_first  INTEGER NOT NULL,
    task_id_second INTEGER NOT NULL,
    PRIMARY KEY (task_id_first, task_id_second),
    CONSTRAINT task_fk_first FOREIGN KEY (task_id_first) REFERENCES tasks (id),
    CONSTRAINT task_fk_second FOREIGN KEY (task_id_second) REFERENCES tasks (id)
);