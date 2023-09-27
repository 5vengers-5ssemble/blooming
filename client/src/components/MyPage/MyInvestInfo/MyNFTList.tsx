import styled from 'styled-components';
import { useState } from 'react';
import { ReactComponent as ArrowSvg } from '@assets/icons/arrow-right.svg';
import { MyNftInfo } from '@type/MyPage';
import { NFTListElement } from './ListElement';

interface Props {
  nowNftInfo: MyNftInfo[] | undefined;
  doneNftInfo: MyNftInfo[] | undefined;
}
const MyNFTList = ({ nowNftInfo, doneNftInfo }: Props) => {
  const [isModalOpen, setModalOpen] = useState(false);
  const [selectedNft, setSelectedNft] = useState<MyNftInfo[] | null>(null);

  const openModal = (nft: MyNftInfo[]) => {
    setSelectedNft(nft);
    setModalOpen(true);
  };

  const closeModal = () => {
    setSelectedNft(null);
    setModalOpen(false);
  };

  const handleModalContainerClick = (e: React.MouseEvent) => {
    if (e.target === e.currentTarget) {
      closeModal();
    }
  };

  if (!nowNftInfo || !doneNftInfo) {
    return <></>;
  }
  return (
    <>
      <ResultEachFrame>
        <div className="title">
          <div className="text">현재 진행중인 멤버쉽</div>
          <div className="moreInfo" onClick={() => openModal(doneNftInfo)}>
            더보기 <ArrowSvg />
          </div>
        </div>
        {doneNftInfo.length === 0 && <>아직 구입한 내역이 없어요</>}
        {doneNftInfo?.slice(0, 3).map((nft, idx) => (
          <NFTListElement nft={nft} key={idx} />
        ))}
      </ResultEachFrame>
      <ResultEachFrame>
        <div className="title">
          <div className="text">진행 종료된 멤버쉽</div>
          <div className="moreInfo" onClick={() => openModal(nowNftInfo)}>
            더보기 <ArrowSvg />
          </div>
        </div>
        {nowNftInfo.length === 0 && <>아직 구입한 내역이 없어요</>}
        {nowNftInfo?.slice(0, 3).map((nft, idx) => (
          <NFTListElement nft={nft} key={idx} />
        ))}
      </ResultEachFrame>
      {isModalOpen && selectedNft && (
        <ModalContainer onClick={handleModalContainerClick}>
          <ModalContent>
            {selectedNft?.map((nft, idx) => (
              <NFTListElement nft={nft} key={idx} />
            ))}
          </ModalContent>
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
export default MyNFTList;
