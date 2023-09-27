import { ProfitInfo, SettlementInfo } from '@type/MyPage';
import styled from 'styled-components';
import { ReactComponent as MoneySvg } from '@assets/icons/coins.svg';
import { ReactComponent as WalletSvg } from '@assets/icons/wallet.svg';

interface Props {
  profitInfo: ProfitInfo | undefined;
  settleInfo: SettlementInfo | undefined;
}

const MoneyInfo = ({ profitInfo, settleInfo }: Props) => {
  return (
    <MyProfitInfoFrame>
      <ProfitFrame>
        <div className="title">
          <MoneySvg />내 수익
        </div>
        <ProfitBox>
          <EachBox>
            <div className="price">
              <span>{profitInfo?.totalProfit}</span> 원
            </div>
            <div className="eachtitle">총 수익</div>
          </EachBox>
          <EachBox>
            <div className="price">
              <span>{profitInfo?.investForMonth}</span> 원
            </div>
            <div className="eachtitle">이번 달 투자 금액</div>
          </EachBox>
          <EachBox>
            <div className="price">
              <span>{profitInfo?.totalInvest}</span> 원
            </div>
            <div className="eachtitle">총 투자 금액</div>
          </EachBox>
        </ProfitBox>
      </ProfitFrame>
      <SettleFrame>
        <div className="title">
          <WalletSvg />
          정산 정보
        </div>
        <ProfitBox>
          <EachBox>
            <div className="price">
              <span>{settleInfo?.settlementCompleteCnt}</span> 건
            </div>
            <div className="eachtitle">정산 완료</div>
          </EachBox>
          <EachBox>
            <div className="price">
              <span>
                {settleInfo &&
                  settleInfo.totalFundingCnt - settleInfo.settlementCompleteCnt}
              </span>
              건
            </div>
            <div className="eachtitle">정산 예정</div>
          </EachBox>
        </ProfitBox>
      </SettleFrame>
    </MyProfitInfoFrame>
  );
};

const MyProfitInfoFrame = styled.div`
  display: flex;
  margin-top: 35px;
  gap: 40px;
  .title {
    display: flex;
    align-items: center;
    gap: 5px;
    font-size: 18px;
    font-weight: 700;
  }
`;

const ProfitFrame = styled.div`
  width: 55%;
`;

const SettleFrame = styled.div`
  width: 35%;
`;

const EachBox = styled.div`
  display: flex;
  flex: 1;
  flex-direction: column;
  gap: 10px;
  align-items: center;
  justify-content: center;
  font-weight: 500;
  border-right: 1px solid var(--gray-color);

  &:last-child {
    border-right: none;
  }

  > .price span {
    font-size: 22px;
    font-weight: 600;
  }

  > .eachtitle {
    font-size: 14px;
    font-weight: 600;
  }
`;

const ProfitBox = styled.div`
  display: flex;
  align-items: center;
  margin-top: 15px;
  height: 100px;
  border-radius: 6px;
  background-color: var(--background2-color);
`;

export default MoneyInfo;
