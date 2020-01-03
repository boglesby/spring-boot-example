package io.pivotal.test.client.service;

import io.pivotal.test.client.domain.CusipHelper;
import io.pivotal.test.client.domain.Trade;
import io.pivotal.test.client.function.AdminFunctions;
import io.pivotal.test.client.function.TradeFunctions;
import io.pivotal.test.client.metrics.Metrics;
import io.pivotal.test.client.metrics.MetricsHelper;
import io.pivotal.test.client.repository.TradeRepository;
import org.apache.geode.internal.logging.LogService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import static io.pivotal.test.client.Constants.*;

@Service
public class TradeService {

  @Autowired
  private TradeRepository repository;

  @Autowired
  private TradeFunctions tradeFunctions;

  @Autowired
  private AdminFunctions adminFunctions;

  private final AtomicInteger putOperations = new AtomicInteger();

  private final AtomicInteger getOperations = new AtomicInteger();

  private final AtomicInteger destroyOperations = new AtomicInteger();

  private final AtomicInteger queryByCusipOperations = new AtomicInteger();

  private final AtomicInteger functionUpdateOperations = new AtomicInteger();

  private volatile boolean continueOperations;

  private static final Logger logger = LogService.getLogger();

  private static final Random RANDOM = new Random();

  private static final int ENTRIES_TO_LOG = 10000;

  public Collection<Trade> queryOneByCusip(String cusip) {
    logger.info("Executing query for cusip=" + cusip);
    long start, end;
    start = System.currentTimeMillis();
    Collection<Trade> results = executeFindByCusipQuery(cusip);
    end = System.currentTimeMillis();
    logger.info("Returned " + results.size() + " trades for cusip=" + cusip + " in " + (end-start) + " ms:");
    for (Trade trade : results) {
      logger.info("\t" + trade);
    }
    return results;
  }

  public Trade getOne(int key) {
    return get(key, true);
  }

  public OperationResponse put(int numEntries, int entrySize) {
    logger.info("Putting " + numEntries + " trades of size " + entrySize + " bytes");
    long start, end;
    start = System.currentTimeMillis();
    for (int i=0; i<numEntries; i++) {
      put(i, entrySize, true);
    }
    end = System.currentTimeMillis();
    logger.info("Put " + numEntries + " trades of size " + entrySize + " bytes in " + (end-start) + " ms");
    return new OperationResponse(PUT, Status.SUCCESS, end-start);
  }

  public OperationResponse get(int numEntries) {
    logger.info("Getting " + numEntries + " trades");
    long start, end;
    start = System.currentTimeMillis();
    for (int i=0; i<numEntries; i++) {
      get(i, true);
    }
    end = System.currentTimeMillis();
    logger.info("Got " + numEntries + " trades in " + (end-start) + " ms");
    return new OperationResponse(GET, Status.SUCCESS, end-start);
  }

  public OperationResponse destroy(int numEntries) {
    logger.info("Destroying " + numEntries + " trades");
    long start, end;
    start = System.currentTimeMillis();
    for (int i=0; i<numEntries; i++) {
      destroy(i, true);
    }
    end = System.currentTimeMillis();
    logger.info("Destroyed " + numEntries + " trades in " + (end-start) + " ms");
    return new OperationResponse(DESTROY, Status.SUCCESS, end-start);
  }

  public OperationResponse queryByCusip(int numQueries) {
    logger.info("Executing " + numQueries + " cusip queries");
    long start, end;
    start = System.currentTimeMillis();
    for (int i=0; i<numQueries; i++) {
      String cusip = CusipHelper.getCusip();
      Collection<Trade> results = executeFindByCusipQuery(cusip);
      logger.info("Returned " + results.size() + " trades for cusip=" + cusip);
    }
    end = System.currentTimeMillis();
    logger.info("Executed " + numQueries + " cusip queries in " + (end-start) + " ms");
    return new OperationResponse(QUERY_BY_CUSIP, Status.SUCCESS, end-start);
  }

  public OperationResponse functionUpdate(int numEntries) {
    logger.info("Updating " + numEntries + " trades with function");
    long start, end;
    start = System.currentTimeMillis();
    for (int i = 0; i < numEntries; i++) {
      Object result = executUpdateFunction(i);
      logger.info("Updated trade " + i + " result=" + result);
    }
    end = System.currentTimeMillis();
    logger.info("Updated " + numEntries + " trades with function in " + (end - start) + " ms");
    return new OperationResponse(FUNCTION_UPDATE, Status.SUCCESS, end - start);
  }

