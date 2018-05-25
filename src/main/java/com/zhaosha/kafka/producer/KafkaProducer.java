package com.zhaosha.kafka.producer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by rainsherry on 2018/5/25.
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic, String message){
        if (StringUtils.isNotBlank(topic) && StringUtils.isNotBlank(message)){
            kafkaTemplate.send(topic, message);
        }
    }
}
