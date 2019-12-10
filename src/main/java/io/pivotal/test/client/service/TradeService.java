package io.pivotal.test.client.service;

import io.pivotal.test.client.domain.CusipHelper;
import io.pivotal.test.client.domain.Trade;
import io.pivotal.test.client.function.TradeFunctions;
import io.pivotal.test.client.repository.TradeRepository;
import org.apache.geode.internal.logging.LogService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

@Service
public class TradeService {

  @Autowired
  private TradeRepository repository;

//  @Autowired
//  private TradeFunctions functions;

  private volatile boolean continueLoading;

  private static final Logger logger = LogService.getLogger();

  private static final Random RANDOM = new Random();

  public Collection<Trade> queryByCusip(String cusip) {
    logger.info("Query trades by cusip=" + cusip);
    long start, end;
    start = System.currentTimeMillis();
    Collection<Trade> results = repository.findByCusip(cusip);
    end = System.currentTimeMillis();
    logger.info("Retrieved " + results.size() + " trades in " + (end-start) + " ms:");
    for (Trade trade : results) {
      logger.info("\t" + trade);
    }
    return results;
  }

  public Optional<Trade> get(String id) {
    Optional<Trade> trade = this.repository.findById(id);
    logger.info("Retrieved " + trade.get());
    return trade;
  }

  public OperationResponse doPuts(int numEntries) {
    logger.info("Loading " + numEntries + " trades");
    long start, end;
    start = System.currentTimeMillis();
    for (int i=0; i<numEntries; i++) {
      put(i, true);
    }
    end = System.currentTimeMillis();
    logger.info("Loading " + numEntries + " trades in " + (end-start) + " ms");
    return new OperationResponse(Status.SUCCESS, end-start);
  }

  public OperationResponse doGets(int numEntries) {
    logger.info("Retrieving " + numEntries + " trades");
    long start, end;
    start = System.currentTimeMillis();
    for (int i=0; i<numEntries; i++) {
      Optional<Trade> trade = this.repository.findById(String.valueOf(i));
      logger.info("Retrieved " + trade.get());
    }
    end = System.currentTimeMillis();
    logger.info("Retrieved " + numEntries + " trades in " + (end-start) + " ms");
    return new OperationResponse(Status.SUCCESS, end-start);
  }

  public OperationResponse doDestroys(int numEntries) {
    logger.info("Destroying " + numEntries + " trades");
    long start, end;
    start = System.currentTimeMillis();
    for (int i=0; i<numEntries; i++) {
      String key = String.valueOf(i);
      this.repository.deleteById(key);
      logger.info("Destroyed key=" + key);
    }
    end = System.currentTimeMillis();
    logger.info("Destroyed " + numEntries + " trades in " + (end-start) + " ms");
    return new OperationResponse(Status.SUCCESS, end-start);
  }

  public OperationResponse doPutsForever(int numEntries) {
    logger.info("Loading entries forever");
    this.continueLoading = true;
    int i=0;
    long start=0, end=0;
    start = System.currentTimeMillis();
    while (continueLoading()) {
      put(RANDOM.nextInt(numEntries), false);
      if ((i+1) % 1000 == 0) {
        end = System.currentTimeMillis();
        logger.info("Loaded " + (i+1) + " entries in " + (end-start) + " ms");
        start = System.currentTimeMillis();
      }
      i++;
    }
    return new OperationResponse(Status.SUCCESS, 0);
  }

  public OperationResponse doPutsForeverThreads(final int numEntries, int numThreads) {
    for (int i=0; i<numThreads; i++) {
      Thread thread = new Thread(() -> doPutsForever(numEntries));
      thread.start();
    }
    return new OperationResponse(Status.SUCCESS, 0);
  }

  public OperationResponse stopPutting() {
    this.continueLoading = false;
    return new OperationResponse(Status.SUCCESS, 0);
  }

  private void put(int i, boolean log) {
    Trade trade = new Trade(String.valueOf(i), CusipHelper.getCusip(), RANDOM.nextInt(100), new BigDecimal(BigInteger.valueOf(RANDOM.nextInt(100000)), 2));
    trade = this.repository.save(trade);
    if (log) {
      logger.info("Saved " + trade);
    }
  }

  private boolean continueLoading() {
    return this.continueLoading;
  }
}
