import {Instance, types} from "mobx-state-tree";

export const Customer = types.model({
   idx: types.number,
   businessCategory: types.string,
   companyName: types.string,
   licenseNumber: types.string,
   companyAddress: types.string,
   managerName: types.string,
   position: types.string,
   department: types.string,
   telNumber: types.string,
   phoneNumber: types.string,
   faxNumber: types.string,
   email: types.string,
   finalCreator: types.string,
   memo: types.string,
   createdTime: types.number,
   updatedTime: types.number,
})


export const CustomerNetResData = types.model({
   items: types.array(Customer),
   page: types.number,
   totalPages: types.number,
   totalCount: types.number,
})

export const CustomerNetResDataInitialState = {
   items: [],
   page: 0,
   totalPages: 0,
   totalCount: 0,
};

export type ICustomer = Instance<typeof Customer>;
