package com.example.project_hex_one.service;


import com.example.project_hex_one.dto.LabelDto;
import com.example.project_hex_one.exception.LabelNotFoundException;
import com.example.project_hex_one.model.Label;
import com.example.project_hex_one.repository.LabelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class LabelService {

    private LabelRepository labelRepository;

    public List<Label> getAllLabel() {
        return labelRepository.findAll();
    }

    public Label getLabelById(long id) {
        return labelRepository.findById(id).orElseThrow(() -> new LabelNotFoundException("Label not found"));
    }

    public Label createLabel(LabelDto labelDto) {
        Label label = new Label();
        label.setName(labelDto.getName());
        return labelRepository.save(label);
    }

    public Label updateLabelById(long id, LabelDto labelDtoUpdated) {
        Label labelToBeUpdated = labelRepository.findById(id).get();
        labelToBeUpdated.setName(labelDtoUpdated.getName());
        return labelRepository.save(labelToBeUpdated);
    }

    public void deleteLabelById(long id) {
        labelRepository.deleteById(id);
    }
}

