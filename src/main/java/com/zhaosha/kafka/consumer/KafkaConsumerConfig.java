package com.zhaosha.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rainsherry on 2018/5/25.
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${kafka.consumer.servers}")
    private String servers;//kafka消费方服务器地址
    @Value("${kafka.consumer.enable.auto.commit}")
    private boolean enableAutoCommit;//true表示consumer所fetch的消息的offset将会自动的同步到zookeeper。这项提交的offset将在进程挂掉时，由新的consumer使用
    @Value("${kafka.consumer.session.timeout}")
    private String sessionTimeout;//zk会话的超时限制
    @Value("${kafka.consumer.auto.commit.interval}")
    private String autoCommitInterval;//consumer向zookeeper提交offset的频率
    @Value("${kafka.consumer.group.id}")
    private String groupId;//表示consumer所在组的id
    //zk中没有初始化offset时，如果offset是以下值的回应：smallest：自动复位,offset为smallest的offsetlargest：自动复位,offset为largest的offsetanything else：向consumer抛出异常
    @Value("${kafka.consumer.auto.offset.reset}")
    private String autoOffsetReset;
    @Value("${kafka.consumer.concurrency}")
    private int concurrency;
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }


    public Map<String, Object> consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<>(8);
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return propsMap;
    }
}
