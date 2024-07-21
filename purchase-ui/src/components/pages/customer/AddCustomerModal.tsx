import React, { useState } from 'react';
import { Modal } from '@consta/uikit/Modal';
import { TextField } from '@consta/uikit/TextField';
import { Button } from '@consta/uikit/Button';
import { Grid, GridItem } from '@consta/uikit/Grid';
import { Select } from '@consta/uikit/Select';
import { Customer } from '../../intefaces/CustomerInteface';
import './AddCustomerModal.css';

const customerTypeOptions = [
  { label: 'Юридическое лицо', value: 'organization' },
  { label: 'Физическое лицо', value: 'person' },
];

interface Props {
  isOpen: boolean;
  onClose: () => void;
  handleSaveCustomer: (customer: Customer) => void;
}

const AddCustomerModal: React.FC<Props> = ({
  isOpen,
  onClose,
  handleSaveCustomer,
}) => {
  const [customer, setCustomer] = useState<Omit<Customer, 'id'>>({
    customerCode: '',
    customerName: '',
    customerInn: '',
    customerKpp: '',
    customerLegalAddress: '',
    customerPostalAddress: '',
    customerEmail: '',
    customerCodeMain: null,
    isOrganization: true,
    isPerson: false,
  });
  const [error, setError] = useState<string | null>(null);

  const handleInputChange =
    (field: keyof Omit<Customer, 'id'>) => (value: string | null) => {
      const processedValue = value ? value.trim() || null : null;
      setCustomer((prev) => ({
        ...prev,
        [field]: processedValue,
      }));
    };

  const handleSelectChange = (
    option: { label: string; value: string } | null
  ) => {
    setCustomer((prev) => ({
      ...prev,
      isOrganization: option?.value === 'organization',
      isPerson: option?.value === 'person',
    }));
  };

  const handleCancel = () => {
    setError(null);
    onClose();
  };

  const handleSubmit = async () => {
    const newCustomer: Customer = { id: 0, ...customer };
    try {
      await handleSaveCustomer(newCustomer);
      setCustomer({
        customerCode: '',
        customerName: '',
        customerInn: '',
        customerKpp: '',
        customerLegalAddress: '',
        customerPostalAddress: '',
        customerEmail: '',
        customerCodeMain: null,
        isOrganization: true,
        isPerson: false,
      });
      setError(null);
      onClose();
    } catch {
      setError('Ошибка заполнения, проверьте корректность вводимых данных.');
    }
  };

  return (
    <Modal isOpen={isOpen} onClose={handleCancel} style={{ maxWidth: '800px' }}>
      <div className="modalContainer">
        <h3>Добавление нового заказчика</h3>
        {error && <div className="errorMessage">{error}</div>}
        <Grid cols={3} gap="m">
          <GridItem>
            <TextField
              label="Код контрагента"
              value={customer.customerCode}
              onChange={handleInputChange('customerCode')}
              placeholder="Введите код контрагента"
            />
          </GridItem>
          <GridItem>
            <TextField
              label="Наименование"
              value={customer.customerName}
              onChange={handleInputChange('customerName')}
              placeholder="Введите наименование"
            />
          </GridItem>
          <GridItem>
            <TextField
              label="ИНН"
              value={customer.customerInn}
              onChange={handleInputChange('customerInn')}
              placeholder="Введите ИНН"
            />
          </GridItem>
          <GridItem>
            <TextField
              label="КПП"
              value={customer.customerKpp}
              onChange={handleInputChange('customerKpp')}
              placeholder="Введите КПП"
            />
          </GridItem>
          <GridItem>
            <TextField
              label="Юридический адрес"
              value={customer.customerLegalAddress}
              onChange={handleInputChange('customerLegalAddress')}
              placeholder="Введите юридический адрес"
            />
          </GridItem>
          <GridItem>
            <TextField
              label="Почтовый адрес"
              value={customer.customerPostalAddress}
              onChange={handleInputChange('customerPostalAddress')}
              placeholder="Введите почтовый адрес"
            />
          </GridItem>
          <GridItem>
            <TextField
              label="Электронная почта"
              value={customer.customerEmail}
              onChange={handleInputChange('customerEmail')}
              placeholder="Введите электронную почту"
            />
          </GridItem>
          <GridItem>
            <TextField
              label="Код конграгента"
              value={customer.customerCodeMain ?? null}
              onChange={handleInputChange('customerCodeMain')}
              placeholder="Введите контрагента"
            />
          </GridItem>
          <GridItem>
            <Select
              label="Тип лица"
              placeholder="Выберите тип"
              items={customerTypeOptions}
              value={customerTypeOptions.find(
                (option) =>
                  option.value ===
                  (customer.isOrganization ? 'organization' : 'person')
              )}
              onChange={handleSelectChange}
              getItemKey={(item) => item.value}
            />
          </GridItem>
        </Grid>
        <div className="modalFooter">
          <Button label="Сохранить" onClick={handleSubmit} view="primary" />
          <Button label="Отмена" onClick={handleCancel} />
        </div>
      </div>
    </Modal>
  );
};

export default AddCustomerModal;
