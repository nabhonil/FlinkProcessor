# FlinkProcessor

This is a sample application created with Flink version 1.14 and Kafka 2.13.
The various concepts used in this example are as follows:-

1. Flink KafkaSource available from 1.14 onwards. 
2. Flink KafkaSink available from 1.14 onwards.
3. Flink DataStream
4. Kafka Connectors

The logic of this application is to read the data stream (in the form of Strings) from a Kafka Topic "testtopic" and use the Flink to 
process the String by converting it to Upper Case and appending this Upper Case String to the statement "Receiving from Kafka: ".
Once the data is processed it is sent to another Kafka topic "testtopicoutput" and also shown in the application console.

## How to use the code

Please refer to the Commands.txt on how to run this application.

## Prerequisites

* Flink Version 1.14
* Kafka Version 2.13
* Eclipse - Latest version available
* JDK 11. (**USE ONLY JDK 8 or JDK 11, DONT USE ANY OTHER JDK VERSIONS, FLINK WILL NOT WORK**)

*The example is created in MAC laptop so the Commands.txt uses mac style folder structures and the respective .sh files of Kafka and Flink.*
*To use this example with Windows, refer the Kafka's windows documentation for the respective Kafka and Flink commands.*
 
