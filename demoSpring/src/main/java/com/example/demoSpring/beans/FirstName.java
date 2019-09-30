package com.example.demoSpring.beans;

import com.example.demoSpring.interfaces.BeanName;
import org.springframework.stereotype.Component;

@Component
public class FirstName implements BeanName {
    @Override
    public String getName() {
        return "C";
    }
}
