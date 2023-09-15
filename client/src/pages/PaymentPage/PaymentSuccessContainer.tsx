import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

const PaymentSuccessContainer = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const queryList = [...searchParams];

  const [paymentType, setPaymentType] = useState(
    searchParams.get('paymentType'),
  );
  const [paymentKey, setPaymentKey] = useState(searchParams.get('paymentKey'));
  const [orderid, setOrderId] = useState(searchParams.get('orderId'));
  const [amount, setAmount] = useState(searchParams.get('amount'));

  useEffect(() => {
    const init = async () => {
      axios.post('http://localhost:8080/payment');
    };
  }, []);

  return (
    <div>
      <p>결제가 진행 중입니다.</p>
    </div>
  );
};

export default PaymentSuccessContainer;
