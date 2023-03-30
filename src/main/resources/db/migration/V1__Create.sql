create table customers
(
    id           uuid primary key default gen_random_uuid(),
    name         text not null,
    surname      text not null,
    phone_number text not null
);

create table purchase
(
    id          bigserial primary key,
    customer_id uuid      references customers (id) on delete set null,
    cost        bigint    not null,
    created_at  timestamp not null default now()
)