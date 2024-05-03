--Заповнення даними таблиці "Користувач"
INSERT INTO user(login, password, status) VALUES
('lollipop321', 'lily098', 'student'),
('millenium', 'Mmm7', 'student'),
('waX99', 'Wax00', 'teacher'),
('sunny', '777', 'teacher');

--Заповнення даними таблиці "Викладач"
INSERT INTO teacher(first_name, last_name, patronymic, email, user_id) VALUES
('Мелорі', 'Джонсон', 'Яківна', 'wax@i.ua', 3),
('Аделія', 'Хоуп', 'Вікторівна', 'suunnyyy@gmail.com', 4);

--Заповнення даними таблиці "Учень"
INSERT INTO  student(first_name, last_name, grade, user_id) VALUES
('Міа', 'Лейм', 11, 2),
('Фанні', 'Марвен', 11, 1);

--Заповнення даними таблиці "Розділи"
INSERT INTO section(name, description) VALUES
('Access', 'Все про аксес'),
('DML команди', NULL);

--Заповнення даними таблиці "Тест"
INSERT INTO test(title, section_id, author, image) VALUES
('Основи Access', 1, NULL, NULL),
('SELECT', 2, 2, NULL);

--Заповнення даними таблиці "Тип питання"
    INSERT INTO question_type(type) VALUES
('1 варіант відповіді'),
('декілька варіантів відповіді');

--Заповнення даними таблиці "Питання"
INSERT INTO question(type_id, image, question_text, test_id) VALUES
(1, NULL, 'Якесь питання по темі аксесу 1...', 1),
(2, NULL, 'Якесь питання по темі аксесу 2...', 1),
(2, NULL, 'Якесь питання по темі DML->SELECT 1...', 2),
(1, NULL, 'Якесь питання по темі DML->SELECT 2...', 2);

--Заповнення даними таблиці "Відповідь"
INSERT INTO  answer(question_id, answer_text, correctness) VALUES
(1, '1 неправильний варіант відповіді', 0),
(1, 'ще 1 непр.варіант відповіді', 0),
(1, 'правильний варіант!', 1),
(2, 'right answer!', 1),
(2, 'not correct...', 0),
(2, 'one more right answer!', 1);
