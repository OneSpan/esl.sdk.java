package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.EslException;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class EslClientBuilderTest {

    @Test
    public void buildWithAPIKEY() throws IOException {
        Properties props = getPropertiesFromFile("src/test/resources/clientbuilderapikey.properties");
        EslClient client = EslClientBuilder.newEslClientBuilder().withProperties(props).build();
        assertNotNull(client);
    }
    @Test
    public void buildWithApiTokenKey() throws IOException {
        Properties props = getPropertiesFromFile("src/test/resources/clientbuilderapitoken.properties");
        EslClient client = EslClientBuilder.newEslClientBuilder().withProperties(props).build();
        assertNotNull(client);
    }

    @Test
    public void buildWithOAuth() throws IOException {
        Properties props = getPropertiesFromFile("src/test/resources/clientbuilderoauthtoken.properties");
        EslClient client = EslClientBuilder.newEslClientBuilder().withProperties(props).build();
        assertNotNull(client);
    }

    @Test(expected = EslException.class)
    public void buildWithOAuthWithEmptyValues() throws IOException {
        Properties props = getPropertiesFromFile("src/test/resources/clientbuilderoauthtokenempty.properties");
        EslClientBuilder.newEslClientBuilder().withProperties(props).build();
    }

    @Test(expected = EslException.class)
    public void buildWithoutAPIKEY() throws IOException {
        Properties props = getPropertiesFromFile("src/test/resources/clientbuilderempty.properties");
        EslClientBuilder.newEslClientBuilder().withProperties(props).build();
    }

    private Properties getPropertiesFromFile(String filename) throws IOException {
        Properties properties = new Properties();

        try (FileInputStream file = new FileInputStream(filename)) {
            properties.load(file);
        }
        return properties;
    }
}
