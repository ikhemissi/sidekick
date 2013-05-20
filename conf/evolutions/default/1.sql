# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table application (
  id                        bigint not null,
  code                      varchar(255),
  client                    varchar(255),
  constraint pk_application primary key (id))
;

create table backend (
  id                        bigint not null,
  environment_id            bigint,
  url                       varchar(255),
  name                      varchar(255),
  _type                     varchar(255),
  path                      varchar(255),
  description               varchar(255),
  constraint pk_backend primary key (id))
;

create table data (
  id                        bigint not null,
  release_id                bigint,
  constraint pk_data primary key (id))
;

create table data_check_environment (
  id                        bigint not null,
  environment_id            bigint,
  time                      timestamp,
  status                    smallint,
  error                     varchar(255),
  constraint pk_data_check_environment primary key (id))
;

create table database (
  id                        bigint not null,
  logic_server_id           bigint,
  name                      varchar(255),
  url                       varchar(255),
  user                      varchar(255),
  password                  varchar(255),
  enabled                   boolean,
  constraint pk_database primary key (id))
;

create table entitlement (
  id                        bigint not null,
  data_id                   bigint not null,
  treatment_id              bigint,
  operation                 varchar(255),
  creation                  timestamp,
  modification              timestamp,
  versioning                smallint,
  created_by_id             bigint,
  constraint pk_entitlement primary key (id))
;

create table entitlement_check (
  id                        bigint not null,
  entitlement_id            bigint,
  date                      timestamp,
  constraint pk_entitlement_check primary key (id))
;

create table entitlement_group (
  id                        bigint not null,
  application_id            bigint not null,
  name                      varchar(255),
  versioning                smallint,
  constraint pk_entitlement_group primary key (id))
;

create table entitlement_group_application (
  id                        bigint not null,
  application_id            bigint,
  name                      varchar(255),
  versioning                smallint,
  constraint pk_entitlement_group_application primary key (id))
;

create table environment (
  id                        bigint not null,
  name                      varchar(255),
  logic_id                  bigint,
  type_id                   bigint,
  mq                        varchar(255),
  constraint pk_environment primary key (id))
;

create table environment_type (
  id                        bigint not null,
  name                      varchar(255),
  rank                      smallint,
  client                    varchar(255),
  color                     varchar(255),
  constraint pk_environment_type primary key (id))
;

create table ibmi (
  id                        bigint not null,
  name                      varchar(255),
  iasp                      varchar(255),
  database_id               bigint,
  constraint pk_ibmi primary key (id))
;

create table metrology (
  id                        bigint not null,
  environment_id            bigint,
  url                       varchar(255),
  name                      varchar(255),
  _type                     varchar(255),
  path                      varchar(255),
  description               varchar(255),
  constraint pk_metrology primary key (id))
;

create table project (
  id                        bigint not null,
  name                      varchar(255),
  _type                     smallint,
  exportname                varchar(255),
  restart                   boolean,
  svnroot                   varchar(255),
  projectname               varchar(255),
  constraint pk_project primary key (id))
;

create table project_export (
  id                        bigint not null,
  svnbranch_id              bigint,
  exported_by_id            bigint,
  project_id                bigint,
  num                       smallint,
  export_date               timestamp,
  svnversion                integer,
  cancelled                 boolean,
  constraint pk_project_export primary key (id))
;

create table release (
  id                        bigint not null,
  data_id                   bigint,
  version_id                bigint,
  title                     varchar(255),
  status                    integer,
  due_date                  timestamp,
  constraint pk_release primary key (id))
;

create table reverse_proxy (
  id                        bigint not null,
  environment_id            bigint,
  name                      varchar(255),
  _type                     varchar(255),
  path                      varchar(255),
  description               varchar(255),
  constraint pk_reverse_proxy primary key (id))
;

create table rubrique (
  id                        bigint not null,
  data_id                   bigint not null,
  code                      varchar(255),
  pays                      varchar(255),
  invite                    varchar(255),
  versioning                smallint,
  constraint pk_rubrique primary key (id))
