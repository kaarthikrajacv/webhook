package com.webhook.security.client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ExternalRequestImpl implements ExternalRequest{
	
	
	@Override
	public Integer postRequest(String URI, JsonNode node) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpPost post = new HttpPost(URI);
		
		post.addHeader("content-type", "application/json");
		
		StringEntity entity = new StringEntity(node.toString());
		
		post.setEntity(entity);
		
		HttpResponse response = httpClient.execute(post);
		
		httpClient.close();
		
		return response.getStatusLine().getStatusCode();
	}

	@Override
	public Integer putRequest(String URI, JsonNode node) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpPut post = new HttpPut(URI);
		
		post.addHeader("content-type", "application/json");
		
		StringEntity entity = new StringEntity(node.toString());
		
		post.setEntity(entity);
		
		HttpResponse response = httpClient.execute(post);
		
		httpClient.close();
		
		return response.getStatusLine().getStatusCode();
	}

	@Override
	public Integer deleteRequest(String URI, JsonNode node) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpDelete del = new HttpDelete(URI);
		
		del.addHeader("content-type", "application/json");
		
		HttpResponse response = httpClient.execute(del);
		
		httpClient.close();
		
		return response.getStatusLine().getStatusCode();
		
		
	}
	
	

}
