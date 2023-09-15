import { BrowserRouter, Route, Routes } from 'react-router-dom';
import MainPage from '@pages/MainPage';
import ArtistList from '@pages/ListPage/ArtistList';
import ConcertList from '@pages/ListPage/ConcertList';
import ActiveList from '@pages/ListPage/ActiveList';
import AddFund from '@pages/AddFund';
import ActiveDetail from '@pages/DetailPage/ActiveDetail';
import Test from '@components/artistDetail/ArtistNFT';

export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage />}></Route>
        <Route path="/artist" element={<ArtistList />}></Route>
        <Route path="/concert" element={<ConcertList />}></Route>
        <Route path="/active" element={<ActiveList />}></Route>
        <Route path="/add-fund" element={<AddFund />}></Route>
        <Route path="/active-detail" element={<ActiveDetail />}></Route>
        <Route path="/test" element={<Test />}></Route>
      </Routes>
    </BrowserRouter>
  );
}
