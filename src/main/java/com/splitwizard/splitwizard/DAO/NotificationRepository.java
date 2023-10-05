package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAllByReceiverId(Integer receiverId);
    Page<Notification> findNotificationsByReceiverId(Integer receiverId, Pageable pageable);
}
