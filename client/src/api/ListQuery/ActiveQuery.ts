import { QueryFunctionContext, useInfiniteQuery } from 'react-query';
import axios from '@api/apiController';
import { useMemo } from 'react';
import { POPULAR } from '@components/common/constant';

const userKeys = {
  search: 'search-result-active',
  default: 'default-result-active',
};

const ACTIVE_BASE_URL = '/search-result';
const SEARCH_QUERY_SIZE = 10;

export const useFetchActiveSearch = ({ query }: { query: string }) =>
  useInfiniteQuery(
    userKeys.search,
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(ACTIVE_BASE_URL, {
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

export const getSearchData = ({ searchKeyword }: { searchKeyword: string }) => {
  const { data, hasNextPage, isFetching, fetchNextPage, isLoading } =
    useFetchActiveSearch({
      query: searchKeyword,
    });

  const searchData = useMemo(
    () => (data ? data.pages.flatMap(({ data }) => data.content) : []),
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
    userKeys.default,
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(`${ACTIVE_BASE_URL}${url}`, {
        params: {
          page: pageParam,
          size: SEARCH_QUERY_SIZE,
          sort: `${sort}`,
        },
      }),
    {
      getNextPageParam: ({ data: { last, number } }) =>
        last ? undefined : number + 1,
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
      sort: sort === POPULAR ? 'nftAmount,desc' : 'createdAt,desc',
    });

  const searchData = useMemo(
    () => (data ? data.pages.flatMap(({ data }) => data.content) : []),
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
