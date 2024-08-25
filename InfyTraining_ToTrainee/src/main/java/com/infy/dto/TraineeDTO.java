package com.infy.dto;

public class TraineeDTO {
    private Integer traineeId;
    private String traineeName;
    private DesktopDTO desktopDTO;

    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer traineeId) {
        this.traineeId = traineeId;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public DesktopDTO getDesktopDTO() {
        return desktopDTO;
    }

    public void setDesktopDTO(DesktopDTO desktopDTO) {
        this.desktopDTO = desktopDTO;
    }

    @Override
    public String toString() {
        return "TraineeDTO{" + "traineeId=" + traineeId + ", traineeName='" + traineeName + '\'' + ", desktopDTO=" + desktopDTO + '}';
    }
}
