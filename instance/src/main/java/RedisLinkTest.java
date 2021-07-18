import org.junit.Test;
import redis.clients.jedis.Jedis;
import utils.RedisUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisLinkTest {
    //@Test表示这个方法是单元测试的方法
    //连接并添加String类型数据
    @Test
    public void fun1() {
        //直接连接redis数据库
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //设置连接密码
        //jedis.auth("123");
        //添加String类型数据
        jedis.set("field1","i am field1");
        //输出添加的数据（根据键，输出对应的值）
        System.out.println(jedis.get("field1"));
        //删除String类型数据（根据键删除）
        jedis.del("field1");
        //输出数据，查看是否删除成功
        System.out.println(jedis.get("field1"));
    }

    //连接并添加hash类型数据（我理解为给String类型的数据进行了分类，每个hash可以存储2^32-1个键值对）
    @Test
    public void fun2(){
        //通过连接池方式连接redis数据库
        Jedis jedis = RedisUtils.getJedis();
        //添加hash类型数据
        jedis.hset("hset1","name","张三");
        jedis.hset("hset1","age","22");
        jedis.hset("hset1","sex","男");
        //获取数据
        List<String> hmget = jedis.hmget("hset1", "name","age","sex");
        //输出
        System.out.println(hmget);
        //删除
        jedis.hdel("hset1","name","sex");
        //删除后再输出，看看是否删除成功
        System.out.println(jedis.hmget("hset1", "name","age","sex"));
        RedisUtils.returnResource(jedis);
    }

    //连接并添加List类型数据（队列，按照插入顺序排序，可以添加一个元素到列表的头部（左边），或者尾部（右边））
    @Test
    public void fun3() {
        //通过连接池方式连接redis数据库
        Jedis jedis = RedisUtils.getJedis();
        //添加List类型数据，lpush添加到列表头部，即后添加的数据在最前面
        jedis.lpush("field2","aaa");
        jedis.lpush("field2","bbb");
        jedis.lpush("field2","ccc");
        //从索引1的位置获取到索引6位置的值，因为超出了实际索引2，所以后面会继续循环输出
        List<String> field2 = jedis.lrange("field2", 0, 6);
        //输出添加的键值对
        for(String item:field2){
            System.out.println(item);
        }
        RedisUtils.returnResource(jedis);
    }

    //连接并添加Set类型数据（一堆不重复值的组合）
    @Test
    public void fun4(){
        //通过连接池方式连接redis数据库
        Jedis jedis = RedisUtils.getJedis();
        //添加Set类型数据
        jedis.sadd("name","zhangsan");
        jedis.sadd("name","lisi");
        jedis.sadd("name","lisi");  //同一个数据再次添加，会覆盖上一次的
        jedis.sadd("age","16");
        jedis.sadd("sex","nan");
        jedis.sadd("address","china");
        //获取Set类型数据
        Set<String> name = jedis.smembers("name");
        //输出获取到的Set类型数据（输出的顺序是无序的）
        System.out.println(name);
        System.out.println("\n");
        //获取当前redis数据库中，所有key，以Set集合的方式返回数据
        Set<String> set = jedis.keys("*");
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        RedisUtils.returnResource(jedis);
    }

    //连接并添加ZSet类型数据（zset是set的升级版，它在set的基础上增加了顺序属性score，这一属性在添加修改元素的时候可以指定，每次指定后，zset会自动重新按新的值调整顺序）
    @Test
    public void fun5(){
        //通过连接池方式连接redis数据库
        Jedis jedis = RedisUtils.getJedis();
        //添加zset型数据
        jedis.zadd("field3",1,"hhh");
        jedis.zadd("field3",0,"jjj");
        jedis.zadd("field3",3,"bbb");
        //获取set型数据
        Set<String> field3 = jedis.zrangeByScore("field3", 0, 5);
        //用迭代器循环输出
        Iterator<String> iterator = field3.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        RedisUtils.returnResource(jedis);
    }
}