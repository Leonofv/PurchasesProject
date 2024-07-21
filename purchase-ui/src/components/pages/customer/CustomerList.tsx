import React from 'react';
import {
  Table,
  TableColumn,
  TableRow as ConstaTableRow,
} from '@consta/uikit/Table';
import { IconEdit } from '@consta/icons/IconEdit';
import { IconTrash } from '@consta/icons/IconTrash';
import { Button } from '@consta/uikit/Button';
import { Customer } from '../../intefaces/CustomerInteface';

interface CustomTableRow extends ConstaTableRow {
  id: string;
  customerCode: string;
  customerName: string;
  customerInn: string;
  customerKpp: string;
  customerLegalAddress: string;
  customerPostalAddress: string;
  customerEmail: string;
  customerCodeMain: string | null;
  actions: JSX.Element;
}

interface Props {
  customers: Customer[];
  handleDeleteCustomer: (id: number) => void;
  openEditModal: (customer: Customer) => void;
}

const CustomerList: React.FC<Props> = ({
  customers,
  handleDeleteCustomer,
  openEditModal,
}) => {
  const columns: TableColumn<CustomTableRow>[] = [
    {
      title: 'Код',
      accessor: 'customerCode',
      width: 100,
    },
    {
      title: 'Контрагент',
      accessor: 'customerCodeMain',
    },
    {
      title: 'Наименование',
      accessor: 'customerName',
    },
    {
      title: 'ИНН',
      accessor: 'customerInn',
      width: 10,
    },
    {
      title: 'КПП',
      accessor: 'customerKpp',
      width: 100,
    },
    {
      title: 'Юридический адрес',
      accessor: 'customerLegalAddress',
    },
    {
      title: 'Почтовый адрес',
      accessor: 'customerPostalAddress',
    },
    {
      title: 'Электронная почта',
      accessor: 'customerEmail',
    },
    {
      title: 'Действия',
      accessor: 'actions',
    },
  ];

  const rows: CustomTableRow[] = customers.map((customer) => ({
    id: customer.id.toString(),
    customerCode: customer.customerCode,
    customerName: customer.customerName,
    customerInn: customer.customerInn,
    customerKpp: customer.customerKpp,
    customerLegalAddress: customer.customerLegalAddress,
    customerPostalAddress: customer.customerPostalAddress,
    customerEmail: customer.customerEmail,
    customerCodeMain: customer.customerCodeMain,
    actions: (
      <div style={{ display: 'flex', gap: '8px' }}>
        <Button
          iconLeft={IconEdit}
          size="s"
          onClick={() => openEditModal(customer)}
        />
        <Button
          iconLeft={IconTrash}
          size="s"
          onClick={() => handleDeleteCustomer(customer.id)}
        />
      </div>
    ),
  }));

  return <Table columns={columns} rows={rows} />;
};

export default CustomerList;
