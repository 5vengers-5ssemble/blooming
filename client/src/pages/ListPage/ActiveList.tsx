import styled from 'styled-components';
import { useQuery } from 'react-query';
import { useState } from 'react';
import axios from '@api/apiController';
import { getActiveData, getSearchData } from '@api/ListQuery/ActiveQuery';
import { MainTitle } from '@style/common';
import { ProcessInfo } from '@type/ProcessInfo';
import useIntersect from '@hooks/IntersectionObserverHook';

import SearchBar from '@components/Search/SearchBar';
import TopRankList from '@components/ListPage/TopRankList';
import { ResultList } from '@components/ListPage/ResultList';
import {
  ACTIVE,
  FUNDING_PHRASES,
  POPULAR,
  RECENTLY,
} from '@components/common/constant';
import SearchResultTitle from '@components/ListPage/SearchResultTitle';
import {
  LeftSection,
  NowToggle,
  RightSection,
  SortOption,
  Target,
} from './NFTList';
import ToggleButton from '@components/Button/ToggleButton';
import Loading from '@components/Animation/Loading';
import Navbar from '@components/common/NavBar';
import { ListFrame } from './ConcertList';
import NoSearchResults from '@components/Search/NoSearchResults';

const ActiveList = () => {
  const [keyword, setKeyword] = useState<string>('');
  const [showResult, setShowResult] = useState(false);
  const [searchKeyword, setSearchKeyword] = useState<string>('');
  const [isToggled, setIsToggled] = useState(true);
  const [selectedSort, setSelectedSort] = useState<string>(POPULAR);
  const [searchByKeyword, setSearchByKeyword] = useState<boolean>(true);

  const scrollInfoForSearch = getSearchData({ searchKeyword, searchByKeyword });
  const scrollInfoForDefault = getActiveData({
    sort: selectedSort,
    ongoing: isToggled,
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

  const { data: bestActiveData } = useQuery<ProcessInfo[]>(
    ['active-best'],
    fetchBestConcert,
  );

  const handleSearch = (data?: string, isBlankSearch?: boolean) => {
    setSearchKeyword(data ? data : keyword);
    setShowResult(!isBlankSearch);
  };

  const handleToggleChange = (checked: boolean) => {
    setIsToggled(checked);
    scrollInfoForDefault.remove();
  };

  const handleSortChange = (sort: string) => {
    setSelectedSort(sort);
    scrollInfoForDefault.remove();
  };

  if (!bestActiveData) {
    return <></>;
  }

  return (
    <div>
      <Navbar activeIdx={2} />
      <ListFrame>
        <TopFrame>
          <MainTitle
            onClick={() => {
              setShowResult(false);
            }}
          >
            활동<div className="dot"></div>
          </MainTitle>
          <SearchBar
            nowStat={ACTIVE}
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
              <ResultList
                datas={scrollInfoForSearch.searchData}
                nowStat={ACTIVE}
              />
            )}

            {scrollInfoForSearch.isFetching &&
              scrollInfoForSearch.isLoading && <Loading />}
            <Target ref={refForSearch} />
          </>
        ) : (
          <>
            <TopRankList bestData={bestActiveData} nowStat={ACTIVE} />
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
            {!scrollInfoForDefault.isLoading &&
            scrollInfoForDefault.searchData.length === 0 ? (
              <>
                <NoSearchResults />
              </>
            ) : (
              <ResultList
                datas={scrollInfoForDefault.searchData}
                nowStat={ACTIVE}
              />
            )}

            {scrollInfoForDefault.isFetching &&
              scrollInfoForDefault.isLoading && <Loading />}
            <Target ref={refForDefault} />
          </>
        )}
      </ListFrame>
    </div>
  );
};
const fetchBestConcert = async () => {
  try {
    const response = await axios.get('/activities/best');
    return response.data.results;
  } catch (error) {
    console.log(error);
    throw new Error('활동 베스트 리스트 요청 실패');
  }
};
const TopFrame = styled.div`
  margin-top: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export default ActiveList;
