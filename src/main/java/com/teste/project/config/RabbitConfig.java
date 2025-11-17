package com.teste.project.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("pedido.sucesso")
                .deadLetterExchange("pedido.falha")
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.nonDurable("pedido.falha").build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("pedido.aberto");
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("pedido.falha");
    }

    @Bean
    public Binding binding(DirectExchange exchange) {
        return BindingBuilder.bind(queue())
                .to(exchange)
                .with("pagamento.sucesso");
    }
}
