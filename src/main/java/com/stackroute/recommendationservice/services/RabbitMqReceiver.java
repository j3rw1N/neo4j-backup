package com.stackroute.recommendationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class RabbitMqReceiver {
    private CountDownLatch latch = new CountDownLatch(1);


    public void receiveMessage(String user) {
        System.out.println("user");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}

