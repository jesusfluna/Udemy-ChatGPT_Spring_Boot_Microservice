package com.springboot.SpringBootChatGPT.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.springboot.SpringBootChatGPT.model.ChatGPTRequest;
import com.springboot.SpringBootChatGPT.model.ChatGPTResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatGPTService {
	@Value("${OPEN_AI_URL}")
	private String OPEN_AI_URL;
	@Value("${OPEN_AI_KEY}")
	private String OPEN_AI_KEY;
	
	public String processChatGPTSearch(String query) {
		
		try {
			ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
			chatGPTRequest.setPromt(query);
			
			HttpPost post = new HttpPost(OPEN_AI_URL);
			post.addHeader("Content-Type","application/json");
			post.addHeader("Authorization","Bearer " + OPEN_AI_KEY);
			
			Gson gson= new Gson();
			log.info("body: "+gson.toJson(chatGPTRequest));
			
			StringEntity entity = new StringEntity(gson.toJson(chatGPTRequest));
			post.setEntity(entity);
			
			try {
				CloseableHttpClient httpClient = HttpClients.custom().build();
				CloseableHttpResponse response = httpClient.execute(post);
				
				
				String responseBody = EntityUtils.toString(response.getEntity());
				log.info("responseBody: "+responseBody);
				ChatGPTResponse chatGPTResponse = gson.fromJson(responseBody, ChatGPTResponse.class);
				
				return chatGPTResponse.getChoices().get(0).getText();
			}catch(Exception e){
				return "Failed";
			}
		}catch(Exception e){
			return "Failed";
		}

	}
	
}
