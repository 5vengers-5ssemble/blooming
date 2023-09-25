import { QueryFunctionContext, useInfiniteQuery } from 'react-query';
import axios from '@api/apiController';
import { useMemo } from 'react';
import { POPULAR } from '@components/common/constant';

const userKeys = {
  search: 'search-result-concert',
  default: 'default-result-concert',
};

const CONCERT_BASE_URL = '/concerts';
const SEARCH_QUERY_SIZE = 10;

export const useFetchConcertSearch = ({
  query,
  searchUrl,
}: {
  query: string;
  searchUrl: string;
}) =>
  useInfiniteQuery(
    [userKeys.search, query, searchUrl],
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(CONCERT_BASE_URL + `/search${searchUrl}`, {
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
    useFetchConcertSearch({
      query: searchKeyword,
      searchUrl: searchByKeyword ? '/keyword' : '/artist',
    });

  const searchData = useMemo(
    () => (data ? data.pages.flatMap(({ data }) => data.results.content) : []),
    [data],
  );

  return { searchData, hasNextPage, isFetching, fetchNextPage, isLoading };
};

export const useFetchConcertDefault = ({
  url,
  sort,
}: {
  url: string;
  sort: string;
}) =>
  useInfiniteQuery(
    [userKeys.default, url, sort],
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(`${CONCERT_BASE_URL}${url}`, {
        params: {
          page: pageParam,
          size: SEARCH_QUERY_SIZE,
          sort: `${sort}`,
        },
      }),
    {
      getNextPageParam: ({ data: { results } }) => {
        return results.last ? undefined : results.number + 1;
      },
    },
  );

export const getConcertData = ({
  sort,
  ongoing,
}: {
  sort: string;
  ongoing: boolean;
}) => {
  const { data, hasNextPage, isFetching, fetchNextPage, isLoading, remove } =
    useFetchConcertDefault({
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
