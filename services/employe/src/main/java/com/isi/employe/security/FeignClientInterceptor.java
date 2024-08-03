//package com.isi.employe.security;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.stereotype.Component;
//
//
//@Component
//
//public class FeignClientInterceptor implements RequestInterceptor {
//    @Autowired
//    private OAuth2AuthorizedClientService clientService;
//
//    @Autowired
//    private ClientRegistrationRepository clientRegistrationRepository;
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
//                "keycloak",
//                "service-employe"
//        );
//
//        if (client != null && client.getAccessToken() != null) {
//            String accessToken = client.getAccessToken().getTokenValue();
//            requestTemplate.header("Authorization", "Bearer " + accessToken);
//        } else {
//            throw new RuntimeException("Failed to load OAuth2AuthorizedClient or access token is null");
//        }
//    }
//
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor() {
//        return new FeignClientInterceptor();
//    }
//}