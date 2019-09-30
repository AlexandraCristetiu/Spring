package com.example.demoSpring.beans;

import com.example.demoSpring.interfaces.BeanName;
import org.springframework.stereotype.Component;

@Component
public class SecondName implements BeanName {
    @Override
    public String getName() {
        return "B";
    }
}
