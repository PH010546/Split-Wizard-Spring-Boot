package com.splitwizard.splitwizard.Util;

import jakarta.persistence.AttributeConverter;

public class NotificationTypeConverter implements AttributeConverter<NotificationType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(NotificationType attribute) {
        return attribute.getCode();
    }

    @Override
    public NotificationType convertToEntityAttribute(Integer dbData) {
        return NotificationType.of(dbData);
    }
}
