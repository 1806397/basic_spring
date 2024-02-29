package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filterning(){
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3"));
        someBean sb=new someBean("value1","value2","value3");
        MappingJacksonValue mjv=new MappingJacksonValue(sb);
        mjv.setFilters(filters);
        return mjv;
    };

    @GetMapping("/filtering-list")
    public MappingJacksonValue filterningList(){
        List<someBean> list=Arrays.asList(
                new someBean("value1","value2","value2")
                ,new someBean("value2","value3","value4")
        );
        MappingJacksonValue mjv=new MappingJacksonValue(list);
        FilterProvider filter=new SimpleFilterProvider().addFilter("SomeBeanFilter",SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3"));
        mjv.setFilters(filter);
        return mjv;
    }

}
