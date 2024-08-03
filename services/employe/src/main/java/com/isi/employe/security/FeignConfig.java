//package com.isi.employe.security;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//
//@Configuration
//public class FeignConfig {
//
//    @Autowired
//    private OAuth2AuthorizedClientService clientService;
//
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor() {
//        return template -> {
//            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient("keycloak", "service-employe");
//            if (client != null) {
//                String accessToken = client.getAccessToken().getTokenValue();
//                template.header("Authorization", "Bearer " + accessToken);
//            }
//        };
//    }
//}