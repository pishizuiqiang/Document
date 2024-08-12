package com.pishi.doc20240810.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@RestController
@RequestMapping("doc20240810")
@RequiredArgsConstructor
@Slf4j
public class DemoController {


    @GetMapping("test")
    public String test(@RequestParam LocalDateTime time) {
        return MessageFormat.format("成功获取到time={0}", time);
    }


}
