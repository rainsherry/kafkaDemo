package com.zhaosha.kafka;

import com.zhaosha.kafka.producer.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by rainsherry on 2018/5/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestKafka {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void testMessage(){
        System.out.println("******************test start***************************");
        kafkaProducer.sendMessage("test0", "hello, kafka!");
    }
}
