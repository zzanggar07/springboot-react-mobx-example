import { types } from 'mobx-state-tree'
import { LEVEL_TYPE, NOTIFICATION } from '../../../Constants'

export const Notice = types.model({
   type: types.string,
   level: types.string,
   message: types.string,
})

export const Toast = types
   .model({
      notice: types.frozen(Notice),
   })
   .actions(self => ({
      sendMessage(notice: any) {
         self.notice = notice
      },
   }))
   .create({
      notice: {
         type: NOTIFICATION.MESSAGE,
         level: LEVEL_TYPE.INFO,
         message: '',
      },
   })
