import { types } from 'mobx-state-tree'

export const Modal = types.model({
   isShow: types.boolean,
   resource: types.frozen({}),
})

export const ModalInitialState = {
   isShow: false,
   resource: {},
}
