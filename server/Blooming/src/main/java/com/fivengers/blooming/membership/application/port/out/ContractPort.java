package com.fivengers.blooming.membership.application.port.out;

import com.fivengers.blooming.membership.application.port.out.dto.ContractDeployRequest;
import com.klaytn.caver.wallet.keyring.SingleKeyring;

public interface ContractPort {

    String deploy(ContractDeployRequest request) throws Exception;

    Boolean isMinter(SingleKeyring sender, String contractAddress) throws Exception;
}
