package TestDateFormat;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-24 10:22
 * @from
 *
 *
 * 测试simpledateformat 线程不安全
 * 当前:0-->Fri Jun 24 10:13:06 CST 2022
 * 当前:1-->Thu Jun 24 10:13:06 CST 2021
 * 当前:2-->Wed Jun 24 10:13:06 CST 2020
 * 当前:3-->Tue Jun 24 10:13:06 CST 2025
 * 当前:4-->Fri Jun 24 10:13:06 CST 2022
 * 当前:4-->2021-06-24 10:13:06
 * 当前:2-->2021-06-24 10:13:06
 * 当前:1-->2021-06-24 10:13:06
 * 当前:0-->2021-06-24 10:13:06
 * 当前:3-->2021-06-24 10:13:06
 *
 * 看到线程0 应该是2022 但是 格式化完变成了2021 证明被其他线程修改过了
 * 因为dataformat 调用到时候 把date赋值给calendar 进行calendar.setTime(date); 这个calendar是个共享变量, 格式化时会调用读取,然后处理.造成线程不安全
 * 当A线程使用 SimpleDateFormat 对象 sa.format -> calendar.setTime(date) -> sa.subFormat()读calendar操作
 * 在线程A操作日期的调用链中，把对象sa暴露给了线程B，线程B也执行了sa.format(date) -> calendar.setTime(date), 写操作
 * 此时线程A在进行读calendar操作，calendar内部的date已经被修改了，就会操作预期外的结果。
 *
 * 解决办法
 *
 * 1. 在每个线程里单独创建format, 进行线程隔离 会产生很多new 的对象
 * 2. 线程池 + ThreadLocal 池化就减少了new 对象 且threadlocal 保证了线程间隔离
 *
 **/
public class TsDateFormat {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        Date date1 = new Date(1656036786108L);
        Date date2 = new Date(1624500786000L);
        Date date3 = new Date(1592964786000L);
        Date date4 = new Date(1750731186000L);
        Date[] dates = new Date[]{date1,date2,date3,date4};
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                // 解决办法如 在每个线程里单独创建format, 进行线程隔离
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(Thread.currentThread().getName() + dates[finalI %4]);
                System.out.println(Thread.currentThread().getName() + simpleDateFormat2.format(dates[finalI %4]));
            },"当前:"+finalI+"-->").start();
        }
    }
}
