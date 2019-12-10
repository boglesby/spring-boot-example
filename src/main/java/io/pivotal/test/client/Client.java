package io.pivotal.test.client;

import io.pivotal.test.client.domain.Trade;
import io.pivotal.test.client.service.TradeService;
import org.apache.geode.internal.logging.LogService;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.geode.boot.autoconfigure.ContinuousQueryAutoConfiguration;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = ContinuousQueryAutoConfiguration.class) // disable subscriptions
@EnableEntityDefinedRegions(basePackageClasses = Trade.class)
public class Client {

  private static final Logger logger = LogService.getLogger();

  public static void main(String[] args) {
    new SpringApplicationBuilder(Client.class)
      .web(WebApplicationType.SERVLET)
      .build()
      .run(args);
  }

  @Bean
  ApplicationRunner runner(TradeService service) {
    return args -> {
      dumpArguments(args);
      List<String> operations = args.getOptionValues("operation");
      if (operations == null) {
        waitForever();
      } else {
        switch (operations.get(0)) {
        case "doputs":
          service.doPuts(Integer.parseInt(args.getOptionValues("parameter1").get(0)));
          break;
        case "doputsforever":
          service.doPutsForever(Integer.parseInt(args.getOptionValues("parameter1").get(0)));
          break;
        case "doputsforeverthreads":
          service.doPutsForeverThreads(Integer.parseInt(args.getOptionValues("parameter1").get(0)), Integer.parseInt(args.getOptionValues("parameter2").get(0)));
          break;
        case "dogets":
          service.doGets(Integer.parseInt(args.getOptionValues("parameter1").get(0)));
          break;
        case "dodestroys":
          service.doDestroys(Integer.parseInt(args.getOptionValues("parameter1").get(0)));
          break;
        case "get":
          service.get(args.getOptionValues("parameter1").get(0));
          break;
        case "querybycusip":
          service.queryByCusip(args.getOptionValues("parameter1").get(0));
          break;
        case "wait":
          waitForever();
        }
      }
    };
  }

  private void dumpArguments(ApplicationArguments args) {
    logger.info("Client Command Line Arguments: " + Arrays.toString(args.getSourceArgs()));
    for (String name : args.getOptionNames()){
      logger.info("Client Option Argument: " + name + "=" + args.getOptionValues(name));
    }
  }

  private void waitForever() throws InterruptedException {
    Object obj = new Object();
    synchronized (obj) {
      obj.wait();
    }
  }
}
