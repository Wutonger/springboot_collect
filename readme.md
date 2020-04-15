Springboot越来越流行，在项目中使用Springboot会让开发效率大大提高。

本文主要整合Springboot与一些常用的中间件，比如Redis、ElasticSearch等等。

Springboot的版本为**2.X**以上

另外欢迎访问本人的博客，网址：www.lxfun.cn

主要更新一些Java方面的技术文章

目前已经完成：

### Springboot整合ElasticSearch的使用

* index创建和mapping设置
* 使用Spring Data封装好的CRUD方式
* 使用Spring Data中的NativeQuery，例如ElasticSearch中的term查询、querystring查询

### Springboot整合Redis的使用

* Redis连接池的配置、redisTemplate乱码问题解决
* Redis常用数据结构的存储、获取，例如String与List
* 利用Redis中的SUB/PUB模式实现消息的自动消费
* 使用Redis实现分布式锁— 主要原理为set  nx px命令