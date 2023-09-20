import styled from 'styled-components';
import { useEffect, useState } from 'react';

import axios from '@api/apiController';
import { useQuery } from 'react-query';
import { ReactComponent as SearchSvg } from '@assets/icons/search.svg';
import { ARTIST } from '@components/common/constant';

interface Props {
  nowStat: string;
  keyword: string;
  setKeyword: React.Dispatch<React.SetStateAction<string>>;
  onSearch: (data?: string, isArtistSearch?: boolean) => void;
}

const SearchBar: React.FC<Props> = ({
  nowStat,
  keyword,
  setKeyword,
  onSearch,
}) => {
  const [isArtist, setIsArtist] = useState(false);
  const [isAutoBox, setIsAutoBox] = useState(true);
  const [nowInput, setNowInput] = useState('');

  const { isLoading, data: autoSearchData } = useQuery(
    ['auto-search', keyword],
    () => fetchAutoSearchData(keyword),
  );

  const handleSearchConditions = () => {
    setIsArtist(!isArtist);
  };

  const onChangeData = (e: React.FormEvent<HTMLInputElement>) => {
    setKeyword(e.currentTarget.value);
    setNowInput(e.currentTarget.value);
  };

  const handleKeyPress = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      setIsAutoBox(false);
      setNowInput('');
      onSearch(undefined, isArtist);
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
        <SearchSvg />
        <AutoSearch isArtist={nowStat === ARTIST}>
          <div className="autolist">
            {autoSearchData &&
              keyword.length > 0 &&
              nowInput.length > 0 &&
              autoSearchData.map((data: string, id: number) => (
                <div
                  key={id}
                  className="eachData"
                  onClick={() => {
                    onSearch(data, nowStat === ARTIST ? undefined : isArtist);
                    setNowInput('');
                  }}
                >
                  {data}
                </div>
              ))}
          </div>
        </AutoSearch>
      </SearchFrame>
    </BarFrame>
  );
};

const fetchAutoSearchData = async (keyword: string) => {
  try {
    const response = await axios.get('/auto-search');
    return response.data;
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
  z-index: 1;
  width: 330px;
  height: max-content;
  position: absolute;
  top: ${(props) => (props.isArtist ? '150px' : '180px')};
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
