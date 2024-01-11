package com.employee;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	@Bean
	public WebClient getBean(){
		HttpClient client = HttpClient.create()
				                      .responseTimeout(Duration.ofSeconds(3));
		return WebClient.builder()
				.baseUrl("http://localhost:8081")
				.clientConnector(new ReactorClientHttpConnector(client)).build();

	}


}
/* This is the extended version of Timeout, above we did only for response time out
Here we can do timeout for

Connection timeout: to 10,000 milliseconds (10 seconds) using ChannelOption.CONNECT_TIMEOUT_MILLIS.
Read timeout: of 10 seconds using ReadTimeoutHandler class. This means if no data is received within 10 seconds of making a request, a ReadTimeoutException exception will be thrown.
Write timeout: of 10 seconds using WriteTimeoutHandler class. When a write operation cannot finish in the specified period of time, a WriteTimeoutException exception will be thrown.


@Bean
	public WebClient serviceAWebClient() {
		HttpClient httpClient = HttpClient.create().tcpConfiguration(tcpClient ->
				tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
						.doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(5, TimeUnit.SECONDS)))
		);

		return WebClient.builder()
				.baseUrl("http://localhost:8081")
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
* */