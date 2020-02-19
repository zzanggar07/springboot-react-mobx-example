import { inject, observer } from 'mobx-react'
import * as React from 'react'

import Notifications, { notify } from 'react-notify-toast'

import { LEVEL, NOTIFICATION } from '../../Constants'

@inject((stores: any) => ({
   toast: stores.common.toast,
}))
@observer
export default class Notification extends React.Component {
   private readonly show: any
   constructor(props: Readonly<{}>) {
      super(props)
      this.show = notify.createShowQueue()
   }

   componentWillUpdate(nextProps: { toast: { notice: any } }, nextState: any) {
      const notice = nextProps.toast.notice
      if (notice.type === NOTIFICATION.MESSAGE) {
         this.showMessage(notice.level, notice.message)
      }
   }

   showMessage(level: string, message: string) {
      return this.show(
         <div>
            <i className={LEVEL.get(level).icon} />
            {message}
         </div>,
         'custom',
         5000,
         LEVEL.get(level).style,
      )
   }

   render() {
      // @ts-ignore
      const { toast } = this.props
      console.log(toast.notice.message)
      return (
         <div id="toast">
            <Notifications />
         </div>
      )
   }
}
