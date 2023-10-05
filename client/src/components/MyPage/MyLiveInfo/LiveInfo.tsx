import { LiveResultList } from '@components/ListPage/ResultList';
import axios from '@api/apiController';
import { useEffect, useState } from 'react';
import { LiveInfo } from '@type/ProcessInfo';
import Loading from '@components/Animation/Loading';
import { MainTitle } from '@style/common';
import styled from 'styled-components';
import { Frame } from '../MyMembershipInfo/MembershipInterface';
import NoSearchResults from '@components/Search/NoSearchResults';

const LiveInfo = () => {
  const [livedata, setLiveData] = useState<LiveInfo[]>();

  useEffect(() => {
    axios.get('/lives/nft-purchased').then((res) => {
      console.log(res.data.results);
      setLiveData(res.data.results);
    });
  }, []);

  if (!livedata) {
    return (
      <>
        <Loading />
      </>
    );
  }

  return (
    <LiveFrame>
      <LiveTitle>
        현재 시청 가능한 라이브<div className="dot"></div>
      </LiveTitle>
      {livedata.length > 0 ? (
        <LiveResultList datas={livedata} />
      ) : (
        <NoSearchResults />
      )}
    </LiveFrame>
  );
};

const LiveFrame = styled(Frame)`
  padding: 70px 40px;
`;
const LiveTitle = styled(MainTitle)`
  font-size: 30px;
`;
export default LiveInfo;
