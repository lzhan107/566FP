package service;

import model.User;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserEndpoint {
	private ApplicationContext context;
	public void copyUser(User user){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageChannel channel = (MessageChannel) context.getBean("deleteUserInboundGateway",CopyUserEndpoint.class);
		Message<?> message = MessageBuilder.withPayload("delete").build();
		channel.send(message);
	}
}
