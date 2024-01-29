package com.example.library.code.config.cloud_dinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClouddinaryConfig {

    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "dw4zakhwr");
        config.put("api_key", "697834762542465");
        config.put("api_secret", "xihSuIAdZiqhrmXE1yEti_3lzNE");
        config.put("secure", true);
        return new Cloudinary(config);
    }

}