  public void putForever(int numEntries, int entrySize) {
    logger.info("Putting " + numEntries + " trades of size " + entrySize + " bytes forever");
    this.continueOperations = true;
    this.putOperations.set(0);
    int i=0;
    long start=0, end=0;
    start = System.currentTimeMillis();
    while (continueOperations()) {
      put(RANDOM.nextInt(numEntries), entrySize, false);
      if ((i+1) % ENTRIES_TO_LOG == 0) {
        end = System.currentTimeMillis();
        logger.info("Put " + (i+1) + " trades of size " + entrySize + " bytes in " + (end-start) + " ms");
        start = System.currentTimeMillis();
      }
      i++;
      this.putOperations.incrementAndGet();
    }
    logger.info("Stopping after putting " + i + " trades of size " + entrySize + " bytes");
  }

  public void getForever(int numEntries) {
    logger.info("Getting " + numEntries + " trades forever");
    this.continueOperations = true;
    this.getOperations.set(0);
    int i=0;
    long start=0, end=0;
    start = System.currentTimeMillis();
    while (continueOperations()) {
      get(RANDOM.nextInt(numEntries), false);
      if ((i+1) % ENTRIES_TO_LOG == 0) {
        end = System.currentTimeMillis();
        logger.info("Got " + (i+1) + " trades in " + (end-start) + " ms");
        start = System.currentTimeMillis();
      }
      i++;
      this.getOperations.incrementAndGet();
    }
    logger.info("Stopping after getting " + i + " trades");
  }

  public void destroyForever(int numEntries) {
    logger.info("Destroying " + numEntries + " trades forever");
    this.continueOperations = true;
    this.destroyOperations.set(0);
    int i=0;
    long start=0, end=0;
    start = System.currentTimeMillis();
    while (continueOperations()) {
      destroy(RANDOM.nextInt(numEntries), false);
      if ((i+1) % ENTRIES_TO_LOG == 0) {
        end = System.currentTimeMillis();
        logger.info("Destroyed " + (i+1) + " trades in " + (end-start) + " ms");
        start = System.currentTimeMillis();
      }
      i++;
      this.destroyOperations.incrementAndGet();
    }
    logger.info("Stopping after destroying " + i + " trades");
  }

  public void queryByCusipForever() {
    logger.info("Executing cusip queries forever");
    this.continueOperations = true;
    this.queryByCusipOperations.set(0);
    int i=0;
    long start=0, end=0;
    start = System.currentTimeMillis();
    while (continueOperations()) {
      executeFindByCusipQuery(CusipHelper.getCusip());
      if ((i+1) % ENTRIES_TO_LOG == 0) {
        end = System.currentTimeMillis();
        logger.info("Executed " + (i+1) + " cusip queries in " + (end-start) + " ms");
        start = System.currentTimeMillis();
      }
      i++;
      this.queryByCusipOperations.incrementAndGet();
    }
    logger.info("Stopping after executing " + i + " cusip queries");
  }

  public void functionUpdateForever(int numEntries) {
    logger.info("Updating " + numEntries + " trades with function forever");
    this.continueOperations = true;
    this.functionUpdateOperations.set(0);
    int i=0;
    long start=0, end=0;
    start = System.currentTimeMillis();
    while (continueOperations()) {
      executUpdateFunction(RANDOM.nextInt(numEntries));
      if ((i+1) % ENTRIES_TO_LOG == 0) {
        end = System.currentTimeMillis();
        logger.info("Updated " + (i+1) + " trades with function in " + (end-start) + " ms");
        start = System.currentTimeMillis();
      }
      i++;
      this.functionUpdateOperations.incrementAndGet();
    }
    logger.info("Stopping after updating " + i + " trades with function");
  }

  public OperationResponse putForever(final int numEntries, final int entrySize, Optional<Integer> numThreads) {
    for (int i=0; i<numThreads.orElse(1); i++) {
      Thread thread = new Thread(() -> putForever(numEntries, entrySize));
      thread.start();
    }
    return new OperationResponse(PUT_FOREVER, Status.SUCCESS, 0);
  }

  public OperationResponse getForever(final int numEntries, Optional<Integer> numThreads) {
    for (int i=0; i<numThreads.orElse(1); i++) {
      Thread thread = new Thread(() -> getForever(numEntries));
      thread.start();
    }
    return new OperationResponse(GET_FOREVER, Status.SUCCESS, 0);
  }

