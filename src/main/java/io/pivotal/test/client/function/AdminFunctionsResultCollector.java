package io.pivotal.test.client.function;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.internal.logging.LogService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component("adminResultCollector")
public class AdminFunctionsResultCollector implements ResultCollector<Map, Set> {

  private Map<String,Map> results = new ConcurrentHashMap<>();

  @Override
  public Set getResult() throws FunctionException {
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
