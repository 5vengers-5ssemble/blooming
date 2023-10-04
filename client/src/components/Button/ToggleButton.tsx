import React, { useState } from 'react';
import styled from 'styled-components';

interface ToggleProps {
  defaultChecked?: boolean;
  onChange: (checked: boolean) => void;
}

const ToggleButton: React.FC<ToggleProps> = ({ defaultChecked, onChange }) => {
  const [checked, setChecked] = useState(defaultChecked || false);

  const handleChange = () => {
    const newChecked = !checked;
    setChecked(newChecked);
    onChange(newChecked);
  };

  return (
    <ToggleContainer>
      {/* <ToggleLabel>{label}</ToggleLabel> */}
      <ToggleSwitch checked={checked} onChange={handleChange} />
    </ToggleContainer>
  );
};

const ToggleContainer = styled.label`
  display: flex;
  align-items: center;
  cursor: pointer;
  user-select: none;
`;

const ToggleSwitch = styled.input.attrs({ type: 'checkbox' })`
  appearance: none;
  width: 50px;
  height: 17px;
  background-color: var(--gray-color);
  border-radius: 20px;
  position: relative;
  transition: background-color 0.3s;
  cursor: pointer;

  &::before {
    content: '';
    width: 25px;
    height: 25px;
    background-color: var(--gray-color);
    border-radius: 50%;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    transition: transform 0.3s, background-color 0.3s;
  }

  &:checked {
    background-color: var(--gray-color);
  }

  &:checked::before {
    transform: translateY(-50%) translateX(100%);
    background-color: var(--main1-color);
  }
`;

const ToggleLabel = styled.span`
  margin-right: 8px;
`;

export default ToggleButton;
