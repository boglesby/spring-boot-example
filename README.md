# Spring Boot Data Geode Example

## Description
This is an example of a Spring Boot Data Geode client.

It uses:

- SB 2.2.2.RELEASE
- SBDG 1.2.2.RELEASE
- GEODE 1.9.2
- GemFire 9.8.4

## Deploy Application
### PCFOne
#### Build Application
Build the application jar using **mvn** like:

```
mvn clean package
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------------< io.pivotal:test >---------------------------
[INFO] Building test 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ test ---
[INFO] Deleting /Users/boglesby/Dev/Tests/spring-boot/long_running_test/client/target
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ test ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 9 source files to /Users/boglesby/Dev/Tests/spring-boot/long_running_test/client/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/boglesby/Dev/Tests/spring-boot/long_running_test/client/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ test ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ test ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:3.1.2:jar (default-jar) @ test ---
[INFO] Building jar: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/client/target/test-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.2.1.RELEASE:repackage (repackage) @ test ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.355 s
[INFO] Finished at: 2019-12-10T14:45:49-08:00
[INFO] ------------------------------------------------------------------------
```
#### Push Application
Push the application using **cf push** like:

```
cf push -f manifest.yml --no-start
Pushing from manifest to org pivot-boglesby / space playground as boglesby@pivotal.io...
Using manifest file manifest.yml
Getting app info...
Updating app with these attributes...
  name:                sdgApp
  path:                /Users/boglesby/Dev/Tests/spring-boot/long_running_test/client/target/test-0.0.1-SNAPSHOT.jar
  buildpacks:
    https://github.com/cloudfoundry/java-buildpack.git
  command:             JAVA_OPTS="-agentpath:$PWD/.java-buildpack/open_jdk_jre/bin/jvmkill-1.16.0_RELEASE=printHeapHistogram=1 -Djava.io.tmpdir=$TMPDIR -XX:ActiveProcessorCount=$(nproc) -Djava.ext.dirs=$PWD/.java-buildpack/container_security_provider:$PWD/.java-buildpack/open_jdk_jre/lib/ext -Djava.security.properties=$PWD/.java-buildpack/java_security/java.security $JAVA_OPTS" && CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-3.13.0_RELEASE -totMemory=$MEMORY_LIMIT -loadedClasses=23784 -poolType=metaspace -stackThreads=250 -vmOptions="$JAVA_OPTS") && echo JVM Memory Configuration: $CALCULATED_MEMORY && JAVA_OPTS="$JAVA_OPTS $CALCULATED_MEMORY" && MALLOC_ARENA_MAX=2 SERVER_PORT=$PORT eval exec $PWD/.java-buildpack/open_jdk_jre/bin/java $JAVA_OPTS -cp $PWD/. org.springframework.boot.loader.JarLauncher
  disk quota:          1G
  health check type:   port
  instances:           1
  memory:              768M
  stack:               cflinuxfs3
  services:
    pccService
  routes:
    sdgapp.apps.pcfone.io

Updating app sdgApp...
Mapping routes...
Comparing local files to remote cache...
Packaging files to upload...
Uploading files...
 784.36 KiB / 784.36 KiB [==========================================] 100.00% 2s

Waiting for API to complete processing files...

name:              sdgApp
requested state:   stopped
routes:            sdgapp.apps.pcfone.io
last uploaded:     Tue 10 Dec 09:14:47 PST 2019
stack:             cflinuxfs3
buildpacks:        https://github.com/cloudfoundry/java-buildpack.git

type:            web
instances:       0/1
memory usage:    768M
start command:   JAVA_OPTS="-agentpath:$PWD/.java-buildpack/open_jdk_jre/bin/jvmkill-1.16.0_RELEASE=printHeapHistogram=1
                 -Djava.io.tmpdir=$TMPDIR -XX:ActiveProcessorCount=$(nproc)
                 -Djava.ext.dirs=$PWD/.java-buildpack/container_security_provider:$PWD/.java-buildpack/open_jdk_jre/lib/ext
                 -Djava.security.properties=$PWD/.java-buildpack/java_security/java.security
                 $JAVA_OPTS" &&
                 CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-3.13.0_RELEASE
                 -totMemory=$MEMORY_LIMIT -loadedClasses=23784
                 -poolType=metaspace -stackThreads=250 -vmOptions="$JAVA_OPTS")
                 && echo JVM Memory Configuration: $CALCULATED_MEMORY &&
                 JAVA_OPTS="$JAVA_OPTS $CALCULATED_MEMORY" && MALLOC_ARENA_MAX=2
                 SERVER_PORT=$PORT eval exec
                 $PWD/.java-buildpack/open_jdk_jre/bin/java $JAVA_OPTS -cp
                 $PWD/. org.springframework.boot.loader.JarLauncher
     state   since                  cpu    memory   disk     details
#0   down    2019-12-10T22:47:29Z   0.0%   0 of 0   0 of 0   
```  
#### Start Application
Start the application using **cf rs** like:

```
cf rs sdgApp
Restarting app sdgApp in org pivot-boglesby / space playground as boglesby@pivotal.io...

Staging app and tracing logs...
   Cell 01a8b19d-13f8-4b95-8ab6-b5b660e81701 creating container for instance 907e3612-5bc1-44ab-88e3-fff9cfe9a44c
   Cell 01a8b19d-13f8-4b95-8ab6-b5b660e81701 successfully created container for instance 907e3612-5bc1-44ab-88e3-fff9cfe9a44c
   Downloading app package...
   Downloading build artifacts cache...
   Downloaded build artifacts cache (42M)
   Downloaded app package (67.4M)
   -----> Java Buildpack e06e00b | https://github.com/cloudfoundry/java-buildpack.git#e06e00b
   -----> Downloading Jvmkill Agent 1.16.0_RELEASE from https://java-buildpack.cloudfoundry.org/jvmkill/bionic/x86_64/jvmkill-1.16.0-RELEASE.so (found in cache)
   -----> Downloading Open Jdk JRE 1.8.0_232 from https://java-buildpack.cloudfoundry.org/openjdk/bionic/x86_64/openjdk-jre-1.8.0_232-bionic.tar.gz (found in cache)
          Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (1.2s)
          JVM DNS caching disabled in lieu of BOSH DNS caching
   -----> Downloading Open JDK Like Memory Calculator 3.13.0_RELEASE from https://java-buildpack.cloudfoundry.org/memory-calculator/bionic/x86_64/memory-calculator-3.13.0-RELEASE.tar.gz (found in cache)
          Loaded Classes: 23004, Threads: 250
   -----> Downloading Client Certificate Mapper 1.11.0_RELEASE from https://java-buildpack.cloudfoundry.org/client-certificate-mapper/client-certificate-mapper-1.11.0-RELEASE.jar (found in cache)
   -----> Downloading Container Security Provider 1.16.0_RELEASE from https://java-buildpack.cloudfoundry.org/container-security-provider/container-security-provider-1.16.0-RELEASE.jar (found in cache)
   -----> Downloading Spring Auto Reconfiguration 2.11.0_RELEASE from https://java-buildpack.cloudfoundry.org/auto-reconfiguration/auto-reconfiguration-2.11.0-RELEASE.jar (found in cache)
   Exit status 0
   Uploading droplet, build artifacts cache...
   Uploading droplet...
   Uploading build artifacts cache...
   Uploaded build artifacts cache (42M)
   Uploaded droplet (109.5M)
   Uploading complete
   Cell 01a8b19d-13f8-4b95-8ab6-b5b660e81701 stopping instance 907e3612-5bc1-44ab-88e3-fff9cfe9a44c

Waiting for app to start...
   Cell 01a8b19d-13f8-4b95-8ab6-b5b660e81701 destroying container for instance 907e3612-5bc1-44ab-88e3-fff9cfe9a44c

name:                sdgApp
requested state:     started
isolation segment:   iso-01
routes:              sdgapp.apps.pcfone.io
last uploaded:       Tue 10 Dec 14:50:51 PST 2019
stack:               cflinuxfs3
buildpacks:          https://github.com/cloudfoundry/java-buildpack.git

type:            web
instances:       1/1
memory usage:    768M
start command:   JAVA_OPTS="-agentpath:$PWD/.java-buildpack/open_jdk_jre/bin/jvmkill-1.16.0_RELEASE=printHeapHistogram=1
                 -Djava.io.tmpdir=$TMPDIR -XX:ActiveProcessorCount=$(nproc)
                 -Djava.ext.dirs=$PWD/.java-buildpack/container_security_provider:$PWD/.java-buildpack/open_jdk_jre/lib/ext
                 -Djava.security.properties=$PWD/.java-buildpack/java_security/java.security
                 $JAVA_OPTS" &&
                 CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-3.13.0_RELEASE
                 -totMemory=$MEMORY_LIMIT -loadedClasses=23784
                 -poolType=metaspace -stackThreads=250 -vmOptions="$JAVA_OPTS")
                 && echo JVM Memory Configuration: $CALCULATED_MEMORY &&
                 JAVA_OPTS="$JAVA_OPTS $CALCULATED_MEMORY" && MALLOC_ARENA_MAX=2
                 SERVER_PORT=$PORT eval exec
                 $PWD/.java-buildpack/open_jdk_jre/bin/java $JAVA_OPTS -cp
                 $PWD/. org.springframework.boot.loader.JarLauncher
     state     since                  cpu    memory          disk       details
#0   running   2019-12-10T22:51:13Z   0.0%   38.4K of 768M   8K of 1G   
```
#### Get Logs
Get the latest application log messages using the **cf logs** command like:

