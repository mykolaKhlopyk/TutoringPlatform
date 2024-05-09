INSERT INTO users (email, username, password, name, surname, phone, sex, user_role)
VALUES
    ('john.doe@example.com', 'johndoe', '$2a$10$5ZFeVx3zNDL.Of5SneubhO2VzmOeQLUXfVtHQOZTPL3KCSzDImHQq', 'John', 'Doe', '555-1234', 'MALE', 'STUDENT'),
    ('jane.smith@example.com', 'janesmith', '$2a$10$5ZFeVx3zNDL.Of5SneubhO2VzmOeQLUXfVtHQOZTPL3KCSzDImHQq', 'Jane', 'Smith', '555-5678', 'FEMALE', 'STUDENT');
--     ('alice.johnson@example.com', 'alicej', '$2a$10$SJaQ14VxombAS4IOHbplZO/Ikdz8S/OENItjz6MF.fQz1kPIUNQXO', 'Alice', 'Johnson', '555-8765', 'Female'),
--     ('bob.brown@example.com', 'bobbrown', '$2a$10$SJaQ14VxombAS4IOHbplZO/Ikdz8S/OENItjz6MF.fQz1kPIUNQXO', 'Bob', 'Brown', '555-4321', 'Male');

INSERT INTO students (user_id)
VALUES
    ((SELECT id FROM users WHERE username = 'johndoe')),
    ((SELECT id FROM users WHERE username = 'janesmith'));

INSERT INTO users_roles (user_id, roles)
VALUES
    ((SELECT id FROM users WHERE username = 'johndoe'), 'ROLE_STUDENT'),
    ((SELECT id FROM users WHERE username = 'janesmith'), 'ROLE_STUDENT');

