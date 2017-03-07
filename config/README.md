#mybatis的示例程序-基于mybatis
1. 使用配置文件，把一些数据库连接数据库密码等配置从xml提取出来放到properties中(但是为何读取不到，难道是我环境的问题？还是依赖的版本问题？)
2. 一级缓存，使用同一个sqlSession查询数据就自动是一级缓存，如果期间不做增删改的操作；否则，就会清空以及缓存重新从数据库拿新数据
3. 二级缓存，需要到mybatis的配置文件中进行配置,二级缓存的entity需实现Serializable接口
4. 整合ehcache



##使用gradle和maven两种构建方式

