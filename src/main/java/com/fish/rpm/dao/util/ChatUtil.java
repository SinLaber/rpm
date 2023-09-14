package com.fish.rpm.dao.util;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ChatUtil {
    String model = "gpt-3.5-turbo";

    List<Msg> messages;

    @Data
    public static class Msg {
        String role = "user";

        String content;
    }

    public static String quickCreate(String content) {
        ChatUtil chatUtil = new ChatUtil();
        Msg msg = new Msg();
        msg.setContent(content);
        chatUtil.setMessages(Collections.singletonList(msg));
        return JSON.toJSONString(chatUtil);
    }

}

