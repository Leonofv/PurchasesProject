import axios from 'axios';
import { config } from './Constants';
import { Customer } from '../intefaces/CustomerInteface';

export const PurchaseApi = {
  getCustomers,
  getCustomerById,
  getCustomersByCustomerCode,
  createCustomer,
  updateCustomer,
  deleteCustomer,

  getLots,
  GetLotById,
  GetLotsByName,
  deleteLot,
  updateLot,
  createLot,
};

function getCustomers() {
  return instance.get(`/api/customers/all`);
}

function getCustomerById(id: number) {
  return instance.get(`/api/customers/id/${id}`);
}

function getCustomersByCustomerCode(customerCode: string) {
  return instance.get(`/api/customers//customersCode/${customerCode}`);
}

function createCustomer(currentCustomer: Customer) {
  return instance.post(`/api/customers/create`, currentCustomer, {
    headers: {
      'Content-type': 'application/json',
    },
  });
}

function updateCustomer(id: number, updatedCustomer: Customer) {
  return instance.put(`/api/customers/update/${id}`, updatedCustomer, {
    headers: {
      'Content-type': 'application/json',
    },
  });
}

function deleteCustomer(customerId: number) {
  return instance.delete(`/api/customers/delete/${customerId}`);
}

function getLots() {
  return instance.get('/api/lots/all');
}

function GetLotById() {}

function GetLotsByName() {}

function deleteLot() {}

function updateLot() {}

function createLot() {}

const instance = axios.create({
  baseURL: config.url.API_BASE_URL,
});