;

create table rubrique_check (
  id                        bigint not null,
  rubrique_id               bigint,
  date                      timestamp,
  constraint pk_rubrique_check primary key (id))
;

create table svnbranch (
  id                        bigint not null,
  root                      varchar(255),
  version_id                bigint,
  creation                  timestamp,
  constraint pk_svnbranch primary key (id))
;

create table treatment (
  id                        bigint not null,
  data_id                   bigint not null,
  created_by_id             bigint,
  application_id            bigint,
  code                      varchar(255),
  menu                      boolean,
  active                    boolean,
  creation                  timestamp,
  modification              timestamp,
  versioning                smallint,
  constraint pk_treatment primary key (id))
;

create table treatment_check (
  id                        bigint not null,
  treatment_id              bigint,
  date                      timestamp,
  constraint pk_treatment_check primary key (id))
;

create table user (
  id                        bigint not null,
  profile                   varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create table version (
  id                        bigint not null,
  svn_branch_id             bigint,
  code                      varchar(255),
  name                      varchar(255),
  description               varchar(255),
  due_date                  timestamp,
  constraint pk_version primary key (id))
;

create table web_server (
  id                        bigint not null,
  environment_id            bigint,
  url                       varchar(255),
  name                      varchar(255),
  _type                     varchar(255),
  path                      varchar(255),
  description               varchar(255),
  constraint pk_web_server primary key (id))
;


create table entitlement_user (
  entitlement_id                 bigint not null,
  user_id                        bigint not null,
  constraint pk_entitlement_user primary key (entitlement_id, user_id))
;

create table entitlement_entitlement_group_ap (
  entitlement_id                 bigint not null,
  entitlement_group_application_id bigint not null,
  constraint pk_entitlement_entitlement_group_ap primary key (entitlement_id, entitlement_group_application_id))
;

create table entitlement_data (
  entitlement_id                 bigint not null,
  data_id                        bigint not null,
  constraint pk_entitlement_data primary key (entitlement_id, data_id))
;

create table entitlement_group_entitlement_gr (
  entitlement_group_id           bigint not null,
  entitlement_group_application_id bigint not null,
  constraint pk_entitlement_group_entitlement_gr primary key (entitlement_group_id, entitlement_group_application_id))
;

create table entitlement_group_application_en (
  entitlement_group_application_id bigint not null,
  entitlement_group_id           bigint not null,
  constraint pk_entitlement_group_application_en primary key (entitlement_group_application_id, entitlement_group_id))
;

create table project_export_release (
  project_export_id              bigint not null,
  release_id                     bigint not null,
  constraint pk_project_export_release primary key (project_export_id, release_id))
;

create table release_project_export (
  release_id                     bigint not null,
  project_export_id              bigint not null,
  constraint pk_release_project_export primary key (release_id, project_export_id))
;

create table rubrique_data (
  rubrique_id                    bigint not null,
  data_id                        bigint not null,
  constraint pk_rubrique_data primary key (rubrique_id, data_id))
;

create table treatment_data (
  treatment_id                   bigint not null,
  data_id                        bigint not null,
  constraint pk_treatment_data primary key (treatment_id, data_id))
;

create table treatment_user (
  treatment_id                   bigint not null,
  user_id                        bigint not null,
  constraint pk_treatment_user primary key (treatment_id, user_id))
;

create table user_treatment (
  user_id                        bigint not null,
  treatment_id                   bigint not null,
  constraint pk_user_treatment primary key (user_id, treatment_id))
;

create table user_entitlement (
  user_id                        bigint not null,
  entitlement_id                 bigint not null,
  constraint pk_user_entitlement primary key (user_id, entitlement_id))
;
create sequence application_seq;

create sequence backend_seq;

create sequence data_seq;

create sequence data_check_environment_seq;

create sequence database_seq;

create sequence entitlement_seq;

create sequence entitlement_check_seq;

create sequence entitlement_group_seq;

create sequence entitlement_group_application_seq;

create sequence environment_seq;

create sequence environment_type_seq;

create sequence ibmi_seq;

create sequence metrology_seq;

create sequence project_seq;

create sequence project_export_seq;

create sequence release_seq;

create sequence reverse_proxy_seq;

create sequence rubrique_seq;

create sequence rubrique_check_seq;

create sequence svnbranch_seq;

create sequence treatment_seq;

create sequence treatment_check_seq;

create sequence user_seq;

create sequence version_seq;

create sequence web_server_seq;

alter table backend add constraint fk_backend_environment_1 foreign key (environment_id) references environment (id) on delete restrict on update restrict;
create index ix_backend_environment_1 on backend (environment_id);
alter table data add constraint fk_data_release_2 foreign key (release_id) references release (id) on delete restrict on update restrict;
create index ix_data_release_2 on data (release_id);
alter table data_check_environment add constraint fk_data_check_environment_envi_3 foreign key (environment_id) references environment (id) on delete restrict on update restrict;
create index ix_data_check_environment_envi_3 on data_check_environment (environment_id);
alter table database add constraint fk_database_logicServer_4 foreign key (logic_server_id) references ibmi (id) on delete restrict on update restrict;
create index ix_database_logicServer_4 on database (logic_server_id);
alter table entitlement add constraint fk_entitlement_data_5 foreign key (data_id) references data (id) on delete restrict on update restrict;
create index ix_entitlement_data_5 on entitlement (data_id);
alter table entitlement add constraint fk_entitlement_treatment_6 foreign key (treatment_id) references treatment (id) on delete restrict on update restrict;
create index ix_entitlement_treatment_6 on entitlement (treatment_id);
alter table entitlement add constraint fk_entitlement_createdBy_7 foreign key (created_by_id) references user (id) on delete restrict on update restrict;
create index ix_entitlement_createdBy_7 on entitlement (created_by_id);
alter table entitlement_check add constraint fk_entitlement_check_entitleme_8 foreign key (entitlement_id) references entitlement (id) on delete restrict on update restrict;
create index ix_entitlement_check_entitleme_8 on entitlement_check (entitlement_id);
alter table entitlement_group add constraint fk_entitlement_group_applicati_9 foreign key (application_id) references application (id) on delete restrict on update restrict;
create index ix_entitlement_group_applicati_9 on entitlement_group (application_id);
alter table entitlement_group_application add constraint fk_entitlement_group_applicat_10 foreign key (application_id) references application (id) on delete restrict on update restrict;
create index ix_entitlement_group_applicat_10 on entitlement_group_application (application_id);
alter table environment add constraint fk_environment_logic_11 foreign key (logic_id) references ibmi (id) on delete restrict on update restrict;
create index ix_environment_logic_11 on environment (logic_id);
alter table environment add constraint fk_environment_type_12 foreign key (type_id) references environment_type (id) on delete restrict on update restrict;
create index ix_environment_type_12 on environment (type_id);
alter table ibmi add constraint fk_ibmi_database_13 foreign key (database_id) references database (id) on delete restrict on update restrict;
create index ix_ibmi_database_13 on ibmi (database_id);
alter table metrology add constraint fk_metrology_environment_14 foreign key (environment_id) references environment (id) on delete restrict on update restrict;
create index ix_metrology_environment_14 on metrology (environment_id);
alter table project_export add constraint fk_project_export_svnbranch_15 foreign key (svnbranch_id) references svnbranch (id) on delete restrict on update restrict;
create index ix_project_export_svnbranch_15 on project_export (svnbranch_id);
alter table project_export add constraint fk_project_export_exportedBy_16 foreign key (exported_by_id) references user (id) on delete restrict on update restrict;
create index ix_project_export_exportedBy_16 on project_export (exported_by_id);
alter table project_export add constraint fk_project_export_project_17 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_project_export_project_17 on project_export (project_id);
alter table release add constraint fk_release_data_18 foreign key (data_id) references data (id) on delete restrict on update restrict;
create index ix_release_data_18 on release (data_id);
alter table release add constraint fk_release_version_19 foreign key (version_id) references version (id) on delete restrict on update restrict;
create index ix_release_version_19 on release (version_id);
alter table reverse_proxy add constraint fk_reverse_proxy_environment_20 foreign key (environment_id) references environment (id) on delete restrict on update restrict;
create index ix_reverse_proxy_environment_20 on reverse_proxy (environment_id);
alter table rubrique add constraint fk_rubrique_data_21 foreign key (data_id) references data (id) on delete restrict on update restrict;
create index ix_rubrique_data_21 on rubrique (data_id);
alter table rubrique_check add constraint fk_rubrique_check_rubrique_22 foreign key (rubrique_id) references rubrique (id) on delete restrict on update restrict;
create index ix_rubrique_check_rubrique_22 on rubrique_check (rubrique_id);
alter table svnbranch add constraint fk_svnbranch_version_23 foreign key (version_id) references version (id) on delete restrict on update restrict;
create index ix_svnbranch_version_23 on svnbranch (version_id);
alter table treatment add constraint fk_treatment_data_24 foreign key (data_id) references data (id) on delete restrict on update restrict;
create index ix_treatment_data_24 on treatment (data_id);
alter table treatment add constraint fk_treatment_createdBy_25 foreign key (created_by_id) references user (id) on delete restrict on update restrict;
create index ix_treatment_createdBy_25 on treatment (created_by_id);
alter table treatment add constraint fk_treatment_application_26 foreign key (application_id) references application (id) on delete restrict on update restrict;
create index ix_treatment_application_26 on treatment (application_id);
alter table treatment_check add constraint fk_treatment_check_treatment_27 foreign key (treatment_id) references treatment (id) on delete restrict on update restrict;
create index ix_treatment_check_treatment_27 on treatment_check (treatment_id);
alter table version add constraint fk_version_svnBranch_28 foreign key (svn_branch_id) references svnbranch (id) on delete restrict on update restrict;
create index ix_version_svnBranch_28 on version (svn_branch_id);
alter table web_server add constraint fk_web_server_environment_29 foreign key (environment_id) references environment (id) on delete restrict on update restrict;
create index ix_web_server_environment_29 on web_server (environment_id);



alter table entitlement_user add constraint fk_entitlement_user_entitleme_01 foreign key (entitlement_id) references entitlement (id) on delete restrict on update restrict;

alter table entitlement_user add constraint fk_entitlement_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table entitlement_entitlement_group_ap add constraint fk_entitlement_entitlement_gr_01 foreign key (entitlement_id) references entitlement (id) on delete restrict on update restrict;

alter table entitlement_entitlement_group_ap add constraint fk_entitlement_entitlement_gr_02 foreign key (entitlement_group_application_id) references entitlement_group_application (id) on delete restrict on update restrict;

alter table entitlement_data add constraint fk_entitlement_data_entitleme_01 foreign key (entitlement_id) references entitlement (id) on delete restrict on update restrict;

alter table entitlement_data add constraint fk_entitlement_data_data_02 foreign key (data_id) references data (id) on delete restrict on update restrict;

alter table entitlement_group_entitlement_gr add constraint fk_entitlement_group_entitlem_01 foreign key (entitlement_group_id) references entitlement_group (id) on delete restrict on update restrict;

alter table entitlement_group_entitlement_gr add constraint fk_entitlement_group_entitlem_02 foreign key (entitlement_group_application_id) references entitlement_group_application (id) on delete restrict on update restrict;

alter table entitlement_group_application_en add constraint fk_entitlement_group_applicat_01 foreign key (entitlement_group_application_id) references entitlement_group_application (id) on delete restrict on update restrict;

alter table entitlement_group_application_en add constraint fk_entitlement_group_applicat_02 foreign key (entitlement_group_id) references entitlement_group (id) on delete restrict on update restrict;

alter table project_export_release add constraint fk_project_export_release_pro_01 foreign key (project_export_id) references project_export (id) on delete restrict on update restrict;

alter table project_export_release add constraint fk_project_export_release_rel_02 foreign key (release_id) references release (id) on delete restrict on update restrict;

alter table release_project_export add constraint fk_release_project_export_rel_01 foreign key (release_id) references release (id) on delete restrict on update restrict;

alter table release_project_export add constraint fk_release_project_export_pro_02 foreign key (project_export_id) references project_export (id) on delete restrict on update restrict;

alter table rubrique_data add constraint fk_rubrique_data_rubrique_01 foreign key (rubrique_id) references rubrique (id) on delete restrict on update restrict;

alter table rubrique_data add constraint fk_rubrique_data_data_02 foreign key (data_id) references data (id) on delete restrict on update restrict;

alter table treatment_data add constraint fk_treatment_data_treatment_01 foreign key (treatment_id) references treatment (id) on delete restrict on update restrict;

alter table treatment_data add constraint fk_treatment_data_data_02 foreign key (data_id) references data (id) on delete restrict on update restrict;

alter table treatment_user add constraint fk_treatment_user_treatment_01 foreign key (treatment_id) references treatment (id) on delete restrict on update restrict;

alter table treatment_user add constraint fk_treatment_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_treatment add constraint fk_user_treatment_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_treatment add constraint fk_user_treatment_treatment_02 foreign key (treatment_id) references treatment (id) on delete restrict on update restrict;

alter table user_entitlement add constraint fk_user_entitlement_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_entitlement add constraint fk_user_entitlement_entitleme_02 foreign key (entitlement_id) references entitlement (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists application;

drop table if exists backend;

drop table if exists data;

drop table if exists data_check_environment;

drop table if exists database;

drop table if exists entitlement;

drop table if exists entitlement_user;

drop table if exists entitlement_entitlement_group_ap;

drop table if exists entitlement_data;

drop table if exists entitlement_check;

drop table if exists entitlement_group;

drop table if exists entitlement_group_entitlement_gr;

drop table if exists entitlement_group_application;

drop table if exists entitlement_group_application_en;

drop table if exists environment;

drop table if exists environment_type;

drop table if exists ibmi;

drop table if exists metrology;

drop table if exists project;

drop table if exists project_export;

drop table if exists project_export_release;

drop table if exists release;

drop table if exists release_project_export;

drop table if exists reverse_proxy;

drop table if exists rubrique;

drop table if exists rubrique_data;

drop table if exists rubrique_check;

drop table if exists svnbranch;

drop table if exists treatment;

drop table if exists treatment_data;

drop table if exists treatment_user;

drop table if exists treatment_check;

drop table if exists user;

drop table if exists user_treatment;

drop table if exists user_entitlement;

drop table if exists version;

drop table if exists web_server;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists application_seq;

drop sequence if exists backend_seq;

drop sequence if exists data_seq;

drop sequence if exists data_check_environment_seq;

drop sequence if exists database_seq;

drop sequence if exists entitlement_seq;

drop sequence if exists entitlement_check_seq;

drop sequence if exists entitlement_group_seq;

drop sequence if exists entitlement_group_application_seq;

drop sequence if exists environment_seq;

drop sequence if exists environment_type_seq;

drop sequence if exists ibmi_seq;

drop sequence if exists metrology_seq;

drop sequence if exists project_seq;

drop sequence if exists project_export_seq;

drop sequence if exists release_seq;

drop sequence if exists reverse_proxy_seq;

drop sequence if exists rubrique_seq;

drop sequence if exists rubrique_check_seq;

drop sequence if exists svnbranch_seq;

drop sequence if exists treatment_seq;

drop sequence if exists treatment_check_seq;

drop sequence if exists user_seq;

drop sequence if exists version_seq;

drop sequence if exists web_server_seq;

