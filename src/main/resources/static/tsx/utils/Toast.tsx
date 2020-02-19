import { inject, observer } from 'mobx-react'
import * as React from 'react'
import { LEVEL_TYPE, NOTIFICATION } from '../Constants'

interface IProps {
   toast?: any
   onRef: any
}

@inject((stores: any) => ({
   toast: stores.common.toast,
}))
@observer
export default class Toast extends React.Component<IProps, {}> {
   componentDidMount() {
      this.props.onRef(this)
   }

   showInfo = (title: string, message: string) => {
      this.props.toast.sendMessage({
         type: NOTIFICATION.MESSAGE,
         level: LEVEL_TYPE.INFO,
         message,
      })
   }

   showWarning = (title: string, message: string) => {
      this.props.toast.sendMessage({
         type: NOTIFICATION.MESSAGE,
         level: LEVEL_TYPE.WARNING,
         message,
      })
   }

   showDanger = (title: string, message: string) => {
      this.props.toast.sendMessage({
         type: NOTIFICATION.MESSAGE,
         level: LEVEL_TYPE.DANGER,
         message,
      })
   }

   render() {
      return false
   }
}
