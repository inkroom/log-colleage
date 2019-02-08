

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

comment on table "backup" is '文件备份';


-- 记录定时任务起始段
-- CREATE TABLE IF NOT EXISTS backup (
--
--
--
-- )
