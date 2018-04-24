package ru.egorza.serviceinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class InfoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "getInfo")
    public String getInfoForComapany(){

        List<String> listCompany = restTemplate.exchange("http://service-company/getNameCompanies",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){}).getBody();
        StringBuilder response = new StringBuilder();

        for(String nameCompany : listCompany) {
            response.append(nameCompany + restTemplate.exchange("http://service-company/getPeopleByNameCompany/"+nameCompany,
                    HttpMethod.GET, null, new ParameterizedTypeReference<String>(){}).getBody() + ";\n");
        }
        return response.toString();
    }

    @Bean
    @LoadBalanced()
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
