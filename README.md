# Spring Boot Data Geode Example

## Description
This is an example of a Spring Boot Data Geode client.

It uses:

- SB 2.2.1.RELEASE
- SBDG 1.2.1.RELEASE

## Run Clients
### PCFOne
#### Run Put Client
#### Run Put Forever Client
#### Run Put Forever Threads Client
#### Run Query Client
#### Run Destroy Client
### Local
#### Run Wait Client
Run the wait client using the **runclient.sh** script like:

```
./runclient.sh wait
2019-12-05 15:31:19.041  INFO 21528 --- [  restartedMain] io.pivotal.test.client.Client: Client Command Line Arguments: [--spring.profiles.active=local, --operation=wait]
2019-12-05 15:31:19.041  INFO 21528 --- [  restartedMain] io.pivotal.test.client.Client: Client Option Argument: operation=[wait]
2019-12-05 15:31:19.041  INFO 21528 --- [  restartedMain] io.pivotal.test.client.Client: Client Option Argument: spring.profiles.active=[local]
```
#### Run Put Client
Run the put client using the **runclient.sh** script like:

```
./runclient.sh doputs 10
2019-12-05 15:33:59.941  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Loading 10 entries
2019-12-05 15:33:59.971  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=0, cusip=POT, shares=70, price=336.84)
2019-12-05 15:33:59.972  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=1, cusip=MCD, shares=51, price=667.81)
2019-12-05 15:33:59.974  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=2, cusip=SLB, shares=43, price=187.01)
2019-12-05 15:33:59.976  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=3, cusip=CMI, shares=91, price=536.14)
2019-12-05 15:33:59.978  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=4, cusip=GG, shares=45, price=991.45)
2019-12-05 15:33:59.980  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=5, cusip=PCP, shares=84, price=415.01)
2019-12-05 15:33:59.981  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=6, cusip=USB, shares=68, price=359.70)
2019-12-05 15:33:59.985  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=7, cusip=USB, shares=97, price=301.11)
2019-12-05 15:33:59.988  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=8, cusip=SDRL, shares=9, price=282.13)
2019-12-05 15:33:59.989  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Saved Trade(id=9, cusip=NOK, shares=0, price=403.29)
2019-12-05 15:33:59.989  INFO 80961 --- [  restartedMain] io.pivotal.test.client.Client: Loading 10 entries in 48 ms
```
#### Run Get Client
Run the get client using the **runclient.sh** script like:

```
./runclient.sh dogets 10
2019-12-05 15:37:58.739  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieving 10 entries
2019-12-05 15:37:58.771  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=0, cusip=POT, shares=70, price=336.84)
2019-12-05 15:37:58.772  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=1, cusip=MCD, shares=51, price=667.81)
2019-12-05 15:37:58.773  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=2, cusip=SLB, shares=43, price=187.01)
2019-12-05 15:37:58.773  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=3, cusip=CMI, shares=91, price=536.14)
2019-12-05 15:37:58.774  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=4, cusip=GG, shares=45, price=991.45)
2019-12-05 15:37:58.774  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=5, cusip=PCP, shares=84, price=415.01)
2019-12-05 15:37:58.775  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=6, cusip=USB, shares=68, price=359.70)
2019-12-05 15:37:58.776  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=7, cusip=USB, shares=97, price=301.11)
2019-12-05 15:37:58.776  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=8, cusip=SDRL, shares=9, price=282.13)
2019-12-05 15:37:58.777  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved Trade(id=9, cusip=NOK, shares=0, price=403.29)
2019-12-05 15:37:58.777  INFO 81185 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved 10 entries in 38 ms
```
#### Run Destroy Client
Run the destroy client using the **runclient.sh** script like:

```
./runclient.sh dodestroys 10
2019-12-05 16:46:03.513  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroying 10 entries
2019-12-05 16:46:03.532  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=0
2019-12-05 16:46:03.541  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=1
2019-12-05 16:46:03.542  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=2
2019-12-05 16:46:03.543  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=3
2019-12-05 16:46:03.544  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=4
2019-12-05 16:46:03.544  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=5
2019-12-05 16:46:03.545  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=6
2019-12-05 16:46:03.545  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=7
2019-12-05 16:46:03.546  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=8
2019-12-05 16:46:03.547  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed key=9
2019-12-05 16:46:03.547  INFO 85897 --- [  restartedMain] io.pivotal.test.client.Client: Destroyed 10 entries in 34 ms
```
#### Run Query Client
Run the query client using the **runclient.sh** script like:

```
./runclient.sh querybycusip AAPL
2019-12-05 15:56:27.262  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: Query entries by cusip=AAPL
2019-12-05 15:56:27.336  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: Retrieved 34 entries in 74 ms:
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=326, cusip=AAPL, shares=77, price=601.84)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=858, cusip=AAPL, shares=66, price=256.49)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=693, cusip=AAPL, shares=85, price=174.21)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=96, cusip=AAPL, shares=63, price=970.54)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=974, cusip=AAPL, shares=94, price=859.43)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=329, cusip=AAPL, shares=9, price=853.49)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=467, cusip=AAPL, shares=40, price=238.67)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=613, cusip=AAPL, shares=88, price=86.11)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=642, cusip=AAPL, shares=14, price=658.67)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=479, cusip=AAPL, shares=19, price=318.52)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=45, cusip=AAPL, shares=19, price=649.71)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=805, cusip=AAPL, shares=2, price=599.79)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=250, cusip=AAPL, shares=79, price=797.48)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=801, cusip=AAPL, shares=3, price=729.96)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=148, cusip=AAPL, shares=44, price=729.22)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=107, cusip=AAPL, shares=25, price=195.17)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=593, cusip=AAPL, shares=18, price=678.83)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=901, cusip=AAPL, shares=2, price=519.49)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=445, cusip=AAPL, shares=65, price=58.39)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=342, cusip=AAPL, shares=16, price=277.71)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=502, cusip=AAPL, shares=2, price=179.76)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=984, cusip=AAPL, shares=47, price=732.35)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=880, cusip=AAPL, shares=30, price=907.80)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=492, cusip=AAPL, shares=67, price=727.57)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=377, cusip=AAPL, shares=40, price=811.11)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=509, cusip=AAPL, shares=82, price=342.36)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=144, cusip=AAPL, shares=19, price=26.39)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=493, cusip=AAPL, shares=5, price=303.85)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=696, cusip=AAPL, shares=81, price=266.96)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=159, cusip=AAPL, shares=64, price=159.08)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=175, cusip=AAPL, shares=20, price=576.99)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=667, cusip=AAPL, shares=44, price=190.50)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=994, cusip=AAPL, shares=7, price=488.73)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] io.pivotal.test.client.Client: 	Trade(id=902, cusip=AAPL, shares=43, price=684.72)
```
