package com.springboot.SpringBootChatGPT.model;

import java.util.List;

import lombok.Data;

@Data
public class ChatGPTResponse {
	private List<ChatGPTChoices> choices;
}
