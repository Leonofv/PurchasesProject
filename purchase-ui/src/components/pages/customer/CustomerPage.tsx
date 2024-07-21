import React, { useState, useEffect, useCallback } from 'react';
import { PurchaseApi } from '../../http/PurchaseApi';
import AddCustomerModal from './AddCustomerModal';
import EditCustomerModal from './EditCustomerModal';
import { Customer } from '../../intefaces/CustomerInteface';
import CustomerPanel from './CustomerPanel';

const CustomerPage: React.FC = () => {
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [filteredCustomers, setFilteredCustomers] = useState<Customer[]>([]);
  const [currentCustomer, setCurrentCustomer] = useState<Customer | null>(null);
  const [isAddModalOpen, setIsAddModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [filter, setFilter] = useState<'all' | 'hasAgent' | 'noAgent'>('all');

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = async () => {
    try {
      const response = await PurchaseApi.getCustomers();
      setCustomers(response.data);
    } catch (error) {
      console.error('Error fetching customers', error);
    }
  };

  const filterCustomers = useCallback(() => {
    let filtered = customers;
    if (filter === 'hasAgent') {
      filtered = customers.filter((customer) => customer.customerCodeMain);
    } else if (filter === 'noAgent') {
      filtered = customers.filter((customer) => !customer.customerCodeMain);
    }
    setFilteredCustomers(filtered);
  }, [filter, customers]);

  useEffect(() => {
    filterCustomers();
  }, [filterCustomers]);

  const handleDeleteCustomer = async (id: number) => {
    try {
      await PurchaseApi.deleteCustomer(id);
      fetchCustomers();
    } catch (error) {
      console.error('Error deleting customer', error);
    }
  };

  const handleSaveCustomer = async (customer: Customer) => {
    try {
      if (customer.id) {
        await PurchaseApi.updateCustomer(customer.id, customer);
      } else {
        await PurchaseApi.createCustomer(customer);
      }
      fetchCustomers();
      setIsAddModalOpen(false);
      setIsEditModalOpen(false);
    } catch (error) {
      console.error('Error saving customer', error);
      throw error;
    }
  };

  const openEditModal = (customer: Customer) => {
    setCurrentCustomer(customer);
    setIsEditModalOpen(true);
  };

  return (
    <div>
      <CustomerPanel
        customers={filteredCustomers}
        handleDeleteCustomer={handleDeleteCustomer}
        openEditModal={openEditModal}
        openAddModal={() => setIsAddModalOpen(true)}
        setFilter={setFilter}
        filter={filter}
      />
      <AddCustomerModal
        isOpen={isAddModalOpen}
        onClose={() => setIsAddModalOpen(false)}
        handleSaveCustomer={handleSaveCustomer}
      />
      <EditCustomerModal
        isOpen={isEditModalOpen}
        onClose={() => setIsEditModalOpen(false)}
        handleSaveCustomer={handleSaveCustomer}
        currentCustomer={currentCustomer}
      />
    </div>
  );
};

export default CustomerPage;
