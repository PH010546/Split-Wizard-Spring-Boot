package com.splitwizard.splitwizard.Util;

import lombok.Getter;
@Getter
public enum NotificationType {
    SYSTEM(101, "System", "更新囉寶貝"),
    INVITATION(201, "Invitation", " 已邀請您至 "),
    ITEM(301, "Item", " 有新增/更新紀錄");

    private final Integer code;
    private final String type;
    private final String text;

    NotificationType(Integer code, String type, String text){
        this.code = code;
        this.type = type;
        this.text = text;
    }
}
