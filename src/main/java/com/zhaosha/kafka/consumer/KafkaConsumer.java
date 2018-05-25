package com.zhaosha.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by rainsherry on 2018/5/25.
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test1"})
    public void consumerMessage(String message){
        System.out.println("message consumer is " + message);

    }
}
