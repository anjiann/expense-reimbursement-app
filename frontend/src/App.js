import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Route,
  Redirect,
  Switch,
} from "react-router-dom";

import LoginForm from "./components/loginForm";
import NavBar from "./components/navBar";
import NotFound from "./components/notFound";
import auth from "./services/auth_Service";
import "./App.css";

class App extends Component {
  state = {};

  componentDidMount() {
    const user = auth.getCurrentUser();
    this.setState({ user });
  }

  render() {
    const { user } = this.state;
    return (
      <React.Fragment>
        <NavBar user={this.state.user} />
        <main className="container">
          <Router>
            <Switch>
              <Route path="/login" component={LoginForm} />
              <Route path="/not-found" component={NotFound} />
              <Redirect exact from="/" to="/login" />
              <Redirect to="/not-found" />
            </Switch>
          </Router>
        </main>
      </React.Fragment>
    );
  }
}

export default App;
