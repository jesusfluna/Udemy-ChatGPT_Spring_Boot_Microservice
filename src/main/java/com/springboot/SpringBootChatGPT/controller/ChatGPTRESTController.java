package com.springboot.SpringBootChatGPT.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.SpringBootChatGPT.model.SearchRequest;
import com.springboot.SpringBootChatGPT.service.ChatGPTService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class ChatGPTRESTController {
	private ChatGPTService chatGPTService;
	
	public ChatGPTRESTController(ChatGPTService chatGPTService) {
		this.chatGPTService = chatGPTService;
	}
	
	@PostMapping("searchChatGPT")
	public String searchChatGPT(@RequestBody SearchRequest searchRequest) {
		
		log.info("searchChatGPT started query: "+searchRequest.getQuery());
		
		return chatGPTService.processChatGPTSearch(searchRequest.getQuery());

	}

}
