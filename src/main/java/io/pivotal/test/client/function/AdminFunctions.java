package io.pivotal.test.client.function;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;
//import org.springframework.data.gemfire.function.annotation.OnServers;

import java.util.Map;

//@OnServers(resultCollector = "adminResultCollector")
@OnRegion(region = "Trades", resultCollector = "adminResultCollector")
public interface AdminFunctions {

  @FunctionId("GetMetricsFunction")
  //Map<String,Map<String,Map<String,?>>> getMetrics(String type);
  Object getMetrics();
}
