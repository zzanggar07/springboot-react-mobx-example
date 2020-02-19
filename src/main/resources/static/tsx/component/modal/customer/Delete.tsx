import * as React from 'react'
import {inject, observer} from 'mobx-react'
import {observable} from 'mobx'

import Modal from 'react-bootstrap4-modal'
import Toast from '../../../utils/Toast'

@inject((stores: any) => ({
   modal: stores.modal.customer.delete.modal,
   hideDialog: stores.modal.customer.delete.hideDialog,
}))
@observer
export default class Delete extends React.Component {
   @observable isWorking = false
   private toast: Toast

   componentDidMount() {
      // this.fetchCustomer({
      //    keyword: '',
      //    searchRule: SEARCH_RULE_TYPE.COMPANY_NAME,
      //    page: 0,
      //    size: 10,
      // })
   }

   componentWillReceiveProps(nextProps: any) {
      // if (nextProps.deletedResult) {
      //     if (JSON.stringify(nextProps.deletedResult) !== JSON.stringify(this.props.deletedResult)) {
      //         switch (nextProps.deletedResult.status) {
      //             case NET_RES_STATUS.SUCCESS:
      //                 this.isWorking = false;
      //                 this.toast.showInfo('삭제', '고객 삭제되었습니다.');
      //                 this.props.hideModalDialog();
      //                 break;
      //             case NET_RES_STATUS.FAILED:
      //                 this.isWorking = false;
      //                 const message = nextProps.registerResult.error;
      //                 this.toast.showDanger('삭제실패', message);
      //                 break;
      //         }
      //     }
      // }
   }

   render() {
      return ((props: any) => {
         return (
            <React.Fragment>
               <Toast onRef={(ref: Toast) => (this.toast = ref)} />
               <Modal
                  visible={props.modal.isShow}
                  dialogClassName="modal-dialog modal-dialog-centered"
                  onClickBackdrop={() => {
                     props.hideDialog().then((r: any) => {
                        console.log(r)
                        this.toast.showInfo('토스트', '모달을 닫습니다.')
                     })
                  }}
               >
                  <div className="modal-content">
                     <div className="modal-header">
                        <h5 className="modal-title">고객 삭제</h5>
                        <button
                           type="button"
                           className="btn-closed"
                           onClick={() => {
                              props
                                 .hideDialog()
                                 .then((r: any) =>
                                    this.toast.showInfo(
                                       '토스트',
                                       '모달을 닫습니다.',
                                    ),
                                 )
                           }}
                        />
                     </div>
                     <div className="modal-body modal-confirm">
                        <p>선택한 항목을 삭제 하시겠습니까?</p>
                     </div>
                     <div className="modal-footer">
                        <button
                           type="button"
                           className="btn btn-default"
                           onClick={() => {
                              this.isWorking = true
                           }}
                        >
                           {this.isWorking ? (
                              <i className="fas fa-redo-alt fa-spin fa-1x fa-fw" />
                           ) : (
                              '삭제'
                           )}
                        </button>
                        <button
                           type="button"
                           className="btn btn-white"
                           disabled={this.isWorking}
                           onClick={() => {
                              props
                                 .hideDialog()
                                 .then((r: any) =>
                                    this.toast.showInfo(
                                       '토스트',
                                       '모달을 닫습니다.',
                                    ),
                                 )
                           }}
                        >
                           취소
                        </button>
                     </div>
                  </div>
               </Modal>
            </React.Fragment>
         )
      })(this.props)
   }
}
