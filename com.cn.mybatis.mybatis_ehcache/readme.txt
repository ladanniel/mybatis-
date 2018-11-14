二级缓存：
首先开启二级缓存；
sqlsession去查询用户id=1的用户信息，查询到用户信息会将查询到的数据存储到二级缓存中
sqlsession2去查询用户id=1的用户信息，去缓存中找是否有缓存数据，如果有直接取出数据

二级缓存与一级缓存的区别
 二级缓存的范围更大，多个session可以共享一个User Mapper 的二级缓存区域
 User Mapper有一个二级缓存区域，（按namespace分） 其他的mapper也有自己的二级缓存（按namespace分） ，每一个namespace都有一个二级缓存区域
两mapper的namespace，如果相同，sql查询到底数据存储在一个二级缓存中
例如：sqlsession3执行相同的方法 执行commit 就会清空二级缓存的区域
【开启二级缓存】
mybaits的二级缓存是mapper级别的，除了在sqlMapConfig.xml 设置二级缓存总开关，还要具体在mapper.xml中开启二级缓存

<setting name="cacheEnabled" value="true">
在userMapper中开启二级缓存 会存储到缓存区域 结构还是hash map

调用pojo类实现序列化接口


【禁用二级缓存】
 useCache="false";禁用
    <select id="findUserByIdResultMap" parameterType="int" resultMap="userResultMap" useCache="false">

使用场景：针对每次查询都需要最新数据的，设置二级缓存为禁用
【刷新二级缓存==清空缓存】insert /update/delete的操作可以刷新缓存、
flashCache = true 
    <select id="findUserByIdResultMap" parameterType="int" resultMap="userResultMap" flashCache="true" >
【刷新间隔】
flushInterval（刷新间隔）：可以设置为任意整数，而且代表一个合理的毫秒形式的时间段，默认情况不设置 size（引用数据）:可以设置为任意整数
注意：缓存的对象数目和运行环境的可用内存资源数目，默认值为1024
readOnly（只读）属性可以被设置为true或false 只读的缓存会给所有调用者返回缓存对象的相同实例。这些对象不能被修改
性能优势：可读写的缓存会返回缓存对象的拷贝（通过序列化），速度慢，但安全，默认是false


【mybatis整合ehcache（分布式缓存）】
【分布式缓存】
ehcache：是一个分布式缓存框架
为提高系统并发，性能，一般对系统进行分布式部署（集群部署方式）

mybatis无法实现分布式缓存，需要与第三方框架整合
   【整合方法】
mybatis提供了一个cache接口，如果需要实现自己的缓存逻辑，实现cache接口即可;
mybatis与ehcache整合，mybatis和ehcache整合包中提供了一个cache接口的实现类

org.apache.ibatis.cache中Cache.class去找实现类的接口
可以参考mybatis默认支持的实现类： 
public class PerpetualCache implements Cache {

  private String id;