```
cf logs sdgApp --recent
...
   2019-12-19T16:20:09.95-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:20:09.955  INFO 33 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
   2019-12-19T16:20:09.96-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:20:09.960  INFO 33 --- [           main] io.pivotal.test.client.Client            : Started Client in 7.316 seconds (JVM running for 8.365)
   2019-12-19T16:20:10.01-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:20:10.017  INFO 33 --- [           main] io.pivotal.test.client.Client            : Client Command Line Arguments: []
   2019-12-19T16:20:11.67-0800 [CELL/0] OUT Container became healthy

```
#### Tail Logs
Tail the application log messages using the **cf logs** command like:

```
cf logs sdgApp
Retrieving logs for app sdgApp in org pivot-boglesby / space playground as boglesby@pivotal.io...
```
## Run Clients
### PCFOne
#### REST
##### Put Command
Run the put command using **curl** like:

```
curl -X POST https://sdgapp.apps.pcfone.io/trades/put/10/1024
{"operation":"put","status":"SUCCESS","completionTime":191}
```
The application will log messages like:

```
   2019-12-19T16:45:46.45-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.457  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Putting 10 trades of size 1024 bytes
   2019-12-19T16:45:46.51-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.517  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=0, cusip=HD, shares=55, price=371.99)
   2019-12-19T16:45:46.52-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.524  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=1, cusip=SAP, shares=98, price=544.99)
   2019-12-19T16:45:46.53-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.531  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=2, cusip=UPS, shares=39, price=114.47)
   2019-12-19T16:45:46.54-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.548  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=3, cusip=HD, shares=77, price=613.03)
   2019-12-19T16:45:46.56-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.560  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=4, cusip=MMM, shares=59, price=671.10)
   2019-12-19T16:45:46.57-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.570  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=5, cusip=BUD, shares=92, price=226.78)
   2019-12-19T16:45:46.59-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.589  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=6, cusip=AVGO, shares=6, price=321.33)
   2019-12-19T16:45:46.59-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.592  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=7, cusip=AMZN, shares=26, price=908.64)
   2019-12-19T16:45:46.64-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.645  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=8, cusip=AXP, shares=69, price=911.00)
   2019-12-19T16:45:46.64-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.648  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Saved Trade(id=9, cusip=TM, shares=72, price=975.52)
   2019-12-19T16:45:46.64-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:45:46.649  INFO 33 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Put 10 trades of size 1024 bytes in 191 ms
```
###### Get Command
Run the get command using **curl** like:

```
curl https://sdgapp.apps.pcfone.io/trades/get/10
{"operation":"get","status":"SUCCESS","completionTime":55}
```
The application will log messages like:

```
   2019-12-19T16:47:42.28-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.286  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Getting 10 trades
   2019-12-19T16:47:42.31-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.318  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=0, cusip=HD, shares=55, price=371.99)
   2019-12-19T16:47:42.32-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.326  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=1, cusip=SAP, shares=98, price=544.99)
   2019-12-19T16:47:42.32-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.327  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=2, cusip=UPS, shares=39, price=114.47)
   2019-12-19T16:47:42.32-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.329  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=3, cusip=HD, shares=77, price=613.03)
   2019-12-19T16:47:42.33-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.330  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=4, cusip=MMM, shares=59, price=671.10)
   2019-12-19T16:47:42.33-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.331  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=5, cusip=BUD, shares=92, price=226.78)
   2019-12-19T16:47:42.33-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.338  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=6, cusip=AVGO, shares=6, price=321.33)
   2019-12-19T16:47:42.33-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.339  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=7, cusip=AMZN, shares=26, price=908.64)
   2019-12-19T16:47:42.34-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.340  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=8, cusip=AXP, shares=69, price=911.00)
   2019-12-19T16:47:42.34-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.341  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got Trade(id=9, cusip=TM, shares=72, price=975.52)
   2019-12-19T16:47:42.34-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:47:42.341  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Got 10 trades in 55 ms
```
##### Destroy Command
Run the destroy command using **curl** like:

```
curl -X DELETE https://sdgapp.apps.pcfone.io/trades/destroy/10
{"operation":"destroy","status":"SUCCESS","completionTime":63}
```
The application will log messages like:

```
   2019-12-19T16:48:10.72-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.719  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroying 10 trades
   2019-12-19T16:48:10.73-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.735  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=0
   2019-12-19T16:48:10.74-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.740  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=1
   2019-12-19T16:48:10.75-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.750  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=2
   2019-12-19T16:48:10.75-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.758  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=3
   2019-12-19T16:48:10.76-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.761  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=4
   2019-12-19T16:48:10.76-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.765  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=5
   2019-12-19T16:48:10.76-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.768  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=6
   2019-12-19T16:48:10.77-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.779  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=7
   2019-12-19T16:48:10.78-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.781  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=8
   2019-12-19T16:48:10.78-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.783  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed key=9
   2019-12-19T16:48:10.78-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:48:10.783  INFO 33 --- [nio-8080-exec-2] i.p.test.client.service.TradeService     : Destroyed 10 trades in 63 ms
```
##### Query By Cusip Command
Run the query by cusip command using **curl** like:

```
curl https://sdgapp.apps.pcfone.io/trades/querybycusip/10
```
The application will log messages like:

```
   2019-12-19T16:49:04.52-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:04.529  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Executing 10 cusip queries
   2019-12-19T16:49:04.79-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:04.797  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1803 trades for cusip=FB
   2019-12-19T16:49:04.96-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:04.959  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1848 trades for cusip=AAPL
   2019-12-19T16:49:05.04-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.049  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1889 trades for cusip=ADBE
   2019-12-19T16:49:05.11-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.110  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1838 trades for cusip=AVGO
   2019-12-19T16:49:05.25-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.259  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1832 trades for cusip=CRM
   2019-12-19T16:49:05.41-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.414  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1801 trades for cusip=SAP
   2019-12-19T16:49:05.52-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.523  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1826 trades for cusip=XOM
   2019-12-19T16:49:05.58-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.580  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1886 trades for cusip=GE
   2019-12-19T16:49:05.64-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.642  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1879 trades for cusip=NKE
   2019-12-19T16:49:05.70-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.705  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Returned 1886 trades for cusip=GE
   2019-12-19T16:49:05.70-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:49:05.705  INFO 33 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Executed 10 cusip queries in 1176 ms
```
##### Function Update Command
Run the function update command using **curl** like:

```
curl -X POST https://sdgapp.apps.pcfone.io/trades/functionupdate/10
{"operation":"functionupdate","status":"SUCCESS","completionTime":65}
```
The application will log messages like:

```
   2019-12-19T16:50:12.12-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.125  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updating 10 trades with function
   2019-12-19T16:50:12.14-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.149  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 0 result=[true]
   2019-12-19T16:50:12.16-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.164  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 1 result=[true]
   2019-12-19T16:50:12.16-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.167  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 2 result=[true]
   2019-12-19T16:50:12.17-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.174  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 3 result=[true]
   2019-12-19T16:50:12.17-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.176  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 4 result=[true]
   2019-12-19T16:50:12.17-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.179  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 5 result=[true]
   2019-12-19T16:50:12.18-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.181  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 6 result=[true]
   2019-12-19T16:50:12.18-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.186  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 7 result=[true]
   2019-12-19T16:50:12.18-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.188  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 8 result=[true]
   2019-12-19T16:50:12.19-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.190  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated trade 9 result=[true]
   2019-12-19T16:50:12.19-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:50:12.190  INFO 33 --- [io-8080-exec-10] i.p.test.client.service.TradeService     : Updated 10 trades with function in 65 ms
```
##### Put Forever Command
Run the function update command using **curl** like:

```
curl -X POST https://sdgapp.apps.pcfone.io/trades/putforever/10/1024
```
The application will log messages like:

```
...
```
##### Get Forever Command
Run the function update command using **curl** like:

```
curl https://sdgapp.apps.pcfone.io/trades/getforever/10
```
The application will log messages like:

```
...
```
##### Destroy Forever Command
Run the function update command using **curl** like:

```
curl -X DELETE https://sdgapp.apps.pcfone.io/trades/destroyforever/10
```
The application will log messages like:

```
...
```
##### Query by Cusip Forever Command
Run the function update command using **curl** like:

```
curl https://sdgapp.apps.pcfone.io/trades/querybycusipforever
```
The application will log messages like:

```
...
```
##### Function Update Forever Command
Run the function update command using **curl** like:

```
curl -X POST https://sdgapp.apps.pcfone.io/trades/functionupdateforever/10
```
The application will log messages like:

```
...
```
##### Multi-threaded Put Forever Command
Run the multi-threaded put forever command using **curl** like:

```
curl -X POST https://sdgapp.apps.pcfone.io/trades/putforever/10/1024/5
```
The application will log messages like:

```
...
```
##### Multi-threaded Get Forever Command
Run the multi-threaded get forever command using **curl** like:

```
curl https://sdgapp.apps.pcfone.io/trades/getforever/10/5
```
The application will log messages like:

```
...
```
##### Multi-threaded Destroy Forever Command
Run the multi-threaded destroy forever command using **curl** like:

```
curl -X DELETE https://sdgapp.apps.pcfone.io/trades/destroyforever/10/5
```
The application will log messages like:

```
...
```
##### Multi-threaded Query by Cusip Forever Command
Run the multi-threaded query by cusip forever command using **curl** like:

```
curl https://sdgapp.apps.pcfone.io/trades/querybycusipforever/5
```
The application will log messages like:

```
...
```
##### Multi-threaded Function Update Forever Command
Run the multi-threaded function update command using **curl** like:

```
curl -X POST https://sdgapp.apps.pcfone.io/trades/functionupdateforever/10/5
```
The application will log messages like:

```
...
```
##### Get One Command
Run the get one command using **curl** like:

