

CREATE TABLE IF NOT EXISTS backup (
   id INT NOT NULL, 
   path VARCHAR(255) NOT NULL,
   created timestamp NOT NULL,
   start timestamp ,
   end timestamp ,
   size int ,
   length int
);
alter table backup alter column ID INTEGER auto_increment;

comment on table backup is '文件备份';
comment on column backup.size is '包含日志条数';
comment on column backup.length is '文件字节数';

-- 记录定时任务起始段
-- CREATE TABLE IF NOT EXISTS backup (
--
--
--
-- )
-- create table server
-- (
-- 	ip varchar(30) not null,
-- 	run long not null,
-- 	status bool,
-- 	file_port int not null,
-- 	last datetime not null,
-- 	constraint server_pk
-- 		primary key (ip)
-- );
--
-- comment on table server is 'server端状态信息';
--
-- comment on column server.run is '运行时长，单位毫秒';
--
-- comment on column server.status is '是否运行中';
--
-- comment on column server.file_port is '文件下载端口';
--
-- comment on column server.last is '上次通信时间';

