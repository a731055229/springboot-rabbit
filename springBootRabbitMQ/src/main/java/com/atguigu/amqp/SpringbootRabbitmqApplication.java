package com.atguigu.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 1.RabbitAutoConfiguration自动配置了ConnectionFactory
 * 2.RabbitProperties封装了rabbitMq的配置
 * 3.RabbitTemplate:给rabbitmq发送和接收消息的
 * 4.AmqpAdmin ： RabbitMq系统管理功能组件
 * 		AmqpAdmin:创建和删除 Queue、exchange、Binding
 * 5.@EnableRabbit + @RabbitListener 监听消息队列里面的内容
 */
@EnableRabbit  //开启基于注解的Rabbitmq模式
@SpringBootApplication
public class SpringbootRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqApplication.class, args);
	}
}
