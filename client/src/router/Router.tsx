import { BrowserRouter, Route, Routes } from 'react-router-dom';

import MainPage from '@pages/MainPage';
import NFTList from '@pages/ListPage/NFTList';
import ConcertList from '@pages/ListPage/ConcertList';
import ActiveList from '@pages/ListPage/ActiveList';
import ConcertDetail from '@pages/DetailPage/ConcertDetail';
import NFTDetail from '@pages/DetailPage/NFTDetail';
import LiveList from '@pages/ListPage/LiveList';
import AddFund from '@pages/AddFund';
import ArtistDetail from '@pages/DetailPage/ArtistDetail';
import ConcertDetail from '@pages/DetailPage/ConcertDetail';
import MyPage from '@pages/MyPage/MyPage';
import PostSuccess from '@pages/common/PostSuccess';
import PageNotFound from '@pages/common/PageNotFound';
import MeetingPage from '@pages/MeetingPage/MeetingPage';
import ActiveDetailPage from '@pages/DetailPage/ActiveDetail';

export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage />}></Route>
        <Route path="/nft" element={<NFTList />}></Route>
        <Route path="/concert" element={<ConcertList />}></Route>
        <Route path="/active" element={<ActiveList />}></Route>
        <Route path="/live" element={<LiveList />}></Route>
        <Route path="/add-fund" element={<AddFund />}></Route>
        <Route path="/active-detail" element={<ActiveDetailPage />}></Route>
        <Route path="/artist-detail" element={<ArtistDetail />}></Route>
        <Route path="/concert-detail" element={<ConcertDetail />}></Route>
        <Route path="/nft-detail" element={<NFTDetail />}></Route>
        <Route
          path="/meeting-artist"
          element={<MeetingPage isArtist={true} />}
        ></Route>
        <Route
          path="/meeting"
          element={<MeetingPage isArtist={false} />}
        ></Route>
        <Route path="/mypage" element={<MyPage />}></Route>
        <Route path="/post-success/:category" element={<PostSuccess />}></Route>

        <Route path="*" element={<PageNotFound />}></Route>
      </Routes>
    </BrowserRouter>
  );
}
