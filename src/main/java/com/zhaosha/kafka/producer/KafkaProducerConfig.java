package com.zhaosha.kafka.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rainsherry on 2018/5/25.
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${kafka.producer.servers}")
    private String servers;//kafka生产者服务器地址
    @Value("${kafka.producer.retries}")
    private int retries;//消息重视次数
    @Value("${kafka.producer.batch.size}")
    private int batchSize;//批处理消息的字节数
    @Value("${kafka.producer.linger}")
    private int linger;//延迟时间
    @Value("${kafka.producer.buffer.memory}")
    private int bufferMemory;//发送数据的缓存空间大小

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate(producerFactory());
    }

    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
}
