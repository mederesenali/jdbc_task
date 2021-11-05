create table assessment
(
    assessment_id   bigserial
        primary key,
    company_id      bigint default 0,
    assessment_name varchar(100) not null
);


create table company
(
    company_id   bigserial
        primary key,
    company_name varchar(500)
);


create table company_department
(
    company_department_id  bigserial
        primary key,
    company_id             bigint                             not null,
    department_parent      bigint,
    department_name        varchar(200),
    department_description varchar(2500),
    department_date_added  timestamp with time zone default CURRENT_TIMESTAMP,
    department_active      integer                  default 1 not null,
    invite_token           varchar(255),
    order_number           integer                  default 0
);

create table company_department_manager
(
    company_department_manager_id bigserial primary key,
    company_department_id         bigint not null,
    user_id                       bigint not null
);


create table company_jobposting
(
    company_jobposting_id bigserial primary key,
    jobpost_title         varchar(200) not null,
    company_id            bigint       not null
);


create table company_jobposting_candidate_assessment
(
    company_jobposting_candidate_assessment_id bigserial primary key,
    company_jobposting_candidate_id            bigint                                 not null,
    assessment_id                              bigint                                 not null,
    date_assigned                              timestamp with time zone default now() not null,
    added_by                                   bigint                                 not null,
    assessment_complete                        integer                  default 0     not null,
    completion_date                            timestamp with time zone
);


create table company_jobposting_candidate
(
    company_jobposting_candidate_id bigserial primary key,
    company_jobposting_id           bigint not null,
    user_id                         bigint not null,
    candidate_date_added            timestamp with time zone default now(),
    first_name                      varchar(200),
    last_name                       varchar(200)
);


create table company_jobposting_interview_settings
(
    company_jobposting_interview_settings_id bigserial primary key,
    company_jobposting_id                    bigint                                                       not null,
    one_way_interview                        boolean                  default false,
    interview_contact_type                   varchar(200)             default 'manual'::character varying not null,
    send_interview_status_id                 bigint,
    interview_deadline                       varchar(200),
    intro_video_url                          varchar(255),
    outro_video_url                          varchar(255),
    intro_video_name                         varchar(255),
    outro_video_name                         varchar(255),
    interview_name                           varchar(200),
    date_added                               timestamp with time zone default now()
);


create table user_myaccount
(
    user_myaccount_id bigserial
        constraint user_myaccount_pkey
            primary key,
    user_id           bigint,
    company_id        bigint
);

create table "user"
(
    user_id         bigserial
        constraint user_pkey
            primary key,
    user_last_name  varchar(200),
    user_first_name varchar(200)
);

