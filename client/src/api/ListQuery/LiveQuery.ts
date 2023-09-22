import { QueryFunctionContext, useInfiniteQuery } from 'react-query';
import axios from '@api/apiController';
import { useMemo } from 'react';

const userKeys = {
  search: 'search-result-live',
  default: 'default-result-live',
};

const LIVE_BASE_URL = '/lives'; //search/keyword';
const SEARCH_QUERY_SIZE = 20;

//!isArtist 에 따라 /artist, /keyword LIVE_BASE_URL 뒤에 붙이기
export const useFetchLiveSearch = ({
  query,
  isArtist,
}: {
  query: string;
  isArtist: boolean;
}) =>
  useInfiniteQuery(
    userKeys.search,
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(LIVE_BASE_URL, {
        params: {
          q: query,
          page: pageParam,
          size: SEARCH_QUERY_SIZE,
          sort: 'desc',
        },
      }),
    {
      getNextPageParam: ({ data: { last, number } }) =>
        last ? undefined : number + 1,
    },
  );

export const getSearchData = ({
  searchKeyword,
  isForArtistSearch,
}: {
  searchKeyword: string;
  isForArtistSearch?: boolean;
}) => {
  const { data, hasNextPage, isFetching, fetchNextPage, isLoading } =
    useFetchLiveSearch({
      query: searchKeyword,
      isArtist: isForArtistSearch ? isForArtistSearch : false,
    });

  const searchData = useMemo(
    () => (data ? data.pages.flatMap(({ data }) => data.content) : []),
    [data],
  );

  return { searchData, hasNextPage, isFetching, fetchNextPage, isLoading };
};
