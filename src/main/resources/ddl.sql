DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS section;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS question_type;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS student_test;

---Табличка "Користувач"
---3 NF
    CREATE TABLE IF NOT EXISTS user
    (
        id       UUID PRIMARY KEY,
        login    VARCHAR(20)                       NOT NULL UNIQUE,
        password VARCHAR(20)                       NOT NULL UNIQUE,
        status   VARCHAR(7)                        NOT NULL,
        CHECK (status IN ('teacher', 'student'))
    );

---Табличка "Учень"
---3 NF
CREATE TABLE IF NOT EXISTS student
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    first_name VARCHAR(30)                       NOT NULL,
    last_name  VARCHAR(30)                       NOT NULL,
    date_of_birth      DATE,
    user_id    INTEGER,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);

---Табличка "Вчитель"
---3 NF
CREATE TABLE IF NOT EXISTS teacher
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    first_name VARCHAR(30)                       NOT NULL,
    last_name  VARCHAR(30)                       NOT NULL,
    patronymic VARCHAR(50)                       NOT NULL,
    email      VARCHAR(50),
    user_id    INTEGER,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);

---Табличка "Тест"
---2 NF
CREATE TABLE IF NOT EXISTS test
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    title      VARCHAR(20)                       NOT NULL,
    section_id INTEGER,
    author     INTEGER,
    image      BLOB,
    FOREIGN KEY (section_id)
        REFERENCES section (id),
    FOREIGN KEY (author)
        REFERENCES teacher (id)
);

---Табличка "Розділ"
---2 NF
CREATE TABLE IF NOT EXISTS section
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name        VARCHAR(30)                       NOT NULL,
    description VARCHAR(50)
);

---Табличка "Питання"
---2 NF
CREATE TABLE IF NOT EXISTS question
(
    id            INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    type_id       INT,
    image         BLOB,
    question_text VARCHAR(100)                      NOT NULL,
    test_id       INTEGER,
    FOREIGN KEY (test_id)
        REFERENCES test (id),
    FOREIGN KEY (type_id)
        REFERENCES question_type (id)
);

CREATE TABLE IF NOT EXISTS question_type
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    type VARCHAR(30) DEFAULT ('1 варіант відповіді') NOT NULL
);

---Табличка "Відповідь"
---2 NF
CREATE TABLE IF NOT EXISTS answer
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    question_id INTEGER                           NOT NULL,
    answer_text VARCHAR(50)                       NOT NULL,
    correctness BIT,
    FOREIGN KEY (question_id)
        REFERENCES question (id)
);

---Табличка "Результат"
---3 NF
CREATE TABLE IF NOT EXISTS result
(
    id           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    user_id      INTEGER                           NOT NULL,
    test_id      INTEGER                           NOT NULL,
    date_of_test DATE DEFAULT CURRENT_TIMESTAMP,
    grade        INT                               NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user (id),
    FOREIGN KEY (test_id)
        REFERENCES test (id),
    CHECK (grade BETWEEN 1 AND 100)
);

---Табличка "Студент-тест" (багато до багатьох)
CREATE TABLE student_test
(
    student_id INTEGER NOT NULL,
    test_id    INTEGER NOT NULL,
    FOREIGN KEY (student_id)
        REFERENCES student (id),
    FOREIGN KEY (test_id)
        REFERENCES test (id)
);
