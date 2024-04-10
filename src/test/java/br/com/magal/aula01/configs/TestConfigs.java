package br.com.magal.aula01.configs;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfigs {

    public static final int SERVER_PORT = 8888;

    public static final String HEADER_PARAM_ORIGIN = "Origin";
    public static final String HEADER_PARAM_AUTHORIZATION = "Authorization";

    public static final String CONTENT_TYPE_JSON = "aplication/json";
    public static final String CONTENT_TYPE_XML = "aplication/xml";
    public static final String CONTENT_TYPE_YML = "aplication/x-yaml";

    public static final String ORIGIN_ERUDIO = "https://erudio.com.br";
    public static final String ORIGIN_SEMERU = "https://semeru.com.br";
}
