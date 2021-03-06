# log-colleage

初始化构建模块

----

共分为以下模块
- client 
    > 日志发送端，目前仅支持log4j的socket实现
- server
    > 日志接收端，负责日志接收，备份持久化
- web
    > 负责呈现master管理功能，提供日志文件查询、下载功能；短时间内的日志实时查看

----

## features
- [ ] server端处理无法入库
- [ ] 运行引入自定义jar，替换原本的mq方案
- [ ] 邮件报警

----

## bugs
- [ ] 文件下载和列表文件流可能存在问题

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
- thyleamf

### 前段（仅web模块）

- elementUI
- vue

### 注意事项

- 日志消息保证有server接收的同时，也要保证web端一定可以收到消息用于实时日志
> client server之间使用queue，server收到消息后发送到topic，web端通过订阅topic获取消息 
> web如果没有socket连接则不订阅topic