```
curl https://sdgapp.apps.pcfone.io/trades/getone/0
```
The application will log messages like:

```
```
##### Query One By Cusip Command
Run the query one by cusip command using **curl** like:

```
curl https://sdgapp.apps.pcfone.io/trades/queryonebycusip/AAPL
```
The application will log messages like:

```
```
##### Test Command
Run the test command using **curl** like:

```
curl -X POST https://sdgapp.apps.pcfone.io/trades/starttest/100000/1024
[{"operation":"putforever","status":"SUCCESS","completionTime":0},{"operation":"getforever","status":"SUCCESS","completionTime":0},{"operation":"querybycusipforever","status":"SUCCESS","completionTime":0},{"operation":"functionupdateforever","status":"SUCCESS","completionTime":0}]
```
The application will log messages like:

```
   2019-12-19T16:55:37.45-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:55:37.450  INFO 33 --- [      Thread-12] i.p.test.client.service.TradeService     : Putting 10000 trades of size 1024 bytes forever
   2019-12-19T16:55:37.45-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:55:37.451  INFO 33 --- [      Thread-13] i.p.test.client.service.TradeService     : Getting 100000 trades forever
   2019-12-19T16:55:37.45-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:55:37.452  INFO 33 --- [      Thread-14] i.p.test.client.service.TradeService     : Executing cusip queries forever
   2019-12-19T16:55:37.45-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:55:37.458  INFO 33 --- [      Thread-15] i.p.test.client.service.TradeService     : Updating 100000 trades with function forever
   2019-12-19T16:55:50.19-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:55:50.194  INFO 33 --- [      Thread-13] i.p.test.client.service.TradeService     : Got 10000 trades in 12743 ms
   2019-12-19T16:55:55.83-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:55:55.830  INFO 33 --- [      Thread-12] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 18379 ms
   2019-12-19T16:55:56.82-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:55:56.822  INFO 33 --- [      Thread-15] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 19364 ms
   2019-12-19T16:55:59.66-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:55:59.662  INFO 33 --- [      Thread-13] i.p.test.client.service.TradeService     : Got 20000 trades in 9468 ms
   2019-12-19T16:56:11.27-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:56:11.274  INFO 33 --- [      Thread-13] i.p.test.client.service.TradeService     : Got 30000 trades in 11612 ms
   2019-12-19T16:56:11.96-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:56:11.960  INFO 33 --- [      Thread-12] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 16130 ms
   2019-12-19T16:56:13.96-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:56:13.966  INFO 33 --- [      Thread-15] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 17143 ms
   2019-12-19T16:56:25.74-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:56:25.742  INFO 33 --- [      Thread-12] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 13781 ms
   2019-12-19T16:56:29.08-0800 [APP/PROC/WEB/0] OUT 2019-12-20 00:56:29.085  INFO 33 --- [      Thread-15] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 15118 ms
   2019-12-19T17:03:56.82-0800 [APP/PROC/WEB/0] OUT 2019-12-20 01:03:56.829  INFO 33 --- [      Thread-14] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 499377 ms
   2019-12-19T17:12:08.31-0800 [APP/PROC/WEB/0] OUT 2019-12-20 01:12:08.314  INFO 33 --- [      Thread-14] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 491485 ms
   2019-12-19T17:20:22.31-0800 [APP/PROC/WEB/0] OUT 2019-12-20 01:20:22.310  INFO 33 --- [      Thread-14] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 493995 ms
...
```
##### Stop Operations Command
Run the stop operations command using **curl** like:

```
curl -X POST https://sdgapp.apps.pcfone.io/trades/stopoperations
```
The application will log messages like:

```
```
### Local
#### Java
##### Put Command
Run the put command using the **runclient.sh** script like:

```
./runclient.sh put 10 1024
...
2019-12-18 15:55:16.285  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Putting 10 trades of size 1024 bytes
2019-12-18 15:55:16.324  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=0, cusip=BA, shares=66, price=217.50)
2019-12-18 15:55:16.330  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=1, cusip=JPM, shares=87, price=182.33)
2019-12-18 15:55:16.337  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=2, cusip=SAP, shares=87, price=415.07)
2019-12-18 15:55:16.344  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=3, cusip=CMCSA, shares=70, price=972.59)
2019-12-18 15:55:16.347  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=4, cusip=AAPL, shares=93, price=315.32)
2019-12-18 15:55:16.351  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=5, cusip=JNJ, shares=90, price=404.47)
2019-12-18 15:55:16.356  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=6, cusip=HSBC, shares=64, price=879.82)
2019-12-18 15:55:16.363  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=7, cusip=COST, shares=48, price=378.87)
2019-12-18 15:55:16.367  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=8, cusip=ORCL, shares=3, price=882.84)
2019-12-18 15:55:16.371  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Saved Trade(id=9, cusip=SAP, shares=15, price=384.58)
2019-12-18 15:55:16.371  INFO 95421 --- [           main] i.p.test.client.service.TradeService     : Put 10 trades of size 1024 bytes in 86 ms
```
###### Get Command
Run the get command using the **runclient.sh** script like:

```
./runclient.sh get 10
...
2019-12-18 16:16:11.679  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Getting 10 trades
2019-12-18 16:16:11.720  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=0, cusip=BA, shares=66, price=217.50)
2019-12-18 16:16:11.721  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=1, cusip=JPM, shares=87, price=182.33)
2019-12-18 16:16:11.725  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=2, cusip=SAP, shares=87, price=415.07)
2019-12-18 16:16:11.730  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=3, cusip=CMCSA, shares=70, price=972.59)
2019-12-18 16:16:11.731  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=4, cusip=AAPL, shares=93, price=315.32)
2019-12-18 16:16:11.732  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=5, cusip=JNJ, shares=90, price=404.47)
2019-12-18 16:16:11.733  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=6, cusip=HSBC, shares=64, price=879.82)
2019-12-18 16:16:11.738  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=7, cusip=COST, shares=48, price=378.87)
2019-12-18 16:16:11.740  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=8, cusip=ORCL, shares=3, price=882.84)
2019-12-18 16:16:11.741  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=9, cusip=SAP, shares=15, price=384.58)
2019-12-18 16:16:11.741  INFO 96776 --- [           main] i.p.test.client.service.TradeService     : Got 10 trades in 62 ms
```
##### Destroy Command
Run the destroy command using the **runclient.sh** script like:

```
./runclient.sh destroy 10
...
2019-12-18 16:19:30.910  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroying 10 trades
2019-12-18 16:19:30.937  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=0
2019-12-18 16:19:30.950  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=1
2019-12-18 16:19:30.956  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=2
2019-12-18 16:19:30.962  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=3
2019-12-18 16:19:30.964  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=4
2019-12-18 16:19:30.968  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=5
2019-12-18 16:19:30.970  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=6
2019-12-18 16:19:30.976  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=7
2019-12-18 16:19:30.979  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=8
2019-12-18 16:19:30.982  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed key=9
2019-12-18 16:19:30.982  INFO 97101 --- [           main] i.p.test.client.service.TradeService     : Destroyed 10 trades in 71 ms
```
##### Query By Cusip Command
Run the query by cusip command using the **runclient.sh** script like:

```
./runclient.sh querybycusip 10
...
2019-12-18 16:20:58.897  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Executing 10 cusip queries
2019-12-18 16:20:59.000  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 2 trades for cusip=HON
2019-12-18 16:20:59.007  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 2 trades for cusip=PYPL
2019-12-18 16:20:59.016  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 4 trades for cusip=GE
2019-12-18 16:20:59.041  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 3 trades for cusip=ORCL
2019-12-18 16:20:59.048  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 1 trades for cusip=LMT
2019-12-18 16:20:59.055  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 3 trades for cusip=JPM
2019-12-18 16:20:59.063  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 4 trades for cusip=CSCO
2019-12-18 16:20:59.071  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 2 trades for cusip=BAC
2019-12-18 16:20:59.078  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 3 trades for cusip=JPM
2019-12-18 16:20:59.084  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Returned 4 trades for cusip=GE
2019-12-18 16:20:59.084  INFO 97261 --- [           main] i.p.test.client.service.TradeService     : Executed 10 cusip queries in 187 ms
```
##### Function Update Command
Run the function update command using the **runclient.sh** script like:

```
./runclient.sh functionupdate 10
...
2019-12-19 15:36:02.235  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updating 10 trades with function
2019-12-19 15:36:02.368  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 0 result=[true]
2019-12-19 15:36:02.484  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 1 result=[true]
2019-12-19 15:36:02.520  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 2 result=[true]
2019-12-19 15:36:02.556  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 3 result=[true]
2019-12-19 15:36:02.575  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 4 result=[true]
2019-12-19 15:36:02.596  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 5 result=[true]
2019-12-19 15:36:02.596  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 6 result=[true]
2019-12-19 15:36:02.636  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 7 result=[true]
2019-12-19 15:36:02.661  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 8 result=[true]
2019-12-19 15:36:02.683  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated trade 9 result=[true]
2019-12-19 15:36:02.683  INFO 57857 --- [           main] i.p.test.client.service.TradeService     : Updated 10 trades with function in 448 ms
```
##### Put Forever Command
Run the put forever command using the **runclient.sh** script like:

