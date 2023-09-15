import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

const PaymentFailContainer = () => {
  const [searchParams, setSearchParams] = useSearchParams();

  const [code, setCode] = useState(searchParams.get('code'));
  const [message, setMessage] = useState(searchParams.get('message'));

  return (
    <div>
      <p>결제가 실패했습니다.</p>
    </div>
  );
};

export default PaymentFailContainer;