  public OperationResponse destroyForever(final int numEntries, Optional<Integer> numThreads) {
    for (int i=0; i<numThreads.orElse(1); i++) {
      Thread thread = new Thread(() -> destroyForever(numEntries));
      thread.start();
    }
    return new OperationResponse(DESTROY_FOREVER, Status.SUCCESS, 0);
  }

  public OperationResponse queryByCusipForever(Optional<Integer> numThreads) {
    for (int i=0; i<numThreads.orElse(1); i++) {
      Thread thread = new Thread(() -> queryByCusipForever());
      thread.start();
    }
    return new OperationResponse(QUERY_BY_CUSIP_FOREVER, Status.SUCCESS, 0);
  }

  public OperationResponse functionUpdateForever(final int numEntries, Optional<Integer> numThreads) {
    for (int i=0; i<numThreads.orElse(1); i++) {
      Thread thread = new Thread(() -> functionUpdateForever(numEntries));
      thread.start();
    }
    return new OperationResponse(FUNCTION_UPDATE_FOREVER, Status.SUCCESS, 0);
  }

  public OperationResponse[] startTest(int numEntries, int entrySize, Optional<Integer> numThreads) {
    OperationResponse putResponse = putForever(numEntries, entrySize, numThreads);
    OperationResponse getResponse = getForever(numEntries, numThreads);
    OperationResponse queryResponse = queryByCusipForever(numThreads);
    OperationResponse functionResponse = functionUpdateForever(numEntries, numThreads);
    return new OperationResponse[] {putResponse, getResponse, queryResponse, functionResponse};
  }

  public OperationResponse stopOperations() {
    this.continueOperations = false;
    logger.info("Stopping operations");
    return new OperationResponse(STOP_OPERATIONS, Status.SUCCESS, 0);
  }

  public Object getMetrics(String type) {
    return new Metrics(getClientMetrics(type), getServerMetrics(type));
  }

  private void put(int index, int entrySize, boolean log) {
    Trade trade = new Trade(String.valueOf(index), CusipHelper.getCusip(), RANDOM.nextInt(100), new BigDecimal(BigInteger.valueOf(RANDOM.nextInt(100000)), 2), new byte[entrySize]);
    trade = this.repository.save(trade);
    if (log) {
      logger.info("Saved " + trade);
    }
  }

  private Trade get(int index, boolean log) {
    String key = String.valueOf(index);
    Optional<Trade> tradeOptional = this.repository.findById(key);
    Trade trade = null;
    if (tradeOptional.isPresent()) {
      trade = tradeOptional.get();
    }
    if (log) {
      logger.info(trade == null ? "No trade exists for key=" + key : "Got " + trade);
    }
    return trade;
  }

  private void destroy(int index, boolean log) {
    String key = String.valueOf(index);
    this.repository.deleteById(key);
    if (log) {
      logger.info("Destroyed key=" + key);
    }
  }

  private Collection<Trade> executeFindByCusipQuery(String cusip) {
    return this.repository.findByCusip(cusip);
  }

  private Object executUpdateFunction(int index) {
    return this.tradeFunctions.updateTrade(Collections.singleton(String.valueOf(index)), RANDOM.nextInt(100), new BigDecimal(BigInteger.valueOf(RANDOM.nextInt(100000)), 2));
  }

  private Map getServerMetrics(String type) {
    Map serverMetrics = null;
    if (type.equals("all") || type.equals("server")) {
      Iterable serverMetricsIter = (Iterable) this.adminFunctions.getMetrics();
      serverMetrics = (Map) serverMetricsIter.iterator().next();
    }
    return serverMetrics;
  }

  private Map getClientMetrics(String type) {
    Map clientMetrics = null;
    if (type.equals("all") || type.equals("client")) {
      clientMetrics = new TreeMap();
      addOperationMetrics(clientMetrics);
      MetricsHelper.addOSMetrics(clientMetrics);
      MetricsHelper.addGCMetrics(clientMetrics);
      MetricsHelper.addMemoryMetrics(clientMetrics);
    }
    return clientMetrics;
  }

  private void addOperationMetrics(Map clientMetrics) {
    Map operationMetrics = new TreeMap();
    clientMetrics.put("operations", operationMetrics);
    operationMetrics.put("puts", this.putOperations.get());
    operationMetrics.put("gets", this.getOperations.get());
    operationMetrics.put("destroys", this.destroyOperations.get());
    operationMetrics.put("cusipQueries", this.queryByCusipOperations.get());
    operationMetrics.put("functionUpdates", this.functionUpdateOperations.get());
  }

  private boolean continueOperations() {
    return this.continueOperations;
  }
}
