package com.webhook.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.webhook.security.client.ExternalRequest;

@RestController
@RequestMapping("/api/basic")
public class BasicAuthController {
	
	@Autowired ExternalRequest req;
	
	
	@GetMapping
	public String returtTestString() {
		return "Hi Basic health check";
	}
	
	@PostMapping("/post")
	public String testPost(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.postRequest(url, node);
				
				if(status == 200) {
					
					return "The External Webhook call for BASIC POST succeeded! Basic Authentication verified!";
				}
				
			}catch(Exception e) {
				
				return "Basic Authentication verified! but Invalid External URL or Call to Webhook wasn't successful for BASIC POST";
			}
		}
		
		return "Basic Authentication checked! Call to External hook is not made. BASIC 'POST' verification successful";
	}
	
	@PutMapping("/put")
	public String testPut(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.putRequest(url, node);
				
				System.out.println(status);
				
				if(status == 200) {
					
					return "The External Webhook call for BASIC PUT succeeded! Basic Authentication verified!";
				}
				
			}catch(Exception e) {
				
				return "Basic Authentication verified! but Invalid External URL or Call to Webhook wasn't successful for BASIC PUT";
			}
		}
		
		return "Basic Authentication checked! Call to External hook is not made. BASIC 'PUT' verification successful";
	}
	
	@DeleteMapping("/delete")
	public String testDelete(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.deleteRequest(url, node);
				
				System.out.println(status);
				
				if(status == 200) {
					
					return "The External Webhook call for BASIC AUTH DELETE succeeded! Basic Authentication verified!";
				}
				
			}catch(Exception e) {
				
				return "Basic Authentication verified! but Invalid External URL or Call to Webhook wasn't successful for BASIC DELETE";
			}
		}
		
		return "Basic Authentication checked! Call to External hook is not made. BASIC 'DELETE' verification successful";
	}

}
