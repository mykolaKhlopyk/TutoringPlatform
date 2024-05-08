drop table if exists users_roles;
drop table if exists students_groups;
drop table if exists students_courses;
drop table if exists course_groups;
drop table if exists teachers_students;
drop table if exists not_confirmed_teachers_students;

drop table if exists lessons;
drop table if exists groups;
drop table if exists courses;
drop table if exists teachers;
drop table if exists students;
drop table if exists users;


create table users
(
    id       bigint generated by default as identity primary key,
    email    varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    name     varchar(255) not null,
    surname  varchar(255) not null,
    phone    varchar(255) not null,
    sex      varchar(255)
);

create table students
(
    id      bigint generated by default as identity primary key,
    user_id bigint,
    foreign key (user_id) references users (id)
);

create table teachers
(
    id            bigint generated by default as identity primary key,
    user_id       bigint,
    registered_at date not null,
    foreign key (user_id) references users (id)
);

create table courses
(
    id         bigint generated by default as identity primary key,
    name       varchar(255) not null,
    teacher_id bigint,
    literature text,
    tasks      text,
    links      text,
    foreign key (teacher_id) references teachers (id)
);

create table groups
(
    id         bigint generated by default as identity primary key,
    name       varchar(255) not null,
    teacher_id bigint,
    foreign key (teacher_id) references teachers (id)
);

create table lessons
(
    id               bigint generated by default as identity primary key,
    name             varchar(255) not null,
    duration_minutes int          not null,
    time_start       date         not null,
    group_id         bigint,
    foreign key (group_id) references groups (id)
);

create table not_confirmed_teachers_students
(
    student_id bigint,
    teacher_id   bigint,
    primary key (student_id, teacher_id),
    foreign key (student_id) references students (id),
    foreign key (teacher_id) references teachers (id)
);

create table teachers_students
(
    student_id bigint,
    teacher_id   bigint,
    primary key (student_id, teacher_id),
    foreign key (student_id) references students (id),
    foreign key (teacher_id) references teachers (id)
);

create table course_groups
(
    course_id bigint,
    group_id  bigint,
    primary key (course_id, group_id),
    foreign key (course_id) references courses (id),
    foreign key (group_id) references groups (id)
);

create table students_courses
(
    student_id bigint,
    course_id  bigint,
    primary key (student_id, course_id),
    foreign key (student_id) references students (id),
    foreign key (course_id) references courses (id)
);

create table students_groups
(
    student_id bigint,
    group_id   bigint,
    primary key (student_id, group_id),
    foreign key (student_id) references students (id),
    foreign key (group_id) references groups (id)
);

create table users_roles
(
    user_id bigint,
    roles   varchar(255),
    primary key (user_id, roles),
    foreign key (user_id) references users (id)
);
