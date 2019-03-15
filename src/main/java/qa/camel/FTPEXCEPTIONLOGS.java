package qa.camel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FTPEXCEPTIONLOGS {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
            	from("ftp://test.rebex.net?username=demo&password=password")
            	.throwException(Exception.class, "I failed you Sowwy :(")
            		.log("Accounting recieved order:${header.CamelFileName}")
            		.to("activemq:accountwithlog");
               
            		
                	
            }
        });
        context.start();
        Thread.sleep(20000);
        context.stop();
    
 
}

}
