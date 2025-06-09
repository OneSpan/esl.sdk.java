package com.silanis.esl.sdk;

import java.util.*;

public class Notification {

    private Set<NotificationMethod> primaryMethods;
    private String phone;

    public Notification(){
        this.primaryMethods = new HashSet<>();
        primaryMethods.add(NotificationMethod.EMAIL);
    }

    public Notification(NotificationMethod... methods){
        this.primaryMethods = new HashSet<>();
        this.setNotificationMethods(methods);
    }

    public Notification(Set<NotificationMethod> methods, String phone) {
        this.phone = phone;
        if (methods != null){
            this.primaryMethods = new HashSet<>();
            this.setNotificationMethods(methods.toArray(new NotificationMethod[0]));
        }
        else{
            this.primaryMethods = null;
        }
    }

    public Notification(String phone, NotificationMethod... methods) {
        this.phone = phone;
        this.setNotificationMethods(methods);
    }

    public Set<NotificationMethod> getMethods() {
        return primaryMethods;
    }

    public void setNotificationMethods(NotificationMethod... methods) {
        this.primaryMethods.clear();
        this.primaryMethods.add(NotificationMethod.EMAIL);
        this.addNotificationMethods(methods);
    }

    public void addNotificationMethods(NotificationMethod... methods) {
        Arrays.stream(methods)
                .peek(this::validateSMSRequirements)
                .forEach(this.primaryMethods::add);
    }

    private void validateSMSRequirements(NotificationMethod method) {
        if (method == NotificationMethod.SMS &&
                (phone == null || phone.trim().isEmpty())){
            throw new IllegalStateException("Phone number must be set before enabling SMS notifications");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}