```
./runclient.sh putforever 10 1024
...
2019-12-18 16:25:13.375  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 16:25:26.151  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 12776 ms
2019-12-18 16:25:33.715  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 7564 ms
2019-12-18 16:25:39.731  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 6016 ms
2019-12-18 16:25:45.077  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 40000 trades of size 1024 bytes in 5346 ms
2019-12-18 16:25:50.076  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 50000 trades of size 1024 bytes in 4999 ms
2019-12-18 16:25:55.212  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 60000 trades of size 1024 bytes in 5135 ms
2019-12-18 16:26:00.291  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 70000 trades of size 1024 bytes in 5078 ms
2019-12-18 16:26:05.810  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 80000 trades of size 1024 bytes in 5519 ms
2019-12-18 16:26:10.709  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 90000 trades of size 1024 bytes in 4899 ms
2019-12-18 16:26:15.723  INFO 97527 --- [           main] i.p.test.client.service.TradeService     : Put 100000 trades of size 1024 bytes in 5014 ms
...
```
##### Get Forever Command
Run the get forever command using the **runclient.sh** script like:

```
./runclient.sh getforever 10
...
2019-12-18 16:28:41.760  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 16:28:43.031  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 10000 trades in 1271 ms
2019-12-18 16:28:43.892  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 20000 trades in 861 ms
2019-12-18 16:28:44.760  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 30000 trades in 868 ms
2019-12-18 16:28:45.509  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 40000 trades in 749 ms
2019-12-18 16:28:46.274  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 50000 trades in 765 ms
2019-12-18 16:28:47.087  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 60000 trades in 812 ms
2019-12-18 16:28:47.808  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 70000 trades in 721 ms
2019-12-18 16:28:48.550  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 80000 trades in 742 ms
2019-12-18 16:28:49.301  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 90000 trades in 750 ms
2019-12-18 16:28:50.037  INFO 97736 --- [           main] i.p.test.client.service.TradeService     : Got 100000 trades in 736 ms
...
```
##### Destroy Forever Command
Run the destroy forever command using the **runclient.sh** script like:

```
./runclient.sh destroyforever 10
...
2019-12-18 16:31:22.751  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 16:31:23.175  INFO 97971 --- [Timer-DEFAULT-3] o.a.g.internal.admin.ClientStatsManager  : ClientStatsManager, intializing the statistics...
2019-12-18 16:31:23.630  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 879 ms
2019-12-18 16:31:24.325  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 695 ms
2019-12-18 16:31:25.111  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 786 ms
2019-12-18 16:31:25.807  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 40000 trades in 696 ms
2019-12-18 16:31:26.446  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 50000 trades in 639 ms
2019-12-18 16:31:27.089  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 60000 trades in 643 ms
2019-12-18 16:31:27.842  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 70000 trades in 753 ms
2019-12-18 16:31:28.495  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 80000 trades in 653 ms
2019-12-18 16:31:29.127  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 90000 trades in 632 ms
2019-12-18 16:31:29.807  INFO 97971 --- [           main] i.p.test.client.service.TradeService     : Destroyed 100000 trades in 680 ms
...
```
##### Query by Cusip Forever Command
Run the query-by-cusip forever command using the **runclient.sh** script like:

```
./runclient.sh querybycusipforever
...
2019-12-18 16:35:55.360  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-18 16:36:18.278  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 22918 ms
2019-12-18 16:36:38.614  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 20336 ms
2019-12-18 16:36:58.543  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 19929 ms
2019-12-18 16:37:18.054  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 40000 cusip queries in 19511 ms
2019-12-18 16:37:38.098  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 50000 cusip queries in 20044 ms
2019-12-18 16:37:58.478  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 60000 cusip queries in 20380 ms
2019-12-18 16:38:18.119  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 70000 cusip queries in 19641 ms
2019-12-18 16:38:36.396  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 80000 cusip queries in 18277 ms
2019-12-18 16:38:55.145  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 90000 cusip queries in 18749 ms
2019-12-18 16:39:14.346  INFO 98398 --- [           main] i.p.test.client.service.TradeService     : Executed 100000 cusip queries in 19201 ms
...
```
##### Function Update Forever Command
Run the function update forever command using the **runclient.sh** script like:

```
./runclient.sh functionupdateforever 10
...
2019-12-19 15:39:07.259  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 15:39:14.541  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 7282 ms
2019-12-19 15:39:18.485  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 3944 ms
2019-12-19 15:39:21.483  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 2998 ms
2019-12-19 15:39:24.141  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 40000 trades with function in 2658 ms
2019-12-19 15:39:26.307  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 50000 trades with function in 2166 ms
2019-12-19 15:39:28.426  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 60000 trades with function in 2119 ms
2019-12-19 15:39:30.504  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 70000 trades with function in 2077 ms
2019-12-19 15:39:32.625  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 80000 trades with function in 2121 ms
2019-12-19 15:39:34.810  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 90000 trades with function in 2185 ms
2019-12-19 15:39:36.904  INFO 57950 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 100000 trades with function in 2094 ms
...
```
##### Multi-threaded Put Forever Command
Run the multi-threaded put forever command using the **runclient.sh** script like:

```
./runclient.sh putforever 10 1024 5
...
2019-12-18 16:46:12.424  INFO 99172 --- [       Thread-6] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 16:46:12.424  INFO 99172 --- [       Thread-7] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 16:46:12.424  INFO 99172 --- [       Thread-8] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 16:46:12.424  INFO 99172 --- [       Thread-9] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 16:46:12.424  INFO 99172 --- [      Thread-10] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 16:46:22.227  INFO 99172 --- [       Thread-9] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 9802 ms
2019-12-18 16:46:22.255  INFO 99172 --- [       Thread-8] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 9831 ms
2019-12-18 16:46:22.255  INFO 99172 --- [       Thread-7] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 9831 ms
2019-12-18 16:46:22.263  INFO 99172 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 9839 ms
2019-12-18 16:46:22.277  INFO 99172 --- [      Thread-10] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 9853 ms
2019-12-18 16:46:30.672  INFO 99172 --- [      Thread-10] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8395 ms
2019-12-18 16:46:30.676  INFO 99172 --- [       Thread-9] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8448 ms
2019-12-18 16:46:30.692  INFO 99172 --- [       Thread-7] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8437 ms
2019-12-18 16:46:30.696  INFO 99172 --- [       Thread-8] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8441 ms
2019-12-18 16:46:30.711  INFO 99172 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8448 ms
2019-12-18 16:46:38.654  INFO 99172 --- [      Thread-10] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 7982 ms
2019-12-18 16:46:38.702  INFO 99172 --- [       Thread-9] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 8026 ms
2019-12-18 16:46:38.730  INFO 99172 --- [       Thread-8] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 8034 ms
2019-12-18 16:46:38.730  INFO 99172 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 8018 ms
2019-12-18 16:46:38.736  INFO 99172 --- [       Thread-7] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 8044 ms
...
```
##### Multi-threaded Get Forever Command
Run the multi-threaded get forever command using the **runclient.sh** script like:

```
./runclient.sh getforever 10 5
...
2019-12-18 16:49:08.951  INFO 99345 --- [       Thread-6] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 16:49:08.951  INFO 99345 --- [       Thread-7] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 16:49:08.951  INFO 99345 --- [       Thread-8] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 16:49:08.951  INFO 99345 --- [       Thread-9] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 16:49:08.951  INFO 99345 --- [      Thread-10] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 16:49:10.288  INFO 99345 --- [      Thread-10] i.p.test.client.service.TradeService     : Got 10000 trades in 1337 ms
2019-12-18 16:49:10.292  INFO 99345 --- [       Thread-6] i.p.test.client.service.TradeService     : Got 10000 trades in 1341 ms
2019-12-18 16:49:10.294  INFO 99345 --- [       Thread-9] i.p.test.client.service.TradeService     : Got 10000 trades in 1343 ms
2019-12-18 16:49:10.295  INFO 99345 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 10000 trades in 1344 ms
2019-12-18 16:49:10.297  INFO 99345 --- [       Thread-8] i.p.test.client.service.TradeService     : Got 10000 trades in 1346 ms
2019-12-18 16:49:11.192  INFO 99345 --- [      Thread-10] i.p.test.client.service.TradeService     : Got 20000 trades in 904 ms
2019-12-18 16:49:11.195  INFO 99345 --- [       Thread-6] i.p.test.client.service.TradeService     : Got 20000 trades in 901 ms
2019-12-18 16:49:11.200  INFO 99345 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 20000 trades in 905 ms
2019-12-18 16:49:11.201  INFO 99345 --- [       Thread-8] i.p.test.client.service.TradeService     : Got 20000 trades in 904 ms
2019-12-18 16:49:11.204  INFO 99345 --- [       Thread-9] i.p.test.client.service.TradeService     : Got 20000 trades in 910 ms
2019-12-18 16:49:12.059  INFO 99345 --- [      Thread-10] i.p.test.client.service.TradeService     : Got 30000 trades in 867 ms
2019-12-18 16:49:12.065  INFO 99345 --- [       Thread-6] i.p.test.client.service.TradeService     : Got 30000 trades in 870 ms
2019-12-18 16:49:12.068  INFO 99345 --- [       Thread-8] i.p.test.client.service.TradeService     : Got 30000 trades in 867 ms
2019-12-18 16:49:12.070  INFO 99345 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 30000 trades in 870 ms
2019-12-18 16:49:12.075  INFO 99345 --- [       Thread-9] i.p.test.client.service.TradeService     : Got 30000 trades in 871 ms
...
```
##### Multi-threaded Destroy Forever Command
Run the multi-threaded destroy forever threads command using the **runclient.sh** script like:

