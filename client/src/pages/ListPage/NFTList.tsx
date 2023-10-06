import styled from 'styled-components';
import { useQuery } from 'react-query';

import axios from '@api/apiController';
import SearchBar from '@components/Search/SearchBar';
import { MainTitle } from '@style/common';
import TopRankList from '@components/ListPage/TopRankList';
import { NFTResultList, ResultList } from '@components/ListPage/ResultList';
import {
  ARTIST,
  FUNDING_PHRASES,
  NFT_PHRASES,
  POPULAR,
  RECENTLY,
} from '@components/common/constant';
import { useMemo, useState } from 'react';
import Loading from '@components/Animation/Loading';
import SearchResultTitle from '@components/ListPage/SearchResultTitle';
import ToggleButton from '@components/Button/ToggleButton';
import { getArtistData, getSearchData } from '@api/ListQuery/ArtistQuery';
import useIntersect from '@hooks/IntersectionObserverHook';
import Navbar from '@components/common/NavBar';
import { ListFrame } from './ConcertList';
import NoSearchResults from '@components/Search/NoSearchResults';
import TopNFTList from '@components/ListPage/TopNFTList';

const NFTList = () => {
  const [keyword, setKeyword] = useState<string>('');
  const [showResult, setShowResult] = useState(false);
  const [searchKeyword, setSearchKeyword] = useState<string>('');

  const [isToggled, setIsToggled] = useState(true);
  const [selectedSort, setSelectedSort] = useState<string>(POPULAR);
  const [searchByKeyword, setSearchByKeyword] = useState<boolean>(false);

  const { data: bestArtistData } = useArtistBestQuery();

  const scrollInfoForSearch = getSearchData({ searchKeyword, searchByKeyword });
  const scrollInfoForDefault = getArtistData({
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

  if (!bestArtistData) {
    return <Loading />;
  }

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

  return (
    <div>
      <Navbar activeIdx={0} />
      <ListFrame>
        <TopFrame>
          <MainTitle
            onClick={() => {
              setShowResult(false);
            }}
          >
            NFT<div className="dot"></div>
          </MainTitle>
          <SearchBar
            nowStat={ARTIST}
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
              <NFTResultList
                datas={scrollInfoForSearch.searchData}
                nowStat={ARTIST}
              />
            )}

            {scrollInfoForSearch.isFetching &&
              scrollInfoForSearch.isLoading && <Loading />}
            <Target ref={refForSearch} />
          </>
        ) : (
          <>
            <TopNFTList bestData={bestArtistData} nowStat={ARTIST} />
            <NowToggle>
              <LeftSection>
                <div className="toggleTitle">
                  모집중인 {ARTIST ? NFT_PHRASES.name : FUNDING_PHRASES.name}만
                  보기
                </div>
                <ToggleButton
                  defaultChecked={isToggled}
                  onChange={handleToggleChange}
                />
              </LeftSection>
              <RightSection>
                <SortOption
                  onClick={() => handleSortChange(POPULAR)}
                  $isSelected={selectedSort === POPULAR}
                >
                  인기순
                </SortOption>
                |
                <SortOption
                  onClick={() => handleSortChange(RECENTLY)}
                  $isSelected={selectedSort === RECENTLY}
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
              <NFTResultList
                datas={scrollInfoForDefault.searchData}
                nowStat={ARTIST}
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

const fetchBestArtist = async () => {
  try {
    const response = await axios.get('/memberships/best');
    return response.data.results;
  } catch (error) {
    console.log(error);
    throw new Error('아티스트 베스트 리스트 요청 실패');
  }
};

const useArtistBestQuery = () => {
  return useQuery(['artist-best'], () => fetchBestArtist(), {});
};

const TopFrame = styled.div`
  margin-top: 80px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const LeftSection = styled.div`
  display: flex;
  gap: 10px;
  align-items: center;
`;
export const RightSection = styled.div`
  display: flex;
  gap: 10px;
  font-size: 16px;
  font-weight: 600;
  line-height: 17px;
  color: var(--gray-color);
`;

export const SortOption = styled.div<{ $isSelected: boolean }>`
  cursor: pointer;
  color: ${(props) =>
    props.$isSelected ? 'var(--main1-color)' : 'var(--gray-color)'};

  &:hover {
    color: var(--main1-color);
  }
`;

export const NowToggle = styled.div`
  .toggleTitle {
    font-size: 25px;
    font-weight: 700;
  }
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 100px;
`;
export const Target = styled.div`
  height: 1px;
`;

export default NFTList;
