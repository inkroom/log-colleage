
-- server端状态记录
create table server
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