```
./runclient.sh destroyforever 10 5
...
2019-12-18 16:52:34.692  INFO 99731 --- [       Thread-6] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 16:52:34.692  INFO 99731 --- [       Thread-7] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 16:52:34.692  INFO 99731 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 16:52:34.692  INFO 99731 --- [       Thread-9] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 16:52:34.693  INFO 99731 --- [      Thread-10] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 16:52:35.831  INFO 99731 --- [       Thread-6] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1139 ms
2019-12-18 16:52:35.835  INFO 99731 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1143 ms
2019-12-18 16:52:35.835  INFO 99731 --- [      Thread-10] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1142 ms
2019-12-18 16:52:35.837  INFO 99731 --- [       Thread-7] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1145 ms
2019-12-18 16:52:35.840  INFO 99731 --- [       Thread-9] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1147 ms
2019-12-18 16:52:36.920  INFO 99731 --- [       Thread-6] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1088 ms
2019-12-18 16:52:36.923  INFO 99731 --- [       Thread-7] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1086 ms
2019-12-18 16:52:36.923  INFO 99731 --- [      Thread-10] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1088 ms
2019-12-18 16:52:36.925  INFO 99731 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1090 ms
2019-12-18 16:52:36.927  INFO 99731 --- [       Thread-9] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1087 ms
2019-12-18 16:52:37.864  INFO 99731 --- [       Thread-6] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 944 ms
2019-12-18 16:52:37.867  INFO 99731 --- [      Thread-10] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 943 ms
2019-12-18 16:52:37.867  INFO 99731 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 942 ms
2019-12-18 16:52:37.868  INFO 99731 --- [       Thread-9] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 941 ms
2019-12-18 16:52:37.869  INFO 99731 --- [       Thread-7] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 946 ms
...
```
##### Multi-threaded Query by Cusip Forever Command
Run the multi-threaded query-by-cusip forever command using the **runclient.sh** script like:

```
./runclient.sh querybycusipforever 5
...
2019-12-18 16:54:15.296  INFO 99865 --- [       Thread-6] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-18 16:54:15.297  INFO 99865 --- [       Thread-8] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-18 16:54:15.297  INFO 99865 --- [       Thread-7] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-18 16:54:15.297  INFO 99865 --- [       Thread-9] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-18 16:54:15.297  INFO 99865 --- [      Thread-10] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-18 16:55:12.166  INFO 99865 --- [       Thread-6] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 56869 ms
2019-12-18 16:55:12.226  INFO 99865 --- [      Thread-10] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 56929 ms
2019-12-18 16:55:12.426  INFO 99865 --- [       Thread-8] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 57129 ms
2019-12-18 16:55:12.654  INFO 99865 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 57357 ms
2019-12-18 16:55:12.763  INFO 99865 --- [       Thread-7] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 57466 ms
2019-12-18 16:56:09.829  INFO 99865 --- [       Thread-6] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 57663 ms
2019-12-18 16:56:09.831  INFO 99865 --- [      Thread-10] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 57605 ms
2019-12-18 16:56:10.236  INFO 99865 --- [       Thread-8] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 57810 ms
2019-12-18 16:56:10.355  INFO 99865 --- [       Thread-7] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 57592 ms
2019-12-18 16:56:10.390  INFO 99865 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 57735 ms
2019-12-18 16:57:13.819  INFO 99865 --- [      Thread-10] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 63988 ms
2019-12-18 16:57:14.317  INFO 99865 --- [       Thread-6] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 64487 ms
2019-12-18 16:57:14.716  INFO 99865 --- [       Thread-7] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 64361 ms
2019-12-18 16:57:14.923  INFO 99865 --- [       Thread-8] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 64687 ms
2019-12-18 16:57:15.151  INFO 99865 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 64761 ms
...
```
##### Multi-threaded Function Update Forever Command
Run the multi-threaded function update forever command using the **runclient.sh** script like:

```
./runclient.sh functionupdateforever 10 5
...
2019-12-19 15:46:58.070  INFO 58292 --- [       Thread-7] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 15:46:58.070  INFO 58292 --- [       Thread-6] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 15:46:58.070  INFO 58292 --- [       Thread-8] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 15:46:58.070  INFO 58292 --- [       Thread-9] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 15:46:58.070  INFO 58292 --- [      Thread-10] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 15:47:03.126  INFO 58292 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 5056 ms
2019-12-19 15:47:03.136  INFO 58292 --- [       Thread-8] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 5066 ms
2019-12-19 15:47:03.141  INFO 58292 --- [      Thread-10] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 5071 ms
2019-12-19 15:47:03.145  INFO 58292 --- [       Thread-7] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 5075 ms
2019-12-19 15:47:03.146  INFO 58292 --- [       Thread-9] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 5076 ms
2019-12-19 15:47:06.926  INFO 58292 --- [      Thread-10] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 3784 ms
2019-12-19 15:47:06.926  INFO 58292 --- [       Thread-7] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 3781 ms
2019-12-19 15:47:06.927  INFO 58292 --- [       Thread-8] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 3791 ms
2019-12-19 15:47:06.930  INFO 58292 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 3804 ms
2019-12-19 15:47:06.943  INFO 58292 --- [       Thread-9] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 3797 ms
2019-12-19 15:47:10.836  INFO 58292 --- [      Thread-10] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 3910 ms
2019-12-19 15:47:10.840  INFO 58292 --- [       Thread-7] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 3914 ms
2019-12-19 15:47:10.845  INFO 58292 --- [       Thread-8] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 3918 ms
2019-12-19 15:47:10.846  INFO 58292 --- [       Thread-6] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 3916 ms
2019-12-19 15:47:10.862  INFO 58292 --- [       Thread-9] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 3919 ms
...
```
##### Get One Command
Run the get one command using the **runclient.sh** script like:

```
./runclient.sh getone 0
...
2019-12-18 16:59:25.722  INFO 551 --- [           main] i.p.test.client.service.TradeService     : Got Trade(id=0, cusip=GOOGL, shares=27, price=278.51)
```
##### Query One By Cusip Command
Run the query one by cusip command using the **runclient.sh** script like:

```
./runclient.sh queryonebycusip AAPL
...
2019-12-18 17:00:23.615  INFO 636 --- [           main] i.p.test.client.service.TradeService     : Executing query for cusip=AAPL
2019-12-18 17:00:23.689  INFO 636 --- [           main] i.p.test.client.service.TradeService     : Returned 5 trades for cusip=AAPL in 74 ms:
2019-12-18 17:00:23.689  INFO 636 --- [           main] i.p.test.client.service.TradeService     : 	Trade(id=98, cusip=AAPL, shares=24, price=305.78)
2019-12-18 17:00:23.689  INFO 636 --- [           main] i.p.test.client.service.TradeService     : 	Trade(id=43, cusip=AAPL, shares=0, price=833.94)
2019-12-18 17:00:23.689  INFO 636 --- [           main] i.p.test.client.service.TradeService     : 	Trade(id=68, cusip=AAPL, shares=64, price=383.67)
2019-12-18 17:00:23.689  INFO 636 --- [           main] i.p.test.client.service.TradeService     : 	Trade(id=99, cusip=AAPL, shares=52, price=169.36)
2019-12-18 17:00:23.690  INFO 636 --- [           main] i.p.test.client.service.TradeService     : 	Trade(id=75, cusip=AAPL, shares=45, price=273.66)
```
##### Test Command
Run the test command using the **runclient.sh** script like:

```
./runclient.sh starttest 10 1024 1
...
2019-12-18 17:02:27.921  INFO 855 --- [       Thread-6] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 17:02:27.922  INFO 855 --- [       Thread-7] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 17:02:27.922  INFO 855 --- [       Thread-8] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-18 17:02:29.679  INFO 855 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 10000 trades in 1757 ms
2019-12-18 17:02:31.141  INFO 855 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 20000 trades in 1462 ms
2019-12-18 17:02:32.653  INFO 855 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 30000 trades in 1512 ms
2019-12-18 17:02:36.009  INFO 855 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 8088 ms
2019-12-18 17:02:43.971  INFO 855 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 7962 ms
2019-12-18 17:02:50.814  INFO 855 --- [       Thread-8] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 22892 ms
2019-12-18 17:02:51.744  INFO 855 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 7772 ms
2019-12-18 17:03:13.281  INFO 855 --- [       Thread-8] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 22467 ms
2019-12-18 17:03:35.607  INFO 855 --- [       Thread-8] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 22325 ms
...
```
#### REST
##### Start Client Application
Start the client application using the **runclient.sh** script like:

```
./runclient.sh wait
2019-12-18 17:06:49.078  INFO 1239 --- [           main] io.pivotal.test.client.Client            : Started Client in 2.917 seconds (JVM running for 3.252)
2019-12-18 17:06:49.106  INFO 1239 --- [           main] io.pivotal.test.client.Client            : Client Command Line Arguments: [--spring.profiles.active=local, --operation=wait]
2019-12-18 17:06:49.106  INFO 1239 --- [           main] io.pivotal.test.client.Client            : Client Option Argument: operation=[wait]
2019-12-18 17:06:49.106  INFO 1239 --- [           main] io.pivotal.test.client.Client            : Client Option Argument: spring.profiles.active=[local]
```
##### Put Command
Run the put command using **curl** like:

```
curl -X POST http://localhost:8080/trades/put/10/1024
{"operation":"put","status":"SUCCESS","completionTime":55}
```
The application will log messages like:

```
2019-12-18 17:09:00.073  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Putting 10 trades of size 1024 bytes
2019-12-18 17:09:00.100  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=0, cusip=JNJ, shares=43, price=176.49)
2019-12-18 17:09:00.103  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=1, cusip=MA, shares=40, price=45.30)
2019-12-18 17:09:00.107  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=2, cusip=SBUX, shares=36, price=329.88)
2019-12-18 17:09:00.111  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=3, cusip=UNP, shares=91, price=944.36)
2019-12-18 17:09:00.113  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=4, cusip=MMM, shares=14, price=295.43)
2019-12-18 17:09:00.118  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=5, cusip=NFLX, shares=42, price=808.43)
2019-12-18 17:09:00.121  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=6, cusip=BA, shares=46, price=431.10)
2019-12-18 17:09:00.125  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=7, cusip=AAPL, shares=26, price=163.14)
2019-12-18 17:09:00.127  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=8, cusip=QCOM, shares=14, price=5.41)
2019-12-18 17:09:00.128  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Saved Trade(id=9, cusip=TXN, shares=13, price=149.88)
2019-12-18 17:09:00.128  INFO 1239 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Put 10 trades of size 1024 bytes in 55 ms
```
###### Get Command
Run the get command using **curl** like:

