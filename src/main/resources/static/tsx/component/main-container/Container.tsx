import * as React from 'react'
import { Provider } from 'mobx-react'

import RootStore from '../../models/Root'

import ModalWrapper from '../modal/ModalWrapper'
import Notification from '../notify/Notification'
// // import Greetings from './content/Greetings'
import ModuleComponent from './content/ModuleComponent'

export default class Container extends React.Component {
   render() {
      return (
         <Provider {...RootStore}>
            <React.Fragment>
               <ModuleComponent name="zzanggar07" project="모듈테스트" />
               <ModalWrapper/>
               <Notification />
            </React.Fragment>
         </Provider>
      )
   }
}
