package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.ProxyConfigurationBuilder;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.ProxyAuthenticator;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by whou on 08/12/14.
 */
public class ProxyConfigurationExample extends SDKSample{

    private EslClient eslClientWithHttpProxy;
    private final String httpProxyURL = "localhost";
    private final int httpProxyPort = 8001;

    private EslClient eslClientWithHttpProxyHasCredentials;
    private final String httpProxyWithCredentialsURL = "localhost";
    private final int httpProxyWithCredentialsPort = 8002;
    private final String httpProxyUserName = "httpUser";
    private final String httpProxyPassword = "httpPwd";

    private EslClient eslClientWithHttpsProxy;
    private final String httpsProxyURL = "localhost";
    private final int httpsProxyPort = 8003;

    private EslClient eslClientWithHttpsProxyHasCredentials;
    private final String httpsProxyWithCredentialsURL = "localhost";
    private final int httpsProxyWithCredentialsPort = 8004;
    private final String httpsProxyUserName = "httpsUser";
    private final String httpsProxyPassword = "httpsPwd";

    private PackageId packageId;

    private boolean allowAllSSLCertificates = false;

    private String email1;
    private InputStream documentInputStream1, documentInputStream2;
    public DocumentPackage documentPackage1, documentPackage2;

    public ProxyConfigurationExample(Properties props) {
        this(props.getProperty("api.key"),
                props.getProperty("api.url"));
    }

    public ProxyConfigurationExample( String apiKey, String apiUrl) {
        super( apiKey, apiUrl );
        email1 = getRandomEmail();
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

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

        eslClientWithHttpProxy = new EslClient(apiKey, apiUrl, allowAllSSLCertificates, httpProxyConfiguration);
        eslClientWithHttpProxyHasCredentials = new EslClient(apiKey, apiUrl, allowAllSSLCertificates, httpProxyWithCredentialsConfiguration);
        eslClientWithHttpsProxy = new EslClient(apiKey, apiUrl, allowAllSSLCertificates, httpsProxyConfiguration);
        eslClientWithHttpsProxyHasCredentials = new EslClient(apiKey, apiUrl, allowAllSSLCertificates, httpsProxyWithCredentialsConfiguration);
    }

    public void execute() {
        HttpProxyServer httpProxyServer = startHttpProxy(httpProxyPort);
        executeHttpProxy();
        documentPackage1 = eslClientWithHttpProxy.getPackage(packageId);
        httpProxyServer.stop();

        HttpProxyServer httpProxyWithCredentialsServer = startHttpProxyWithCredentials(httpProxyWithCredentialsPort, "httpUser", "httpPwd");
        executeHttpProxyWithCredentials();
        documentPackage2 = eslClientWithHttpProxyHasCredentials.getPackage(packageId);
        httpProxyWithCredentialsServer.stop();
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

    private HttpProxyServer startHttpProxy(int port) {
        final HttpProxyServer httpProxyServer = DefaultHttpProxyServer.bootstrap().
                withPort(port).start();
        return httpProxyServer;
    }

    private HttpProxyServer startHttpProxyWithCredentials(int port, final String acceptedUsername, final String acceptedPassword) {
        final HttpProxyServer httpProxyServer = DefaultHttpProxyServer.bootstrap().
                withPort(port).withProxyAuthenticator(new ProxyAuthenticator() {
                  @Override
                  public boolean authenticate(String s1, String s2) {
                      return acceptedUsername.equals(s1) && acceptedPassword.equals(s2);
                  }
              }).
              start();
        return httpProxyServer;
    }

    private void executeHttpProxy() {
        DocumentPackage packageTest = createTestPackage(documentInputStream1);
        packageId = eslClientWithHttpProxy.createPackage(packageTest);
        eslClientWithHttpProxy.sendPackage(packageId);
    }

    private void executeHttpProxyWithCredentials() {
        DocumentPackage packageTest = createTestPackage(documentInputStream2);
        packageId = eslClientWithHttpProxyHasCredentials.createPackage(packageTest);
        eslClientWithHttpProxyHasCredentials.sendPackage(packageId);
    }

}
