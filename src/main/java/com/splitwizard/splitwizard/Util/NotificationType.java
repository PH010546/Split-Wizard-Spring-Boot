package com.splitwizard.splitwizard.Util;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum NotificationType {
    SYSTEM(101, "System", "更新囉寶貝"),
    INVITATION(201, "Invitation", " 已邀請您至 "),

    ITEM_ADD(301, "Item", " 中有新增紀錄"),
    ITEM_UPDATE(302, "Item", " 中有更新紀錄"),
    ITEM_DELETE(303, "Item", " 中有紀錄被刪除"),

    RESULT_PAY(401, "Result", "支付帳款"),
    RESULT_UNPAY(402, "Result", "取消支付");

    private final Integer code;
    private final String type;
    private final String text;

    static class FindEnum{

        static Map<Integer, NotificationType> byCodeInMap = new HashMap<>();

    }

    NotificationType(Integer code, String type, String text){
        this.code = code;
        this.type = type;
        this.text = text;
        FindEnum.byCodeInMap.put(code, this);
    }

    public static NotificationType of(Integer code){
        return FindEnum.byCodeInMap.get(code);
    }
}
