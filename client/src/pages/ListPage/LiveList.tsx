import styled from 'styled-components';
import { useState } from 'react';
import { useQuery } from 'react-query';

import { MainTitle } from '@style/common';

import SearchBar from '@components/Search/SearchBar';
import { LiveResultList } from '@components/ListPage/ResultList';
import { LIVE } from '@components/common/constant';
import SearchResultTitle from '@components/ListPage/SearchResultTitle';
import { Target } from './NFTList';
import Loading from '@components/Animation/Loading';
import Navbar from '@components/common/NavBar';
import { ListFrame } from './ConcertList';
import NoSearchResults from '@components/Search/NoSearchResults';
import TopLiveList from '@components/ListPage/TopLiveList';

import useIntersect from '@hooks/IntersectionObserverHook';
import { getLiveData, getSearchData } from '@api/ListQuery/LiveQuery';
import axios from '@api/apiController';

const fetchBestLive = async () => {
  try {
    const response = await axios.get('/lives/best');
    return response.data.results;
  } catch (error) {
    console.log(error);
    throw new Error('live 베스트 리스트 요청 실패');
  }
};

const LiveList = () => {
  const [keyword, setKeyword] = useState<string>('');
  const [showResult, setShowResult] = useState(false);
  const [searchKeyword, setSearchKeyword] = useState<string>('');
  const [searchByKeyword, setSearchByKeyword] = useState<boolean>(true);

  const scrollInfoForSearch = getSearchData({
    searchKeyword,
    isForArtistSearch: !searchByKeyword,
  });
  const scrollInfoForDefault = getLiveData();

  const refForSearch = useIntersect(async (entry, observer) => {
    observer.unobserve(entry.target);
    if (scrollInfoForSearch.hasNextPage && !scrollInfoForSearch.isFetching) {
      scrollInfoForSearch.fetchNextPage();
    }
  });
  const refForDefault = useIntersect(async (entry, observer) => {
    observer.unobserve(entry.target);
    if (scrollInfoForDefault.hasNextPage && !scrollInfoForDefault.isFetching) {
      scrollInfoForDefault.fetchNextPage();
    }
  });

  const { isLoading: isLoadingBest, data: bestLiveData } = useQuery(
    ['live-best'],
    fetchBestLive,
  );

  const handleSearch = (data?: string, isBlankSearch?: boolean) => {
    setSearchKeyword(data ? data : keyword);
    setShowResult(!isBlankSearch);
  };

  if (!bestLiveData || isLoadingBest) {
    return <Loading />;
  }

  return (
    <div>
      <Navbar activeIdx={3} />
      <ListFrame>
        <TopFrame>
          <MainTitle
            onClick={() => {
              setShowResult(false);
            }}
          >
            진행중인 라이브<div className="dot"></div>
          </MainTitle>
          <SearchBar
            nowStat={LIVE}
            keyword={keyword}
            setKeyword={setKeyword}
            onSearch={handleSearch}
            setSearchByKeyword={setSearchByKeyword}
          />
        </TopFrame>
        {showResult ? (
          <>
            <SearchResultTitle title={searchKeyword} />
            {!scrollInfoForSearch.isLoading &&
            scrollInfoForSearch.searchData.length === 0 ? (
              <>
                <NoSearchResults />
              </>
            ) : (
              <LiveResultList datas={scrollInfoForSearch.searchData} />
            )}

            {scrollInfoForSearch.isFetching &&
              scrollInfoForSearch.isLoading && <Loading />}
            <Target ref={refForSearch} />
          </>
        ) : (
          <>
            <TopLiveList nowStat={LIVE} bestData={bestLiveData} />
            <SubTitle>전체 라이브</SubTitle>
            <ResultFrame>
              {!scrollInfoForDefault.isLoading &&
              scrollInfoForDefault.searchData.length === 0 ? (
                <>
                  <NoSearchResults />
                </>
              ) : (
                <LiveResultList datas={scrollInfoForDefault.searchData} />
              )}
              {scrollInfoForDefault.isFetching &&
                scrollInfoForDefault.isLoading && <Loading />}
              <Target ref={refForDefault} />
            </ResultFrame>
          </>
        )}
      </ListFrame>
    </div>
  );
};

const ResultFrame = styled.div`
  margin-top: -10px;
`;

const TopFrame = styled.div`
  margin-top: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const SubTitle = styled.div`
  border-top: 1px solid var(--background2-color);
  margin-top: 30px;
  padding-top: 60px;
  font-size: 25px;
  font-weight: 700;
`;
export default LiveList;
