# log-colleage

初始化构建模块

----

共分为以下模块
- client 
    > 日志发送端，目前仅支持log4j的socket实现
- server
    > 日志接收端，负责日志接收，备份持久化
- master
    > 负责管理各server，维护管理
- web
    > 负责呈现master管理功能，提供日志文件查询、下载功能；短时间内的日志实时查看

---- 

## features
- [ ] server端处理无法入库
- [ ] master server全面使用mq通信
- [ ] 运行引入自定义jar，替换原本的mq方案

---- 

## bugs
- [ ] influxdb 保留策略有问题

----

## 使用到的服务


| 服务 | 功能 | 配置文件所在 |备注|
|:-----|:----|:--|:----|
|activeMq|传输日志| 待定 | 日志使用quene方式， |
| influxDb时序数据库 |待定 | 用于存储日志 |


## 框架

### 后端

- spring
- springMVC
- activeMq
- quartz
- vert.x-core
- slf4j
- logback
- log4j

### 前段（仅web模块）

- elementUI
- vue