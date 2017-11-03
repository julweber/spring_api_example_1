package training.api;

import training.Greeter;
// spring dependencies
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

// other dependencies
import org.joda.time.LocalTime;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping(value = "/")
    @ResponseBody
    String home() {
      LocalTime currentTime = new LocalTime();
      System.out.println("The current local time is: " + currentTime);

      Greeter greeter = new Greeter();
      return greeter.sayHello();
    }

    // public static void main(String[] args) throws Exception {
    //     SpringApplication.run(SampleController.class, args);
    // }
}
