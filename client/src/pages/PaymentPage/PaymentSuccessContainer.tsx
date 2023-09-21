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
        // 첫 번째 POST 요청: 로컬 서버로 주문 확인 요청 보내기
        const checkResponse = await axios.post(
          'http://localhost:8080/api/v1/payments/check',
          {
            orderId: orderId,
            amount: amount,
          },
          {
            headers: {
              'Content-Type': 'application/json',
            },
          },
        );

        if (checkResponse.status === 200) {
          // 주문 확인이 성공하면 두 번째 POST 요청: 외부 결제 확인 요청 보내기
          const paymentResponse = await axios.post(
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

          if (paymentResponse.status === 200) {
            // 두 번째 POST 요청이 성공하면 세 번째 PATCH 요청: 결제 완료 요청 보내기
            const completeResponse = await axios.patch(
              'http://localhost:8080/api/v1/payments/complete',
              {
                paymentKey: paymentKey,
                orderId: orderId,
              },
              {
                headers: {
                  'Content-Type': 'application/json',
                },
              },
            );

            if (completeResponse.status === 204) {
              console.log('결제가 성공적으로 완료되었습니다.');
            } else {
              console.error('결제 완료 요청이 실패했습니다.');
            }
          } else {
            console.error('외부 결제 확인 요청이 실패했습니다.');
          }
        } else {
          console.error('주문 확인 요청이 실패했습니다.');
        }
      } catch (error) {
        console.error('오류 발생:', error);
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
