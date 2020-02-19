import * as React from 'react'
import Toast from '../../../utils/Toast'
import {inject, observer} from 'mobx-react'
import {SEARCH_RULE_TYPE} from '../../../Constants'

interface ITestComponentProps {
    name: string
    project: string
    customer?: any
    modal?: any
    // showModalDelete?: any
}

@inject((stores: any) => {
    return {
        counter: stores.common.counter,
        customer: stores.api.custom.list,
        modal: stores.modal.customer.delete,
    }
})
@observer
export default class ModuleComponent extends React.Component<ITestComponentProps,
    {}> {
    private toast: Toast

    componentWillMount() {
        console.log('Component WILL MOUNT!')
    }

    componentDidMount() {
        console.log('Component DID MOUNT!')
    }

    // componentWillReceiveProps(nextProps) {
    //    console.log('Component WILL RECIEVE PROPS!')
    // }
    // shouldComponentUpdate(nextProps, nextState) {
    //    return true;
    // }
    componentWillUpdate(nextProps: any, nextState: any) {
        console.log('Component WILL UPDATE!')
        console.log(nextProps)
    }

    // componentDidUpdate(prevProps, prevState) {
    //    console.log('Component DID UPDATE!')
    //
    // }
    // componentWillUnmount() {
    //    console.log('Component WILL UNMOUNT!')
    // }

    getCustomerList = (params: {}) => {
        this.props.customer.getList(params)
    }

    render() {
        return ((props: any) => {
            return (
                <React.Fragment>
                    <Toast onRef={(ref: Toast) => (this.toast = ref)}/>
                    <div>
                        <h1>프로필</h1>
                        <div>
                            <b>이름: </b>
                            {name}
                        </div>
                        <div>
                            <b>프로젝트: </b>
                            {props.project}
                        </div>
                        <br/>
                        <div>
                            <b>테스트목록 </b>
                            <button
                                onClick={e => {
                                    this.toast.showDanger('xptmxm', '테스트입니다.')
                                }}
                            >
                                토스트
                            </button>
                            <button
                                onClick={e => {
                                    this.props
                                        .customer
                                        .getList({
                                            keyword: '',
                                            searchRule: SEARCH_RULE_TYPE.COMPANY_NAME,
                                            page: 0,
                                            size: 10,
                                        })
                                        .then((r: any) =>
                                            this.toast.showDanger(
                                                'api테스트',
                                                'api테스트입니다.',
                                            )
                                        )
                                }}
                            >
                                api
                            </button>
                            <button
                                title={'모달테스트'}
                                onClick={e => {
                                    props.modal
                                        .showDialog()
                                        .then((r: any) =>
                                            this.toast.showDanger(
                                                '모',
                                                '모달테스트입니다.',
                                            )
                                        )
                                }}
                            >
                                모달
                            </button>

                            <button
                                onClick={e => {
                                    props.counter.increment()
                                }}
                            >
                                mobx-state-tree
                            </button>
                            <div>
                                <b>counter: </b>
                                {props.counter.count}
                            </div>
                            <div>
                                <b>api result: </b>
                                고객 건수 {props.customer.result.items.length}
                            </div>
                        </div>
                    </div>
                </React.Fragment>
            )
        })(this.props)
    }
}
