�������棺
���ȿ����������棻
sqlsessionȥ��ѯ�û�id=1���û���Ϣ����ѯ���û���Ϣ�Ὣ��ѯ�������ݴ洢������������
sqlsession2ȥ��ѯ�û�id=1���û���Ϣ��ȥ���������Ƿ��л������ݣ������ֱ��ȡ������

����������һ�����������
 ��������ķ�Χ���󣬶��session���Թ���һ��User Mapper �Ķ�����������
 User Mapper��һ�������������򣬣���namespace�֣� ������mapperҲ���Լ��Ķ������棨��namespace�֣� ��ÿһ��namespace����һ��������������
��mapper��namespace�������ͬ��sql��ѯ�������ݴ洢��һ������������
���磺sqlsession3ִ����ͬ�ķ��� ִ��commit �ͻ���ն������������
�������������桿
mybaits�Ķ���������mapper����ģ�������sqlMapConfig.xml ���ö��������ܿ��أ���Ҫ������mapper.xml�п�����������

<setting name="cacheEnabled" value="true">
��userMapper�п����������� ��洢���������� �ṹ����hash map

����pojo��ʵ�����л��ӿ�


�����ö������桿
 useCache="false";����
    <select id="findUserByIdResultMap" parameterType="int" resultMap="userResultMap" useCache="false">

ʹ�ó��������ÿ�β�ѯ����Ҫ�������ݵģ����ö�������Ϊ����
��ˢ�¶�������==��ջ��桿insert /update/delete�Ĳ�������ˢ�»��桢
flashCache = true 
    <select id="findUserByIdResultMap" parameterType="int" resultMap="userResultMap" flashCache="true" >
��ˢ�¼����
flushInterval��ˢ�¼��������������Ϊ�������������Ҵ���һ������ĺ�����ʽ��ʱ��Σ�Ĭ����������� size���������ݣ�:��������Ϊ��������
ע�⣺����Ķ�����Ŀ�����л����Ŀ����ڴ���Դ��Ŀ��Ĭ��ֵΪ1024
readOnly��ֻ�������Կ��Ա�����Ϊtrue��false ֻ���Ļ��������е����߷��ػ���������ͬʵ������Щ�����ܱ��޸�
�������ƣ��ɶ�д�Ļ���᷵�ػ������Ŀ�����ͨ�����л������ٶ���������ȫ��Ĭ����false


��mybatis����ehcache���ֲ�ʽ���棩��
���ֲ�ʽ���桿
ehcache����һ���ֲ�ʽ������
Ϊ���ϵͳ���������ܣ�һ���ϵͳ���зֲ�ʽ���𣨼�Ⱥ����ʽ��

mybatis�޷�ʵ�ֲַ�ʽ���棬��Ҫ��������������
   �����Ϸ�����
mybatis�ṩ��һ��cache�ӿڣ������Ҫʵ���Լ��Ļ����߼���ʵ��cache�ӿڼ���;
mybatis��ehcache���ϣ�mybatis��ehcache���ϰ����ṩ��һ��cache�ӿڵ�ʵ����

org.apache.ibatis.cache��Cache.classȥ��ʵ����Ľӿ�
���Բο�mybatisĬ��֧�ֵ�ʵ���ࣺ 
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
type :ָ��cache�ӿ�ʵ��������ͣ�mybatisĬ��ʹ��Perpetual Cache
Ҫ��ehcahce���ϣ���Ҫ����typeΪechacheʵ��cache�ӿڵ�����
������ehcache����
<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
������ehcache�����ļ���
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

��Ӧ�ó�����
�����û���ѯ���Ҫ��ʵʱ�Բ��ߵ� ��ʱ�ϸߵ�ͳ�Ʒ�����

resultMapʹ�ã�
�����ѯ��������������������һ�£�ͨ������һ��resultMap��������pojo��������һ��ӳ���ϵ
��̬sql:mybatis ����
��sql��������������ƴ�ӣ���װ��
sqlƬ�Σ�
��֮ǰʵ�ֵĶ�̬sql�жϴ���Ƭ�γ�ȡ���������һ��sqlƬ�Σ�������statement�оͿ������ø�sqlƬ��
foreach��ǩ��
��sql�д���������򼯺ϣ�����Ҫʹ��foreach
���磺���û���ѯ�Ͳ�ѯ������ ���Ӷ��id�����ѯ
�����ݿ���������
����ģ�ͷ���
1������ÿ�ű��¼������ �����ÿ�ÿ�ű����
      ��ģ���ÿ�ű��¼����������   �൱�� ѧϰϵͳ���󣨹��ܣ��Ĺ���
