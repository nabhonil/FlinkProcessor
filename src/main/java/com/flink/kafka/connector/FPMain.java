package com.flink.kafka.connector;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.flink.connector.kafka.source.reader.deserializer.KafkaRecordDeserializationSchema;
import org.apache.flink.core.execution.JobClient;

public class FPMain {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Flink Processor");
		try {
			String topic = "testtopic";
			String outputtopic = "testtopicoutput";
			String server = "localhost:9092";
			
			//Create the execution environment
			final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			
			KafkaSource<String> source = KafkaSource.<String>builder()
					.setBootstrapServers(server)
					.setTopics(topic)
					.setDeserializer(KafkaRecordDeserializationSchema.valueOnly(StringDeserializer.class))
					.build();
			
			
			DataStream<String> stream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source With No Watermark Strategy");
			
			SingleOutputStreamOperator<String> output = stream.map(new FPMapReceiver());
			
			output.print();
			
			KafkaSink<String> sink = KafkaSink.<String>builder()
					.setBootstrapServers(server)
					.setRecordSerializer(KafkaRecordSerializationSchema.builder()
							.setTopic(outputtopic)
							.setValueSerializationSchema(new SimpleStringSchema())
							.build()
							)
					.build();
			
			output.sinkTo(sink);
			
			//stream.sinkTo(sink);
			
			//env.execute();
			final JobClient jobClient = env.executeAsync();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
