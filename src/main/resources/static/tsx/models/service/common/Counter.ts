import { types } from 'mobx-state-tree'

export const Counter = types
   .model({
      count: types.number,
   })
   .actions(self => ({
      increment() {
         self.count++
      },
      decrement() {
         self.count--
      },
   }))
   .create({ count: 0 })

// const collectionModel = types.model({
//     type: types.optional(types.literal('collections'), 'collections'),
//     preview: types.optional(
//         types.model({
//             products: types.array(SelectableProduct)x
//         }),
//         {}
//     ),
//     data: types.optional(types.model({
//         items: 24,
//         perRow: 4,
//         global: types.optional(EvergreenQuery, {}),
//         curated: types.array(EvergreenItemSettings)
//     }), {})
// })
