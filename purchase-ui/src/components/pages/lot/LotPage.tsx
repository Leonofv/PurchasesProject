import React, { useState, useEffect } from 'react';
import { PurchaseApi } from '../../http/PurchaseApi';
import { Lot } from '../../intefaces/LotInterface';
import LotPanel from './LotPanel';
import { format } from 'date-fns';

const formatDate = (date: Date) => {
  return format(date, 'dd.MM.yyyy HH:mm');
};

const LotPage: React.FC = () => {
  const [lots, setLots] = useState<Lot[]>([]);

  useEffect(() => {
    fetchLots();
  }, []);

  const fetchLots = async () => {
    try {
      const response = await PurchaseApi.getLots();
      setLots(response.data);
    } catch (error) {
      console.error('Error fetching lots', error);
    }
  };

  return (
    <div>
      <LotPanel lots={lots} formatDate={formatDate} />
    </div>
  );
};

export default LotPage;
