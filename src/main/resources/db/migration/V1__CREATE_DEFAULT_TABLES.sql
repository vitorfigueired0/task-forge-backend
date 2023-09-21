CREATE TABLE IF NOT EXISTS USERS
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(60)  NOT NULL,
    is_confirmed  BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS TASKS
(
    id            SERIAL PRIMARY KEY,
    user_id       BIGINT,
    name          VARCHAR(100) NOT NULL UNIQUE,
    description   TEXT,
    start_date    DATE NOT NULL,
    end_date      DATE,
    status        VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);

CREATE TABLE IF NOT EXISTS EMAIL_CONFIRMATION_CODE
(
    user_id       BIGINT,
    code          VARCHAR(6) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);

CREATE TABLE IF NOT EXISTS RESET_PASSWORD_CODE
(
    user_id       BIGINT,
    code          VARCHAR(6) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);

