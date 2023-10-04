import React, { useState } from 'react';
import styled from 'styled-components';
import { useQuery } from 'react-query';
import Loading from '@components/Animation/Loading';
import NoSearchResults from '@components/Search/NoSearchResults';
import {
  APPROVE,
  INPROGRESS,
  REJECT,
  STATE_APPROVAL,
  STATE_RETURN,
} from '@components/common/constant';
import { MembershipAdmit } from '@type/AdminAdmit';
import DetailModal from './DetailModal';

import axios from '@api/apiController';

const MembershipList = () => {
  const [activeTab, setActiveTab] = useState(INPROGRESS); // 현재 활성 탭 상태
  const [selectedNftData, setSelectedNftData] =
    useState<MembershipAdmit | null>(null);

  const handleNftClick = (nftData: MembershipAdmit) => {
    if (activeTab === INPROGRESS) {
      setSelectedNftData(nftData);
    }
  };

  const handleModalClose = () => {
    setSelectedNftData(null);
  };

  const handleApprove = async () => {
    const response = await axios.put(
      `/admin/membership-applications/${selectedNftData?.id}/states`,
      {
        applicationState: STATE_APPROVAL,
      },
    );

    if (response) {
      handleModalClose();
    } else {
      console.error('승인처리실패');
    }
  };

  const handleReject = async () => {
    const response = await axios.put(
      `/admin/membership-applications/${selectedNftData?.id}/states`,
      {
        applicationState: STATE_RETURN,
      },
    );

    if (response) {
      handleModalClose();
    } else {
      console.error('거절처리실패');
    }
  };

  // API 엔드포인트와 쿼리 키 설정
  const apiEndpoint = getApiEndpointByTab(activeTab); // activeTab에 따라 엔드포인트 설정
  const queryKey = ['membershipList', activeTab];

  // React Query를 사용하여 데이터 가져오기
  const { data, isLoading, isError } = useQuery<MembershipAdmit[], Error>(
    queryKey,
    fetchMembershipData,
  );

  function getApiEndpointByTab(tab: string): string {
    switch (tab) {
      //추후 변경
      case INPROGRESS:
        return '/admin/membership-applications?state=APPLY';
      case APPROVE:
        return '/admin/membership-applications?state=APPROVAL';
      case REJECT:
        return '/admin/membership-applications?state=RETURN';
      default:
        throw new Error(`Invalid tab: ${tab}`);
    }
  }

  // 데이터를 가져오는 함수
  async function fetchMembershipData(): Promise<MembershipAdmit[]> {
    const response = await axios.get(apiEndpoint);
    console.log(response.data);
    return response.data.results.content;
  }

  return (
    <div>
      {/* 탭 메뉴 */}
      <TabMenu>
        <TabItem
          onClick={() => setActiveTab(INPROGRESS)}
          $isActive={activeTab === INPROGRESS}
        >
          승인 대기중
        </TabItem>
        <TabItem
          onClick={() => setActiveTab(APPROVE)}
          $isActive={activeTab === APPROVE}
        >
          승인됨
        </TabItem>
        <TabItem
          onClick={() => setActiveTab(REJECT)}
          $isActive={activeTab === REJECT}
        >
          승인거부
        </TabItem>
      </TabMenu>

      {/* 데이터 표시 */}
      {isLoading ? (
        <div>
          <Loading />
        </div>
      ) : isError ? (
        <div>
          <NoSearchResults />
        </div>
      ) : data && data.length > 0 ? (
        <>
          <ResultDataFrame>
            {data?.map((nft, idx) => (
              <EachResultData key={idx} onClick={() => handleNftClick(nft)}>
                <ThumbnailImg>
                  <img src={nft.thumbnailUrl} />
                </ThumbnailImg>
                <TextInfo>
                  <div className="title">{nft.title}</div>
                  <div className="info">
                    {nft.seasonStart.split('T')[0].toString()} ~{' '}
                    {nft.seasonEnd.split('T')[0].toString()}
                  </div>
                </TextInfo>
              </EachResultData>
            ))}
          </ResultDataFrame>
          <DetailModal
            isOpen={!!selectedNftData}
            onClose={handleModalClose}
            nftData={selectedNftData || ({} as MembershipAdmit)}
            onApprove={handleApprove}
            onReject={handleReject}
          />
        </>
      ) : (
        <>
          <NoSearchResults />
        </>
      )}
    </div>
  );
};

const TabMenu = styled.div`
  display: flex;
  gap: 12px;
`;

const TabItem = styled.div<{ $isActive: boolean }>`
  padding: 8px 16px;
  cursor: pointer;
  background-color: ${(props) =>
    props.$isActive ? 'var(--main1-color)' : 'var(--white-color)'};
  color: ${(props) =>
    props.$isActive ? 'var(--white-color)' : 'var(--black-color)'};
  border: 2px solid
    ${(props) =>
      props.$isActive ? 'var(--white-color)' : 'var(--background2-color)'};
  border-radius: 4px;
`;

const ResultDataFrame = styled.div`
  margin-top: 30px;
  display: flex;
  /* flex-direction: column; */
  justify-content: space-between;
  flex-wrap: wrap;
`;

const EachResultData = styled.div`
  cursor: pointer;
  display: flex;
  align-items: center;
  /* justify-content: space-between; */
  gap: 30px;
  width: 500px;
  height: 100px;
  margin-bottom: 20px;
  padding: 20px;
  border: 1px solid var(--main3-color);
  border-radius: 6px;
`;

const ThumbnailImg = styled.div`
  img {
    width: 100px;
    height: 100px;
    object-fit: cover;
    border-radius: 6px;
  }
`;

const TextInfo = styled.div`
  display: flex;
  flex-direction: column;

  .title {
    font-weight: 600;
    font-size: 18px;
  }
  .info {
    margin-top: 20px;
    font-weight: 400;
    font-size: 14px;
  }
`;
export default MembershipList;
