import { AxiosPromise } from 'axios'

export default interface IRepository {
    readonly url: string
    fetchData: (params: any) => AxiosPromise
}
