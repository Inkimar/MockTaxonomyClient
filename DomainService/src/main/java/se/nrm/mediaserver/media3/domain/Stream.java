/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.media3.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author ingimar
 */
@MappedSuperclass
public abstract class Stream extends Media {

    @Column(name = "START_TIME") 
    private long startTime;

    @Column(name = "END_TIME")
    private long endTime; // lägg in condition, måste vara efter starttime

    public Stream() {
    }

    public Stream(String owner) {
        super(owner);
    }

    public Stream(String owner, String visibility, String filename, String mimetype) {
        super(owner, visibility, filename, mimetype);
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

}
