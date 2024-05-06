package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Feedback;
import com.fyp.MyParentPal.Entity.Notification;
import com.fyp.MyParentPal.Entity.RewardRequest;
import com.fyp.MyParentPal.Repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices {

    @Autowired
    private NotificationRepo repo;

    public void save(Notification notification) {
        repo.save(notification);
    }


    public Iterable<Notification> listAll() {

        return this.repo.findAll();
    }

    public void sendMessage(Long toId, String messageContent) {
        Notification notification = new Notification();
        notification.setToId(toId);
        notification.setMessage(messageContent);
        repo.save(notification);
    }


}
