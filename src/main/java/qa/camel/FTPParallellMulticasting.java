package qa.camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
 
import javax.jms.ConnectionFactory;

public class FTPParallellMulticasting {





	 
	    public static void main(String args[]) throws Exception {
	        CamelContext context = new DefaultCamelContext();
	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
	        
	        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
	        context.addRoutes(new RouteBuilder() {
	            @Override
	            public void configure() {
	                from("ftp://test.rebex.net?username=demo&password=password")
	                	.multicast()
	                		.parallelProcessing()
	                	.to("activemq:multiorder1", "activemq:multiOrder2");
	                	
	            }
	        });
	        context.start();
	        Thread.sleep(20000);
	        context.stop();
	    
	 
	}
	    
}
