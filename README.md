# Spring Boot Data Geode Example

## Description
This is an example of a Spring Boot Data Geode client.

It uses:

- SB 2.2.1.RELEASE
- SBDG 1.2.1.RELEASE

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
2019-12-05 15:31:19.041  INFO 21528 --- [  restartedMain] Client: Client Command Line Arguments: [--spring.profiles.active=local, --operation=wait]
2019-12-05 15:31:19.041  INFO 21528 --- [  restartedMain] Client: Client Option Argument: operation=[wait]
2019-12-05 15:31:19.041  INFO 21528 --- [  restartedMain] Client: Client Option Argument: spring.profiles.active=[local]
```
#### Run Put Client
Run the put client using the **runclient.sh** script like:

```
./runclient.sh doputs 10
2019-12-05 15:33:59.941  INFO 80961 --- [  restartedMain] Client: Loading 10 entries
2019-12-05 15:33:59.971  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=0, cusip=POT, shares=70, price=336.84)
2019-12-05 15:33:59.972  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=1, cusip=MCD, shares=51, price=667.81)
2019-12-05 15:33:59.974  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=2, cusip=SLB, shares=43, price=187.01)
2019-12-05 15:33:59.976  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=3, cusip=CMI, shares=91, price=536.14)
2019-12-05 15:33:59.978  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=4, cusip=GG, shares=45, price=991.45)
2019-12-05 15:33:59.980  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=5, cusip=PCP, shares=84, price=415.01)
2019-12-05 15:33:59.981  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=6, cusip=USB, shares=68, price=359.70)
2019-12-05 15:33:59.985  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=7, cusip=USB, shares=97, price=301.11)
2019-12-05 15:33:59.988  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=8, cusip=SDRL, shares=9, price=282.13)
2019-12-05 15:33:59.989  INFO 80961 --- [  restartedMain] Client: Saved Trade(id=9, cusip=NOK, shares=0, price=403.29)
2019-12-05 15:33:59.989  INFO 80961 --- [  restartedMain] Client: Loading 10 entries in 48 ms
```
#### Run Get Client
Run the get client using the **runclient.sh** script like:

```
./runclient.sh dogets 10
2019-12-05 15:37:58.739  INFO 81185 --- [  restartedMain] Client: Retrieving 10 entries
2019-12-05 15:37:58.771  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=0, cusip=POT, shares=70, price=336.84)
2019-12-05 15:37:58.772  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=1, cusip=MCD, shares=51, price=667.81)
2019-12-05 15:37:58.773  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=2, cusip=SLB, shares=43, price=187.01)
2019-12-05 15:37:58.773  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=3, cusip=CMI, shares=91, price=536.14)
2019-12-05 15:37:58.774  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=4, cusip=GG, shares=45, price=991.45)
2019-12-05 15:37:58.774  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=5, cusip=PCP, shares=84, price=415.01)
2019-12-05 15:37:58.775  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=6, cusip=USB, shares=68, price=359.70)
2019-12-05 15:37:58.776  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=7, cusip=USB, shares=97, price=301.11)
2019-12-05 15:37:58.776  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=8, cusip=SDRL, shares=9, price=282.13)
2019-12-05 15:37:58.777  INFO 81185 --- [  restartedMain] Client: Retrieved Trade(id=9, cusip=NOK, shares=0, price=403.29)
2019-12-05 15:37:58.777  INFO 81185 --- [  restartedMain] Client: Retrieved 10 entries in 38 ms
```
#### Run Destroy Client
Run the destroy client using the **runclient.sh** script like:

```
./runclient.sh dodestroys 10
2019-12-05 16:46:03.513  INFO 85897 --- [  restartedMain] Client: Destroying 10 entries
2019-12-05 16:46:03.532  INFO 85897 --- [  restartedMain] Client: Destroyed key=0
2019-12-05 16:46:03.541  INFO 85897 --- [  restartedMain] Client: Destroyed key=1
2019-12-05 16:46:03.542  INFO 85897 --- [  restartedMain] Client: Destroyed key=2
2019-12-05 16:46:03.543  INFO 85897 --- [  restartedMain] Client: Destroyed key=3
2019-12-05 16:46:03.544  INFO 85897 --- [  restartedMain] Client: Destroyed key=4
2019-12-05 16:46:03.544  INFO 85897 --- [  restartedMain] Client: Destroyed key=5
2019-12-05 16:46:03.545  INFO 85897 --- [  restartedMain] Client: Destroyed key=6
2019-12-05 16:46:03.545  INFO 85897 --- [  restartedMain] Client: Destroyed key=7
2019-12-05 16:46:03.546  INFO 85897 --- [  restartedMain] Client: Destroyed key=8
2019-12-05 16:46:03.547  INFO 85897 --- [  restartedMain] Client: Destroyed key=9
2019-12-05 16:46:03.547  INFO 85897 --- [  restartedMain] Client: Destroyed 10 entries in 34 ms
```
#### Run Query Client
Run the query client using the **runclient.sh** script like:

```
./runclient.sh querybycusip AAPL
2019-12-05 15:56:27.262  INFO 82584 --- [  restartedMain] Client: Query entries by cusip=AAPL
2019-12-05 15:56:27.336  INFO 82584 --- [  restartedMain] Client: Retrieved 34 entries in 74 ms:
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=326, cusip=AAPL, shares=77, price=601.84)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=858, cusip=AAPL, shares=66, price=256.49)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=693, cusip=AAPL, shares=85, price=174.21)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=96, cusip=AAPL, shares=63, price=970.54)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=974, cusip=AAPL, shares=94, price=859.43)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=329, cusip=AAPL, shares=9, price=853.49)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=467, cusip=AAPL, shares=40, price=238.67)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=613, cusip=AAPL, shares=88, price=86.11)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=642, cusip=AAPL, shares=14, price=658.67)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=479, cusip=AAPL, shares=19, price=318.52)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=45, cusip=AAPL, shares=19, price=649.71)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=805, cusip=AAPL, shares=2, price=599.79)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=250, cusip=AAPL, shares=79, price=797.48)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=801, cusip=AAPL, shares=3, price=729.96)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=148, cusip=AAPL, shares=44, price=729.22)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=107, cusip=AAPL, shares=25, price=195.17)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=593, cusip=AAPL, shares=18, price=678.83)
2019-12-05 15:56:27.337  INFO 82584 --- [  restartedMain] Client: 	Trade(id=901, cusip=AAPL, shares=2, price=519.49)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=445, cusip=AAPL, shares=65, price=58.39)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=342, cusip=AAPL, shares=16, price=277.71)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=502, cusip=AAPL, shares=2, price=179.76)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=984, cusip=AAPL, shares=47, price=732.35)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=880, cusip=AAPL, shares=30, price=907.80)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=492, cusip=AAPL, shares=67, price=727.57)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=377, cusip=AAPL, shares=40, price=811.11)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=509, cusip=AAPL, shares=82, price=342.36)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=144, cusip=AAPL, shares=19, price=26.39)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=493, cusip=AAPL, shares=5, price=303.85)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=696, cusip=AAPL, shares=81, price=266.96)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=159, cusip=AAPL, shares=64, price=159.08)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=175, cusip=AAPL, shares=20, price=576.99)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=667, cusip=AAPL, shares=44, price=190.50)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=994, cusip=AAPL, shares=7, price=488.73)
2019-12-05 15:56:27.338  INFO 82584 --- [  restartedMain] Client: 	Trade(id=902, cusip=AAPL, shares=43, price=684.72)
```
