package com.fivengers.blooming.form.adapter.out.persistence.mapper;


import com.fivengers.blooming.form.adapter.out.persistence.entity.FundAddFormJpaEntity;
import com.fivengers.blooming.form.adapter.out.persistence.entity.PolicyInfoInForm;
import com.fivengers.blooming.form.adapter.out.persistence.entity.RepresentInfoInForm;
import com.fivengers.blooming.form.adapter.out.persistence.entity.StoryInfoInForm;
import com.fivengers.blooming.form.domain.FundAddForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FundAddFormMapper {

    private final ProjectInfoMapper projectInfoMapper;
    private final DefaultInfoMapper defaultInfoMapper;
    private final StoryInfoMapper storyInfoMapper;
    private final PolicyInfoMapper policyInfoMapper;
    private final RepresentInfoMapper representInfoMapper;

    public FundAddForm toDomain(FundAddFormJpaEntity fundAddForm) {

        return FundAddForm.builder()
                .projectInfo(projectInfoMapper.toDomain(fundAddForm.getProjectInfo()))
                .defaultInfo(defaultInfoMapper.toDomain(fundAddForm.getDefaultInfo()))
                .storyInfo(storyInfoMapper.toDomain(fundAddForm.getStoryInfo()))
                .policyInfo(policyInfoMapper.toDomain(fundAddForm.getPolicyInfo()))
                .representInfo(representInfoMapper.toDomain(fundAddForm.getRepresentInfo()))
                .build();
    }

    public FundAddFormJpaEntity toJpaEntity(FundAddForm fundAddForm) {

        return FundAddFormJpaEntity.builder()
                .projectInfo(projectInfoMapper.toInForm(fundAddForm.getProjectInfo()))
                .defaultInfo(defaultInfoMapper.toInForm(fundAddForm.getDefaultInfo()))
                .storyInfo(storyInfoMapper.toInForm(fundAddForm.getStoryInfo()))
                .policyInfo(policyInfoMapper.toInForm(fundAddForm.getPolicyInfo()))
                .representInfo(representInfoMapper.toInForm(fundAddForm.getRepresentInfo()))
                .build();
    }
}
