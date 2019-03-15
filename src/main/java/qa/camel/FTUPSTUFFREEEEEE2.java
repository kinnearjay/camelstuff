package qa.camel;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
 
import javax.jms.ConnectionFactory;

public class FTUPSTUFFREEEEEE2 {
	 
	    public static void main(String args[]) throws Exception {
	        CamelContext context = new DefaultCamelContext();
	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
	        
	        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
	        context.addRoutes(new RouteBuilder() {
	            @Override
	            public void configure() {
	                from("file:inbox?noop=true")
	                	.choice()
	                		.when(header("CamelFileName").endsWith(".xml"))
	                        	.to("activemq:xmlOrders")
                    	.choice()
                    		.when(header("CamelFileName").endsWith(".txt"))
                    			.to("activemq:txtOrder}")
                    		.otherwise()
                    			.to("activemq:otherOrders")
                    		.end()
                    	.to("activemq:continuedProcessing");
	                	
	            }
	        });
	        context.start();
	        Thread.sleep(20000);
	        context.stop();
	    }
	 
	}


