package io.pivotal.test.client.controller;

import io.pivotal.test.client.domain.Trade;
import io.pivotal.test.client.service.OperationResponse;
import io.pivotal.test.client.service.TradeService;
import org.apache.geode.cache.client.Pool;
import org.apache.geode.cache.client.PoolManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
public class TradeController {

  private final TradeService service;

  public TradeController(TradeService service) {
    this.service = service;
  }

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public String index() {
    return "Spring Data Geode Example";
  }

  @GetMapping("/trades/query/{cusip}")
  @ResponseStatus(HttpStatus.OK)
  public Collection<Trade> getTrades(@PathVariable String cusip) {
    return this.service.queryByCusip(cusip);
  }

  @GetMapping("/trades/get/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Trade> getTrade(@PathVariable String id) {
    return this.service.get(id);
  }

  @PostMapping("/trades/put/{entries}")
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse putTrades(@PathVariable int entries) {
    return this.service.doPuts(entries);
  }

  @PostMapping("/trades/putforever/{entries}")
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse putTradesForever(@PathVariable int entries) {
    return this.service.doPutsForeverThreads(entries, 1);
  }

  @PostMapping("/trades/putforeverthreads/{entries}/{threads}")
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse putTradesForeverThreads(@PathVariable int entries, @PathVariable int threads) {
    return this.service.doPutsForeverThreads(entries, threads);
  }

  @PostMapping("/trades/stopputting")
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse stopPutting() {
    return this.service.stopPutting();
  }

  @DeleteMapping("/trades/destroy/{entries}")
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse destroyTrades(@PathVariable int entries) {
    return this.service.doDestroys(entries);
  }

  @GetMapping("/getpools")
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
}
