package com.silanis.esl.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class VersionUtil {

    private static Logger logger = Logger.getLogger("VersionUtil");

    private static final String ESL_JAVA_SDK_VERSION = "version";

    public static String getVersion() {

        String sdkVersion = "";

        try{
            Properties prop = new Properties();
            InputStream probStream =  VersionUtil.class.getResourceAsStream("/version/version.properties");

            prop.load(probStream);
            sdkVersion = prop.getProperty(ESL_JAVA_SDK_VERSION);

        }catch (IOException ioEx){
            logger.warning("VersionUtil Exception : " + ioEx.getMessage());
        }

        logger.info("VersionUtil java getVersion() : " + sdkVersion);
        return sdkVersion;
    }
}