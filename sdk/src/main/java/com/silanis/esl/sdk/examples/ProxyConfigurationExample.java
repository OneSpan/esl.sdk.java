package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.ProxyConfiguration;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.ProxyAuthenticator;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

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
public class ProxyConfigurationExample extends SDKSample {

    private EslClient eslClientWithHttpProxy, eslClientWithHttpProxyHasCredentials;
    public DocumentPackage retrievedPackage1, retrievedPackage2;

    public static void main(String... args) {
        new ProxyConfigurationExample().run();
    }

    public ProxyConfigurationExample() {
        ProxyConfiguration httpProxyConfiguration = newProxyConfiguration().withHttpHost(proxyHost)
            .withHttpPort(proxyPort).build();

        ProxyConfiguration httpProxyWithCredentialsConfiguration = newProxyConfiguration()
            .withHttpHost(proxyWithCredentialsHost).withHttpPort(proxyWithCredentialsPort)
            .withCredentials(proxyUserName, proxyPassword).build();

        eslClientWithHttpProxy = setupEslClientFromProps(Collections.<String, String>emptyMap(), httpProxyConfiguration);
        eslClientWithHttpProxyHasCredentials = setupEslClientFromProps(Collections.<String, String>emptyMap(),
            httpProxyWithCredentialsConfiguration);
    }

    public void execute() {
        HttpProxyServer httpProxyServer = bootstrap().withPort(proxyPort).start();

        DocumentPackage package1 = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("signer1")
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document pdf")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        PackageId packageId1 = eslClientWithHttpProxy.createAndSendPackage(package1);
        eslClientWithHttpProxy.signDocuments(packageId1);
        eslClientWithHttpProxy.signDocuments(packageId1, "signer1");

        retrievedPackage1 = eslClientWithHttpProxy.getPackage(packageId1);
        httpProxyServer.stop();

        HttpProxyServer httpProxyWithCredentialsServer = startHttpProxyWithCredentials(proxyWithCredentialsPort);

        DocumentPackage package2 = newPackageNamed("ProxyConfigurationExample2 " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the eSignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("signer2")
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document pdf")
                        .fromStream(documentInputStream2, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        PackageId packageId2 = eslClientWithHttpProxyHasCredentials.createAndSendPackage(package2);
        eslClientWithHttpProxyHasCredentials.signDocuments(packageId2);
        eslClientWithHttpProxyHasCredentials.signDocuments(packageId2, "signer2");

        retrievedPackage2 = eslClientWithHttpProxyHasCredentials.getPackage(packageId2);
        httpProxyWithCredentialsServer.stop();
    }

    private HttpProxyServer startHttpProxyWithCredentials(int port) {
        return bootstrap().
                withPort(port).withProxyAuthenticator(new ProxyAuthenticator() {
            @Override
            public boolean authenticate(String s1, String s2) {
                return "httpUser".equals(s1) && "httpPwd".equals(s2);
            }

            @Override
            public String getRealm() {
                return null;
            }
        }).start();
    }
}
