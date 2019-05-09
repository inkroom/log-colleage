-- server端状态记录
create table if not exists server
(
    ip        varchar(30) not null,
    run       long        not null,
    status    bool,
    file_port int         not null,
    last      datetime    not null,
    constraint server_pk
        primary key (ip)
);

comment on table server is 'server端状态信息';

comment on column server.run is '运行时长，单位毫秒';

comment on column server.status is '是否运行中';

comment on column server.file_port is '文件下载端口';

comment on column server.last is '上次通信时间';

-- 登录记录
create table if not exists login_log
(
    ip          varchar(30)  not null,
    ua          varchar(255) not null,
    createdTime datetime     not null,
    account     varchar(100) not null
);

comment on table login_log is '登录记录';

comment on column login_log.ip is '登录的ip地址';

comment on column login_log.ua is '登录使用的浏览器信息';

comment on column login_log.createdTime is '登录时间';

comment on column login_log.account is '登录的账号';



create table if not exists download
(
    id         int auto_increment,
    username   varchar(100),
    file       varchar(255),
    start      timestamp,
    end        timestamp,
    created_at timestamp,
    size       int
);

comment on table download is '下载操作记录';

comment on column download.username is '下载账号';

comment on column download.start is '日志文件的记录开始时间';

comment on column download.end is '日志文件的记录结束时间';

comment on column download.created_at is '下载时间';

comment on column download.size is '记录条数';



create table if not exists alarm
(
    id         int auto_increment,
    message    text,
    tag        varchar(100),
    ip         varchar(30),
    created_at timestamp,
    status     int
);

comment on table alarm is '报警记录';

comment on column alarm.message is '发送的消息';
comment on column alarm.tag is 'tag';
comment on column alarm.ip is 'ip';
comment on column alarm.created_at is '下载时间';


create table if not exists Downtime
(
    id         int auto_increment,
    tag        varchar(100),
    ip         varchar(30),
    created_at timestamp,
    status     int
);

comment on table Downtime is '宕机记录';

comment on column Downtime.tag is 'tag';
comment on column Downtime.ip is 'ip';
comment on column Downtime.created_at is '宕机时间';



create table if not exists statistics
(
    id         int auto_increment,
    tag        varchar(100),
    ip         varchar(30),
    count      int,
    created_at timestamp,
    level int ,
    status     int
);

comment on table statistics is '数据统计';

comment on column statistics.tag is 'tag';
comment on column statistics.ip is 'ip';
comment on column statistics.count is '日志条数';
comment on column statistics.level is '日志级别，debug这些';
comment on column statistics.created_at is '记录时间';







