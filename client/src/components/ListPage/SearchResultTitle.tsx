import styled from 'styled-components';

const SearchResultTitle = ({ title }: { title: string }) => {
  return (
    <SearchText>
      <span>{title}</span>에 대한 검색 결과 입니다.
    </SearchText>
  );
};
const SearchText = styled.div`
  font-weight: 700;
  font-size: 20px;
  margin-top: 60px;

  span {
    color: var(--main1-color);
  }
`;

export default SearchResultTitle;
