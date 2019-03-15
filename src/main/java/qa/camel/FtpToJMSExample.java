package qa.camel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FtpToJMSExample {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		  context.addRoutes(new RouteBuilder() {
	            public void configure() {
	                from("ftp://test.rebex.net?username=demo&password=password").to("activemq:queue:orders");
	                from("ftp://test.rebex.net/pub/example?username=demo&password=password").to("activemq:queue:orders");
	            }
	        });
		  context.start();
	      Thread.sleep(10000); // need sleep to keep JVM running until the job is done
	      context.stop();
		

	}

}
