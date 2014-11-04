# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table csruser (
  user_id                   varchar(255) not null,
  user_email                varchar(255),
  user_name                 varchar(255),
  user_password             varchar(255),
  constraint pk_csruser primary key (user_id))
;

create table tickets (
  ticket_id                 varchar(255) not null,
  customer_name             varchar(255),
  assigned_to               varchar(255),
  comments                  varchar(255),
  query_context             varchar(255),
  status                    varchar(255),
  user_id                   varchar(255),
  constraint pk_tickets primary key (ticket_id))
;

create sequence csruser_seq;

create sequence tickets_seq;

alter table tickets add constraint fk_tickets_csruser_1 foreign key (user_id) references csruser (user_id);
create index ix_tickets_csruser_1 on tickets (user_id);



# --- !Downs

drop table if exists csruser cascade;

drop table if exists tickets cascade;

drop sequence if exists csruser_seq;

drop sequence if exists tickets_seq;

