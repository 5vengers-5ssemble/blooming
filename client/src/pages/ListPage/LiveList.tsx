import styled from 'styled-components';
import { useState } from 'react';
import SearchBar from '@components/Search/SearchBar';
import { MainTitle } from '@style/common';
import { LiveResultList } from '@components/ListPage/ResultList';
import { LIVE } from '@components/common/constant';
import SearchResultTitle from '@components/ListPage/SearchResultTitle';
import { Target } from './NFTList';
import useIntersect from '@hooks/IntersectionObserverHook';
import { getLiveData, getSearchData } from '@api/ListQuery/LiveQuery';
import Loading from '@components/Animation/Loading';
import Navbar from '@components/common/NavBar';
import { ListFrame } from './ConcertList';
import NoSearchResults from '@components/Search/NoSearchResults';

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

  const handleSearch = (data?: string, isBlankSearch?: boolean) => {
    setSearchKeyword(data ? data : keyword);
    setShowResult(!isBlankSearch);
  };

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
        )}
      </ListFrame>
    </div>
  );
};

const ResultFrame = styled.div`
  margin-top: 30px;
`;

const TopFrame = styled.div`
  margin-top: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export default LiveList;
