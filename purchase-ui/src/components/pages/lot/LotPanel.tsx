import React, { useState, useEffect } from 'react';
import { Text } from '@consta/uikit/Text';
import { Select } from '@consta/uikit/Select';
import LotList from './LotList';
import { Lot } from '../../intefaces/LotInterface';
import './LotPanel.css';

type SortOption = {
  label: string;
  value: 'nearest' | 'latest' | 'none';
};

const sortOptions: SortOption[] = [
  { label: 'Ближайшая доставка', value: 'nearest' },
  { label: 'Позднейшая доставка', value: 'latest' },
  { label: 'Без сортировки', value: 'none' },
];

interface Props {
  lots: Lot[];
  formatDate: (date: Date) => string;
}

const LotPanel: React.FC<Props> = ({ lots, formatDate }) => {
  const [sortedLots, setSortedLots] = useState<Lot[]>(lots);
  const [selectedSort, setSelectedSort] = useState<
    'nearest' | 'latest' | 'none'
  >('none');

  useEffect(() => {
    setSortedLots(lots);
  }, [lots]);

  const handleSortChange = (option: SortOption | null) => {
    if (option) {
      setSelectedSort(option.value);
      let sortedArray;
      if (option.value === 'nearest') {
        sortedArray = [...lots].sort(
          (a, b) =>
            new Date(a.dateDelivery).getTime() -
            new Date(b.dateDelivery).getTime()
        );
      } else if (option.value === 'latest') {
        sortedArray = [...lots].sort(
          (a, b) =>
            new Date(b.dateDelivery).getTime() -
            new Date(a.dateDelivery).getTime()
        );
      } else {
        sortedArray = lots;
      }
      setSortedLots(sortedArray);
    }
  };

  const selectedSortOption =
    sortOptions.find((option) => option.value === selectedSort) || null;

  return (
    <div className="container">
      <div className="header">
        <div>
          <Text as="h1">Управление лотами</Text>
          <div className="controls">
            <Select
              className="select"
              value={selectedSortOption}
              items={sortOptions}
              onChange={handleSortChange}
              getItemKey={(item) => item.value}
              getItemLabel={(item) => item.label}
              placeholder="Сортировка по дате доставки"
            />
          </div>
        </div>
      </div>
      <Text as="p" lineHeight="m">
        В этом окне вы можете просматривать лоты, а также сортировать их по дате
        доставки. Благодаря удобному интерфейсу, процесс просмотра лотов
        является максимально простым и удобным для пользовательского опыта.
      </Text>
      <hr />
      <LotList lots={sortedLots} formatDate={formatDate} />
    </div>
  );
};

export default LotPanel;
