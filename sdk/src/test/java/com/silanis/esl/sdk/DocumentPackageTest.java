package com.silanis.esl.sdk;

import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by lena on 2014-05-06.
 */
public class DocumentPackageTest {

    public static final String lowerCaseEmail1 = "email1@email.com";
    public static final String lowerCaseEmail2 = "email2@email.com";
    public static final String upperCaseEmail1 = "EmaIL1@email.com";
    public static final String upperCaseEmail2 = "eMAIL2@Email.com";

    @Test
    public void addTypicalSigner() {
        Signer signer1 = SignerBuilder.newSignerWithEmail(lowerCaseEmail1)
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .withSigner(signer1)
                .build();

        Signer newSigner = SignerBuilder.newSignerWithEmail(lowerCaseEmail2)
                .withFirstName("Patty")
                .withLastName("Galant")
                .build();
        documentPackage.addSigner(newSigner);

        assertThat("Document package should have 2 signers.", documentPackage.getSigners().size(), is(2));
        assertThat("Document package is missing signer1.", documentPackage.getSigners().get(0), is(signer1));
        assertThat("Document package did not add the new signer.", documentPackage.getSigners().get(1), is(newSigner));
    }

    @Test
    public void addDuplicateSigner() {
        Signer signer1 = SignerBuilder.newSignerWithEmail(lowerCaseEmail1)
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .withSigner(signer1)
                .build();

        try {
            documentPackage.addSigner(SignerBuilder.newSignerWithEmail(upperCaseEmail1)
                    .withFirstName("Patty")
                    .withLastName("Galant")
                    .build());
            fail("No exception thrown");
        } catch (EslException e) {
            assertThat("Wrong exception thrown", e.getMessage(), is("Another signer with same email or another placeholder with same id already exists."));
        }
        assertThat("Document package should not add duplicate signers.", documentPackage.getSigners().size(), is(1));
        assertThat("Document package is missing signer1", documentPackage.getSigners().get(0), is(signer1));
    }

    @Test
    public void addSignerWithUpperCaseEmail() {
        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .build();

        Signer signer1 = new Signer(lowerCaseEmail1, "John", "Smith", new Authentication(AuthenticationMethod.EMAIL));
        Signer signer2 = SignerBuilder.newSignerWithEmail(upperCaseEmail2)
                .withFirstName("Patty")
                .withLastName("Galant")
                .build();
        documentPackage.addSigner(signer1);
        documentPackage.addSigner(signer2);

        assertThat("Document package should have 2 signers", documentPackage.getSigners().size(), is(2));
        assertThat("Document package should have the signer1's email in lower case.", documentPackage.getSigners().get(0), is(signer1));
        assertThat("Document package should have the signer2's email in lower case.", documentPackage.getSigners().get(1), is(signer2));
    }

    @Test
    public void removeTypicalSigner() {
        Signer signer1 = SignerBuilder.newSignerWithEmail(lowerCaseEmail1)
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .withSigner(signer1)
                .build();

        documentPackage.removeSigner(signer1);

        assertThat("Document package should have 0 signers.", documentPackage.getSigners().size(), is(0));
    }

    @Test
    public void removeAbsentSigner() {
        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .build();

        try {
            documentPackage.removeSigner(SignerBuilder.newSignerWithEmail(lowerCaseEmail1)
                    .withFirstName("John")
                    .withLastName("Smith")
                    .build());
            fail("No exception thrown");
        } catch (EslException e) {
            assertThat("Wrong exception thrown", e.getMessage(), is("Signer does not exist."));
        }
        assertThat("Document package should have 0 signers.", documentPackage.getSigners().size(), is(0));
    }

    @Test
    public void removeSignerWithUpperCaseEmail() {
        Signer signer1 = SignerBuilder.newSignerWithEmail(upperCaseEmail1)
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .withSigner(signer1)
                .build();

        documentPackage.removeSigner(SignerBuilder.newSignerWithEmail(lowerCaseEmail1)
                .withFirstName("John")
                .withLastName("Smith")
                .build());

        assertThat("Document package should have 0 signers.", documentPackage.getSigners().size(), is(0));
    }

    @Test
    public void addPlaceholder(){
        Signer placeholder1 = SignerBuilder.newSignerPlaceholder(new Placeholder("placeholderId1"))
                            .build();

        Signer placeholder2 = SignerBuilder.newSignerPlaceholder(new Placeholder("placeholderId2"))
                .build();

        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .withSigner(placeholder1)
                .withSigner(placeholder2)
                .build();

        assertThat("Document package should have 2 placeholders.", documentPackage.getPlaceholders().size(), is(2));
        assertThat("Document package is missing placeholder1", documentPackage.getPlaceholders().contains(placeholder1));
        assertThat("Document package is missing placeholder2", documentPackage.getPlaceholders().contains(placeholder2));
    }

    @Test
    public void addDuplicatePlaceholder(){
        Signer placeholder1 = SignerBuilder.newSignerPlaceholder(new Placeholder("placeholderId1"))
                .build();

        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .withSigner(placeholder1)
                .build();

        try {
            documentPackage.addSigner(SignerBuilder.newSignerPlaceholder(new Placeholder("placeholderId1"))
                                                   .build());
            fail("No exception thrown");
        } catch (EslException e) {
            assertThat("Wrong exception thrown", e.getMessage(), is("Another signer with same email or another placeholder with same id already exists."));
        }
        assertThat("Document package should have 1 placeholder.", documentPackage.getPlaceholders().size(), is(1));
        assertThat("Document package is missing placeholder1", documentPackage.getPlaceholders().contains(placeholder1));
    }

    @Test
    public void removePlaceholder(){
        Signer placeholder = SignerBuilder.newSignerPlaceholder(new Placeholder("placeholderId1"))
                .build();

        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .withSigner(placeholder)
                .build();

        documentPackage.removePlaceholder(placeholder);

        assertThat("Document package should have 1 placeholder.", documentPackage.getPlaceholders().size(), is(0));
        assertThat("Document package is missing placeholder1", documentPackage.getPlaceholders().contains(placeholder), is(false));
    }


    @Test(expected = EslException.class)
    public void removeAbsentPlaceholder(){
        Signer placeholder = SignerBuilder.newSignerPlaceholder(new Placeholder("absent"))
                .build();

        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test")
                .build();

        documentPackage.removePlaceholder(placeholder);
    }
}
