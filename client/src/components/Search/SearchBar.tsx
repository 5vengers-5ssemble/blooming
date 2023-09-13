import { ReactComponent as SearchSvg } from '@assets/icons/search.svg';
import { ARTIST } from '@components/common/constant';
import { useState } from 'react';
import styled from 'styled-components';

interface Props {
  nowStat: string;
}

const SearchBar: React.FC<Props> = ({ nowStat }) => {
  const [isArtist, setIsArtist] = useState(false);
  const handleSearchConditions = () => {
    setIsArtist(!isArtist);
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
        />
        <SearchSvg />
      </SearchFrame>
    </BarFrame>
  );
};
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
