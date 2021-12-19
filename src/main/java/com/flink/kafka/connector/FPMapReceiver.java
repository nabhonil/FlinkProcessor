package com.flink.kafka.connector;

import org.apache.flink.api.common.functions.MapFunction;

public class FPMapReceiver implements MapFunction<String, String> {

	private static final long serialVersionUID = 1L;

	public String map(String value) throws Exception {
		return "Receiving from Kafka: " + value.toUpperCase();
	}

}
