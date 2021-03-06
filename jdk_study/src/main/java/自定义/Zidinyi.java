package 自定义;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/28<p/>
 * Time: 17:42<p/>
 */
public class Zidinyi {

    //定义这样一种集合

    //参考JDK的某个类, 但是要和jdk区别开来, 因为要比jdk更特殊的要求.
    //特殊要求:
    //1. 多种策略, 达到多少条或者间隔多长时间了就提交一次.
    //2. 无锁化编程,针对每个业务开辟一个单线程的线程池,不停的写入 (netty) ,这样可以提高客户端的提交速度
    // 如果客户端关心写入的结果, 可以让他一直在等待结果, 超时了可以取消任务
    // 任务本身需要保证幂等性,一个同样的操作发生两次不能产生副作用 (幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同)
    // 可以认为消费端本身是很快的,因为提交的策略都是达到多少条或者间隔多长时间就提交一次, 所以可以认为任务的超时时间 <= 提交的间隔时间
    // 需要保证自定义的提交策略是很快的,比如批量提交数据, 否则

    // 适用场景:

    // 不适用 任务量特别大, 提交中编写消费速度特别慢的业务. 当队列中的任务满了以后会,降级为客户端线程自己操作.

    //2. 提交的策略抽象成接口, 自己定义是发消息还是提交数据库
    //3. 去重操作怎么实现      (ConcurrentHashSet?)
    //4. 提供扩展, 可以运用监控 (这个参照线程池)
    //5. 不同的业务用String类型或者枚举类型区分开来
    //6. 统计操作需要使用CAS,这样统计操作就没有加锁,都是自旋会很快



}
