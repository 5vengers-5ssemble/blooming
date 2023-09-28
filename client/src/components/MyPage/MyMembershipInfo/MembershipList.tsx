import React, { useState } from 'react';
import styled from 'styled-components';
import { useQuery } from 'react-query';
import axiosTemp from '@api/apiControllerTemp';
import { NFTProcessApplication } from '@type/ApplicationList';
import Loading from '@components/Animation/Loading';
import NoSearchResults from '@components/Search/NoSearchResults';

const MembershipList = () => {
  const [activeTab, setActiveTab] = useState('inProgress'); // 현재 활성 탭 상태

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
      case 'inProgress':
        return '/application-nft-inprogress';
      case 'admit':
        return '/application-nft-admit';
      case 'reject':
        return '/application-nft-reject';
      default:
        throw new Error(`Invalid tab: ${tab}`);
    }
  }

  // 데이터를 가져오는 함수
  async function fetchMembershipData(): Promise<NFTProcessApplication[]> {
    const response = await axiosTemp.get(apiEndpoint);
    console.log(response.data);
    return response.data.applicationList;
  }

  return (
    <div>
      {/* 탭 메뉴 */}
      <TabMenu>
        <TabItem
          onClick={() => setActiveTab('inProgress')}
          isActive={activeTab === 'inProgress'}
        >
          승인 대기중
        </TabItem>
        <TabItem
          onClick={() => setActiveTab('admit')}
          isActive={activeTab === 'admit'}
        >
          승인됨
        </TabItem>
        <TabItem
          onClick={() => setActiveTab('reject')}
          isActive={activeTab === 'reject'}
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
      ) : (
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
      )}
    </div>
  );
};

const TabMenu = styled.div`
  display: flex;
  gap: 12px;
`;

const TabItem = styled.div<{ isActive: boolean }>`
  padding: 8px 16px;
  cursor: pointer;
  background-color: ${(props) =>
    props.isActive ? 'var(--main1-color)' : 'var(--white-color)'};
  color: ${(props) =>
    props.isActive ? 'var(--white-color)' : 'var(--black-color)'};
  border: 2px solid
    ${(props) =>
      props.isActive ? 'var(--white-color)' : 'var(--background2-color)'};
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
