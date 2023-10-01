package com.fivengers.blooming.payment.adapter.out.persistence.mapper;

import com.fivengers.blooming.global.exception.project.ProjectNotFoundException;
import com.fivengers.blooming.payment.domain.ProjectType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProjectTypeConverter implements AttributeConverter<ProjectType, String> {

    @Override
    public String convertToDatabaseColumn(ProjectType projectType) {
        return projectType == null ? null : projectType.getValue();
    }

    @Override
    public ProjectType convertToEntityAttribute(String value) {
        if(value == null) return null;
        for(ProjectType projectType : ProjectType.values()){
            if(projectType.getValue().equals(value)){
                return projectType;
            }
        }

        throw new ProjectNotFoundException();
    }
}
