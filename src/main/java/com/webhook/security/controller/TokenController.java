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
@RequestMapping("/api/token")
public class TokenController {
	
	@Autowired ExternalRequest req;
	
	@GetMapping
	public String getToken() {
		return "Hi Token health check";
	}
	
	@PostMapping("/post")
	public String testPost(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.postRequest(url, node);
				
				if(status == 200) {
					
					return "The External Webhook call for TOKEN POST succeeded! TOKEN Authentication verified!";
				}
				
			}catch(Exception e) {
				
				return "TOKEN Authentication verified! but Invalid External URL or Call to Webhook wasn't successful for TOKEN POST";
			}
		}
		
		return "TOKEN Authentication checked! Call to External hook is not made. TOKEN 'POST' verification successful";
	}
	
	@PutMapping("/put")
	public String testPut(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.putRequest(url, node);
				
				System.out.println(status);
				
				if(status == 200) {
					
					return "The External Webhook call for TOKEN PUT succeeded! TOKEN Authentication verified!";
				}
				
			}catch(Exception e) {
				
				return "TOKEN Authentication verified! but Invalid External URL or Call to Webhook wasn't successful for TOKEN PUT";
			}
		}
		
		return "TOKEN Authentication checked! Call to External hook is not made. TOKEN 'PUT' verification successful";
	}
	
	@DeleteMapping("/delete")
	public String testDelete(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.deleteRequest(url, node);
				
				System.out.println(status);
				
				if(status == 200) {
					
					return "The External Webhook call for TOKEN AUTH DELETE succeeded! TOKEN Authentication verified!";
				}
				
			}catch(Exception e) {
				
				return "TOKEN Authentication verified! but Invalid External URL or Call to Webhook wasn't successful for TOKEN DELETE";
			}
		}
		
		return "TOKEN Authentication checked! Call to External hook is not made. TOKEN 'DELETE' verification successful";
	}

}
