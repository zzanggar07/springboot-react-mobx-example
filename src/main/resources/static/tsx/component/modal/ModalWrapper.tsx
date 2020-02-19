import * as React from 'react'
import ModalDeleteOfCustomer from './customer/Delete'

export default class ModalWrapper extends React.Component {
   render() {
      return (
         <React.Fragment>
            <ModalDeleteOfCustomer />
         </React.Fragment>
      )
   }
}
