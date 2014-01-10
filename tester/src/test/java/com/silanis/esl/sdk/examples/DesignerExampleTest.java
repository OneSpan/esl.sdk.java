package com.silanis.esl.sdk.examples;

import org.junit.Test;

public class DesignerExampleTest {
    @Test
    public void verifyResult() {
        DesignerExample designerExample = new DesignerExample( Props.get() );
        designerExample.run();
    }
}
