INSERT INTO users (email, username, password, name, surname, phone, sex, user_role)
VALUES
    ('john.doe@example.com', 'student', '$2a$10$5ZFeVx3zNDL.Of5SneubhO2VzmOeQLUXfVtHQOZTPL3KCSzDImHQq', 'John', 'Doe', '555-1234', 'MALE', 'STUDENT'),
    ('jane.smith@example.com', 'student0', '$2a$10$5ZFeVx3zNDL.Of5SneubhO2VzmOeQLUXfVtHQOZTPL3KCSzDImHQq', 'Jane', 'Smith', '555-5678', 'FEMALE', 'STUDENT'),
    ('john.doe1@example.com', 'student1', '$2a$10$5ZFeVx3zNDL.Of5SneubhO2VzmOeQLUXfVtHQOZTPL3KCSzDImHQq', 'John', 'Doe', '555-1234', 'MALE', 'STUDENT'),
    ('john.doe2@example.com', 'student2', '$2a$10$5ZFeVx3zNDL.Of5SneubhO2VzmOeQLUXfVtHQOZTPL3KCSzDImHQq', 'John', 'Doe', '555-1234', 'MALE', 'STUDENT'),
    ('alice.johnson@example.com', 'qwerty', '$2a$10$5ZFeVx3zNDL.Of5SneubhO2VzmOeQLUXfVtHQOZTPL3KCSzDImHQq', 'Alice', 'Johnson', '555-8765', 'FEMALE', 'TEACHER');
--     ('bob.brown@example.com', 'bobbrown', '$2a$10$SJaQ14VxombAS4IOHbplZO/Ikdz8S/OENItjz6MF.fQz1kPIUNQXO', 'Bob', 'Brown', '555-4321', 'MALE');

INSERT INTO students (user_id)
VALUES
    ((SELECT id FROM users WHERE username = 'student')),
    ((SELECT id FROM users WHERE username = 'student1')),
    ((SELECT id FROM users WHERE username = 'student2')),
    ((SELECT id FROM users WHERE username = 'student0'));

INSERT INTO teachers (user_id, registered_at)
VALUES
    ((SELECT id FROM users WHERE username = 'qwerty'), '2024-05-01');
--     ((SELECT id FROM users WHERE username = 'student0'));

INSERT INTO users_roles (user_id, roles)
VALUES
    ((SELECT id FROM users WHERE username = 'student'), 'ROLE_STUDENT'),
    ((SELECT id FROM users WHERE username = 'student1'), 'ROLE_STUDENT'),
    ((SELECT id FROM users WHERE username = 'student2'), 'ROLE_STUDENT'),
    ((SELECT id FROM users WHERE username = 'student0'), 'ROLE_STUDENT');

INSERT INTO courses(name, description, teacher_id)
VALUES ('Math', 'super lessons', 1);

INSERT INTO requested_students_courses (student_id, course_id)
VALUES (1, 1);
