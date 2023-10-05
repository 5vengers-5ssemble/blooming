import { QueryFunctionContext, useInfiniteQuery } from 'react-query';
import axios from '@api/apiController';
import { useMemo } from 'react';
import { POPULAR } from '@components/common/constant';

const userKeys = {
  search: 'search-result-artist',
  default: 'default-result-artist',
};

const ARTIST_BASE_URL = '/memberships';
const SEARCH_QUERY_SIZE = 10;

export const useFetchArtistSearch = ({
  query,
  searchUrl,
}: {
  query: string;
  searchUrl: string;
}) =>
  useInfiniteQuery(
    [userKeys.search, query, searchUrl],
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(`/memberships/search`, {
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
    useFetchArtistSearch({
      query: searchKeyword,
      searchUrl: searchByKeyword ? '/keyword' : '/artist',
    });

  const searchData = useMemo(
    () => (data ? data.pages.flatMap(({ data }) => data.results.content) : []),
    [data],
  );

  return { searchData, hasNextPage, isFetching, fetchNextPage, isLoading };
};

export const useFetchArtistDefault = ({
  url,
  sort,
}: {
  url: string;
  sort: string;
}) =>
  useInfiniteQuery(
    userKeys.default,
    ({ pageParam = 0 }: QueryFunctionContext) =>
      axios.get(`${ARTIST_BASE_URL}${url}`, {
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

export const getArtistData = ({
  sort,
  ongoing,
}: {
  sort: string;
  ongoing: boolean;
}) => {
  const { data, hasNextPage, isFetching, fetchNextPage, isLoading, remove } =
    useFetchArtistDefault({
      url: ongoing ? '/ongoing' : '',
      sort: sort === POPULAR ? 'saleCount,desc' : 'createdAt,desc',
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
