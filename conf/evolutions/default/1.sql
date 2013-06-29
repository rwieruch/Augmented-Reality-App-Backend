# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apfingerprint (
  id                        bigint not null,
  fingerprint_id            bigint not null,
  mac_address               varchar(255),
  intensity                 integer,
  constraint pk_apfingerprint primary key (id))
;

create table access_point (
  id                        bigint not null,
  mac_adress                varchar(255),
  ssid                      varchar(255),
  constraint pk_access_point primary key (id))
;

create table fingerprint (
  id                        bigint not null,
  x                         float,
  y                         float,
  constraint pk_fingerprint primary key (id))
;

create table location (
  id                        bigint not null,
  floor                     integer,
  x                         float,
  y                         float,
  name                      varchar(255),
  constraint pk_location primary key (id))
;

create table note (
  id                        bigint not null,
  title                     varchar(255),
  text                      varchar(255),
  x                         float,
  y                         float,
  z                         float,
  viewable                  integer,
  editable                  boolean,
  user_email                varchar(255),
  constraint pk_note primary key (id))
;

create table number (
  id                        bigint not null,
  value                     float,
  constraint pk_number primary key (id))
;

create table room (
  id                        bigint not null,
  x                         float,
  y                         float,
  width                     float,
  height                    float,
  name                      varchar(255),
  constraint pk_room primary key (id))
;

create table session (
  id                        bigint not null,
  token                     varchar(255),
  timestamp                 timestamp,
  user_email                varchar(255),
  constraint pk_session primary key (id))
;

create table augmenteduser (
  email                     varchar(255) not null,
  password                  varchar(255),
  name                      varchar(255),
  type                      varchar(255),
  realname                  varchar(255),
  image                     varchar(255),
  info                      varchar(255),
  x                         float,
  y                         float,
  z                         float,
  constraint pk_augmenteduser primary key (email))
;

create sequence apfingerprint_seq;

create sequence access_point_seq;

create sequence fingerprint_seq;

create sequence location_seq;

create sequence note_seq;

create sequence number_seq;

create sequence room_seq;

create sequence session_seq;

create sequence augmenteduser_seq;

alter table apfingerprint add constraint fk_apfingerprint_fingerprint_1 foreign key (fingerprint_id) references fingerprint (id) on delete restrict on update restrict;
create index ix_apfingerprint_fingerprint_1 on apfingerprint (fingerprint_id);
alter table note add constraint fk_note_user_2 foreign key (user_email) references augmenteduser (email) on delete restrict on update restrict;
create index ix_note_user_2 on note (user_email);
alter table session add constraint fk_session_user_3 foreign key (user_email) references augmenteduser (email) on delete restrict on update restrict;
create index ix_session_user_3 on session (user_email);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists apfingerprint;

drop table if exists access_point;

drop table if exists fingerprint;

drop table if exists location;

drop table if exists note;

drop table if exists number;

drop table if exists room;

drop table if exists session;

drop table if exists augmenteduser;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists apfingerprint_seq;

drop sequence if exists access_point_seq;

drop sequence if exists fingerprint_seq;

drop sequence if exists location_seq;

drop sequence if exists note_seq;

drop sequence if exists number_seq;

drop sequence if exists room_seq;

drop sequence if exists session_seq;

drop sequence if exists augmenteduser_seq;

