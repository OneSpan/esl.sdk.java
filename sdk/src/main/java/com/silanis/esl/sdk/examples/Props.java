package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.io.Streams;

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
        Object prop = System.getProperties().get( "properties.file" );
        System.out.println( ">>>" + prop.toString() );
        String filename = "signers.properties";
        if ( prop != null ) {
            filename = (String)prop;
        }
        return Props.get( filename );
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
            Streams.close( in );
        }

        instance = signers;
        return signers;
    }
}
