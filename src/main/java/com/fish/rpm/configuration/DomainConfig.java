package com.fish.rpm.configuration;

import com.fish.rpm.dao.util.Constants;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Data
@Configuration
@Service
public class DomainConfig {
    private String domainName = Constants.DOMAIN_NAME;

    private String domainLogin = Constants.DOMAIN_LOGIN;

    private String domainLogout = Constants.DOMAIN_LOGOUT;
}
