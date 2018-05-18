Software Required
1. Confluent 3.0.1  
		  Download Link -> https://github.com/renukaradhya/confluentplatform   \\If we Download from official site. THe Software will not 				    							  support some feature in window OS

2. Eclipse Oxygen EE Edition

3. nginx

4. MySql

Step to Run Program 

1. Download Confluent and unzip in C Drive.

2. Go to location %path%\etc\kafka\zookeeper.properties
	2.1 check setting -> dataDir=/tmp/zookeeper
			    connect
clientPort=2181
			    config
maxClientCnxns=0 

3. Go to location %path%\etc\kafka\server.properties
	3.1 check necessary setting ->  listeners=PLAINTEXT://:9092
					log.dirs=/tmp/kafka-logs
                                        num.partitions=1
					log.retention.ms=15000
					log.retention.check.interval.ms=5000
					zookeeper.connect=localhost:2181

4. Unzip project InternshipKafkaMultiUser 

5. Import data in MySql from %path%\Database\riskassessment.sql

6. Import project in eclipse by selecting Existing Maven Project from File->Import.

7. Before Starting Kafka add path in Environment Variable. 
	7.1 add %pathOfConfluentFolder%\bin\windows.

8. process of starting kafka server
 	8.1 run CMD and type for run zookeeper :  zookeeper-server-start.bat %pathofConfluentFolder%\etc\kafka\zookeeper.properties
	8.2 run CMD and type for run Kafka     : kafka-server-start.bat %pathofConfluentFolder%\etc\kafka\server.properties

9. Run Main File in com.risk package by run as spring boot app.

10. Do the configuration of nginx and run nginx.

11. Start first page by running "localhost" through  browser.




