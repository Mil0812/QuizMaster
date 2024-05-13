DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS section;
DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS test_type;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS section_test;

---Табличка "Користувач"
---3 NF
CREATE TABLE IF NOT EXISTS user
(
    id        UUID PRIMARY KEY,
    login     VARCHAR(20)  NOT NULL UNIQUE,
    password  VARCHAR(20)  NOT NULL,
    firstName VARCHAR(30)  NOT NULL,
    lastName  VARCHAR(30)  NOT NULL,
    email     VARCHAR(111) NOT NULL UNIQUE,
    status    VARCHAR(7)   NOT NULL,
    CHECK (status IN ('teacher', 'student')),
    CONSTRAINT users_login_key UNIQUE (login),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_login_min_length_check CHECK (LENGTH(login) >= 5)
);

---Табличка "Розділ"
---2 NF
CREATE TABLE IF NOT EXISTS section
(
    id   UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT section_name_key UNIQUE (name)
);

---Табличка "Тест"
---2 NF
CREATE TABLE IF NOT EXISTS test
(
    id             UUID PRIMARY KEY,
    section_id     id UUID     NOT NULL,
    author_id      UUID        NULL,
    type_id        UUID        NOT NULL,
    title          VARCHAR(40) NOT NULL,
    image          VARCHAR(30) NULL,
    question_count INT         NULL,
    CONSTRAINT tests_title_key UNIQUE (title),
    CONSTRAINT tests_section_id_fk FOREIGN KEY (section_id) REFERENCES section (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT tests_author_id_fk FOREIGN KEY (author_id) REFERENCES user (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT tests_type_id_fk FOREIGN KEY (type_id) REFERENCES test_type (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

---Табличка "Тип тесту"
---2 NF
CREATE TABLE IF NOT EXISTS test_type
(
    id                   UUID PRIMARY KEY,
    name                 VARCHAR(40)  NOT NULL,
    description          VARCHAR(130) NOT NULL,
    title                VARCHAR(40)  NULL,
    image                VARCHAR(30)  NULL,
    max_answer_count     INT          NOT NULL,
    correct_answer_count INT          NOT NULL,
    CONSTRAINT correct_answer_count CHECK (max_answer_count >= test_type.correct_answer_count),
    CONSTRAINT test_type_name_key UNIQUE (name)
);


---Табличка "Питання"
---2 NF
CREATE TABLE IF NOT EXISTS question
(
    id            UUID PRIMARY KEY,
    question_text VARCHAR(100) NOT NULL,
    test_id       UUID         NOT NULL,
    CONSTRAINT question_test_id_fk FOREIGN KEY (test_id) REFERENCES test (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

---Табличка "Відповідь"
---2 NF
CREATE TABLE IF NOT EXISTS answer
(
    id          UUID PRIMARY KEY,
    question_id UUID        NOT NULL,
    answer_text VARCHAR(50) NOT NULL,
    correctness BOOLEAN DEFAULT false,
    CONSTRAINT answer_question_id_fk FOREIGN KEY (question_id) REFERENCES question (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

---Табличка "Результат"
---3 NF
CREATE TABLE IF NOT EXISTS result
(
    id           UUID PRIMARY KEY,
    user_id      UUID NOT NULL,
    test_id      UUID NOT NULL,
    section_id   UUID NOT NULL,
    grade        INT  NOT NULL,
    date_of_test TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT grade_between_1_and_100 CHECK (grade BETWEEN 1 AND 100),
    CONSTRAINT result_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT result_test_id_fk FOREIGN KEY (test_id) REFERENCES test (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT result_section_id_fk FOREIGN KEY (section_id) REFERENCES section (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

---Табличка "Тест-розділ" (багато до багатьох)
CREATE TABLE section_test
(
    section_id UUID NOT NULL,
    test_id    UUID NOT NULL,
    PRIMARY KEY (section_id, test_id),
    CONSTRAINT fk_section_test_tests FOREIGN KEY (test_id) REFERENCES test (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_section_test_sections FOREIGN KEY (section_id) REFERENCES section (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);