import React from 'react';
import { Text } from '@consta/uikit/Text';
import { Button } from '@consta/uikit/Button';
import { Select } from '@consta/uikit/Select';
import CustomerList from './CustomerList';
import { Customer } from '../../intefaces/CustomerInteface';
import './CustomerPanel.css';

type FilterOption = {
  label: string;
  value: 'all' | 'hasAgent' | 'noAgent';
};

const filterOptions: FilterOption[] = [
  { label: 'Все', value: 'all' },
  { label: 'С контрагентом', value: 'hasAgent' },
  { label: 'Без контрагента', value: 'noAgent' },
];

interface Props {
  customers: Customer[];
  handleDeleteCustomer: (id: number) => void;
  openEditModal: (customer: Customer) => void;
  openAddModal: () => void;
  setFilter: (filter: 'all' | 'hasAgent' | 'noAgent') => void;
  filter: 'all' | 'hasAgent' | 'noAgent';
}

const CustomerPanel: React.FC<Props> = ({
  customers,
  handleDeleteCustomer,
  openEditModal,
  openAddModal,
  setFilter,
  filter,
}) => {
  const handleSelectChange = (option: FilterOption | null) => {
    if (option) {
      setFilter(option.value);
    }
  };

  const selectedFilter =
    filterOptions.find((option) => option.value === filter) || null;

  return (
    <div className="container">
      <div className="header">
        <div>
          <Text as="h1">Управление заказчиками</Text>
          <div className="controls">
            <Select
              className="select"
              value={selectedFilter}
              items={filterOptions}
              onChange={handleSelectChange}
              getItemKey={(item) => item.value}
              getItemLabel={(item) => item.label}
              placeholder="Фильтр"
            />
            <Button
              label="Добавить"
              onClick={openAddModal}
              className="button"
            />
          </div>
        </div>
      </div>
      <Text as="p" lineHeight="m">
        В этом окне вы можете управлять заказчиками, добавлять и удалять их, а
        также вносить необходимые изменения. Благодаря удобному интерфейсу,
        процесс управления клиентами является максимально простым и эффективным.
      </Text>
      <hr />
      <CustomerList
        customers={customers}
        handleDeleteCustomer={handleDeleteCustomer}
        openEditModal={openEditModal}
      />
    </div>
  );
};

export default CustomerPanel;
