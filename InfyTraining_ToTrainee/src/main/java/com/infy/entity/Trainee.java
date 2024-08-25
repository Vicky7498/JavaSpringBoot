package com.infy.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Trainee {
    @Id
    @Column(name = "trainee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer traineeId;
    @Column(name = "trainee_name")
    private String traineeName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "desktopId")
    private Desktop desktop;

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

    public Desktop getDesktop() {
        return desktop;
    }

    public void setDesktop(Desktop desktop) {
        this.desktop = desktop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainee trainee)) return false;
        return Objects.equals(traineeId, trainee.traineeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(traineeId);
    }

    @Override
    public String toString() {
        return "Trainee{" + "traineeId=" + traineeId + ", traineeName='" + traineeName + '\'' + ", desktopDTO=" + desktop + '}';
    }
}