```
curl http://localhost:8080/trades/get/10
{"operation":"get","status":"SUCCESS","completionTime":41}
```
The application will log messages like:

```
2019-12-18 17:14:33.635  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Getting 10 trades
2019-12-18 17:14:33.648  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=0, cusip=JNJ, shares=43, price=176.49)
2019-12-18 17:14:33.649  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=1, cusip=MA, shares=40, price=45.30)
2019-12-18 17:14:33.671  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=2, cusip=SBUX, shares=36, price=329.88)
2019-12-18 17:14:33.674  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=3, cusip=UNP, shares=91, price=944.36)
2019-12-18 17:14:33.675  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=4, cusip=MMM, shares=14, price=295.43)
2019-12-18 17:14:33.675  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=5, cusip=NFLX, shares=42, price=808.43)
2019-12-18 17:14:33.676  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=6, cusip=BA, shares=46, price=431.10)
2019-12-18 17:14:33.676  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=7, cusip=AAPL, shares=26, price=163.14)
2019-12-18 17:14:33.677  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=8, cusip=QCOM, shares=14, price=5.41)
2019-12-18 17:14:33.677  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got Trade(id=9, cusip=TXN, shares=13, price=149.88)
2019-12-18 17:14:33.677  INFO 1239 --- [nio-8080-exec-4] i.p.test.client.service.TradeService     : Got 10 trades in 41 ms
```
##### Destroy Command
Run the destroy command using **curl** like:

```
curl -X DELETE http://localhost:8080/trades/destroy/10
{"operation":"destroy","status":"SUCCESS","completionTime":35}
```
The application will log messages like:

```
2019-12-18 17:17:34.514  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroying 10 trades
2019-12-18 17:17:34.524  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=0
2019-12-18 17:17:34.527  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=1
2019-12-18 17:17:34.532  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=2
2019-12-18 17:17:34.537  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=3
2019-12-18 17:17:34.539  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=4
2019-12-18 17:17:34.541  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=5
2019-12-18 17:17:34.543  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=6
2019-12-18 17:17:34.545  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=7
2019-12-18 17:17:34.547  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=8
2019-12-18 17:17:34.549  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed key=9
2019-12-18 17:17:34.549  INFO 1239 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Destroyed 10 trades in 35 ms
```
##### Query By Cusip Command
Run the query by cusip command using **curl** like:

```
curl http://localhost:8080/trades/querybycusip/10
{"operation":"querybycusip","status":"SUCCESS","completionTime":71}
```
The application will log messages like:

```
2019-12-18 17:18:54.977  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Executing 10 cusip queries
2019-12-18 17:18:55.017  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 1 trades for cusip=JPM
2019-12-18 17:18:55.021  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 0 trades for cusip=KO
2019-12-18 17:18:55.024  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 0 trades for cusip=BUD
2019-12-18 17:18:55.028  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 1 trades for cusip=MRK
2019-12-18 17:18:55.031  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 1 trades for cusip=C
2019-12-18 17:18:55.035  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 3 trades for cusip=LMT
2019-12-18 17:18:55.038  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 0 trades for cusip=KO
2019-12-18 17:18:55.042  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 2 trades for cusip=WMT
2019-12-18 17:18:55.045  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 1 trades for cusip=MCD
2019-12-18 17:18:55.048  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Returned 2 trades for cusip=NVS
2019-12-18 17:18:55.048  INFO 1239 --- [nio-8080-exec-7] i.p.test.client.service.TradeService     : Executed 10 cusip queries in 71 ms
```
##### Function Update Command
Run the function update command using **curl** like:

```
curl -X POST http://localhost:8080/trades/functionupdate/10
{"operation":"functionupdate","status":"SUCCESS","completionTime":15}
```
The application will log messages like:

```
2019-12-19 16:14:43.667  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updating 10 trades with function
2019-12-19 16:14:43.670  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 0 result=[true]
2019-12-19 16:14:43.674  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 1 result=[true]
2019-12-19 16:14:43.677  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 2 result=[true]
2019-12-19 16:14:43.678  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 3 result=[true]
2019-12-19 16:14:43.679  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 4 result=[true]
2019-12-19 16:14:43.680  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 5 result=[true]
2019-12-19 16:14:43.680  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 6 result=[true]
2019-12-19 16:14:43.681  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 7 result=[true]
2019-12-19 16:14:43.681  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 8 result=[true]
2019-12-19 16:14:43.682  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated trade 9 result=[true]
2019-12-19 16:14:43.682  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Updated 10 trades with function in 15 ms
```
##### Put Forever Command
Run the function update command using **curl** like:

```
curl -X POST http://localhost:8080/trades/putforever/10/1024
{"operation":"putforeverthreads","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-18 17:21:49.050  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 17:21:56.581  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 7531 ms
2019-12-18 17:22:05.740  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 9159 ms
2019-12-18 17:22:11.330  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 5590 ms
2019-12-18 17:22:16.271  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 40000 trades of size 1024 bytes in 4941 ms
2019-12-18 17:22:21.705  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 50000 trades of size 1024 bytes in 5434 ms
2019-12-18 17:22:27.906  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 60000 trades of size 1024 bytes in 6201 ms
2019-12-18 17:22:33.774  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 70000 trades of size 1024 bytes in 5868 ms
2019-12-18 17:22:39.254  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 80000 trades of size 1024 bytes in 5480 ms
2019-12-18 17:22:44.481  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 90000 trades of size 1024 bytes in 5226 ms
2019-12-18 17:22:49.693  INFO 1239 --- [       Thread-6] i.p.test.client.service.TradeService     : Put 100000 trades of size 1024 bytes in 5210 ms
...
```
##### Get Forever Command
Run the function update command using **curl** like:

```
curl http://localhost:8080/trades/getforever/10
{"operation":"getforeverthreads","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-18 17:23:47.516  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 17:23:48.358  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 10000 trades in 842 ms
2019-12-18 17:23:49.069  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 20000 trades in 711 ms
2019-12-18 17:23:49.789  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 30000 trades in 720 ms
2019-12-18 17:23:50.512  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 40000 trades in 723 ms
2019-12-18 17:23:51.247  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 50000 trades in 735 ms
2019-12-18 17:23:51.971  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 60000 trades in 724 ms
2019-12-18 17:23:52.672  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 70000 trades in 701 ms
2019-12-18 17:23:53.376  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 80000 trades in 704 ms
2019-12-18 17:23:54.067  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 90000 trades in 691 ms
2019-12-18 17:23:54.749  INFO 1239 --- [       Thread-7] i.p.test.client.service.TradeService     : Got 100000 trades in 681 ms
...
```
##### Destroy Forever Command
Run the function update command using **curl** like:

```
curl -X DELETE http://localhost:8080/trades/destroyforever/10
{"operation":"destroyforeverthreads","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-18 17:24:45.007  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 17:24:45.763  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 756 ms
2019-12-18 17:24:46.471  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 707 ms
2019-12-18 17:24:47.193  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 722 ms
2019-12-18 17:24:47.892  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 40000 trades in 699 ms
2019-12-18 17:24:48.585  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 50000 trades in 693 ms
2019-12-18 17:24:49.280  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 60000 trades in 695 ms
2019-12-18 17:24:49.952  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 70000 trades in 672 ms
2019-12-18 17:24:50.604  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 80000 trades in 652 ms
2019-12-18 17:24:51.302  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 90000 trades in 698 ms
2019-12-18 17:24:52.037  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 100000 trades in 735 ms
2019-12-18 17:24:52.709  INFO 1239 --- [       Thread-8] i.p.test.client.service.TradeService     : Destroyed 110000 trades in 672 ms
...
```
##### Query by Cusip Forever Command
Run the function update command using **curl** like:

```
curl http://localhost:8080/trades/querybycusipforever
{"operation":"querybycusipforeverthreads","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-18 17:25:41.177  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-18 17:26:01.775  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 20598 ms
2019-12-18 17:26:20.484  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 18709 ms
2019-12-18 17:26:39.543  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 19059 ms
2019-12-18 17:26:57.534  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 40000 cusip queries in 17991 ms
2019-12-18 17:27:15.314  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 50000 cusip queries in 17780 ms
2019-12-18 17:27:33.081  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 60000 cusip queries in 17767 ms
2019-12-18 17:27:50.804  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 70000 cusip queries in 17723 ms
2019-12-18 17:28:09.086  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 80000 cusip queries in 18281 ms
2019-12-18 17:28:27.066  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 90000 cusip queries in 17980 ms
2019-12-18 17:28:44.831  INFO 1239 --- [       Thread-9] i.p.test.client.service.TradeService     : Executed 100000 cusip queries in 17765 ms
...
```
##### Function Update Forever Command
Run the function update command using **curl** like:

