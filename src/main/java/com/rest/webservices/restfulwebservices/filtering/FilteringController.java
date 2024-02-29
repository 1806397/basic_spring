package com.rest.webservices.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public someBean filterning(){
        return new someBean("value1","value2","value3");
    };

    @GetMapping("/filtering-list")
    public List<someBean> filterningList(){
        return Arrays.asList(
                new someBean("value1","value2","value3"),
                new someBean("value1","value2","value3")
        );
    };

}
