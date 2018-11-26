package com.atguigu.amqp.service;

import com.atguigu.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Auther tang
 * @Date 2018/11/20
 * @Description
 */
@Service
public class BookService {

    @RabbitListener(queues = "atguigu.emps")  //配合@EnableRabbit使用
    public void receiveInfo(Book book){
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues = "atguigu")
    public void receiveInfo02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
