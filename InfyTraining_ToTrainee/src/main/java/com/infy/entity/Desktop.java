package com.infy.entity;

import com.infy.dto.DesktopStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Desktop {
    @Id
    @Column(name = "desktop_id")
    private String desktopId;
    @Column(name = "desktop_make")
    private String desktopMaker;
    @Column(name = "desktop_model")
    private String desktopModel;
    @Enumerated(EnumType.STRING)
    @Column(name = "desktop_status")
    private DesktopStatus desktopStatus;

    public String getDesktopId() {
        return desktopId;
    }

    public void setDesktopId(String desktopId) {
        this.desktopId = desktopId;
    }

    public String getDesktopMaker() {
        return desktopMaker;
    }

    public void setDesktopMaker(String desktopMaker) {
        this.desktopMaker = desktopMaker;
    }

    public String getDesktopModel() {
        return desktopModel;
    }

    public void setDesktopModel(String desktopModel) {
        this.desktopModel = desktopModel;
    }

    public DesktopStatus getDesktopStatus() {
        return desktopStatus;
    }

    public void setDesktopStatus(DesktopStatus desktopStatus) {
        this.desktopStatus = desktopStatus;
    }

    @Override
    public String toString() {
        return "Desktop{" + "desktopId='" + desktopId + '\'' + ", desktopMaker='" + desktopMaker + '\'' + ", desktopModel='" + desktopModel + '\'' + ", desktopStatus=" + desktopStatus + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Desktop desktop)) return false;
        return Objects.equals(desktopId, desktop.desktopId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(desktopId);
    }
}
