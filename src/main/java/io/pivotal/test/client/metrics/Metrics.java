package io.pivotal.test.client.metrics;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class Metrics {

  private Map clientMetrics;

  private Map serverMetrics;

  public Metrics(Map clientMetrics, Map serverMetrics) {
    this.clientMetrics = clientMetrics;
    this.serverMetrics = serverMetrics;
  }
}
