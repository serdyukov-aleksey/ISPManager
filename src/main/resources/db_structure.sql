create table accounts
(
    id      integer          not null
        constraint accounts_pkey
            primary key,
    number  integer          not null,
    balance double precision not null
);

create table contact_details
(
    id        integer     not null
        constraint contact_details_pkey
            primary key,
    city      varchar(45) not null,
    street    varchar(45) not null,
    home      varchar(45) not null,
    apartment varchar(45) not null,
    email     varchar(45) not null,
    phone     varchar(45) not null
);

create table roles
(
    id   integer     not null
        constraint roles_pkey
            primary key,
    name varchar(45) not null
);

create table services
(
    id          integer      not null
        constraint services_pkey
            primary key,
    name        varchar(45)  not null,
    description varchar(255) not null
);

create table tariffs
(
    id          integer generated always as identity
        constraint tariffs_pkey
            primary key,
    name        varchar(45)      not null,
    description varchar(255)     not null,
    price       double precision not null,
    services_id integer          not null
);

create index fk_tariffs_services1_idx
    on tariffs (services_id);

create table users
(
    id                 integer not null
        constraint users_pkey
            primary key,
    login              varchar(45),
    password           varchar(150),
    first_name         varchar(45),
    last_name          varchar(45),
    surname            varchar(45),
    roles_id           integer,
    contact_details_id integer,
    accounts_id        integer,
    blocked            boolean
);

create index fk_users_roles_idx
    on users (roles_id);

create index fk_users_contact_details1_idx
    on users (contact_details_id);

create index fk_users_accounts1_idx
    on users (accounts_id);

create index "login_UNIQUE"
    on users (login);

create table users_has_tariffs
(
    users_id   integer not null,
    tariffs_id integer not null,
    constraint users_has_tariffs_pkey
        primary key (users_id, tariffs_id)
);

create index fk_users_has_tariffs_tariffs1_idx
    on users_has_tariffs (tariffs_id);

create index fk_users_has_tariffs_users1_idx
    on users_has_tariffs (users_id);

create table transactions
(
    id          integer generated always as identity
        constraint transactions_pkey
            primary key,
    timestamp   timestamp,
    account_id  integer
        constraint account_id_fk
            references accounts,
    amount      numeric(255, 2),
    is_credit   boolean,
    description varchar(255)
);

create index account_id
    on transactions (account_id);


