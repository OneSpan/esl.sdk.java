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
/*    public final String proxyURL;
    public final int proxyPort;
    public final String proxyUserName;
    public final String proxyPassword;
    public final String proxyScheme;*/

    protected EslClient eslClientWithProxy;
    protected PackageId packageId;

    public final String email1;
    private InputStream documentInputStream1;

    private final int ProxyPort = 10000;

    private final String ApiKey;
    private final String ApiURL;
    EslClient eslClient;

    public static void main(String... args) {
        new ProxyConfigurationExample(Props.get()).execute();
    }

    public ProxyConfigurationExample(Properties props) {
        this(props.getProperty("api.key"), props.getProperty("api.url"), props.getProperty("1.email"));
    }

    public ProxyConfigurationExample(String apiKey, String apiUrl, String email1) {

        ProxyConfiguration proxyConfiguration = ProxyConfigurationBuilder.newProxyConfiguration()
                .withHttpHost("http://localhost")
                .withHttpPort(ProxyPort)
                .withCredentials("abc", "123")
                .build();
        eslClient = new EslClient(apiKey, apiUrl);
        eslClientWithProxy = new EslClient(apiKey, apiUrl, proxyConfiguration);

        this.ApiKey = apiKey;
        this.ApiURL = apiUrl;
        this.email1 = UUID.randomUUID().toString().replace("-","") + "@e-signlive.com";
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    public void execute() {

        DocumentPackage packageTest = newPackageNamed("ProxyExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
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

/*        HttpProxyServer proxyServer = DefaultHttpProxyServer.bootstrap()
                        .withPort(ProxyPort)
                        .start();*/

        //packageId = eslClientWithProxy.createPackage(packageTest);
        //eslClientWithProxy.sendPackage(packageId);

        packageId = eslClient.createPackage(packageTest);
        eslClient.sendPackage(packageId);

/*        proxyServer.stop();*/
    }
}
