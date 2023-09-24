import React from 'react';
import styled from 'styled-components';

const NFTSales = () => {
  return (
    <NFTSalesBox>
      <PublishedDateBox>
        <div className="title">발행 일자</div>
        <div className="content">2023.09.12</div>
      </PublishedDateBox>
      <SalesStaticsBox>
        <div className="title">판매 통계</div>
        <GraphBox>
          <div className="sub_title">가격 변동</div>
        </GraphBox>
        <TableBox>
          <div className="sub_title">영업 활동</div>
          <TableContainer>
            <TableBody>
              <TableHeader>
                <TableColumn1>가격</TableColumn1>
                <TableColumn2>원화</TableColumn2>
                <TableColumn3>수량</TableColumn3>
                <TableColumn4>하한치</TableColumn4>
                <TableColumn5>만료일</TableColumn5>
                <TableColumn6>닉네임</TableColumn6>
              </TableHeader>

              <TableRow>
                <TableColumn1>0.8 WETH</TableColumn1>
                <TableColumn2>$ 1,312.10</TableColumn2>
                <TableColumn3>1</TableColumn3>
                <TableColumn4>19% ↓</TableColumn4>
                <TableColumn5>22 시간 남음</TableColumn5>
                <TableColumn6>닉네임입니당</TableColumn6>
              </TableRow>
              <TableRow>
                <TableColumn1>0.8 WETH</TableColumn1>
                <TableColumn2>$ 1,312.10</TableColumn2>
                <TableColumn3>1</TableColumn3>
                <TableColumn4>19% ↓</TableColumn4>
                <TableColumn5>22 시간 남음</TableColumn5>
                <TableColumn6>닉네임입니당</TableColumn6>
              </TableRow>
              <TableRow>
                <TableColumn1>0.8 WETH</TableColumn1>
                <TableColumn2>$ 1,312.10</TableColumn2>
                <TableColumn3>1</TableColumn3>
                <TableColumn4>19% ↓</TableColumn4>
                <TableColumn5>22 시간 남음</TableColumn5>
                <TableColumn6>닉네임입니당</TableColumn6>
              </TableRow>
              <TableRow>
                <TableColumn1>0.8 WETH</TableColumn1>
                <TableColumn2>$ 1,312.10</TableColumn2>
                <TableColumn3>1</TableColumn3>
                <TableColumn4>19% ↓</TableColumn4>
                <TableColumn5>22 시간 남음</TableColumn5>
                <TableColumn6>닉네임입니당</TableColumn6>
              </TableRow>
              <TableRow>
                <TableColumn1>0.8 WETH</TableColumn1>
                <TableColumn2>$ 1,312.10</TableColumn2>
                <TableColumn3>1</TableColumn3>
                <TableColumn4>19% ↓</TableColumn4>
                <TableColumn5>22 시간 남음</TableColumn5>
                <TableColumn6>닉네임입니당</TableColumn6>
              </TableRow>
            </TableBody>
          </TableContainer>
        </TableBox>
      </SalesStaticsBox>
    </NFTSalesBox>
  );
};

const TableColumn6 = styled.td``;
const TableColumn5 = styled.td``;
const TableColumn4 = styled.td``;
const TableColumn3 = styled.td``;
const TableColumn2 = styled.td``;
const TableColumn1 = styled.td``;
const TableRow = styled.tr`
  height: 46px;
  border-bottom: 1px solid #c7c7c7;
`;

const TableHeader = styled.tr`
  font-size: 14px;
  font-weight: 700;
  height: 46px;
  line-height: 46px;
  text-align: center;
  width: 100%;

  border-bottom: 3px solid #3061b9;
`;
const TableBody = styled.tbody`
  font-size: 14px;
  font-weight: 500;
  line-height: 46px;
  text-align: center;

  width: 100%;
`;
const TableContainer = styled.table`
  border-collapse: collapse;
  width: 100%;
`;
const TableBox = styled.div``;
const GraphBox = styled.div``;
const SalesStaticsBox = styled.div``;
const PublishedDateBox = styled.div``;
const NFTSalesBox = styled.div`
  margin-top: 75px;
  .title {
    font-size: 25px;
    font-weight: 700;
    line-height: 25px;
    margin-bottom: 20px;
  }

  .content {
    font-size: 16px;
    font-weight: 600;
    line-height: 30px;
    margin-bottom: 35px;
    margin-left: 26px;
  }

  .sub_title {
    font-size: 20px;
    font-weight: 700;
    line-height: 20px;
    margin-left: 26px;
    margin-bottom: 15px;
  }
`;

export default NFTSales;
