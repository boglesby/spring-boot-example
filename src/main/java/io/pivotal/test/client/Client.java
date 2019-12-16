package io.pivotal.test.client;

import io.pivotal.test.client.domain.Trade;
import io.pivotal.test.client.service.TradeService;
import org.apache.geode.internal.logging.LogService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import static io.pivotal.test.client.Constants.*;

@SpringBootApplication(exclude = ContinuousQueryAutoConfiguration.class) // disable subscriptions
@EnableEntityDefinedRegions(basePackageClasses = Trade.class)
public class Client {

  private static final Logger logger = LogService.getLogger();

  @Autowired
  private TradeService service;

  public static void main(String[] args) {
    new SpringApplicationBuilder(Client.class)
      .web(WebApplicationType.SERVLET)
      .build()
      .run(args);
  }

  @Bean
  ApplicationRunner runner() {
    return args -> {
      dumpArguments(args);
      List<String> operations = args.getOptionValues(OPERATION);
      if (operations == null || operations.get(0).equals("wait")) {
        waitForever();
      } else {
        String parameter1 = args.getOptionValues(PARAMETER_1).get(0);
        String parameter2 = null;
        if (args.containsOption(PARAMETER_2)) {
          parameter2 = args.getOptionValues(PARAMETER_2).get(0);
        }
        String parameter3 = null;
        if (args.containsOption(PARAMETER_3)) {
          parameter3 = args.getOptionValues(PARAMETER_3).get(0);
        }
        switch (operations.get(0)) {
        case PUT:
          this.service.put(Integer.parseInt(parameter1), Integer.parseInt(parameter2));
          break;
        case GET:
          this.service.get(Integer.parseInt(parameter1));
          break;
        case DESTROY:
          this.service.destroy(Integer.parseInt(parameter1));
          break;
        case QUERY_BY_CUSIP:
          this.service.queryByCusip(Integer.parseInt(parameter1));
          break;
        case FUNCTION_UPDATE:
          this.service.functionUpdate(Integer.parseInt(parameter1));
          break;
        case PUT_FOREVER:
          this.service.putForever(Integer.parseInt(parameter1), Integer.parseInt(parameter2));
          break;
        case GET_FOREVER:
          this.service.getForever(Integer.parseInt(parameter1));
          break;
        case DESTROY_FOREVER:
          this.service.getForever(Integer.parseInt(parameter1));
          break;
        case QUERY_BY_CUSIP_FOREVER:
          this.service.queryByCusipForever();
          break;
        case FUNCTION_UPDATE_FOREVER:
          this.service.functionUpdateForever(Integer.parseInt(parameter1));
          break;
        case PUT_FOREVER_THREADS:
          this.service.putForeverThreads(Integer.parseInt(parameter1), Integer.parseInt(parameter2), Integer.parseInt(parameter3));
          break;
        case GET_FOREVER_THREADS:
          this.service.getForeverThreads(Integer.parseInt(parameter1), Integer.parseInt(parameter2));
          break;
        case DESTROY_FOREVER_THREADS:
          this.service.destroyForeverThreads(Integer.parseInt(parameter1), Integer.parseInt(parameter2));
          break;
        case QUERY_BY_CUSIP_FOREVER_THREADS:
          this.service.queryByCusipForeverThreads(Integer.parseInt(parameter1));
          break;
        case FUNCTION_UPDATE_FOREVER_THREADS:
          this.service.functionUpdateForeverThreads(Integer.parseInt(parameter1), Integer.parseInt(parameter2));
          break;
        case GET_ONE:
          this.service.getOne(Integer.parseInt(parameter1));
          break;
        case QUERY_ONE_BY_CUSIP:
          this.service.queryOneByCusip(parameter1);
          break;
        }
      }
    };
  }

//  @Bean
//  MappingPdxSerializer myCustomMappingPdxSerializer() {
//    logger.warn("XXX Client.myCustomMappingPdxSerializer ", new Exception());
//    return MappingPdxSerializer.newMappingPdxSerializer();
//  }

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
