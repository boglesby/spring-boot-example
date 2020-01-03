package io.pivotal.test.client.function;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.internal.logging.LogService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component("adminResultCollector")
public class AdminFunctionsResultCollector implements ResultCollector<Map, Set> {

  private Map<String,Map> results = new ConcurrentHashMap<>();

  @Override
  public Set getResult() throws FunctionException {
    // SDG OnServers functions expect an iterable containing one element (the actual result).
    // In this case the first element of the iterable is passed to the caller.
    // java.lang.ClassCastException: java.util.concurrent.ConcurrentHashMap cannot be cast to java.lang.Iterable
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionExecution.execute(AbstractFunctionExecution.java:140) ~[spring-data-geode-2.2.1.RELEASE.jar:2.2.1.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionExecution.execute(AbstractFunctionExecution.java:95) ~[spring-data-geode-2.2.1.RELEASE.jar:2.2.1.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionExecution.executeAndExtract(AbstractFunctionExecution.java:158) ~[spring-data-geode-2.2.1.RELEASE.jar:2.2.1.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionTemplate.executeAndExtract(AbstractFunctionTemplate.java:79) ~[spring-data-geode-2.2.1.RELEASE.jar:2.2.1.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionTemplate.executeAndExtract(AbstractFunctionTemplate.java:57) ~[spring-data-geode-2.2.1.RELEASE.jar:2.2.1.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.GemfireOnServersFunctionTemplate.executeAndExtract(GemfireOnServersFunctionTemplate.java:45) ~[spring-data-geode-2.2.1.RELEASE.jar:2.2.1.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.GemfireFunctionProxyFactoryBean.invokeFunction(GemfireFunctionProxyFactoryBean.java:102) ~[spring-data-geode-2.2.1.RELEASE.jar:2.2.1.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.GemfireFunctionProxyFactoryBean.invoke(GemfireFunctionProxyFactoryBean.java:96) ~[spring-data-geode-2.2.1.RELEASE.jar:2.2.1.RELEASE]

    // SDG OnRegions functions expect an iterable result. In this case, the iterable is passed to the caller.
    // java.lang.ClassCastException: java.util.concurrent.ConcurrentHashMap cannot be cast to java.lang.Iterable
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionExecution.execute(AbstractFunctionExecution.java:140) ~[spring-data-geode-2.2.3.RELEASE.jar:2.2.3.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionExecution.execute(AbstractFunctionExecution.java:95) ~[spring-data-geode-2.2.3.RELEASE.jar:2.2.3.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionTemplate.execute(AbstractFunctionTemplate.java:71) ~[spring-data-geode-2.2.3.RELEASE.jar:2.2.3.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.AbstractFunctionTemplate.execute(AbstractFunctionTemplate.java:52) ~[spring-data-geode-2.2.3.RELEASE.jar:2.2.3.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.GemfireOnRegionFunctionTemplate.execute(GemfireOnRegionFunctionTemplate.java:26) ~[spring-data-geode-2.2.3.RELEASE.jar:2.2.3.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.OnRegionFunctionProxyFactoryBean.invokeFunction(OnRegionFunctionProxyFactoryBean.java:65) ~[spring-data-geode-2.2.3.RELEASE.jar:2.2.3.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.OnRegionFunctionProxyFactoryBean.invokeFunction(OnRegionFunctionProxyFactoryBean.java:28) ~[spring-data-geode-2.2.3.RELEASE.jar:2.2.3.RELEASE]
    //   at org.springframework.data.gemfire.function.execution.GemfireFunctionProxyFactoryBean.invoke(GemfireFunctionProxyFactoryBean.java:96) ~[spring-data-geode-2.2.3.RELEASE.jar:2.2.3.RELEASE]

    return Collections.singleton(this.results);
  }

  @Override
  public Set getResult(long timeout, TimeUnit unit) throws FunctionException, InterruptedException {
    return getResult();
  }

  @Override
  public void addResult(DistributedMember memberID, Map result) {
    //LogService.getLogger().warn("XXX AdminFunctionsResultCollector.addResult memberID=" + memberID + "; result=" + result);
    this.results.put(memberID.toString(), result);
  }

  @Override
  public void endResults() {

  }

  @Override
  public void clearResults() {
    this.results.clear();
  }
}
