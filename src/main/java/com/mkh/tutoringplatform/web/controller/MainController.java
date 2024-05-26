package com.mkh.tutoringplatform.web.controller;

import com.google.protobuf.InvalidProtocolBufferException;
import com.mkh.tutoringplatform.nats.factory.NatsRequestFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import systems.test.entity.Test;

import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final NatsRequestFactory natsRequestFactory;

    @GetMapping
    public String startEndpoint() {
        return "redirect:/courses";
    }

    @GetMapping("/test")
    public String test() throws InvalidProtocolBufferException, ExecutionException, InterruptedException {
        System.out.println(natsRequestFactory
                .doRequest("1", Test.newBuilder().setName("1234").build(), Test.parser())
                .toString());
        return "redirect:/courses";
    }
}
