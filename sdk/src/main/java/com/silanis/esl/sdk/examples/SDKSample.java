package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.Converter;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public abstract class SDKSample {
    protected EslClient eslClient;
    protected PackageId packageId;
    protected String packageName;
    protected DocumentPackage retrievedPackage;
    protected InputStream documentInputStream1, documentInputStream2;

    protected Properties props = Props.get();

    public String email1, email2, email3, email4, email5, email6, senderEmail,
            sms1, sms2, sms3, sms4, sms5, sms6, senderSms, webpageUrl, senderUID,
            proxyHost, proxyWithCredentialsHost, proxyUserName, proxyPassword;

    public int proxyPort, proxyWithCredentialsPort;

    public SDKSample() {
        eslClient = new EslClient(props.getProperty("api.key"), props.getProperty("api.url"), props.getProperty("webpage.url"), true);
        setProperties();
    }

    public SDKSample(String apiKey, String apiUrl) {
        eslClient = new EslClient(apiKey, apiUrl);
        setProperties();
    }

    protected abstract void execute();

    public void run() {
        execute();
    }

    private void setProperties() {
        email1 = props.getProperty("1.email");
        email2 = props.getProperty("2.email");
        email3 = props.getProperty("3.email");
        email4 = props.getProperty("4.email");
        email5 = props.getProperty("5.email");
        email6 = props.getProperty("6.email");
        senderEmail = props.getProperty("sender.email");
        sms1 = props.getProperty("1.sms");
        sms2 = props.getProperty("2.sms");
        sms3 = props.getProperty("3.sms");
        sms4 = props.getProperty("4.sms");
        sms5 = props.getProperty("5.sms");
        sms6 = props.getProperty("6.sms");
        senderSms = props.getProperty("sender.sms");
        webpageUrl = props.getProperty("webpage.url");
        proxyHost = props.getProperty("proxy.host");
        proxyPort = Integer.parseInt(props.getProperty("proxy.port"));
        proxyWithCredentialsHost = props.getProperty("proxyWithCredentials.host");
        proxyWithCredentialsPort = Integer.parseInt(props.getProperty("proxyWithCredentials.port"));
        proxyUserName = props.getProperty("proxy.userName");
        proxyPassword = props.getProperty("proxy.password");
        senderUID = Converter.apiKeyToUID(props.getProperty("api.key"));

        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    protected EslClient getEslClient() {
        return eslClient;
    }

    protected PackageId getPackageId() {
        return packageId;
    }

    protected DocumentPackage getRetrievedPackage() {
        if (null == retrievedPackage)
            retrievedPackage = eslClient.getPackage(packageId);
        return retrievedPackage;
    }

    protected String getPackageName() {
        if (null == packageName)
            packageName = this.getClass().getSimpleName() + " : " + new SimpleDateFormat("HH:mm:ss").format(new Date());
        return packageName;
    }

    protected String getRandomEmail() {
        return UUID.randomUUID().toString().replace("-", "") + "@e-signlive.com";
    }
}
