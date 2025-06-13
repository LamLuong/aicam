package com.bkazx.aicam.websocket;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatMessage {
    private String name;
    private String message;
    private LocalDateTime sendAt;
}