package com.infy.service;

import com.infy.dto.DesktopDTO;
import com.infy.dto.DesktopStatus;
import com.infy.dto.TraineeDTO;
import com.infy.entity.Desktop;
import com.infy.entity.Trainee;
import com.infy.exception.InfyTrainingException;
import com.infy.repository.DesktopRepository;
import com.infy.repository.TraineeRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("desktopAllocationService")
@Transactional
public class DesktopAllocationServiceImpl implements DesktopAllocationService {

    @Autowired
    private TraineeRespository traineeRespository;
    @Autowired
    private DesktopRepository desktopRepository;

    @Override
    public TraineeDTO getTraineeDetails(Integer traineeId) throws InfyTrainingException {
        Optional<Trainee> optionalTrainee = traineeRespository.findById(traineeId);
        Trainee trainee = optionalTrainee.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
        TraineeDTO traineeDTO = new TraineeDTO();
        traineeDTO.setTraineeId(trainee.getTraineeId());
        traineeDTO.setTraineeName(trainee.getTraineeName());
        DesktopDTO desktopDTO = new DesktopDTO();
        desktopDTO.setDesktopId(trainee.getDesktop().getDesktopId());
        desktopDTO.setDesktopMaker(trainee.getDesktop().getDesktopMaker());
        desktopDTO.setDesktopModel(trainee.getDesktop().getDesktopModel());
        desktopDTO.setDesktopStatus(trainee.getDesktop().getDesktopStatus());
        traineeDTO.setDesktopDTO(desktopDTO);
        return traineeDTO;
    }

    @Override
    public Integer addTrainee(TraineeDTO traineeDTO) throws InfyTrainingException {
//        Optional<Trainee> optionalTrainee = traineeRespository.findById(traineeDTO.getTraineeId());
//        if (optionalTrainee.isPresent()) {
//            throw new InfyTrainingException("Service.TRAINEE_NOT_FOUND");
//        }
        Trainee trainee = new Trainee();
        trainee.setTraineeId(traineeDTO.getTraineeId());
        trainee.setTraineeName(traineeDTO.getTraineeName());
//        Desktop desktop = null;
//        desktop.setDesktopId(traineeDTO.getDesktopDTO().getDesktopId());
//        desktop.setDesktopMaker(traineeDTO.getDesktopDTO().getDesktopMaker());
//        desktop.setDesktopModel(traineeDTO.getDesktopDTO().getDesktopModel());
//        desktop.setDesktopStatus(traineeDTO.getDesktopDTO().getDesktopStatus());
        trainee.setDesktop(null);
        traineeRespository.save(trainee);
        return trainee.getTraineeId();
    }

    @Override
    public void allocateDesktop(Integer traineeId, String desktopId) throws InfyTrainingException {
        Optional<Trainee> optionalTrainee = traineeRespository.findById(traineeId);
        Trainee trainee = optionalTrainee.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
        if (trainee.getDesktop() != null) {
            throw new InfyTrainingException("Service.TRAINEE_DESKTOP_FOUND");
        }
        Optional<Desktop> desktopOptional = desktopRepository.findById(desktopId);
        Desktop desktop = desktopOptional.orElseThrow(() -> new InfyTrainingException("Service.DESKTOP_NOT_FOUND"));
        if (desktop.getDesktopStatus() == DesktopStatus.ALLOCATED) {
            throw new InfyTrainingException("Service.DESKTOP_ALREADY_ALLOCATED");
        }
        desktop.setDesktopStatus(DesktopStatus.ALLOCATED);
        trainee.setDesktop(desktop);
        traineeRespository.save(trainee);
    }

    @Override
    public void deallocateDesktop(Integer traineeId) throws InfyTrainingException {
        Optional<Trainee> optionalTrainee = traineeRespository.findById(traineeId);
        Trainee trainee = optionalTrainee.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
        if (trainee.getDesktop().getDesktopId() == null) {
            throw new InfyTrainingException("Service.DESKTOP_NOT_ALLOCATED");
        }
        Optional<Desktop> desktopOptional = desktopRepository.findById(trainee.getDesktop().getDesktopId());
        Desktop desktop = desktopOptional.orElseThrow(() -> new InfyTrainingException("Service.DESKTOP_NOT_FOUND"));
        desktop.setDesktopStatus(DesktopStatus.AVAILABLE);
        trainee.setDesktop(null);
        traineeRespository.save(trainee);
        desktopRepository.save(desktop);
    }

    @Override
    public void deleteTrainee(Integer traineeId) throws InfyTrainingException {
        Optional<Trainee> optionalTrainee = traineeRespository.findById(traineeId);
        Trainee trainee = optionalTrainee.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
        if (trainee.getDesktop() != null) {
            Optional<Desktop> desktopOptional = desktopRepository.findById(trainee.getDesktop().getDesktopId());
            Desktop desktop = desktopOptional.orElseThrow(() -> new InfyTrainingException("Service.DESKTOP_NOT_FOUND"));
            desktop.setDesktopStatus(DesktopStatus.AVAILABLE);
            desktopRepository.save(desktop);
        }
        trainee.setDesktop(null);
        traineeRespository.deleteById(traineeId);
    }

    @Override
    public DesktopDTO getDesktopDetails(String desktopId) throws InfyTrainingException {
        Optional<Desktop> desktopOptional = desktopRepository.findById(desktopId);
        Desktop desktop = desktopOptional.orElseThrow(() -> new InfyTrainingException("Service.DESKTOP_NOT_FOUND"));
        DesktopDTO desktopDTO = new DesktopDTO();
        desktopDTO.setDesktopId(desktop.getDesktopId());
        desktopDTO.setDesktopStatus(desktop.getDesktopStatus());
        desktopDTO.setDesktopModel(desktop.getDesktopModel());
        desktopDTO.setDesktopMaker(desktop.getDesktopMaker());
        return desktopDTO;
    }
}
