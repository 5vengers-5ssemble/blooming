import { BrowserRouter, Route, Routes } from 'react-router-dom';

import MainPage from '@pages/MainPage';
import NFTList from '@pages/ListPage/NFTList';
import ConcertList from '@pages/ListPage/ConcertList';
import ActiveList from '@pages/ListPage/ActiveList';
import NFTDetail from '@pages/DetailPage/NFTDetail';
import LiveList from '@pages/ListPage/LiveList';
import AddFund from '@pages/AddFund';
import ArtistDetail from '@pages/DetailPage/ArtistDetailPage';
import ConcertDetail from '@pages/DetailPage/ConcertDetail';
import MyPage from '@pages/MyPage/MyPage';
import PostSuccess from '@pages/common/PostSuccess';
import PageNotFound from '@pages/common/PageNotFound';
import MeetingPage from '@pages/MeetingPage/MeetingPage';
import ActiveDetailPage from '@pages/DetailPage/ActiveDetail';
import PaymentSuccess from '@pages/common/PaymentSuccess';
import PaymentFailure from '@pages/common/PaymentFailure';
import PaymentPage from '@pages/PaymentPage/PaymentPage';
import LoginSuccess from '@pages/common/Login';
import PrivateRoute from './PrivateRouter';
import AdminRoute from './AdminRouter';
import AdminPage from '@pages/MyPage/AdminPage';
import AccessDeny from '@pages/common/AccessDeny';
import AddMembershipPage from '@pages/MembershipPage/AddMembershipPage';

export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage />}></Route>
        <Route path="/login-success" element={<LoginSuccess />} />
        <Route path="/access-denied" element={<AccessDeny />} />
        <Route element={<PrivateRoute />}>
          <Route element={<AdminRoute />}>
            <Route path="/admin" element={<AdminPage />} />
          </Route>
          <Route path="/nft" element={<NFTList />}></Route>
          <Route path="/concert" element={<ConcertList />}></Route>
          <Route path="/active" element={<ActiveList />}></Route>
          <Route path="/live" element={<LiveList />}></Route>
          <Route path="/add-fund" element={<AddFund />}></Route>
          <Route path="/success" element={<PaymentSuccess />}></Route>
          <Route path="/failure" element={<PaymentFailure />}></Route>
          <Route
            path="/activity-detail/:activityId"
            element={<ActiveDetailPage />}
          ></Route>
          <Route
            path="/concert-detail/:concertId"
            element={<ConcertDetail />}
          ></Route>
          <Route
            path="/artist-detail/:artistId"
            element={<ArtistDetail />}
          ></Route>
          <Route
            path="/nft-detail:membershipId"
            element={<NFTDetail />}
          ></Route>
          {/* <Route path="/pay" element={<PaymentPage />}></Route> */}
          <Route
            path="/meeting-artist/:liveId"
            element={<MeetingPage isArtist={true} />}
          ></Route>
          <Route
            path="/meeting/:liveId"
            element={<MeetingPage isArtist={false} />}
          ></Route>
          <Route path="/mypage/:tab?" element={<MyPage />}></Route>
          <Route
            path="/post-success/:category"
            element={<PostSuccess />}
          ></Route>
        </Route>

        <Route path="*" element={<PageNotFound />}></Route>
        <Route path="/add-membership" element={<AddMembershipPage />}></Route>
      </Routes>
    </BrowserRouter>
  );
}
