package com.example.demo.controller.sys;

import com.example.demo.util.activeMQ.Producer;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

@RestController
@RequestMapping("hello")
public class HelloWorldController {
    @Autowired
    private Producer producer;

    @RequestMapping("say")
    public String say(){
        return "端口9000:Hello,World";
    }

    @RequestMapping("sendMessage")
    public String sendActiveMQMessage(String message){
        //话题
        Destination destination=new ActiveMQTopic("springboot.test.topic");
        //队列
        //Destination destination=new ActiveMQQueue("springboot.test.queue");
        producer.sendMessage(destination,"这是第一次发送测试:"+message);
        return "success";
    }
}
