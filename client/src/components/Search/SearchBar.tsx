import styled from 'styled-components';
import { useEffect, useState } from 'react';

import axios from '@api/apiController';

import { useQuery } from 'react-query';
import { ReactComponent as SearchSvg } from '@assets/icons/search.svg';
import { ACTIVE, ARTIST, CONCERT, LIVE } from '@components/common/constant';
import { ProcessInfo } from '@type/ProcessInfo';

interface Props {
  nowStat: string;
  keyword: string;
  setKeyword: React.Dispatch<React.SetStateAction<string>>;
  setSearchByKeyword: React.Dispatch<React.SetStateAction<boolean>>;
  onSearch: (data?: string, isBlankSearch?: boolean) => void;
}

const SearchBar: React.FC<Props> = ({
  nowStat,
  keyword,
  setKeyword,
  onSearch,
  setSearchByKeyword,
}) => {
  const [isArtist, setIsArtist] = useState(false);
  const [isAutoBox, setIsAutoBox] = useState(true);
  const [nowInput, setNowInput] = useState('');
  const [autoCompleteUrl, setAutoCompleteUrl] = useState('');

  useEffect(() => {
    switch (nowStat) {
      case ARTIST:
        setAutoCompleteUrl('/memberships/search');
        break;
      case CONCERT:
        const url_concert = !isArtist
          ? '/concerts/search/keyword'
          : '/concerts/search/artist';
        setAutoCompleteUrl(url_concert);
        break;
      case ACTIVE:
        const url_active = !isArtist
          ? '/activities/search/keyword'
          : '/activities/search/artist';
        setAutoCompleteUrl(url_active);
        break;
      case LIVE:
        const url_live = !isArtist
          ? '/lives/search/keyword'
          : '/lives/search/artist';
        setAutoCompleteUrl(url_live);
        break;
    }
  }, [isArtist]);

  const { isLoading, data: autoSearchData } = useQuery(
    ['auto-search', keyword],
    () => {
      if (autoCompleteUrl.length > 0) {
        return fetchAutoSearchData(keyword, autoCompleteUrl);
      }
      return Promise.resolve([]); // autoCompleteUrl이 비어있으면 빈 배열 반환
    },
    {
      enabled: autoCompleteUrl.length > 0, // autoCompleteUrl의 length가 0이 아닐 때만 활성화
    },
  );

  const handleSearchConditions = () => {
    setIsArtist(!isArtist);
    setSearchByKeyword((prev) => !prev);
  };

  const onChangeData = (e: React.FormEvent<HTMLInputElement>) => {
    setKeyword(e.currentTarget.value);
    setNowInput(e.currentTarget.value);
  };

  const handleKeyPress = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      if (nowInput.length === 0) {
        onSearch(undefined, true);
      } else {
        setIsAutoBox(false);
        setNowInput('');
        onSearch(undefined, false);
      }
    }
  };

  return (
    <BarFrame>
      {nowStat !== ARTIST && (
        <CheckBox onClick={handleSearchConditions}>
          {isArtist
            ? '키워드로 검색하시겠습니까?'
            : '아티스트로 검색하시겠습니까?'}
        </CheckBox>
      )}
      <SearchFrame>
        <Input
          placeholder={
            isArtist ? '아티스트 명을 입력해주세요' : '키워드를 입력해주세요.'
          }
          value={keyword}
          onKeyDown={handleKeyPress}
          onChange={onChangeData}
        />
        <SearchIcon>
          <SearchSvg />
        </SearchIcon>
        <AutoSearch isArtist={nowStat === ARTIST}>
          <div className="autolist">
            {autoSearchData?.length > 0 &&
              keyword.length > 0 &&
              nowInput.length > 0 &&
              autoSearchData.map((data: ProcessInfo, id: number) => (
                <div
                  key={id}
                  className="eachData"
                  onClick={() => {
                    onSearch(data.title, false);
                    setNowInput('');
                  }}
                >
                  {data.title}
                </div>
              ))}
          </div>
        </AutoSearch>
      </SearchFrame>
    </BarFrame>
  );
};

const fetchAutoSearchData = async (
  keyword: string,
  autoCompleteUrl: string,
) => {
  try {
    const response = await axios.get(autoCompleteUrl, {
      params: {
        query: keyword,
        page: 0,
        size: 10,
        sort: 'createdAt,desc',
      },
    });
    return response.data.results.content;
  } catch (error) {
    console.log(error);
    throw new Error('자동완성 리스트 요청 실패');
  }
};

interface StyleProps {
  isArtist: boolean;
}

const AutoSearch = styled.div<StyleProps>`
  background-color: var(--white-color);
  z-index: 10;
  width: 330px;
  height: max-content;
  position: absolute;
  top: 200px;
  border-radius: 6px;

  .autolist {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    gap: 20px;
    margin: 20px;
  }

  .eachData {
    cursor: pointer;
  }
`;

const SearchIcon = styled.div`
  cursor: pointer;
`;

const BarFrame = styled.div`
  text-align: right;
`;
const SearchFrame = styled.div`
  display: flex;
  align-items: center;
  gap: 12px;
`;
const CheckBox = styled.div`
  color: var(--main1-color);
  font-weight: 600;
  font-size: 14px;
  margin-bottom: 10px;
  cursor: pointer;
`;

const Input = styled.input`
  border: none;
  display: flex;
  padding: 12px 16px;
  width: 300px;
  align-items: center;
  gap: 4px;
  flex: 1 0 0;
  align-self: stretch;
  border-radius: 8px;
  background: rgba(179, 180, 181, 0.22);
  /* background: rgba(168, 190, 225, 0.22); */

  &::placeholder {
    font-size: 14px;
    font-weight: 500;
    line-height: 130%;
    letter-spacing: -0.014px;
    color: var(--gray-color);
  }

  &:focus {
    outline: none !important;
  }
`;
export default SearchBar;
