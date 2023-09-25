package com.fivengers.blooming.form.adapter.in.web;


import com.fivengers.blooming.form.application.port.in.FundAddFormUseCase;
import com.fivengers.blooming.form.application.port.in.dto.FundAddFormRequest;
import com.fivengers.blooming.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FundAddFormController {

    private final FundAddFormUseCase fundAddFormUseCase;

    @PostMapping("/forms")
    public ApiResponse fundFormAdd(@RequestBody FundAddFormRequest request) {

        fundAddFormUseCase.addFundAddForm(request.toDomain());
        return ApiResponse.ok().build();
    }

}
