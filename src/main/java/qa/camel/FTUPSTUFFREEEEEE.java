package qa.camel;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
 
import javax.jms.ConnectionFactory;

public class FTUPSTUFFREEEEEE {
	 
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
                    		.when(header("CamelFileName").endsWith(".csv"))
                    			.to("activemq:csvOrders}")
                    	.choice()
                        	.when(header("CamelFileName").endsWith(".txt"))
                        			.to("activemq:txTorders")
                    		.otherwise()
                    			.to("activemq:otherOrders");
	                	
	            }
	        });
	        context.start();
	        Thread.sleep(10000);
	        context.stop();
	    }
	 
	}


