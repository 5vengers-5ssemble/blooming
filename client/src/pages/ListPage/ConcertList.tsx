import styled from 'styled-components';
import { useQuery } from 'react-query';
import { useState } from 'react';
import axios from '@api/apiController';

import SearchBar from '@components/Search/SearchBar';
import { MainTitle } from '@style/common';
import TopRankList from '@components/ListPage/TopRankList';
import ResultList from '@components/ListPage/ResultList';
import {
  CONCERT,
  FUNDING_PHRASES,
  POPULAR,
  RECENTLY,
} from '@components/common/constant';
import { ProcessInfo } from '@type/ProcessInfo';
import Loading from '@components/Animation/Loading';
import SearchResultTitle from '@components/ListPage/SearchResultTitle';
import {
  LeftSection,
  NowToggle,
  RightSection,
  SortOption,
  Target,
} from './NFTList';
import ToggleButton from '@components/Button/ToggleButton';
import useIntersect from '@hooks/IntersectionObserverHook';
import { getConcertData, getSearchData } from '@api/ListQuery/ConcertQuery';

const ConcertList = () => {
  const [keyword, setKeyword] = useState<string>('');
  const [showResult, setShowResult] = useState(false);
  const [searchKeyword, setSearchKeyword] = useState<string>('');
  const [isToggled, setIsToggled] = useState(true);
  const [selectedSort, setSelectedSort] = useState<string>(POPULAR);

  const scrollInfoForSearch = getSearchData({ searchKeyword });
  const scrollInfoForDefault = getConcertData({
    sort: selectedSort,
    ongoing: false,
    // isToggled 로 바꿀것
  });

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

  const { isLoading: isLoadingBest, data: bestConcertData } = useQuery<
    ProcessInfo[]
  >(['concert-best'], fetchBestConcert);

  const handleSearch = (data?: string, isArtistSearch?: boolean) => {
    setSearchKeyword(data ? data : keyword);
    setShowResult(true);
    if (isArtistSearch === undefined) {
      //아티스트 NFT 검색
      console.log('i am nft');
    } else {
      if (isArtistSearch) {
        //아티스트로 검색
        console.log('artist');
      } else {
        //콘서트나 활동명으로 검색
        console.log('concert or activity');
      }
    }
  };

  const handleToggleChange = (checked: boolean) => {
    setIsToggled(checked);
    scrollInfoForDefault.remove();
  };

  const handleSortChange = (sort: string) => {
    setSelectedSort(sort);
    scrollInfoForDefault.remove();
  };

  if (!bestConcertData || isLoadingBest) {
    return <Loading />;
  }

  return (
    <div>
      <TopFrame>
        <MainTitle>
          콘서트<div className="dot"></div>
        </MainTitle>
        <SearchBar
          nowStat={CONCERT}
          keyword={keyword}
          setKeyword={setKeyword}
          onSearch={handleSearch}
        />
      </TopFrame>
      {showResult ? (
        <>
          <SearchResultTitle title={searchKeyword} />
          <ResultList
            datas={scrollInfoForSearch.searchData}
            nowStat={CONCERT}
          />
          {scrollInfoForSearch.isFetching && scrollInfoForSearch.isLoading && (
            <Loading />
          )}
          <Target ref={refForSearch} />
        </>
      ) : (
        <>
          <TopRankList nowStat={CONCERT} bestData={bestConcertData} />
          <NowToggle>
            <LeftSection>
              <div className="toggleTitle">
                모집중인 {FUNDING_PHRASES.name}만 보기
              </div>
              <ToggleButton
                defaultChecked={isToggled}
                onChange={handleToggleChange}
              />
            </LeftSection>
            <RightSection>
              <SortOption
                onClick={() => handleSortChange(POPULAR)}
                isSelected={selectedSort === POPULAR}
              >
                인기순
              </SortOption>
              |
              <SortOption
                onClick={() => handleSortChange(RECENTLY)}
                isSelected={selectedSort === RECENTLY}
              >
                최신순
              </SortOption>
            </RightSection>
          </NowToggle>
          <ResultList
            datas={scrollInfoForDefault.searchData}
            nowStat={CONCERT}
          />
          {scrollInfoForDefault.isFetching &&
            scrollInfoForDefault.isLoading && <Loading />}
          <Target ref={refForDefault} />
        </>
      )}
    </div>
  );
};

const fetchBestConcert = async () => {
  try {
    const response = await axios.get('/concert-best');
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error('콘서트 베스트 리스트 요청 실패');
  }
};

const TopFrame = styled.div`
  margin-top: 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export default ConcertList;
