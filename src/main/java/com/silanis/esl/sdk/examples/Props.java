package com.silanis.esl.sdk.examples;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    public static Properties get() {
        Properties signers = new Properties();
        InputStream in = null;

        try {
            in = Props.class.getClassLoader().getResourceAsStream( "signers.properties" );
            if (in == null) throw new IOException( "Could not find signers.properties" );
            signers.load( in );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        return signers;
    }
}
