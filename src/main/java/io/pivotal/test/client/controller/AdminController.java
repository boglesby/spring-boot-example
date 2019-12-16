package io.pivotal.test.client.controller;

import io.pivotal.test.client.function.AdminFunctions;
import org.apache.geode.cache.client.Pool;
import org.apache.geode.cache.client.PoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
public class AdminController {

//  @Autowired
//  private AdminFunctions functions;

  @GetMapping("/pools")
  @ResponseStatus(HttpStatus.OK)
  public String getPools() {
    StringBuilder builder = new StringBuilder();
    Collection<Pool> pools = PoolManager.getAll().values();
    builder
      .append("The client defines the following ")
      .append(pools.size())
      .append(" pools:");
    for (Pool pool : pools) {
      builder
        .append("\n\tname=")
        .append(pool.getName())
        .append("; locators=")
        .append(pool.getLocators());
    }
    return builder.toString();
  }

//  @GetMapping("/metrics/{type}")
//  @ResponseStatus(HttpStatus.OK)
//  public Object getMetrics(@PathVariable String type) {
//    StringBuilder builder = new StringBuilder();
//    Map<String,Map<String,Map<String,?>>> allServerMetrics = this.functions.getMetrics(type);
//    for (Map.Entry<String,Map<String,Map<String,?>>> serverMetrics : allServerMetrics.entrySet()) {
//      builder.append("\n").append(serverMetrics.getKey()).append(":");
//      for (Map.Entry<String,Map<String,?>> serverMetricTypes : serverMetrics.getValue().entrySet()) {
//        builder.append("\n\t").append(serverMetricTypes.getKey()).append(":");
//        for (Map.Entry<String,?> serverMetric : serverMetricTypes.getValue().entrySet()) {
//          builder.append("\n\t\t").append(serverMetric.getKey()).append("=").append(serverMetric.getValue());
//        }
//      }
//    }
//    return builder.toString();
//  }
}