```
curl -X POST http://localhost:8080/trades/functionupdateforever/10
{"operation":"functionupdateforeverthreads","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-19 15:56:31.876  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 15:56:34.579  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 2703 ms
2019-12-19 15:56:37.002  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 2423 ms
2019-12-19 15:56:39.491  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 2488 ms
2019-12-19 15:56:41.914  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 40000 trades with function in 2423 ms
2019-12-19 15:56:44.281  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 50000 trades with function in 2366 ms
2019-12-19 15:56:46.519  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 60000 trades with function in 2238 ms
2019-12-19 15:56:48.857  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 70000 trades with function in 2338 ms
2019-12-19 15:56:51.229  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 80000 trades with function in 2372 ms
2019-12-19 15:56:53.563  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 90000 trades with function in 2334 ms
2019-12-19 15:56:56.014  INFO 58371 --- [      Thread-19] i.p.test.client.service.TradeService     : Updated 100000 trades with function in 2451 ms
...
```
##### Multi-threaded Put Forever Command
Run the multi-threaded put forever command using **curl** like:

```
curl -X POST http://localhost:8080/trades/putforever/10/1024/5
{"operation":"putforever","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-18 17:36:22.188  INFO 3437 --- [       Thread-7] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 17:36:22.188  INFO 3437 --- [       Thread-8] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 17:36:22.188  INFO 3437 --- [       Thread-9] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 17:36:22.188  INFO 3437 --- [      Thread-10] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 17:36:22.189  INFO 3437 --- [      Thread-11] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-18 17:36:30.755  INFO 3437 --- [       Thread-8] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 8567 ms
2019-12-18 17:36:30.780  INFO 3437 --- [       Thread-9] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 8592 ms
2019-12-18 17:36:30.788  INFO 3437 --- [      Thread-11] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 8599 ms
2019-12-18 17:36:30.792  INFO 3437 --- [       Thread-7] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 8604 ms
2019-12-18 17:36:30.793  INFO 3437 --- [      Thread-10] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 8604 ms
2019-12-18 17:36:39.269  INFO 3437 --- [      Thread-11] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8481 ms
2019-12-18 17:36:39.275  INFO 3437 --- [       Thread-9] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8495 ms
2019-12-18 17:36:39.280  INFO 3437 --- [      Thread-10] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8487 ms
2019-12-18 17:36:39.322  INFO 3437 --- [       Thread-7] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8530 ms
2019-12-18 17:36:39.328  INFO 3437 --- [       Thread-8] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 8573 ms
2019-12-18 17:36:48.276  INFO 3437 --- [       Thread-9] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 9001 ms
2019-12-18 17:36:48.296  INFO 3437 --- [      Thread-10] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 9016 ms
2019-12-18 17:36:48.297  INFO 3437 --- [       Thread-7] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 8975 ms
2019-12-18 17:36:48.302  INFO 3437 --- [       Thread-8] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 8974 ms
2019-12-18 17:36:48.345  INFO 3437 --- [      Thread-11] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 9076 ms
...
```
##### Multi-threaded Get Forever Command
Run the multi-threaded get forever command using **curl** like:

```
curl http://localhost:8080/trades/getforever/10/5
{"operation":"getforever","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-18 17:41:50.830  INFO 3437 --- [      Thread-12] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 17:41:50.830  INFO 3437 --- [      Thread-13] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 17:41:50.830  INFO 3437 --- [      Thread-14] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 17:41:50.830  INFO 3437 --- [      Thread-15] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 17:41:50.830  INFO 3437 --- [      Thread-16] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-18 17:41:51.745  INFO 3437 --- [      Thread-14] i.p.test.client.service.TradeService     : Got 10000 trades in 915 ms
2019-12-18 17:41:51.747  INFO 3437 --- [      Thread-15] i.p.test.client.service.TradeService     : Got 10000 trades in 917 ms
2019-12-18 17:41:51.750  INFO 3437 --- [      Thread-12] i.p.test.client.service.TradeService     : Got 10000 trades in 920 ms
2019-12-18 17:41:51.750  INFO 3437 --- [      Thread-16] i.p.test.client.service.TradeService     : Got 10000 trades in 920 ms
2019-12-18 17:41:51.753  INFO 3437 --- [      Thread-13] i.p.test.client.service.TradeService     : Got 10000 trades in 923 ms
2019-12-18 17:41:52.577  INFO 3437 --- [      Thread-14] i.p.test.client.service.TradeService     : Got 20000 trades in 831 ms
2019-12-18 17:41:52.581  INFO 3437 --- [      Thread-16] i.p.test.client.service.TradeService     : Got 20000 trades in 831 ms
2019-12-18 17:41:52.582  INFO 3437 --- [      Thread-15] i.p.test.client.service.TradeService     : Got 20000 trades in 835 ms
2019-12-18 17:41:52.583  INFO 3437 --- [      Thread-12] i.p.test.client.service.TradeService     : Got 20000 trades in 833 ms
2019-12-18 17:41:52.590  INFO 3437 --- [      Thread-13] i.p.test.client.service.TradeService     : Got 20000 trades in 836 ms
2019-12-18 17:41:53.440  INFO 3437 --- [      Thread-14] i.p.test.client.service.TradeService     : Got 30000 trades in 863 ms
2019-12-18 17:41:53.442  INFO 3437 --- [      Thread-16] i.p.test.client.service.TradeService     : Got 30000 trades in 861 ms
2019-12-18 17:41:53.444  INFO 3437 --- [      Thread-15] i.p.test.client.service.TradeService     : Got 30000 trades in 862 ms
2019-12-18 17:41:53.445  INFO 3437 --- [      Thread-12] i.p.test.client.service.TradeService     : Got 30000 trades in 862 ms
2019-12-18 17:41:53.454  INFO 3437 --- [      Thread-13] i.p.test.client.service.TradeService     : Got 30000 trades in 864 ms
...
```
##### Multi-threaded Destroy Forever Command
Run the multi-threaded destroy forever command using **curl** like:

```
curl -X DELETE http://localhost:8080/trades/destroyforever/10/5
{"operation":"destroyforever","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-18 17:45:40.359  INFO 3437 --- [      Thread-17] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 17:45:40.359  INFO 3437 --- [      Thread-18] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 17:45:40.359  INFO 3437 --- [      Thread-19] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 17:45:40.359  INFO 3437 --- [      Thread-20] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 17:45:40.360  INFO 3437 --- [      Thread-21] i.p.test.client.service.TradeService     : Destroying trades forever
2019-12-18 17:45:41.412  INFO 3437 --- [      Thread-17] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1053 ms
2019-12-18 17:45:41.413  INFO 3437 --- [      Thread-21] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1053 ms
2019-12-18 17:45:41.414  INFO 3437 --- [      Thread-20] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1055 ms
2019-12-18 17:45:41.416  INFO 3437 --- [      Thread-19] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1056 ms
2019-12-18 17:45:41.417  INFO 3437 --- [      Thread-18] i.p.test.client.service.TradeService     : Destroyed 10000 trades in 1058 ms
2019-12-18 17:45:42.449  INFO 3437 --- [      Thread-17] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1037 ms
2019-12-18 17:45:42.450  INFO 3437 --- [      Thread-21] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1037 ms
2019-12-18 17:45:42.452  INFO 3437 --- [      Thread-20] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1038 ms
2019-12-18 17:45:42.455  INFO 3437 --- [      Thread-19] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1039 ms
2019-12-18 17:45:42.457  INFO 3437 --- [      Thread-18] i.p.test.client.service.TradeService     : Destroyed 20000 trades in 1040 ms
2019-12-18 17:45:43.633  INFO 3437 --- [      Thread-17] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 1184 ms
2019-12-18 17:45:43.634  INFO 3437 --- [      Thread-21] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 1184 ms
2019-12-18 17:45:43.635  INFO 3437 --- [      Thread-20] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 1183 ms
2019-12-18 17:45:43.637  INFO 3437 --- [      Thread-19] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 1182 ms
2019-12-18 17:45:43.642  INFO 3437 --- [      Thread-18] i.p.test.client.service.TradeService     : Destroyed 30000 trades in 1185 ms
...
```
##### Multi-threaded Query by Cusip Forever Command
Run the multi-threaded query by cusip forever command using **curl** like:

```
curl http://localhost:8080/trades/querybycusipforever/5
{"operation":"querybycusipforever","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-19 16:01:46.684  INFO 58371 --- [      Thread-35] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-19 16:01:46.684  INFO 58371 --- [      Thread-36] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-19 16:01:46.684  INFO 58371 --- [      Thread-37] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-19 16:01:46.685  INFO 58371 --- [      Thread-38] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-19 16:01:46.685  INFO 58371 --- [      Thread-39] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-19 16:01:56.990  INFO 58371 --- [      Thread-37] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 10306 ms
2019-12-19 16:01:57.003  INFO 58371 --- [      Thread-38] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 10318 ms
2019-12-19 16:01:57.012  INFO 58371 --- [      Thread-35] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 10328 ms
2019-12-19 16:01:57.013  INFO 58371 --- [      Thread-39] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 10328 ms
2019-12-19 16:01:57.013  INFO 58371 --- [      Thread-36] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 10329 ms
2019-12-19 16:02:06.849  INFO 58371 --- [      Thread-37] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 9859 ms
2019-12-19 16:02:06.859  INFO 58371 --- [      Thread-38] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 9856 ms
2019-12-19 16:02:06.879  INFO 58371 --- [      Thread-36] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 9866 ms
2019-12-19 16:02:06.884  INFO 58371 --- [      Thread-39] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 9871 ms
2019-12-19 16:02:06.889  INFO 58371 --- [      Thread-35] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 9877 ms
2019-12-19 16:02:16.623  INFO 58371 --- [      Thread-37] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 9774 ms
2019-12-19 16:02:16.648  INFO 58371 --- [      Thread-38] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 9789 ms
2019-12-19 16:02:16.655  INFO 58371 --- [      Thread-36] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 9775 ms
2019-12-19 16:02:16.666  INFO 58371 --- [      Thread-39] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 9782 ms
2019-12-19 16:02:16.667  INFO 58371 --- [      Thread-35] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 9778 ms
...
```
##### Multi-threaded Function Update Forever Command
Run the multi-threaded function update command using **curl** like:

