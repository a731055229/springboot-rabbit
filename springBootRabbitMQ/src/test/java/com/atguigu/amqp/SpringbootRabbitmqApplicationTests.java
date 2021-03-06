package com.atguigu.amqp;

import com.atguigu.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void createExchange(){
//		amqpAdmin.declareExchange(new DirectExchange("exchange.amqpAdmin"));
//		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));//创建队列
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",Binding.DestinationType.QUEUE,"exchange.amqpAdmin","amqp.haha",null));
		System.out.println("创建完成");
	}

	/**
	 * 单播（点对点）
	 */
	@Test
	public void contextLoads() {
		//Message需要自己构造一个，定义消息体内容和消息头  import org.springframework.amqp.core.Message;
//		rabbitTemplate.send(exchange,routeKey,message);

		//简单常用的发送消息,object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitMq
//		rabbitTemplate.convertAndSend(exchange,routeKey,object);
		Map<String,Object> map = new HashMap<>();
		map.put("msg","这是第一个demo的消息");
		map.put("data", Arrays.asList("Hello World",123,true));
//		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));
	}

	/**
	 * 接收消息
	 */
	@Test
	public void receiveInfo(){
		Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(o.getClass());
		System.out.println(o);
	}

	/**
	 * 广播
	 */
	@Test
	public void sendMsg(){
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("三国演义","；罗贯中"));
	}

}
