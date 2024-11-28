package com.cs.consumer_service.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.cs.consumer_service.listener.MessageListener;

import jakarta.jms.Queue;

@Configuration
@EnableJms
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}") 
    private String broker_url;

    @Value("${spring.activemq.queue.name}")
    private String queueName;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(broker_url);
        return factory;
    }

    @Bean
    public Queue queue() {
		return new ActiveMQQueue(queueName);
	}

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    /*@Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(ActiveMQConnectionFactory activeMQConnectionFactory,
    MessageListener messageListener){
            DefaultMessageListenerContainer container=new DefaultMessageListenerContainer();
            container.setConnectionFactory(activeMQConnectionFactory);
            container.setDestinationName("${spring.activemq.topic}");
            container.setMessageListener(messageListener);
            return container;
    }*/
}
