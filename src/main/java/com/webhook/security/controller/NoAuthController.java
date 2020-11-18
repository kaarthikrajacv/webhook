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
@RequestMapping("/api/noauth")
public class NoAuthController {
	
	@Autowired ExternalRequest req;
	
	@GetMapping
	public String noAuthTest() {
		
		return "Test Health of NoAuth EndPoint";
	}
	
	@PostMapping("/post")
	public String testPost(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.postRequest(url, node);
				
				if(status == 200) {
					
					return "The External Webhook call for NOAUTH POST succeeded! You can check status there";
				}
				
			}catch(Exception e) {
				
				return "Invalid External URL or Call to Webhook wasn't successful for NOAUTH POST";
			}
		}
		
		return "No Authentication checked! Call to External hook is not made. No Auth 'POST' verification successful";
	}
	
	@PutMapping("/put")
	public String testPut(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.putRequest(url, node);
				
				System.out.println(status);
				
				if(status == 200) {
					
					return "The External Webhook call for NOAUTH PUT succeeded! You can check status there";
				}
				
			}catch(Exception e) {
				
				return "Invalid External URL or Call to Webhook wasn't successful for NOAUTH PUT";
			}
		}
		
		return "No Authentication checked! Call to External hook is not made. No Auth 'PUT' verification successful";
	}
	
	@DeleteMapping("/delete")
	public String testDelete(@RequestBody JsonNode node, @RequestHeader(name="externalHook", required=false) String url) {
		
		if(url != null) {
			try {
				
				Integer status = req.deleteRequest(url, node);
				
				System.out.println(status);
				
				if(status == 200) {
					
					return "The External Webhook call for NOAUTH DELETE succeeded! You can check status there";
				}
				
			}catch(Exception e) {
				
				return "Invalid External URL or Call to Webhook wasn't successful for NOAUTH DELETE";
			}
		}
		
		return "No Authentication checked! Call to External hook is not made. No Auth 'DELETE' verification successful";
	}

}
