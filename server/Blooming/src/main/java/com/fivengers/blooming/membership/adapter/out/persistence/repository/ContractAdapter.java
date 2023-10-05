package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import static com.fivengers.blooming.config.contract.KIP17BloomingToken.ABI_JSON;
import static com.fivengers.blooming.config.contract.KIP17BloomingToken.BYTE_CODE;

import com.fivengers.blooming.membership.application.port.out.ContractPort;
import com.fivengers.blooming.membership.application.port.out.dto.ContractDeployRequest;
import com.klaytn.caver.Caver;
import com.klaytn.caver.contract.Contract;
import com.klaytn.caver.contract.ContractDeployParams;
import com.klaytn.caver.contract.SendOptions;
import com.klaytn.caver.methods.response.Bytes32;
import com.klaytn.caver.transaction.AbstractTransaction;
import com.klaytn.caver.transaction.response.PollingTransactionReceiptProcessor;
import com.klaytn.caver.transaction.response.TransactionReceiptProcessor;
import com.klaytn.caver.wallet.keyring.KeyringFactory;
import com.klaytn.caver.wallet.keyring.SingleKeyring;
import java.math.BigInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContractAdapter implements ContractPort {

    private final Caver caver;
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(50_000_000);
    public static final BigInteger DEPLOY_VALUE = BigInteger.ZERO;
    public static final BigInteger KLAY = BigInteger.valueOf(1_000_000_000_000_000_000L);
    public static final BigInteger ANTI_BOT_INTERVAL = BigInteger.TEN;  // 봇 독점 방지 (minting interval)
    public static final BigInteger MINT_LIMIT_PER_BLOCK = BigInteger.ONE;   // 한 번의 트랜잭션에서 최대로 민팅할 수 있는 양
    public static final BigInteger MINT_LIMIT_PER_SALE = BigInteger.ONE;   // 한 계정 당 가질 수 있는 NFT 개수 제한
    public static final BigInteger MINT_START_BLOCK_NUMBER = BigInteger.ZERO;   // 민팅을 시작할 수 있는 블록 높이
    public static final BigInteger MINT_INDEX_FOR_SALE = BigInteger.ONE;    // 발행할 NFT 토큰 번호의 시작 index

    @Override
    public String deploy(ContractDeployRequest request) throws Exception {

        SingleKeyring deployer = KeyringFactory.createFromPrivateKey(request.privateKey());

        ContractDeployParams params = generateDeployParams(request.title(), request.symbol());
        SendOptions options = generateSendOptions(deployer.getAddress());

        Contract deployedContract = createContract(deployer).deploy(params, options);
        deployedContract.setDefaultSendOptions(options);

        log.info(deployedContract.getContractAddress());
        log.info(request.baseUri());

        printContractMethods();

        executeTransaction("setBaseURI", deployedContract, request.baseUri());
        executeTransaction("reveal", deployedContract, true);
        executeTransaction("setupSale", deployedContract,
                ANTI_BOT_INTERVAL,
                MINT_LIMIT_PER_BLOCK,
                MINT_LIMIT_PER_SALE,
                MINT_START_BLOCK_NUMBER,
                MINT_INDEX_FOR_SALE,
                request.saleCount(),    // 최대 몇 개의 NFT를 발행할 지
                BigInteger.valueOf(request.salePrice()).multiply(KLAY));    // NFT 하나의 가격

        return deployedContract.getContractAddress();
    }

    @Override
    public Boolean isMinter(SingleKeyring sender, String contractAddress) throws Exception {

        Contract contract = createContract(sender, contractAddress);

        return (Boolean) contract.call("isMinter", sender.getAddress())
                .get(0)
                .getValue();
    }

    private void executeTransaction(String method, Contract contract, Object... args) throws Exception {
        AbstractTransaction signedTx = contract.sign(method, args);
        Bytes32 txHash = caver.rpc.klay.sendRawTransaction(signedTx).send();
        TransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(caver, 1000, 15);
        receiptProcessor.waitForTransactionReceipt(txHash.getResult());
    }

    private void executeCall(String method, Contract contract, Object... args) throws Exception {
        AbstractTransaction signedTx = contract.sign(method, args);
        Bytes32 txHash = caver.rpc.klay.sendRawTransaction(signedTx).send();
        TransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(caver, 1000, 15);
        receiptProcessor.waitForTransactionReceipt(txHash.getResult());
    }

    private boolean isExistsKey(String address) {
        return caver.wallet.isExisted(address);
    }

    private Contract createContract() throws Exception {

        return caver.contract.create(ABI_JSON);
    }

    private Contract createContract(SingleKeyring keyring) throws Exception {

        if (!caver.wallet.isExisted(keyring.getAddress())) {
            caver.wallet.add(keyring);
        }
        return caver.contract.create(ABI_JSON);
    }

    private Contract createContract(SingleKeyring keyring, String contractAddress) throws Exception {

        if (!caver.wallet.isExisted(keyring.getAddress())) {
            caver.wallet.add(keyring);
        }
        return caver.contract.create(ABI_JSON, contractAddress);
    }

    private SendOptions generateSendOptions(String address) {

        return new SendOptions(address, GAS_LIMIT, DEPLOY_VALUE);
    }

    private ContractDeployParams generateDeployParams(String name, String symbol) {

        return new ContractDeployParams(BYTE_CODE, name, symbol);
    }

    private void printContractMethods() throws Exception {

        Contract contract = createContract();
        contract.getMethods().forEach((name, method) -> {
            log.info("type: {} | name: {}", method.getType(), name);
        });
    }
}
