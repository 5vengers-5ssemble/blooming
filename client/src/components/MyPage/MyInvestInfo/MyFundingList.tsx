import styled from 'styled-components';
import { useState } from 'react';
import { MySettleFundingInfo, MyUnSettleFundingInfo } from '@type/MyPage';
import { ReactComponent as ArrowSvg } from '@assets/icons/arrow-right.svg';
import {
  FundingSettleListElement,
  FundingUnSettleListElement,
} from './ListElement';

interface Props {
  nowSettleFundingInfo: MySettleFundingInfo[] | undefined;
  nowUnSettleFundingInfo: MyUnSettleFundingInfo[] | undefined;
}

const MyFundingList = ({
  nowSettleFundingInfo,
  nowUnSettleFundingInfo,
}: Props) => {
  const [isModalOpen, setModalOpen] = useState(false);
  const [selectedFunding, setSelectedFunding] = useState<
    MySettleFundingInfo[] | MyUnSettleFundingInfo[] | null
  >();

  const openModal = (
    funding: MySettleFundingInfo[] | MyUnSettleFundingInfo[],
  ) => {
    setSelectedFunding(funding);
    setModalOpen(true);
  };

  const closeModal = () => {
    setSelectedFunding(null);
    setModalOpen(false);
  };

  const handleModalContainerClick = (e: React.MouseEvent) => {
    if (e.target === e.currentTarget) {
      closeModal();
    }
  };

  if (!nowSettleFundingInfo || !nowUnSettleFundingInfo) {
    return <></>;
  }

  const renderFundingList = () => {
    if (selectedFunding) {
      if (Array.isArray(selectedFunding) && selectedFunding.length > 0) {
        if ('profitRate' in selectedFunding[0]) {
          return selectedFunding.map((funding, idx) => (
            <FundingSettleListElement
              funding={funding as MySettleFundingInfo}
              key={idx}
            />
          ));
        } else {
          return selectedFunding.map((funding, idx) => (
            <FundingUnSettleListElement
              funding={funding as MyUnSettleFundingInfo}
              key={idx}
            />
          ));
        }
      } else {
        return <div>No funding data available.</div>;
      }
    } else {
      return null;
    }
  };

  return (
    <>
      <ResultEachFrame>
        <div className="title">
          <div className="text">정산 완료된 펀딩</div>
          <div
            className="moreInfo"
            onClick={() => openModal(nowSettleFundingInfo)}
          >
            더보기 <ArrowSvg />
          </div>
        </div>
        {nowSettleFundingInfo.length === 0 && <>아직 펀딩한 내역이 없어요</>}
        {nowSettleFundingInfo?.slice(0, 3).map((funding, idx) => (
          <FundingSettleListElement funding={funding} key={idx} />
        ))}
      </ResultEachFrame>
      <ResultEachFrame>
        <div className="title">
          <div className="text">진행 중인 펀딩</div>
          <div
            className="moreInfo"
            onClick={() => openModal(nowUnSettleFundingInfo)}
          >
            더보기 <ArrowSvg />
          </div>
        </div>
        {nowUnSettleFundingInfo.length === 0 && <>아직 펀딩한 내역이 없어요</>}
        {nowUnSettleFundingInfo?.slice(0, 3).map((funding, idx) => (
          <FundingUnSettleListElement funding={funding} key={idx} />
        ))}
      </ResultEachFrame>
      {isModalOpen && selectedFunding && (
        <ModalContainer onClick={handleModalContainerClick}>
          <ModalContent>{renderFundingList()}</ModalContent>
        </ModalContainer>
      )}
    </>
  );
};
const ResultEachFrame = styled.div`
  flex: 1;
  gap: 15px;
  display: flex;
  flex-direction: column;

  .title {
    font-weight: 700;
    font-size: 20px;
    margin-bottom: 10px;
    display: flex;
    justify-content: space-between;

    .moreInfo {
      cursor: pointer;
      display: flex;
      align-items: center;
      gap: 2px;
      color: var(--main1-color);
      font-weight: 600;
      font-size: 14px;
      svg {
        width: 14px;
        height: 14px;
      }
    }
  }
`;

const ModalContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
`;

const ModalContent = styled.div`
  background-color: white;
  padding: 50px 20px;
  border-radius: 6px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
  max-width: 80%;
  width: 50dvh;
  height: 60dvh;
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  gap: 25px;

  &::-webkit-scrollbar {
    width: 5px;
    height: 100vh;
    border-radius: 6px;
  }

  &::-webkit-scrollbar-thumb {
    height: 15px;
    background-color: var(--main2-color);
  }
  &::-webkit-scrollbar-track {
    background-color: var(--gray-color);
    border-radius: 6px;
  }

  button {
    background-color: var(--main1-color);
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-weight: 600;
    font-size: 16px;
  }
`;
export default MyFundingList;
