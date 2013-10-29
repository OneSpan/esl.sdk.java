package com.silanis.esl.sdk.examples;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.silanis.esl.sdk.io.Streams.close;

/**
 * Helper class to open the properties file
 */
public class Props {

    private static Properties instance  = null;

    public static Properties get() {
        return Props.get( "signers.properties" );
    }
    synchronized public static Properties get(String filename) {
        if ( instance != null )
            return instance;

        Properties signers = new Properties();
        InputStream in = null;

        try {
            in = Props.class.getClassLoader().getResourceAsStream( filename );
            if (in == null) throw new IOException( "Could not find properties file: " + filename );
            signers.load( in );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(in);
        }

        instance = signers;
        return signers;
    }
}
