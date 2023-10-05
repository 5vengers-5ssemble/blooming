import { QueryFunctionContext, useInfiniteQuery } from 'react-query';
import axios from '@api/apiController';
import { useMemo } from 'react';

const userKeys = {
  search: 'search-result-live',
  default: 'default-result-live',
};

const LIVE_BASE_URL = '/lives';
const SEARCH_QUERY_SIZE = 10;

export const useFetchLiveSearch = ({
  query,
  searchUrl,
}: {
  query: string;
  searchUrl: string;
}) =>
  useInfiniteQuery(
    [userKeys.search, query, searchUrl],
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(LIVE_BASE_URL + `/search${searchUrl}`, {
        params: {
          query: query,
          page: pageParam,
          size: SEARCH_QUERY_SIZE,
          sort: 'desc',
        },
      }),
    {
      getNextPageParam: ({ data: { results } }) =>
        results.last ? undefined : results.number + 1,
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
    searchKeyword.length > 0
      ? useFetchLiveSearch({
          query: searchKeyword,
          searchUrl: isForArtistSearch ? '/artist' : '/keyword',
        })
      : {
          data: undefined,
          hasNextPage: false,
          isFetching: false,
          fetchNextPage: () => {},
          isLoading: false,
        };

  const searchData = useMemo(
    () => (data ? data.pages.flatMap(({ data }) => data.results.content) : []),
    [data],
  );

  return { searchData, hasNextPage, isFetching, fetchNextPage, isLoading };
};

export const useFetchLiveDefault = () =>
  useInfiniteQuery(
    [userKeys.default],
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(`${LIVE_BASE_URL}`, {
        params: {
          page: pageParam,
          size: SEARCH_QUERY_SIZE,
        },
      }),
    {
      getNextPageParam: ({ data: { results } }) => {
        return results.last ? undefined : results.number + 1;
      },
    },
  );

export const getLiveData = () => {
  const { data, hasNextPage, isFetching, fetchNextPage, isLoading, remove } =
    useFetchLiveDefault();

  const searchData = useMemo(
    () => (data ? data.pages.flatMap(({ data }) => data.results.content) : []),
    [data],
  );

  return {
    searchData,
    hasNextPage,
    isFetching,
    fetchNextPage,
    isLoading,
    remove,
  };
};
