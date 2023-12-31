import React, { useState } from 'react';
import styled from 'styled-components';
import { useQuery } from 'react-query';
import axios from '@api/apiController';
import { NFTProcessApplication } from '@type/ApplicationList';
import Loading from '@components/Animation/Loading';
import NoSearchResults from '@components/Search/NoSearchResults';
import { APPROVE, INPROGRESS, REJECT } from '@components/common/constant';

const MembershipList = () => {
  const [activeTab, setActiveTab] = useState(INPROGRESS); // 현재 활성 탭 상태

  // API 엔드포인트와 쿼리 키 설정
  const apiEndpoint = getApiEndpointByTab(activeTab); // activeTab에 따라 엔드포인트 설정
  const queryKey = ['membershipList', activeTab];

  // React Query를 사용하여 데이터 가져오기
  const { data, isLoading, isError } = useQuery<NFTProcessApplication[], Error>(
    queryKey,
    fetchMembershipData, // 데이터를 가져오는 함수
  );

  // API 엔드포인트를 탭에 따라 설정하는 함수
  function getApiEndpointByTab(tab: string): string {
    switch (tab) {
      case INPROGRESS:
        return '/membership-applications/me?state=APPLY';
      case APPROVE:
        return '/membership-applications/me?state=APPROVAL';
      case REJECT:
        return '/membership-applications/me?state=RETURN';
      default:
        throw new Error(`Invalid tab: ${tab}`);
    }
  }

  // 데이터를 가져오는 함수
  async function fetchMembershipData(): Promise<NFTProcessApplication[]> {
    try {
      const response = await axios.get(apiEndpoint);
      console.log(response.data);

      if (response.status === 404) {
        console.error('404 에러: 데이터를 찾을 수 없습니다.');
        return [];
      }

      return response.data.applicationList;
    } catch (error) {
      console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
      return [];
    }
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
        <ResultDataFrame>
          {data?.map((nft, idx) => (
            <EachResultData key={idx}>
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
