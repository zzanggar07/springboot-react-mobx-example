import './utils/CrossBrowserSupport'

import * as React from 'react'
import * as ReactDOM from 'react-dom'

import RouterContainer from './component/router/RouterContainer'

const routerContainerElement = document.getElementById('routerContainer')
if (routerContainerElement !== null) {
  ReactDOM.render(<RouterContainer />, routerContainerElement)
}
