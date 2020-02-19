import { CommonRootModel } from './service/common'
import { ModalRootModel } from './service/modal'
import { APIRootModel } from './api'

const rootStore = { common: CommonRootModel, api: APIRootModel, modal: ModalRootModel }

export default rootStore
