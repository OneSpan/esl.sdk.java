package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.ProxyAuthenticator;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.ProxyConfigurationBuilder.newProxyConfiguration;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;
import static org.littleshoot.proxy.impl.DefaultHttpProxyServer.bootstrap;

/**
 * Created by whou on 08/12/14.
 */
public class ProxyConfigurationExample extends SDKSample{

    private final String httpProxyURL = "localhost";
    private final int httpProxyPort = 8001;

    private final String httpProxyWithCredentialsURL = "localhost";
    private final int httpProxyWithCredentialsPort = 8002;
    private final String httpProxyUserName = "httpUser";
    private final String httpProxyPassword = "httpPwd";

    private boolean allowAllSSLCertificates = false;

    private EslClient eslClientWithHttpProxy, eslClientWithHttpProxyHasCredentials;
    private InputStream documentInputStream1, documentInputStream2;
    private ProxyConfiguration httpProxyConfiguration, httpProxyWithCredentialsConfiguration;
    public DocumentPackage retrievedPackage1, retrievedPackage2;

    private String email1;

    public ProxyConfigurationExample(Properties props) {
        this(props.getProperty("api.key"),
                props.getProperty("api.url"));
    }

    public ProxyConfigurationExample( String apiKey, String apiUrl) {
        super( apiKey, apiUrl );
        email1 = getRandomEmail();
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        httpProxyConfiguration = newProxyConfiguration()
                                    .withHttpHost(httpProxyURL)
                                    .withHttpPort(httpProxyPort)
                                    .build();

        httpProxyWithCredentialsConfiguration = newProxyConfiguration()
                                    .withHttpHost(httpProxyWithCredentialsURL)
                                    .withHttpPort(httpProxyWithCredentialsPort)
                                    .withCredentials(httpProxyUserName, httpProxyPassword)
                                    .build();

        eslClientWithHttpProxy = new EslClient(apiKey, apiUrl, allowAllSSLCertificates, httpProxyConfiguration);
        eslClientWithHttpProxyHasCredentials = new EslClient(apiKey, apiUrl, allowAllSSLCertificates, httpProxyWithCredentialsConfiguration);
    }

    public void execute() {
        HttpProxyServer httpProxyServer = bootstrap().withPort(httpProxyPort).start();

        DocumentPackage package1 = newPackageNamed("ProxyConfigurationExample1 " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John")
                                    .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document pdf")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();

        PackageId packageId1 = eslClientWithHttpProxy.createAndSendPackage(package1);

        retrievedPackage1 = eslClientWithHttpProxy.getPackage(packageId1);
        httpProxyServer.stop();

        HttpProxyServer httpProxyWithCredentialsServer = startHttpProxyWithCredentials(httpProxyWithCredentialsPort, "httpUser", "httpPwd");

        DocumentPackage package2 = newPackageNamed("ProxyConfigurationExample2 " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John")
                                    .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document pdf")
                                      .fromStream(documentInputStream2, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();

        PackageId packageId2 = eslClientWithHttpProxyHasCredentials.createAndSendPackage(package2);

        retrievedPackage2 = eslClientWithHttpProxyHasCredentials.getPackage(packageId2);
        httpProxyWithCredentialsServer.stop();
    }

    private HttpProxyServer startHttpProxyWithCredentials(int port, final String acceptedUsername, final String acceptedPassword) {
        final HttpProxyServer httpProxyServer = bootstrap().
                withPort(port).withProxyAuthenticator(new ProxyAuthenticator() {
                  @Override
                  public boolean authenticate(String s1, String s2) {
                      return acceptedUsername.equals(s1) && acceptedPassword.equals(s2);
                  }
              }).
              start();
        return httpProxyServer;
    }
}
