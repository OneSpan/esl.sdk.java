package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.builder.ProxyConfigurationBuilder;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by whou on 08/12/14.
 */
public class ProxyConfigurationExample {

    protected EslClient eslClientWithHttpProxy;
    private final String httpProxyURL = "localhost";
    private final int httpProxyPort = 8001;

    protected EslClient eslClientWithHttpProxyHasCredentials;
    private final String httpProxyWithCredentialsURL = "localhost";
    private final int httpProxyWithCredentialsPort = 8002;
    private final String httpProxyUserName = "httpUser";
    private final String httpProxyPassword = "httpPwd";

    protected EslClient eslClientWithHttpsProxy;
    private final String httpsProxyURL = "localhost";
    private final int httpsProxyPort = 8003;

    protected EslClient eslClientWithHttpsProxyHasCredentials;
    private final String httpsProxyWithCredentialsURL = "localhost";
    private final int httpsProxyWithCredentialsPort = 8004;
    private final String httpsProxyUserName = "httpsUser";
    private final String httpsProxyPassword = "httpsPwd";

    protected PackageId packageId;

    public final String email1;
    private InputStream documentInputStream1, documentInputStream2, documentInputStream3, documentInputStream4;

    public ProxyConfigurationExample(Properties props) {
        this(props.getProperty("api.key"),
                props.getProperty("api.url"),
                props.getProperty("allow.all.sslcertificates", "false"),
                props.getProperty("1.email"));
    }

    public ProxyConfigurationExample(String apiKey, String apiUrl, String allowAllSSLCertificates, String email1) {

        ProxyConfiguration httpProxyConfiguration = ProxyConfigurationBuilder.newProxyConfiguration()
                .withHttpHost(httpProxyURL)
                .withHttpPort(httpProxyPort)
                .build();

        ProxyConfiguration httpProxyWithCredentialsConfiguration = ProxyConfigurationBuilder.newProxyConfiguration()
                .withHttpHost(httpProxyWithCredentialsURL)
                .withHttpPort(httpProxyWithCredentialsPort)
                .withCredentials(httpProxyUserName, httpProxyPassword)
                .build();

        ProxyConfiguration httpsProxyConfiguration = ProxyConfigurationBuilder.newProxyConfiguration()
                .withHttpsHost(httpsProxyURL)
                .withHttpsPort(httpsProxyPort)
                .build();

        ProxyConfiguration httpsProxyWithCredentialsConfiguration = ProxyConfigurationBuilder.newProxyConfiguration()
                .withHttpsHost(httpsProxyWithCredentialsURL)
                .withHttpsPort(httpsProxyWithCredentialsPort)
                .withCredentials(httpsProxyUserName, httpsProxyPassword)
                .build();

        this.email1 = UUID.randomUUID().toString().replace("-", "") + "@e-signlive.com";
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream3 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream4 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        eslClientWithHttpProxy = new EslClient(apiKey, apiUrl, Boolean.parseBoolean(allowAllSSLCertificates), httpProxyConfiguration);
        eslClientWithHttpProxyHasCredentials = new EslClient(apiKey, apiUrl, Boolean.parseBoolean(allowAllSSLCertificates), httpProxyWithCredentialsConfiguration);
        eslClientWithHttpsProxy = new EslClient(apiKey, apiUrl, Boolean.parseBoolean(allowAllSSLCertificates), httpsProxyConfiguration);
        eslClientWithHttpsProxyHasCredentials = new EslClient(apiKey, apiUrl, Boolean.parseBoolean(allowAllSSLCertificates), httpsProxyWithCredentialsConfiguration);
    }

    private DocumentPackage createTestPackage(InputStream documentStream) {
        DocumentPackage packageTest = newPackageNamed("ProxyExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document pdf")
                        .fromStream(documentStream, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        return packageTest;
    }

    public void executeHttpProxy() {
        DocumentPackage packageTest = createTestPackage(documentInputStream1);
        packageId = eslClientWithHttpProxy.createPackage(packageTest);
        eslClientWithHttpProxy.sendPackage(packageId);
    }

    public void executeHttpProxyWithCredentials() {
        DocumentPackage packageTest = createTestPackage(documentInputStream2);
        packageId = eslClientWithHttpProxyHasCredentials.createPackage(packageTest);
        eslClientWithHttpProxyHasCredentials.sendPackage(packageId);
    }

    public void executeHttpsProxy() {
        DocumentPackage packageTest = createTestPackage(documentInputStream3);
        packageId = eslClientWithHttpsProxy.createPackage(packageTest);
        eslClientWithHttpsProxy.sendPackage(packageId);
    }

    public void executeHttpsProxyWithCredentials() {
        DocumentPackage packageTest = createTestPackage(documentInputStream4);
        packageId = eslClientWithHttpsProxyHasCredentials.createPackage(packageTest);
        eslClientWithHttpsProxyHasCredentials.sendPackage(packageId);
    }

}
