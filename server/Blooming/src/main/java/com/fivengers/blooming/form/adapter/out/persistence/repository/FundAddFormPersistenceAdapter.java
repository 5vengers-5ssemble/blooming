package com.fivengers.blooming.form.adapter.out.persistence.repository;


import com.fivengers.blooming.form.adapter.out.persistence.mapper.FundAddFormMapper;
import com.fivengers.blooming.form.application.port.out.FundAddFormPort;
import com.fivengers.blooming.form.domain.FundAddForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FundAddFormPersistenceAdapter implements FundAddFormPort {

    private final FundAddFormMapper fundAddFormMapper;
    private final FundAddFormSpringJpaRepository fundAddFormSpringJpaRepository;


    @Override
    public void save(FundAddForm form) {
        fundAddFormSpringJpaRepository.save(fundAddFormMapper.toJpaEntity(form));
    }
}
