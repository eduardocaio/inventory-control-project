package com.eduardocaio.inventory_control_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Configuration
public class KeyConfig {

    // Injeta o recurso do arquivo PEM localizado no classpath
    @Value("classpath:private.pem")
    private Resource privateKeyResource;

    @Bean
    public RSAPrivateKey rsaPrivateKey() throws Exception {
        try (InputStream inputStream = privateKeyResource.getInputStream()) {
            // Lê todo o conteúdo do arquivo PEM
            String key = new String(inputStream.readAllBytes());

            // Remove as linhas de cabeçalho e rodapé do PEM
            key = key.replaceAll("-----BEGIN (.*)-----", "")
                     .replaceAll("-----END (.*)-----", "")
                     .replaceAll("\\s", "");

            // Decodifica o conteúdo base64 para bytes
            byte[] decoded = Base64.getDecoder().decode(key);

            // Cria a especificação da chave privada
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);

            // Gera a chave privada a partir da especificação
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) kf.generatePrivate(spec);
        }
    }
}
