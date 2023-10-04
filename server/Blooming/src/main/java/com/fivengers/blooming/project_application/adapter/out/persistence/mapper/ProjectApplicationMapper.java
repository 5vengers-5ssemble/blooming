package com.fivengers.blooming.project_application.adapter.out.persistence.mapper;


import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import com.fivengers.blooming.project_application.adapter.out.persistence.entity.ProjectApplicationJpaEntity;
import com.fivengers.blooming.project_application.domain.ProjectApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectApplicationMapper {

    private final ProjectInfoMapper projectInfoMapper;
    private final BasicInfoMapper defaultInfoMapper;
    private final StoryInfoMapper storyInfoMapper;
    private final PolicyInfoMapper policyInfoMapper;
    private final SettlementInfoMapper settlementInfoMapper;
    private final MemberMapper memberMapper;

    public ProjectApplication toDomain(ProjectApplicationJpaEntity projectApplication) {

        return ProjectApplication.builder()
                .id(projectApplication.getId())
                .projectInfo(projectInfoMapper.toDomain(projectApplication.getProjectInfo()))
                .basicInfo(defaultInfoMapper.toDomain(projectApplication.getBasicInfo()))
                .storyInfo(storyInfoMapper.toDomain(projectApplication.getStoryInfo()))
                .policyInfo(policyInfoMapper.toDomain(projectApplication.getPolicyInfo()))
                .settlementInfo(settlementInfoMapper.toDomain(projectApplication.getSettlementInfo()))
                .state(projectApplication.getState())
                .member(memberMapper.toDomain(projectApplication.getMember()))
                .build();
    }

    public ProjectApplicationJpaEntity toJpaEntity(ProjectApplication projectApplication) {

        return ProjectApplicationJpaEntity.builder()
                .id(projectApplication.getId())
                .projectInfo(projectInfoMapper.toInJpaEntity(projectApplication.getProjectInfo()))
                .basicInfo(defaultInfoMapper.toInJpaEntity(projectApplication.getBasicInfo()))
                .storyInfo(storyInfoMapper.toInJpaEntity(projectApplication.getStoryInfo()))
                .policyInfo(policyInfoMapper.toInJpaEntity(projectApplication.getPolicyInfo()))
                .settlementInfo(settlementInfoMapper.toInJpaEntity(projectApplication.getSettlementInfo()))
                .state(projectApplication.getState())
                .member(memberMapper.toJpaEntity(projectApplication.getMember()))
                .build();
    }
}
