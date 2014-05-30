package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lena on 2014-05-29.
 */
public class SenderCompletionReport {
    private List<PackageCompletionReport> packages = new ArrayList<PackageCompletionReport>();
    private Sender sender;

    public List<PackageCompletionReport> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageCompletionReport> packages) {
        this.packages = packages;
    }

    public void addPackage(PackageCompletionReport aPackage) {
        this.packages.add(aPackage);
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
