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
  const [orderId, setOrderId] = useState(searchParams.get('orderId'));
  const [amount, setAmount] = useState(searchParams.get('amount'));

  useEffect(() => {
    const init = async () => {
      try {
        const res = await axios.post(
          'https://api.tosspayments.com/v1/payments/confirm',
          {
            paymentKey: paymentKey,
            amount: amount,
            orderId: orderId,
          },
          {
            headers: {
              Authorization:
                'Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==',
              'Content-Type': 'application/json',
            },
          },
        );
        console.log(res);
      } catch (error) {
        console.log(error);
      }
    };

    init();
  }, []);

  return (
    <div>
      <p>결제가 진행 중입니다.</p>
    </div>
  );
};

export default PaymentSuccessContainer;