2��ÿ�ű� ��Ҫ�����ݿ��ֶ�
    ��Ҫ�ֶΣ��ɿ��ֶΣ�����ֶ�
3�����Ϸ����� ���൱�ڷ��������ݿ⼶������֮��Ĺ�ϵ   �����ϵ
4�������֮���ҵ���ϵ
    �ڷ��������֮���ҵ���ϵʱ�� һ��Ҫ ���� ��ĳ��ҵ�����������ȥ����
    
 �������ԡ�
 mybatis���������ϸ���ȵ����ݼ��𻺴�ʵ�ֲ��ã��������Ʒ��Ϣ���л��棬һcommitment���л��涼��գ����²�ѯ���ݿ�  
 
 ��Spring+MyBatis��
    
    ����ģ�͵ķ�����
    
    order��������¼���û�������Ʒ�Ķ���
    orderDetail��������ϸ����¼�˶�������ϸ��Ϣ������������Ʒ�ģ�
    iterms����Ʒ����¼����Ʒ��Ϣ
    user ��
    id û������ ����
    order ��
    id û���κ����� ������
    user_id ���
    
    orderDetail
    id
    user_id �û�id  ���
    iterms_id ��Ʒid ���
    ��һ����
==================
    ���������ϵ
==================
    
 user ---ָ��--->user_id
 orderDetail--------ָ��------>order_id
 ==================
 ����������ҵ���ϵ
 ====================
 �ȷ������ݿ⼶���֮���ҵ���ϵ
 ===========================
 user ---  order  :һ���û����Դ���������� �� һ�Զ�
 
 
 order --- user   ��һ������ֻ��һ���û����� �� һ��һ     ���߶��������һ���û����������һ
 
 
 order ---- orderDatil ��һ���������԰������������ϸ һ���������Թ�������Ʒ ÿ����Ʒ�Ĺ�����Ϣ һ�Զ�
 
 orderDetail ----- order :һ��������ϸ ֻ��һ������  һ��һ
 
 orderDetail ------ iterms��һ����ϸ ֻ��¼��һ����Ʒ������Ϣ�� һ��һ
 
 iterms -----  orderDetails:һ����Ʒ�����ڶ��������ϸ�� �� һ�Զ�
 ==================================
 ���ݿ⼶���֮��û�й�ϵ�ı�֮��Ĺ�ϵ
 ==================================
 order  ------ (orderDatil)-------iterms  ������ϵ    ��һ�Զ� 
 
 iterms ------ (orderDatil)------- order һ�Զ�
 
 �ۺ����� order��iterms�Ƕ�Զ��ϵ
 
 User ----(order)---- (orderDatil)-------iterms : ��Զ��ϵ
 
 ���磺��ѯ������Ϣ��������ѯ�����������û���Ϣ
 
 ����������   ������ ���û���
 
 ������ѯ�������ӣ������ӣ�
 ����------����-----�û�
 order��uer_id------->user  id :ֻ�ܲ�ѯһ����¼
 �����ӣ�select order.*,user.name from order,user where order.user_id = user.id
 
 �ֱ���pojo�н���order.java iterms.java orderDetails.javaʵ���ࣺ����sql��ѯ�Ľ��ӳ�䵽ʵ������
ԭʼorder.java����ӳ��ȫ���ֶΣ���Ҫ�´���pojo,����һ��ʵ���� extends ������ѯ�ֶν϶��po��
 
 resultMap����ʵ���ӳټ���
 
 �ӳټ���
 resultMapʹ��association /collectionʵ��һ��һ��һ�Զ�ӳ�䣬association /collection�߱��ӳټ��ع���
 �����ѯ�������ҹ�����ѯ�û���Ϣ������Ȳ�ѯ������Ϣ�������㣬������Ҫ��ѯ�û���Ϣ���ڲ�ѯ�û���Ϣ���ɶ��û���Ϣ
 �����ѯ �ͳ�Ϊ�ӳټ��أ�����������ݿ����ܣ��ȹ�����ѯ��
    