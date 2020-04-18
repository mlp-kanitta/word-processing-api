/**
 * 
 */
package com.wpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;

import com.wpa.model.WordProcessingModel;

/**
 * @author Kanitta Moonlapong
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WordProcessingAPIIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	/*
	 * //"THE WORLD" "VEhFIFdPUkxE"
	 */
	public void testCase1() throws RestClientException, MalformedURLException {

		String funStr = "VEhFIFdPUkxE";

		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/wpapi?funStr=" + funStr).toString(), String.class);

		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("{\"result\":\"ZGxyb3cgZWh0\"}");
	}
	
	@Test
	/*
	 *"A HEN  HAS  MANY   CHICKS"
	 */
	public void testCase2() throws RestClientException, MalformedURLException {

		String funStr = "QSBIRU4gIEhBUyAgTUFOWSAgIENISUNLUw==";

		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/wpapi?funStr=" + funStr).toString(), String.class);

		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		//"THE WORLD" "VEhFIFdPUkxE"
		assertThat(response.getBody()).isEqualTo("{\"result\":\"c2tjaWhjICAgeW5hbSAgc2FoICBuZWggYQ==\"}");
	}
	
	/**
	 * 
	 * @throws RestClientException
	 * @throws MalformedURLException
	 * Exception testing
	 */
	public void testCase3() throws RestClientException, MalformedURLException {


		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/wpapi?funStr=" + "").toString(), String.class);

		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

}
