package training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import training.Configuration;
import training.concepts.customer.CustomerRepository;
import training.concepts.customer.Customer;
import training.concepts.record.Record;
import training.concepts.record.RecordRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages="training")
public class Application {

  public static final Logger logger = LoggerFactory.getLogger(Application.class);
  public static final Configuration configuration = new Configuration();

  public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
  }

  @Bean
	public CommandLineRunner demo(CustomerRepository customerRepository,
    RecordRepository recordRepository) {
		return (args) -> {

      // Log out configuration
      logger.info("Configuration:");
      logger.info(configuration.toString());

			// save a couple of customers and records
      if (customerRepository.count() < 1) {
        Customer cust1 = new Customer("mj@jackson.com", "Michael", "Jackson");
        cust1.setRank(0);
        cust1.setPassword("test123");
  			customerRepository.save(cust1);

        Customer cust2 = new Customer("james@brown.com", "James", "Brown");
        cust2.setRank(1);
        cust2.setPassword("test123");
  			customerRepository.save(cust2);

        Customer cust3 = new Customer("jimi@hendrix.com", "Jimi", "Hendrix");
        cust3.setRank(2);
        cust3.setPassword("test123");
  			customerRepository.save(cust3);

        Customer cust4 = new Customer("marvin@gaye.com", "Marvin", "Gaye");
        cust4.setRank(3);
        cust4.setPassword("test123");
  			customerRepository.save(cust4);

        Record rec1 = new Record("Purple Rain");
        rec1.setArtist("Prince");
        rec1.setGenre("Pop");
        rec1.setFormat("12Inch");
        rec1.setCustomer(cust1);
        recordRepository.save(rec1);

        Record rec2 = new Record("What's going on");
        rec2.setArtist("Marvin Gaye");
        rec2.setGenre("Soul");
        rec2.setFormat("2x12Inch");
        rec2.setCustomer(cust2);
        recordRepository.save(rec2);

        Record rec3 = new Record("Electric Ladyland");
        rec3.setArtist("Jimi Hendrix");
        rec3.setGenre("Rock");
        rec3.setFormat("12Inch");
        rec3.setCustomer(cust3);
        recordRepository.save(rec3);

        Record rec4 = new Record("Songs in the key of life");
        rec4.setArtist("Stevie Wonder");
        rec4.setGenre("Soul");
        rec4.setFormat("12Inch");
        rec4.setCustomer(cust4);
        recordRepository.save(rec4);
      }

			// // fetch all customers
			// logger.info("Customers found with findAll():");
			// logger.info("-------------------------------");
			// for (Customer customer : customerRepository.findAll()) {
			// 	logger.info(customer.toString());
			// }
			// logger.info("");

      // fetch all records
			logger.info("Records found with findAll():");
			logger.info("-------------------------------");
			for (Record record : recordRepository.findAll()) {
				logger.info(record.toString());
			}
			logger.info("");

      // fetch records for customer with id 1
      logger.info("Records found with findByCustomerId():");
			logger.info("-------------------------------");
			for (Record record : recordRepository.findByCustomerId(1L)) {
				logger.info(record.toString());
			}
			logger.info("");

      //
			// // fetch an individual customer by ID
			// Customer customer = customerRepository.findOne(1L);
			// logger.info("Customer found with findOne(1L):");
			// logger.info("--------------------------------");
			// logger.info(customer.toString());
			// logger.info("");
      //
			// // fetch customers by email
			// logger.info("Customer found with findByEmail('mj@jackson.com'):");
			// logger.info("--------------------------------------------");
			// for (Customer mj : customerRepository.findByEmail("mj@jackson.com")) {
			// 	logger.info(mj.toString());
			// }
			// logger.info("");

      // // example operation usage
      // logger.info("Calling example operation customer.Create");
      // Map<String, Object> payload = new HashMap();
      // Map<String, Object> params = new HashMap();
      // params.put("email", "test@example.com");
      // payload.put("params", params);
      //
      // Create op = new Create();
      // Map result = op.run(payload);
      // Customer createdCustomer = (Customer) result.get("model");
      //
      // logger.info(createdCustomer.toString());
      // logger.info("");
		};
	}

}
