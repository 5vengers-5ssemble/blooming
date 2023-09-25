package com.fivengers.blooming.form.application;


import com.fivengers.blooming.form.application.port.in.FundAddFormUseCase;
import com.fivengers.blooming.form.application.port.out.FundAddFormPort;
import com.fivengers.blooming.form.domain.FundAddForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundAddFormService implements FundAddFormUseCase {

    private final FundAddFormPort fundAddFormPort;

    @Override
    public void addFundAddForm(FundAddForm form) {
        fundAddFormPort.save(form);
    }
}
