import React, { useState } from 'react';
import styled from 'styled-components';
import { ReactComponent as UserSvg } from '@assets/icons/user-key.svg';
import { ReactComponent as MyPageSvg } from '@assets/icons/account-mypage.svg';
import { ReactComponent as ModifSvg } from '@assets/icons/keymodify.svg';
import { useNavigate } from 'react-router';

interface NavItemProps {
  onClick: () => void;
  active: boolean;
}

const Navbar = ({ activeIdx }: { activeIdx?: number }) => {
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const navigate = useNavigate();

  const toggleDropdown = () => {
    setDropdownOpen(!isDropdownOpen);
  };

  const closeDropdown = () => {
    setDropdownOpen(false);
  };

  return (
    <Nav>
      <Logo onClick={() => navigate('/')}>LOGO</Logo>
      <NavList>
        <NavItem
          onClick={() => {
            navigate('/nft');
          }}
          active={activeIdx === 0}
        >
          NFT
        </NavItem>
        <NavItem
          onClick={() => {
            navigate('/concert');
          }}
          active={activeIdx === 1}
        >
          콘서트 펀딩
        </NavItem>
        <NavItem
          onClick={() => {
            navigate('/active');
          }}
          active={activeIdx === 2}
        >
          활동 펀딩
        </NavItem>
        <NavItem
          onClick={() => {
            navigate('/live');
          }}
          active={activeIdx === 3}
        >
          진행 중인 라이브
        </NavItem>
      </NavList>
      <UserIcon onClick={toggleDropdown}>
        <div className="user">
          <UserSvg />
          사용자
        </div>

        {isDropdownOpen && (
          <Dropdown onClick={(e) => e.stopPropagation()}>
            <DropdownItem onClick={() => navigate('/mypage')}>
              <MyPageSvg />
              마이페이지
            </DropdownItem>
            <DropdownItem>
              <ModifSvg />
              로그아웃
            </DropdownItem>
          </Dropdown>
        )}
      </UserIcon>
    </Nav>
  );
};

const Nav = styled.nav`
  display: flex;
  margin: 0 -280px;
  justify-content: space-between;
  align-items: center;
  background-color: var(--white-color);
  color: black;
  padding: 10px 40px;
  height: 40px;
  box-shadow: 0px 2px 6px rgba(91, 89, 89, 0.1); /* 그림자 추가 */

  * {
    cursor: pointer;
  }
`;

const Logo = styled.div`
  font-size: 24px;
`;

const NavList = styled.ul`
  list-style: none;
  display: flex;
  gap: 100px;
`;

const NavItem = styled.li<NavItemProps>`
  cursor: pointer;
  font-size: 18px;
  color: ${({ active }) =>
    active ? 'var(--main1-color)' : 'var(--black-color)'};
  font-weight: ${({ active }) => (active ? '500' : 'normal')};
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
