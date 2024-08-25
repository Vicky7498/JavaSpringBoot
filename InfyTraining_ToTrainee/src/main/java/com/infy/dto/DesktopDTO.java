package com.infy.dto;

public class DesktopDTO {
    private String desktopId;
    private String desktopMaker;
    private String desktopModel;
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
        return "DesktopDTO{" + "desktopId='" + desktopId + '\'' + ", desktopMaker='" + desktopMaker + '\'' + ", desktopModel='" + desktopModel + '\'' + ", desktopStatus=" + desktopStatus + '}';
    }
}
