-1. 创建系统资源表
    create table TBL_BTS_SYS_FUNCTION
    (
      func_id              VARCHAR2(20) not null,
      func_name            VARCHAR2(20),
      func_father_id       VARCHAR2(20),
      func_desc            VARCHAR2(100),
      func_remark          VARCHAR2(255),
      func_disable_tag     CHAR(1) default 1,
      func_create_by       VARCHAR2(20),
      func_create_datetime TIMESTAMP(6),
      func_update_by       VARCHAR2(20),
      func_update_datetime TIMESTAMP(6),
      func_tag             CHAR(1) default 0,
      func_level           CHAR(1) default 4,
      func_url             VARCHAR2(255),
      func_icon            VARCHAR2(20),
      func_priority        NUMBER default 99 not null
    );
    comment on column TBL_BTS_SYS_FUNCTION.func_disable_tag  is '0-禁用 1-启用';
    comment on column TBL_BTS_SYS_FUNCTION.func_tag  is '0-菜单 1-资源';
    comment on column TBL_BTS_SYS_FUNCTION.func_level  is '1-一级菜单 2-二级菜单 3-三级菜单 4-资源';
    comment on column TBL_BTS_SYS_FUNCTION.func_url  is '功能链接';
    comment on column TBL_BTS_SYS_FUNCTION.func_icon  is '功能图标';
    comment on column TBL_BTS_SYS_FUNCTION.func_priority  is '优先级（值越小优先级越高）';
    alter table TBL_BTS_SYS_FUNCTION add primary key (FUNC_ID)  using index ;

--2. 创建系统角色表
    create table TBL_BTS_SYS_ROLE
    (
      role_id                  VARCHAR2(20) not null,
      role_name                VARCHAR2(50) not null,
      role_disable_tag         CHAR(1) default 1,
      role_remark              VARCHAR2(255),
      role_create_by           VARCHAR2(20),
      role_create_datetime     TIMESTAMP(6),
      role_updatetime_by       VARCHAR2(20),
      role_updatetime_datetime TIMESTAMP(6)
    )
    ;
    comment on column TBL_BTS_SYS_ROLE.role_disable_tag  is '0-禁用 1-启用';
    create unique index ROLE_NAME_UK on TBL_BTS_SYS_ROLE (ROLE_NAME);
    alter table TBL_BTS_SYS_ROLE  add primary key (ROLE_ID)  using index ;
--3. 创建角色资源表
    create table TBL_BTS_SYS_ROLE_FUNC
    (
      role_id                   VARCHAR2(20) not null,
      func_id                   VARCHAR2(20) not null,
      role_func_remark          VARCHAR2(255),
      role_func_create_by       VARCHAR2(20),
      role_func_create_datetime TIMESTAMP(6),
      role_func_update_by       VARCHAR2(20),
      role_func_update_datetime TIMESTAMP(6)
    )
    ;
    alter table TBL_BTS_SYS_ROLE_FUNC  add primary key (ROLE_ID, FUNC_ID)  using index ;
--4. 创建用户表
    create table TBL_BTS_SYS_USR
    (
      usr_id          VARCHAR2(20) not null,
      usr_name        VARCHAR2(20) not null,
      usr_pwd         VARCHAR2(64) not null,
      usr_remark      VARCHAR2(255),
      usr_disable_tag CHAR(1) default 1 not null,
      usr_email       VARCHAR2(50),
      usr_create_by   VARCHAR2(20),
      usr_create_date TIMESTAMP(6),
      usr_update_by   VARCHAR2(20),
      usr_update_date TIMESTAMP(6),
      usr_type        VARCHAR2(24)
    );
    comment on column TBL_BTS_SYS_USR.usr_remark  is '用户备注';
    comment on column TBL_BTS_SYS_USR.usr_disable_tag  is '0-禁用 1-启用';
    comment on column TBL_BTS_SYS_USR.usr_email  is '用户邮箱';
    create unique index USR_NAME_UN on TBL_BTS_SYS_USR (USR_NAME);

    alter table TBL_BTS_SYS_USR  add primary key (USR_ID)  using index;
--5. 创建用户角色表
    create table TBL_BTS_SYS_USR_ROLE
    (
      usr_id                   VARCHAR2(20) not null,
      role_id                  VARCHAR2(20) not null,
      usr_role_remark          VARCHAR2(255),
      usr_role_create_by       VARCHAR2(20),
      usr_role_create_datetime TIMESTAMP(6),
      usr_role_update_by       VARCHAR2(20),
      usr_role_update_datetime TIMESTAMP(6)
    );
    alter table TBL_BTS_SYS_USR_ROLE  add primary key (USR_ID, ROLE_ID)  using index ;