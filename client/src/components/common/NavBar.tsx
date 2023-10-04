import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router';
import { deleteCookie, getCookie } from '@hooks/useAuth';
import { ACCESS_KEY } from './constant';

import { ReactComponent as UserSvg } from '@assets/icons/user-key.svg';
import { ReactComponent as MyPageSvg } from '@assets/icons/account-mypage.svg';
import { ReactComponent as ModifSvg } from '@assets/icons/keymodify.svg';
import { ReactComponent as YoutubeSvg } from '@assets/icons/youtube-logo.svg';
import LoginModal from '@components/Login/LoginModal';

interface NavItemProps {
  onClick: () => void;
  $active: boolean;
}

const Navbar = ({
  activeIdx,
  $isMain,
}: {
  activeIdx?: number;
  $isMain?: boolean;
}) => {
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const [isLogin, setLogin] = useState<boolean>(false);
  const [isArtist, setArtist] = useState<boolean>(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  //임시로 access-token 앞 5자리 설정
  const [userNickname, setUserNickname] = useState<string>('');

  useEffect(() => {
    const accessToken = getCookie(ACCESS_KEY);
    if (accessToken) {
      setLogin(true);
      setUserNickname(accessToken.slice(0, 5));
    }
  }, []);
  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const navigate = useNavigate();

  const toggleDropdown = () => {
    setDropdownOpen(!isDropdownOpen);
  };

  const closeDropdown = () => {
    setDropdownOpen(false);
  };

  return (
    <Nav $isMain={$isMain}>
      <Logo onClick={() => navigate('/')}>
        <img
          src={
            $isMain
              ? 'src/assets/resourceImg/logofont-white.png'
              : 'src/assets/resourceImg/logofont-color.png'
          }
        />
      </Logo>
      <NavList>
        <NavItem
          onClick={() => {
            navigate('/nft');
          }}
          $active={activeIdx === 0}
        >
          NFT
        </NavItem>
        <NavItem
          onClick={() => {
            navigate('/concert');
          }}
          $active={activeIdx === 1}
        >
          콘서트 펀딩
        </NavItem>
        <NavItem
          onClick={() => {
            navigate('/active');
          }}
          $active={activeIdx === 2}
        >
          활동 펀딩
        </NavItem>
        <NavItem
          onClick={() => {
            navigate('/live');
          }}
          $active={activeIdx === 3}
        >
          진행 중인 라이브
        </NavItem>
      </NavList>
      <UserIcon onClick={toggleDropdown}>
        {isLogin ? (
          <div className="user">
            <UserSvg />
            {userNickname} 님
          </div>
        ) : (
          <div className="user" onClick={openModal}>
            <UserSvg />
            로그인이 필요합니다
          </div>
        )}

        {isDropdownOpen && isLogin && (
          <Dropdown onClick={(e) => e.stopPropagation()}>
            <DropdownItem onClick={() => navigate('/mypage')}>
              <MyPageSvg />
              마이페이지
            </DropdownItem>
            {isArtist && (
              <DropdownItem onClick={() => navigate('/mypage')}>
                <YoutubeSvg />
                LIVE ON
              </DropdownItem>
            )}
            <DropdownItem onClick={() => deleteCookie(ACCESS_KEY)}>
              <ModifSvg />
              로그아웃
            </DropdownItem>
          </Dropdown>
        )}
      </UserIcon>
      {isModalOpen && <LoginModal closeModal={closeModal} />}
    </Nav>
  );
};

interface NavStyleProp {
  $isMain?: boolean;
}

const Nav = styled.nav<NavStyleProp>`
  display: flex;
  margin: 0 -280px;
  justify-content: space-between;
  align-items: center;
  background-color: ${({ $isMain }) =>
    $isMain ? 'var(--main4-color)' : 'var(--white-color)'};
  color: ${({ $isMain }) =>
    $isMain ? 'var(--white-color)' : 'var(--black-color)'};
  padding: ${({ $isMain }) => ($isMain ? '20px 40px 10px' : '15px 40px')};
  height: 40px;
  box-shadow: 0px 2px 6px rgba(91, 89, 89, 0.1); /* 그림자 추가 */

  * {
    cursor: pointer;
  }

  svg {
    color: ${({ $isMain }) =>
      $isMain ? 'var(--white-color)' : 'var(--black-color)'};
  }
`;

const Logo = styled.div`
  font-size: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  img {
    margin-top: 5px;
    width: 220px;
    height: auto;
  }
`;

const NavList = styled.ul`
  list-style: none;
  display: flex;
  gap: 100px;
`;

const NavItem = styled.li<NavItemProps>`
  cursor: pointer;
  font-size: 18px;
  color: ${({ $active }) => ($active ? 'var(--main1-color)' : 'default')};
  font-weight: ${({ $active }) => ($active ? '500' : 'normal')};
  &:hover {
    color: var(--main1-color);
    font-weight: 500;
  }
`;

const UserIcon = styled.div`
  position: relative;
  cursor: pointer;

  .user {
    display: flex;
    align-items: center;
    gap: 5px;

    svg {
      width: 20px;
    }
  }
`;

const Dropdown = styled.div`
  position: absolute;
  top: 120%;
  right: 0;
  background-color: var(--white-color);
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  width: 150px;
  padding: 10px 0px;
  z-index: 999;
  color: var(--black-color);
  svg {
    color: var(--black-color);
  }
`;

const DropdownItem = styled.div`
  cursor: pointer;
  display: flex;
  padding: 10px 20px;
  justify-content: space-between;
  align-items: center;

  svg {
    width: 15px;
  }
  &:hover {
    color: var(--main1-color);
    cursor: pointer;
  }
`;

export default Navbar;
