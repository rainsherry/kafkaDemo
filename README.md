# kafkaDemo
结合spring-boot的简陋版Kafka，后续会改进
简单说一下环境：
安装Kafka需要jdk环境，这里采用1.8 
mac安装Kafka：
   brew install Kafka
启动kafka：
    启动之前先启动zookeeper：
    ./zookeeper-server-start /usr/local/Cellar/kafka/1.1.0/libexec/config/zookeeper.properties     
    启动kafka：
    ./kafka-server-start /usr/local/Cellar/kafka/1.1.0/libexec/config/server.properties 
    创建topic：
    kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test1
    查看topic：
    kafka-topics --list --zookeeper localhost:2181
之后执行testMessage项目即可看到效果
