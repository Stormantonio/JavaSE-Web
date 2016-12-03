package com.work.webapp.model;

import com.sun.org.apache.regexp.internal.RE;

/**
 * com.urise.webapp.model.com.work.webapp.model.Resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    public Resume() {
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
