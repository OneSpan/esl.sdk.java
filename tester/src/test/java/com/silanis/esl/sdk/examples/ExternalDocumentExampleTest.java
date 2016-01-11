package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Ignore;
import org.junit.Test;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by chi-wing on 7/8/14.
 */
public class ExternalDocumentExampleTest {

    private FakeFtpServer fakeFtpServer;
    private final String PATH = "/test/completed/files/";
    private final String HOME_DIRECTORY = "/home/test";

    @Ignore("This example needs special configurations. Please contact us for more information")
    @Test
    public void verifyResult() {
        startFTPProvider();

        ExternalDocumentExample example = new ExternalDocumentExample(Props.get());
        example.run();
        DocumentPackage documentPackage = example.getRetrievedPackage();

        stopFTPProvider();

        // FTP document
        Document ftpDocument = documentPackage.getDocument("FTP Document");
        assertThat("The external ftp document was not added correctly", ftpDocument, notNullValue());
        assertThat("The external ftp document name was not set correctly", ftpDocument.getName(), is("FTP Document"));
    }

    private void startFTPProvider() {
        fakeFtpServer = new FakeFtpServer();
        fakeFtpServer.setServerControlPort(7864);

        fakeFtpServer.addUserAccount(new UserAccount("test", "test", HOME_DIRECTORY));

        FileSystem fileSystem = new UnixFakeFileSystem();
        final FileEntry fileEntry = new FileEntry(HOME_DIRECTORY + PATH + "test");

        byte[] documentContent = new byte[0];

        fileEntry.setContents(documentContent);

        fileSystem.add(fileEntry);

        fakeFtpServer.setFileSystem(fileSystem);
        fakeFtpServer.start();
    }

    private void stopFTPProvider() {
        fakeFtpServer.stop();
    }
}
