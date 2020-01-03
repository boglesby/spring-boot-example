package io.pivotal.test.client.metrics;

import org.apache.geode.StatisticDescriptor;
import org.apache.geode.Statistics;
import org.apache.geode.StatisticsType;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.distributed.internal.InternalDistributedSystem;
import org.apache.geode.internal.statistics.platform.LinuxSystemStats;
import org.apache.geode.management.ManagementService;
import org.apache.geode.management.OSMetrics;
import org.apache.geode.management.internal.beans.MemberMBean;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MetricsHelper {

  public static void addOSMetrics(Map allMetrics) {
    InternalDistributedSystem system = (InternalDistributedSystem) CacheFactory.getAnyInstance().getDistributedSystem();

    Map osMetrics = new TreeMap();
    allMetrics.put("os", osMetrics);

    // If the linux system statistics exist, add them to the os metrics map.
    // Otherwise add the available stats from the osmetrics.
    Statistics[] systemStatisticsArr = system.findStatisticsByType(LinuxSystemStats.getType());
    if (systemStatisticsArr.length > 0) {
      Statistics systemStatistics = systemStatisticsArr[0];
      StatisticsType type = systemStatistics.getType();
      for (StatisticDescriptor descriptor : type.getStatistics()) {
        String statName = descriptor.getName();
        Number statValue = systemStatistics.get(statName);
        osMetrics.put(statName, statValue);
      }
    }
  }

  public static void addGCMetrics(Map allMetrics) {
    Map gcMetrics = new TreeMap();
    allMetrics.put("gc", gcMetrics);
    List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
    for (GarbageCollectorMXBean gcBean : gcBeans) {
      gcMetrics.put(gcBean.getName() + "_collections", gcBean.getCollectionCount());
      gcMetrics.put(gcBean.getName() + "_collectionTime", gcBean.getCollectionTime());
    }
  }

  public static void addMemoryMetrics(Map allMetrics) {
    Map memoryMetrics = new TreeMap();
    allMetrics.put("memory", memoryMetrics);
    List<MemoryPoolMXBean> memoryBeans = ManagementFactory.getMemoryPoolMXBeans();
    for (MemoryPoolMXBean memoryBean : memoryBeans) {
      if (memoryBean.getName().contains("Old") || memoryBean.getName().contains("Eden")) {
        String memoryBeanName = memoryBean.getName().replaceAll(" ", "_");
        MemoryUsage usage = memoryBean.getUsage();
        memoryMetrics.put(memoryBeanName + "_init", usage.getInit());
        memoryMetrics.put(memoryBeanName + "_used", usage.getUsed());
        memoryMetrics.put(memoryBeanName + "_max", usage.getMax());
      }
    }
  }
}
