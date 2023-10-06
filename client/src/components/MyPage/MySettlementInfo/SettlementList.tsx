import React, { useState } from 'react';
import styled from 'styled-components';
import { useQuery } from 'react-query';
import { FundingProcessApplication } from '@type/ApplicationList';

import Loading from '@components/Animation/Loading';
import NoSearchResults from '@components/Search/NoSearchResults';
import SettlementModal from './SettlementModal';
import { APPROVE, INPROGRESS } from '@components/common/constant';
import { settle_admit, settle_prepare } from './SettlementData';

const SettlementList = () => {
  const [activeTab, setActiveTab] = useState(INPROGRESS); // 현재 활성 탭 상태

  // API 엔드포인트와 쿼리 키 설정
  const apiEndpoint = getApiEndpointByTab(activeTab); // activeTab에 따라 엔드포인트 설정
  const queryKey = ['settlementList', activeTab];

  const [isProcess, setProcess] = useState<boolean>(false);

  const [selectedFunding, setSelectedFunding] =
    useState<FundingProcessApplication | null>(null);

  // React Query를 사용하여 데이터 가져오기
  const { data, isLoading, isError } = useQuery<
    FundingProcessApplication[],
    Error
  >(
    queryKey,
    fetchFundingData, // 데이터를 가져오는 함수
  );

  // API 엔드포인트를 탭에 따라 설정하는 함수
  function getApiEndpointByTab(tab: string): string {
    switch (tab) {
      case INPROGRESS:
        return '/application-settle-inprogress';
      case APPROVE:
        return '/application-settle-admit';
      default:
        throw new Error(`Invalid tab: ${tab}`);
    }
  }

  // 데이터를 가져오는 함수
  async function fetchFundingData(): Promise<FundingProcessApplication[]> {
    // const response = await axiosTemp.get(apiEndpoint);
    // console.log(response.data);
    if (activeTab === INPROGRESS) {
      return settle_prepare;
    }
    return settle_admit;
  }

  return (
    <div>
      <TabMenu>
        <TabItem
          onClick={() => setActiveTab(INPROGRESS)}
          $isActive={activeTab === INPROGRESS}
        >
          정산 대기중
        </TabItem>
        <TabItem
          onClick={() => setActiveTab(APPROVE)}
          $isActive={activeTab === APPROVE}
        >
          정산 완료
        </TabItem>
      </TabMenu>

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
          {data?.map((funding, idx) => (
            <EachResultData key={idx}>
              <ThumbnailImg>
                <img src={funding.thumbnailUrl} />
              </ThumbnailImg>
              <TextInfo>
                <div className="title">{funding.title}</div>
                <div className="info">
                  {funding.startDate.split('T')[0].toString()} ~{' '}
                  {funding.endDate.split('T')[0].toString()}
                </div>
                {activeTab === INPROGRESS && (
                  <SettlementButton
                    $isProcess={isProcess}
                    onClick={() => {
                      if (!isProcess) {
                        setSelectedFunding(funding);
                      }
                    }}
                  >
                    정산정보 입력
                  </SettlementButton>
                )}
              </TextInfo>
            </EachResultData>
          ))}
        </ResultDataFrame>
      )}
      {selectedFunding && (
        <SettlementModal
          funding={selectedFunding}
          onClose={() => {
            setProcess(true);
            setSelectedFunding(null);
          }}
        />
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
  height: fit-content;
  margin-bottom: 20px;
  padding: 20px;
  border: 1px solid var(--main3-color);
  border-radius: 6px;
`;

const ThumbnailImg = styled.div`
  img {
    width: 100px;
    height: fit-content;
    object-fit: cover;
    border-radius: 6px;
  }
`;

const TextInfo = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
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

const SettlementButton = styled.div<{ $isProcess: boolean }>`
  cursor: ${(props) => (props.$isProcess ? 'not-allowed' : 'pointer')};
  width: fit-content;
  margin-left: auto;
  margin-top: 20px;
  padding: 8px 16px;
  background-color: var(--background2-color);
  color: ${(props) =>
    props.$isProcess ? 'var(--white-color)' : 'var(--main1-color)'};
  text-align: center;
  font-weight: 600;
  border-radius: 4px;

  &:hover {
    background-color: ${(props) =>
      props.$isProcess === false && 'var(--main1-color)'};
    color: var(--white-color);
    font-weight: 500;
  }
`;
export default SettlementList;
