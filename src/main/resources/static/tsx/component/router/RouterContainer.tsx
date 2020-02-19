import { createBrowserHistory } from 'history'
import * as React from 'react'
import { Redirect, Route, Router } from 'react-router-dom'
import Container from '../main-container/Container'

const history = createBrowserHistory()

export default class RouterContainer extends React.Component {
   render() {
      return (
         <Router history={history}>
            <React.Fragment>
               <Route
                  path="/"
                  render={() => <Redirect to={window.location.pathname} />}
               />
               <Route
                  exact
                  path="/"
                  component={(props: any) => <Container {...props} />}
               />
               {/*<Route exact path="/change/password/:token" component={props => <UserResetPassword {...props}/>}/>*/}
            </React.Fragment>
         </Router>
      )
   }
}

