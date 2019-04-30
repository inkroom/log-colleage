
-- server端状态记录
create table if not exists server
(
	ip varchar(30) not null,
	run long not null,
	status bool,
	file_port int not null,
	last datetime not null,
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
    ip varchar(30) not null,
    ua varchar (255) not null,
    createdTime datetime not null,
    account varchar (100) not null
);

comment on table login_log is '登录记录';

comment on column login_log.ip is '登录的ip地址';

comment on column login_log.ua is '登录使用的浏览器信息';

comment on column login_log.createdTime is '登录时间';

comment on column login_log.account is '登录的账号';
