package training.messaging;

import training.Application;
import training.concepts.record.Record;
import training.messaging.MessageFormatter;

import java.util.concurrent.TimeUnit;
import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class AmqpSender {

    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;

    public AmqpSender(RabbitTemplate rabbitTemplate,
            ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    public void sendMessage(String recipient, String header, String body) {
      try {
        MessageFormatter formatter = new MessageFormatter();
        String message = formatter.generateJsonString(recipient,
          header,
          body);
        Application.logger.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(Application.queueName, message);
      }
      catch (IOException ex) {
        Application.logger.info("Exception while sending AMQP message: {}", ex);
      }
    }

    public void sendRecord(Record record) {
      try {
        RecordFormatter formatter = new RecordFormatter();
        String message = formatter.generateJsonString(record);
        Application.logger.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(Application.queueName, message);
      } catch(IOException ex) {
        Application.logger.info("Exception while sending AMQP message: {}", ex);
      }
    }
}
