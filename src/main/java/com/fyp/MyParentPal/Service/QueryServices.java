package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Query;
import com.fyp.MyParentPal.Repo.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryServices {
    @Autowired
    private QueryRepo repo;
    @Autowired
    private JavaMailSender mailSender;
    public void saveorUpdate(Query queries) {

        repo.save(queries);
    }
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
    public List<Query> getAllQueries() {
        return repo.findAll();
    }

    public Query getQueryById(String id) {
        return repo.findById(id).orElse(null);
    }


    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("myparentpal@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");


    }

}

