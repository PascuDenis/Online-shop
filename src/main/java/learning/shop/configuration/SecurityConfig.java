package learning.shop.configuration;//package ro.msg.learning.shop.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SecuriryConfig {
//    public enum SecurityTypes {
//        BASIC, FORM
//    }
//
//    @Bean
//    public WebSecurityConfigurerAdapter chooseSecurityType(@Value("${security.type}") SecurityTypes security) {
//        switch (security) {
//            case FORM:
//                return new BasicAuthentication();
//            case BASIC:
//                return new FormAuthentication();
//            default:
//                try {
//                    throw new Exception();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//        }
//        return null;
//    }
//
//}