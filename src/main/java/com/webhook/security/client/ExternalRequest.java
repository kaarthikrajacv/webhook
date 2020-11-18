package com.webhook.security.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.databind.JsonNode;

public interface ExternalRequest {
	
	public Integer postRequest(String URI, JsonNode node) throws ClientProtocolException, IOException;
	
	public Integer putRequest(String URI, JsonNode node) throws ClientProtocolException, IOException;
	
	public Integer deleteRequest(String URI, JsonNode node) throws ClientProtocolException, IOException;

}
