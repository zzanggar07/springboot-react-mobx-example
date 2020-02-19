import { flow, types } from 'mobx-state-tree'
import ListRepository from '../../repository/customer/ListRepository'
import { ERROR_PROPS } from '../../../../Constants'
import {
   Customer,
   CustomerNetResData,
   CustomerNetResDataInitialState,
} from '../../response/custom/list/NetResData.type'

export const List = types
   .model({
      result: types.frozen(CustomerNetResData),
      error: '',
      isLoading: types.boolean,
   })
   .actions(self => ({
      getList: flow(function*(param) {
         self.isLoading = true
         try {
            const { data } = yield ListRepository.fetchData(param)
            self.result = {
               items: data.results.map((customer: any) =>
                  types.frozen(Customer).create({
                     idx: customer.idx,
                     businessCategory: customer.businessCategory,
                     companyName: customer.companyName,
                     licenseNumber: customer.companyName,
                     companyAddress: customer.companyAddress,
                     managerName: customer.managerName,
                     position: customer.position,
                     department: customer.department,
                     telNumber: customer.telNumber,
                     phoneNumber: customer.phoneNumber,
                     faxNumber: customer.faxNumber,
                     email: customer.email,
                     finalCreator: customer.finalCreator,
                     memo: customer.memo,
                     createdTime: customer.createdTime,
                     updatedTime: customer.updatedTime,
                  }),
               ),
               page: data.page,
               totalPages: data.totalPages,
               totalCount: data.totalCount,
            }
         } catch (e) {
            if (!!Object.assign({}, e).response) {
               const errorCode = Object.assign({}, e).response.data.code
               self.error = ERROR_PROPS.get(errorCode).message
            }
         }
         self.isLoading = false
      }),
   }))
   .create({
      isLoading: true,
      error: '',
      result: CustomerNetResDataInitialState,
   })
