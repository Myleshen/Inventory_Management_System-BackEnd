create table category
(
    id   bigserial not null,
    name varchar(255) unique,
    created_date    timestamp(6) with time zone,
    updated_date    timestamp(6) with time zone,
    primary key (id)
);

create table product
(
    id              bigserial not null,
    product_code    varchar(255) unique,
    qty             bigint,
    price           float(53),
    cgst_percentage float(53),
    sgst_percentage float(53),
    created_date    timestamp(6) with time zone,
    updated_date    timestamp(6) with time zone,
    primary key (id)
);

create table product_categories
(
    category_id bigint not null,
    product_id  bigint not null
);

alter table if exists product_categories
    add constraint FK7cpkh0ajt3apyej1vtjsvbbeb foreign key (category_id)
        references category;

alter table if exists product_categories
    add constraint FKppc5s0f38pgb35a32dlgyhorc foreign key (product_id)
        references product;
