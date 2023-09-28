import { LiveResultList } from '@components/ListPage/ResultList';
import axiosTemp from '@api/apiControllerTemp';
import { useEffect, useState } from 'react';
import { LiveInfo } from '@type/ProcessInfo';
import Loading from '@components/Animation/Loading';
import { MainTitle } from '@style/common';
import styled from 'styled-components';
import { Frame } from '../MyMembershipInfo/MembershipInterface';

const LiveInfo = () => {
  const [livedata, setLiveData] = useState<LiveInfo[]>();

  useEffect(() => {
    axiosTemp.get('/lives').then((res) => {
      console.log(res.data);
      setLiveData(res.data.content);
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
      <LiveResultList datas={livedata} />
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
