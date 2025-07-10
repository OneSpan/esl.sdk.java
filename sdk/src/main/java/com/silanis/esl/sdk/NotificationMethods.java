package com.silanis.esl.sdk;

import java.util.*;

public class NotificationMethods {

    private final Set<NotificationMethod> primary;
    private String phone;

    public NotificationMethods(){
        this.primary = new HashSet<>();
        primary.add(NotificationMethod.EMAIL);
    }

    public NotificationMethods(Set<NotificationMethod> methods, String phone) {
        this.phone = phone;
        if (methods != null){
            this.primary = new HashSet<>();
            this.setPrimaryMethods(methods.toArray(new NotificationMethod[0]));
        }
        else{
            this.primary = null;
        }
    }

    public Set<NotificationMethod> getPrimary() {
        return primary;
    }

    public void setPrimaryMethods(NotificationMethod... methods) {
        this.primary.clear();
        this.primary.add(NotificationMethod.EMAIL);
        this.addPrimaryMethods(methods);
    }

    public void addPrimaryMethods(NotificationMethod... methods) {
        Arrays.stream(methods)
                .peek(this::validateSMSRequirements)
                .forEach(this.primary::add);
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