  private Map<Object, Object> cache = new HashMap<Object, Object>();

  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  public PerpetualCache(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public int getSize() {
    return cache.size();
  }

  public void putObject(Object key, Object value) {
    cache.put(key, value);
  }

  public Object getObject(Object key) {
    return cache.get(key);
  }

  public Object removeObject(Object key) {
    return cache.remove(key);
  }

  public void clear() {
    cache.clear();
  }
<mapper namespace="">
type :指定cache接口实现类的类型，mybatis默认使用Perpetual Cache
要和ehcahce整合，需要配置type为echache实现cache接口的类型
【加入ehcache包】
<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
【加入ehcache配置文件】
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xsi:noNamespaceSchemaLocation="../config/ehcache.xsd">
 <diskStore path="C:\develop\ehcache"/>
 <defaultCache
  maxElementsInMemory="1000"
  maxElementsOnDisk="10000000"
  overflowToDisk="false"
  timeToIdleSeconds="120"
  timeToLiveSeconds="120"
  diskExpiryThreadIntervalSeconds="120"
  menmoryStoreEvictionPolicy="LRU"
  ></defaultCache>
</ehcache>

【应用场景】
访问用户查询结果要求实时性不高的 耗时较高的统计分析的

resultMap使用：
如果查询出来的列明和属性名不一致，通过定义一个resultMap对列名和pojo属性名做一个映射关系
动态sql:mybatis 核心
对sql语句进行灵活操作，拼接，组装等
sql片段：
将之前实现的动态sql判断代码片段抽取出来，组成一个sql片段，其他的statement中就可以引用该sql片段
foreach标签：
向sql中传递了数组或集合，就需要使用foreach
例如：在用户查询和查询总数中 增加多个id输入查询
【数据库表分析：】
数据模型分析
1、分析每张表记录的内容 ：不用看每张表而是
      分模块对每张表记录的数据内容   相当于 学习系统需求（功能）的过程
2、每张表 重要的数据库字段
    重要字段：飞空字段，外键字段
3、以上分析后 就相当于分析了数据库级别表与表之间的关系   外键关系
4、表与表之间的业务关系
    在分析表与表之间的业务关系时， 一定要 建立 在某个业务意义基础上去分析
    
 【局限性】
 mybatis二级缓存对细粒度的数据级别缓存实现不好，比如对商品信息进行缓存，一commitment所有缓存都清空，重新查询数据库  
 
 【Spring+MyBatis】
    
    数据模型的分析：
    
    order订单表：记录了用户购买商品的订单
    orderDetail：订单明细表：记录了订单的详细信息（订单购买商品的）
    iterms：商品表：记录了商品信息
    user 表
    id 没有意义 自增
    order ：
    id 没有任何意义 订单号
    user_id 外键
    
    orderDetail
    id
    user_id 用户id  外键
    iterms_id 商品id 外键
    下一步：
==================
    分析外键关系
==================
    
 user ---指向--->user_id
 orderDetail--------指向------>order_id
 ==================
 分析表与表的业务关系
 ====================
 先分析数据库级别的之间的业务关系
 ===========================
 user ---  order  :一个用户可以创建多个订单 ： 一对多
 
 
 order --- user   ：一个订单只由一个用户创建 ： 一对一     或者多个订单由一个用户创建：多对一
 
 
 order ---- orderDatil ：一个订单可以包含多个订单明细 一个订单可以购买多个商品 每个商品的购买信息 一对多
 
 orderDetail ----- order :一个订单明细 只有一个订单  一对一
 
 orderDetail ------ iterms：一个明细 只记录了一个商品购买信息： 一对一
 
 iterms -----  orderDetails:一个商品可以在多个订单明细中 ： 一对多
 ==================================
 数据库级别表之间没有关系的表之间的关系
 ==================================
 order  ------ (orderDatil)-------iterms  建立关系    ：一对多 
 
 iterms ------ (orderDatil)------- order 一对多
 
 综合起来 order与iterms是多对多关系
 
 User ----(order)---- (orderDatil)-------iterms : 多对多关系
 
 例如：查询订单信息，关联查询创建订单的用户信息
 
 主表：订单表   关联表 ：用户表
 
 关联查询？内链接？外连接？
 订单------关联-----用户
 order：uer_id------->user  id :只能查询一条记录
 内连接：select order.*,user.name from order,user where order.user_id = user.id
 
 分别在pojo中建立order.java iterms.java orderDetails.java实体类：作用sql查询的结果映射到实体类中
原始order.java不能映射全部字段，需要新创建pojo,创建一个实体类 extends 包括查询字段较多的po类
 
 resultMap可以实现延迟加载
 
 延迟加载
 resultMap使用association /collection实现一对一，一对多映射，association /collection具备延迟加载功能
 如果查询订单并且关联查询用户信息，如果先查询订单信息即可满足，当我们要查询用户信息是在查询用户信息，吧对用户信息
 按需查询 就成为延迟加载，可以提高数据库性能，比关联查询快
    