```
curl -X POST http://localhost:8080/trades/functionupdateforever/10/5
{"operation":"functionupdateforever","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-19 16:03:09.034  INFO 58371 --- [      Thread-41] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 16:03:09.034  INFO 58371 --- [      Thread-42] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 16:03:09.034  INFO 58371 --- [      Thread-40] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 16:03:09.034  INFO 58371 --- [      Thread-43] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 16:03:09.034  INFO 58371 --- [      Thread-44] i.p.test.client.service.TradeService     : Updating 10 trades with function forever
2019-12-19 16:03:11.147  INFO 58371 --- [      Thread-43] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 2113 ms
2019-12-19 16:03:11.150  INFO 58371 --- [      Thread-44] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 2116 ms
2019-12-19 16:03:11.150  INFO 58371 --- [      Thread-42] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 2116 ms
2019-12-19 16:03:11.151  INFO 58371 --- [      Thread-40] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 2117 ms
2019-12-19 16:03:11.152  INFO 58371 --- [      Thread-41] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 2118 ms
2019-12-19 16:03:13.127  INFO 58371 --- [      Thread-42] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 1977 ms
2019-12-19 16:03:13.129  INFO 58371 --- [      Thread-43] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 1982 ms
2019-12-19 16:03:13.130  INFO 58371 --- [      Thread-41] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 1978 ms
2019-12-19 16:03:13.131  INFO 58371 --- [      Thread-44] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 1981 ms
2019-12-19 16:03:13.134  INFO 58371 --- [      Thread-40] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 1983 ms
2019-12-19 16:03:14.831  INFO 58371 --- [      Thread-44] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 1700 ms
2019-12-19 16:03:14.832  INFO 58371 --- [      Thread-42] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 1705 ms
2019-12-19 16:03:14.832  INFO 58371 --- [      Thread-41] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 1702 ms
2019-12-19 16:03:14.832  INFO 58371 --- [      Thread-43] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 1703 ms
2019-12-19 16:03:14.837  INFO 58371 --- [      Thread-40] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 1703 ms
...
```
##### Get One Command
Run the get one command using **curl** like:

```
curl http://localhost:8080/trades/getone/0
{"id":"0","cusip":"NKE","shares":46,"price":343.43,"payload":"..."}
```
The application will log messages like:

```
2019-12-19 16:04:41.261  INFO 58371 --- [nio-8080-exec-5] i.p.test.client.service.TradeService     : Got Trade(id=0, cusip=NKE, shares=46, price=343.43)
```
##### Query One By Cusip Command
Run the query one by cusip command using **curl** like:

```
curl http://localhost:8080/trades/queryonebycusip/AAPL
[{"id":"42","cusip":"AAPL","shares":60,"price":720.05,"payload":"..."}]
```
The application will log messages like:

```
2019-12-19 16:07:59.880  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Executing query for cusip=AAPL
2019-12-19 16:07:59.881  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Returned 1 trades for cusip=AAPL in 1 ms:
2019-12-19 16:07:59.881  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : 	Trade(id=42, cusip=AAPL, shares=60, price=720.05)
```
##### Test Command
Run the test command using **curl** like:

```
curl -X POST http://localhost:8080/trades/starttest/100/1024/2
[{"operation":"putforever","status":"SUCCESS","completionTime":0},{"operation":"getforever","status":"SUCCESS","completionTime":0},{"operation":"querybycusipforever","status":"SUCCESS","completionTime":0},{"operation":"functionupdateforever","status":"SUCCESS","completionTime":0}]
```
The application will log messages like:

```
2019-12-19 16:10:19.415  INFO 58371 --- [      Thread-68] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-19 16:10:19.415  INFO 58371 --- [      Thread-69] i.p.test.client.service.TradeService     : Putting trades forever of size 1024 bytes
2019-12-19 16:10:19.416  INFO 58371 --- [      Thread-70] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-19 16:10:19.416  INFO 58371 --- [      Thread-71] i.p.test.client.service.TradeService     : Getting entries forever
2019-12-19 16:10:19.416  INFO 58371 --- [      Thread-72] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-19 16:10:19.416  INFO 58371 --- [      Thread-73] i.p.test.client.service.TradeService     : Executing cusip queries forever
2019-12-19 16:10:19.417  INFO 58371 --- [      Thread-74] i.p.test.client.service.TradeService     : Updating 100 trades with function forever
2019-12-19 16:10:19.417  INFO 58371 --- [      Thread-75] i.p.test.client.service.TradeService     : Updating 100 trades with function forever
2019-12-19 16:10:21.578  INFO 58371 --- [      Thread-71] i.p.test.client.service.TradeService     : Got 10000 trades in 2162 ms
2019-12-19 16:10:21.583  INFO 58371 --- [      Thread-70] i.p.test.client.service.TradeService     : Got 10000 trades in 2167 ms
2019-12-19 16:10:23.766  INFO 58371 --- [      Thread-71] i.p.test.client.service.TradeService     : Got 20000 trades in 2188 ms
2019-12-19 16:10:23.778  INFO 58371 --- [      Thread-70] i.p.test.client.service.TradeService     : Got 20000 trades in 2195 ms
2019-12-19 16:10:24.341  INFO 58371 --- [      Thread-69] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 4926 ms
2019-12-19 16:10:24.355  INFO 58371 --- [      Thread-68] i.p.test.client.service.TradeService     : Put 10000 trades of size 1024 bytes in 4940 ms
2019-12-19 16:10:25.151  INFO 58371 --- [      Thread-74] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 5734 ms
2019-12-19 16:10:25.153  INFO 58371 --- [      Thread-75] i.p.test.client.service.TradeService     : Updated 10000 trades with function in 5736 ms
2019-12-19 16:10:25.858  INFO 58371 --- [      Thread-71] i.p.test.client.service.TradeService     : Got 30000 trades in 2092 ms
2019-12-19 16:10:25.873  INFO 58371 --- [      Thread-70] i.p.test.client.service.TradeService     : Got 30000 trades in 2095 ms
2019-12-19 16:10:29.129  INFO 58371 --- [      Thread-69] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 4787 ms
2019-12-19 16:10:29.139  INFO 58371 --- [      Thread-68] i.p.test.client.service.TradeService     : Put 20000 trades of size 1024 bytes in 4784 ms
2019-12-19 16:10:30.753  INFO 58371 --- [      Thread-74] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 5602 ms
2019-12-19 16:10:30.770  INFO 58371 --- [      Thread-75] i.p.test.client.service.TradeService     : Updated 20000 trades with function in 5617 ms
2019-12-19 16:10:32.609  INFO 58371 --- [      Thread-72] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 13193 ms
2019-12-19 16:10:32.621  INFO 58371 --- [      Thread-73] i.p.test.client.service.TradeService     : Executed 10000 cusip queries in 13204 ms
2019-12-19 16:10:33.853  INFO 58371 --- [      Thread-69] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 4723 ms
2019-12-19 16:10:33.859  INFO 58371 --- [      Thread-68] i.p.test.client.service.TradeService     : Put 30000 trades of size 1024 bytes in 4720 ms
2019-12-19 16:10:36.184  INFO 58371 --- [      Thread-74] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 5431 ms
2019-12-19 16:10:36.217  INFO 58371 --- [      Thread-75] i.p.test.client.service.TradeService     : Updated 30000 trades with function in 5447 ms
2019-12-19 16:10:45.901  INFO 58371 --- [      Thread-72] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 13292 ms
2019-12-19 16:10:45.911  INFO 58371 --- [      Thread-73] i.p.test.client.service.TradeService     : Executed 20000 cusip queries in 13290 ms
2019-12-19 16:10:59.129  INFO 58371 --- [      Thread-72] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 13228 ms
2019-12-19 16:10:59.169  INFO 58371 --- [      Thread-73] i.p.test.client.service.TradeService     : Executed 30000 cusip queries in 13258 ms
...
```
##### Stop Operations Command
Run the stop operations command using **curl** like:

```
curl -X POST http://localhost:8080/trades/stopoperations
{"operation":"stopoperations","status":"SUCCESS","completionTime":0}
```
The application will log messages like:

```
2019-12-19 15:51:19.022  INFO 58371 --- [nio-8080-exec-1] i.p.test.client.service.TradeService     : Stopping operations
2019-12-19 15:51:19.022  INFO 58371 --- [       Thread-8] i.p.test.client.service.TradeService     : Stopping after getting 168798 trades
2019-12-19 15:51:19.022  INFO 58371 --- [       Thread-9] i.p.test.client.service.TradeService     : Stopping after getting 167932 trades
2019-12-19 15:51:19.022  INFO 58371 --- [      Thread-10] i.p.test.client.service.TradeService     : Stopping after executing 32543 cusip queries
2019-12-19 15:51:19.022  INFO 58371 --- [      Thread-11] i.p.test.client.service.TradeService     : Stopping after executing 32550 cusip queries
2019-12-19 15:51:19.022  INFO 58371 --- [      Thread-12] i.p.test.client.service.TradeService     : Stopping after updating 63897 trades with function
2019-12-19 15:51:19.022  INFO 58371 --- [       Thread-7] i.p.test.client.service.TradeService     : Stopping after putting 74388 trades of size 1024 bytes
2019-12-19 15:51:19.022  INFO 58371 --- [      Thread-13] i.p.test.client.service.TradeService     : Stopping after updating 63754 trades with function
2019-12-19 15:51:19.022  INFO 58371 --- [       Thread-6] i.p.test.client.service.TradeService     : Stopping after putting 74523 trades of size 1024 bytes
```
