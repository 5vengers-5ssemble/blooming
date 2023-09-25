import { QueryFunctionContext, useInfiniteQuery } from 'react-query';
import axios from '@api/apiController';
import { useMemo } from 'react';
import { POPULAR } from '@components/common/constant';

const userKeys = {
  search: 'search-result-active',
  default: 'default-result-active',
};

const ACTIVE_BASE_URL = '/activities';
const SEARCH_QUERY_SIZE = 10;

export const useFetchActiveSearch = ({
  query,
  searchUrl,
}: {
  query: string;
  searchUrl: string;
}) =>
  useInfiniteQuery(
    [userKeys.search, query, searchUrl],
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(ACTIVE_BASE_URL + `/search${searchUrl}`, {
        params: {
          query: query,
          page: pageParam,
          size: SEARCH_QUERY_SIZE,
          sort: 'createdAt,desc',
        },
      }),
    {
      getNextPageParam: ({ data: { results } }) =>
        results.last ? undefined : results.number + 1,
    },
  );

export const getSearchData = ({
  searchKeyword,
  searchByKeyword,
}: {
  searchKeyword: string;
  searchByKeyword: boolean;
}) => {
  const { data, hasNextPage, isFetching, fetchNextPage, isLoading } =
    useFetchActiveSearch({
      query: searchKeyword,
      searchUrl: searchByKeyword ? '/keyword' : '/artist',
    });

  const searchData = useMemo(
    () => (data ? data.pages.flatMap(({ data }) => data.results.content) : []),
    [data],
  );

  return { searchData, hasNextPage, isFetching, fetchNextPage, isLoading };
};

export const useFetchActiveDefault = ({
  url,
  sort,
}: {
  url: string;
  sort: string;
}) =>
  useInfiniteQuery(
    [userKeys.default, url, sort],
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(`${ACTIVE_BASE_URL}${url}`, {
        params: {
          page: pageParam,
          size: SEARCH_QUERY_SIZE,
          sort: `${sort}`,
        },
      }),
    {
      getNextPageParam: ({ data: { results } }) =>
        results.last ? undefined : results.number + 1,
    },
  );

export const getActiveData = ({
  sort,
  ongoing,
}: {
  sort: string;
  ongoing: boolean;
}) => {
  const { data, hasNextPage, isFetching, fetchNextPage, isLoading, remove } =
    useFetchActiveDefault({
      url: ongoing ? '/ongoing' : '',
      sort: sort === POPULAR ? 'fundingAmount,desc' : 'createdAt,desc',
    });

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
