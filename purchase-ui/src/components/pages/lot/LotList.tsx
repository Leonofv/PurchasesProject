import React from 'react';
import {
  Table,
  TableColumn,
  TableRow as ConstaTableRow,
} from '@consta/uikit/Table';
import { Lot } from '../../intefaces/LotInterface';

interface LotTableRow extends ConstaTableRow {
  id: string;
  lotName: string;
  customerCode: string;
  price: number;
  currencyCode: string;
  ndsRate: string;
  placeDelivery: string;
  dateDelivery: string;
}

interface Props {
  lots: Lot[];
  formatDate: (date: Date) => string;
}

const LotList: React.FC<Props> = ({ lots, formatDate }) => {
  const columns: TableColumn<LotTableRow>[] = [
    {
      title: 'Наименование',
      accessor: 'lotName',
      width: 150,
    },
    {
      title: 'Контрагент',
      accessor: 'customerCode',
    },
    {
      title: 'Цена',
      accessor: 'price',
    },
    {
      title: 'Валюта',
      accessor: 'currencyCode',
    },
    {
      title: 'НДС',
      accessor: 'ndsRate',
    },
    {
      title: 'Место доставки',
      accessor: 'placeDelivery',
    },
    {
      title: 'Дата доставки',
      accessor: 'dateDelivery',
    },
  ];

  const rows: LotTableRow[] = lots.map((lot) => ({
    id: lot.id.toString(),
    lotName: lot.lotName,
    customerCode: lot.customerCode,
    price: lot.price,
    currencyCode: lot.currencyCode,
    ndsRate: lot.ndsRate,
    placeDelivery: lot.placeDelivery,
    dateDelivery: formatDate(new Date(lot.dateDelivery)),
  }));

  return <Table columns={columns} rows={rows} />;
};

export default LotList;
