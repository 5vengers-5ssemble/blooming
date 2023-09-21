import { useEffect, useRef, useState } from 'react';
import {
  PaymentWidgetInstance,
  loadPaymentWidget,
  ANONYMOUS,
} from '@tosspayments/payment-widget-sdk';
import { nanoid } from 'nanoid';

const selector = '#payment-widget';
const clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq';
const customerKey = 'YbX2HuSlsC9uVJW6NMRMj';

export default function PaymentPage() {
  const [orderId, setOrderId] = useState(nanoid());
  const [orderName, setOrderName] = useState('아이유 콘서트');
  const [customerName, setCustomerName] = useState('김블루');
  const [customerEmail, setCustomerEmail] = useState('customer123@gmail.com');
  const [projectType, setProjectType] = useState('concert');
  const [projectId, setProjectId] = useState(1);
  const [amount, setAmount] = useState(5000);
  const successUrl = `${window.location.origin}/success`;
  const failUrl = `${window.location.origin}/fail`;

  const paymentWidgetRef = useRef<PaymentWidgetInstance | null>(null);
  const paymentMethodsWidgetRef = useRef<ReturnType<
    PaymentWidgetInstance['renderPaymentMethods']
  > | null>(null);
  const [price, setPrice] = useState(0);

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

  return (
    <div>
      <h1>주문서</h1>
      <span>{`${price.toLocaleString()}원`}</span>
      <div>
        <label>
          <input
            type="checkbox"
            onChange={(event) => {
              setPrice(
                event.target.checked
                  ? Math.max(price - 5_000, 0)
                  : price + 5_000,
              );
            }}
          />
          5,000원 할인 쿠폰 적용
        </label>
      </div>
      <div id="payment-widget" />
      <div id="agreement" />
      <button
        onClick={async () => {
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
        }}
      >
        결제하기
      </button>
    </div>
  );
}
