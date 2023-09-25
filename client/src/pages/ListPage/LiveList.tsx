import styled from 'styled-components';
import { useState } from 'react';

import SearchBar from '@components/Search/SearchBar';
import { MainTitle } from '@style/common';
import { LiveResultList } from '@components/ListPage/ResultList';
import { LIVE } from '@components/common/constant';
import SearchResultTitle from '@components/ListPage/SearchResultTitle';
import { Target } from './NFTList';
import useIntersect from '@hooks/IntersectionObserverHook';
import { getSearchData } from '@api/ListQuery/LiveQuery';
import Loading from '@components/Animation/Loading';
import Navbar from '@components/common/NavBar';

const LiveList = () => {
  const [keyword, setKeyword] = useState<string>('');
  const [showResult, setShowResult] = useState(false);
  const [searchKeyword, setSearchKeyword] = useState<string>('');
  const [isForArtistSearch, setForArtistSearch] = useState<boolean>(false);

  const scrollInfoForSearch = getSearchData({
    searchKeyword,
    isForArtistSearch,
  });
  const scrollInfoForDefault = getSearchData({ searchKeyword: '' });

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

  const handleSearch = (data?: string, isArtistSearch?: boolean) => {
    setSearchKeyword(data ? data : keyword);
    setShowResult(true);
    if (isArtistSearch) {
      //아티스트로 검색
      console.log('artist');
      setForArtistSearch(true);
    } else {
      //콘서트나 활동명으로 검색
      console.log('concert or activity');
    }
  };

  return (
    <div>
      <Navbar />
      <TopFrame>
        <MainTitle>
          진행중인 라이브<div className="dot"></div>
        </MainTitle>
        <SearchBar
          nowStat={LIVE}
          keyword={keyword}
          setKeyword={setKeyword}
          onSearch={handleSearch}
        />
      </TopFrame>
      {showResult ? (
        <>
          <SearchResultTitle title={searchKeyword} />
          <LiveResultList datas={scrollInfoForSearch.searchData} />
          {scrollInfoForSearch.isFetching && scrollInfoForSearch.isLoading && (
            <Loading />
          )}
          <Target ref={refForSearch} />
        </>
      ) : (
        <ResultFrame>
          <LiveResultList datas={scrollInfoForDefault.searchData} />
          {scrollInfoForDefault.isFetching &&
            scrollInfoForDefault.isLoading && <Loading />}
          <Target ref={refForDefault} />
        </ResultFrame>
      )}
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
