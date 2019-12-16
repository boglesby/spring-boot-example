package io.pivotal.test.client.function;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnServers;

import java.util.Map;

//@OnServers(resultCollector = "adminResultCollector")
public interface AdminFunctions {

  // This function returns a map containing entries like:
  // 192.168.1.4(server-1:11750)<v2>:41002:
  //   os:
  //     totalMemory=-1
  //     fdCount=124
  //     cpus=12
  //     processCpuTime=37141888000
  //     name=Mac OS X
  //     loadAverage=12.75537109375
  //     arch=x86_64
  //     freeMemory=-1
  //     version=10.14.3
  //   gc:
  //     ConcurrentMarkSweep_collections=2
  //     ConcurrentMarkSweep_collectionTime=96
  //     ParNew_collectionTime=101
  //     ParNew_collections=3
  //   memory:
  //     CMS_Old_Gen_used=10745056
  //     CMS_Old_Gen_max=1792016384
  //     CMS_Old_Gen_init=1792016384
  @FunctionId("GetMetricsFunction")
  Map<String,Map<String,Map<String,?>>> getMetrics(String type);
}
