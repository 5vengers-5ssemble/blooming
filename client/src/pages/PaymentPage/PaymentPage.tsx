import { useEffect, useRef, useState } from 'react';
import styled from 'styled-components';
import {
  PaymentWidgetInstance,
  loadPaymentWidget,
  ANONYMOUS,
} from '@tosspayments/payment-widget-sdk';
import { nanoid } from 'nanoid';
import { artist, concert, investment } from '@type/ConcertDetail';

const selector = '#payment-widget';
const clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq';
const customerKey = 'YbX2HuSlsC9uVJW6NMRMj';

interface Props {
  setModalOpen: React.Dispatch<React.SetStateAction<boolean>>;
  artistData: artist;
  concertData: concert;
  investmentData: investment;
  count: number;
  orderIdentifier: string;
}

const PaymentPage: React.FC<Props> = ({
  setModalOpen,
  artistData,
  concertData,
  investmentData,
  count,
  orderIdentifier,
}) => {
  const [orderId, setOrderId] = useState(orderIdentifier);
  const [orderName, setOrderName] = useState(concertData.name);
  // 수정필요
  const [customerName, setCustomerName] = useState('sdf');
  const [customerEmail, setCustomerEmail] = useState('sdf@sdf.com');
  const [projectType, setProjectType] = useState('concert');
  const [projectId, setProjectId] = useState(concertData.id);
  const [amount, setAmount] = useState(
    investmentData.overview.pricePerAccount * count,
  );
  const successUrl = `${window.location.origin}/success`;
  const failUrl = `${window.location.origin}/fail`;

  const paymentWidgetRef = useRef<PaymentWidgetInstance | null>(null);
  const paymentMethodsWidgetRef = useRef<ReturnType<
    PaymentWidgetInstance['renderPaymentMethods']
  > | null>(null);
  const [price, setPrice] = useState(
    investmentData.overview.pricePerAccount * count,
  );

  useEffect(() => {
    (async () => {
      const paymentWidget = await loadPaymentWidget(clientKey, customerKey);

      const paymentMethodsWidget = paymentWidget.renderPaymentMethods(
        selector,
        { value: price },
      );

      paymentWidget.renderAgreement('#agreement');

      paymentWidgetRef.current = paymentWidget;
      paymentMethodsWidgetRef.current = paymentMethodsWidget;
    })();
  }, []);

  useEffect(() => {
    const paymentMethodsWidget = paymentMethodsWidgetRef.current;

    if (paymentMethodsWidget == null) {
      return;
    }
    paymentMethodsWidget.updateAmount(
      price,
      paymentMethodsWidget.UPDATE_REASON.COUPON,
    );
  }, [price]);

  // 모달 끄기
  const closeModal = () => {
    setModalOpen(false);
  };

  //결제 요청

  const handlePayment = async () => {
    try {
      const response = await fetch(
        'http://localhost:8080/api/v1/payments/temp',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            orderId: orderId,
            projectType: projectType,
            projectId: projectId,
            amount: amount,
          }),
        },
      );

      if (response.status === 200) {
        const paymentWidget = paymentWidgetRef.current;
        console.log(projectType);

        try {
          await paymentWidget?.requestPayment({
            orderId: orderId,
            orderName: orderName,
            customerName: customerName,
            customerEmail: customerEmail,
            successUrl: successUrl,
            failUrl: failUrl,
          });
        } catch (error) {
          console.error(error);
        }
      } else {
        console.error('POST 요청이 실패하였습니다.');
      }
    } catch (error) {
      console.error('POST 요청 중 오류 발생:', error);
    }
  };

  return (
    <ModalContainer>
      <ModalBox>
        <PriceBox>
          <h1 className="order_sheet">주문서</h1>
          <span className="price">{`${price.toLocaleString()}원`}</span>
        </PriceBox>
        <div id="payment-widget" />
        <div id="agreement" />
        <BtnBox>
          <CloseBtnBox onClick={closeModal}>
            <button className="close_btn">닫기</button>
          </CloseBtnBox>
          <PaymentBtnBox onClick={handlePayment}>
            <button className="payment_btn">결제하기</button>
          </PaymentBtnBox>
        </BtnBox>
      </ModalBox>
    </ModalContainer>
  );
};

const CloseBtnBox = styled.div`
  height: 46px;
  width: 15%;
  background: var(--Main, #c7c7c7);
  border-radius: 6px;
  cursor: pointer;
  text-align: center;

  .close_btn {
    background: none;
    border: none;
    padding: 0;
    cursor: pointer;

    color: var(--White, #fdfdfd);
    font-size: 20px;
    font-weight: 700;
    line-height: 46px;
  }
`;

const PaymentBtnBox = styled.div`
  height: 46px;
  width: 15%;
  margin-left: 15px;

  background: var(--Main, #3061b9);
  border-radius: 6px;
  cursor: pointer;
  text-align: center;

  .payment_btn {
    background: none;
    border: none;
    padding: 0;
    cursor: pointer;

    color: var(--White, #fdfdfd);
    font-size: 20px;
    font-weight: 700;
    line-height: 46px;
  }
`;

const BtnBox = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
`;
const PriceBox = styled.div`
  display: flex;
  flex-direction: column;
  margin: 5px 50px 0px;

  .price {
    align-self: flex-end;
    font-size: 20px;
    font-weight: 500;
  }

  .order_sheet {
    align-self: center;
  }
`;

const ModalBox = styled.div`
  background-color: white;
  border-radius: 10px;
  width: 50%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 15px;
`;
const ModalContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);

  z-index: 9999;
`;
export default PaymentPage;
