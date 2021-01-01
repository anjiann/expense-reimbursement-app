import React, { Fragment } from "react";
import { Navbar, Nav } from "react-bootstrap";

const NavBar = ({ user }) => {
  return (
    <Navbar collapseOnSelect expand="lg" bg="light" variant="light">
      <Navbar.Brand href="/">ERS</Navbar.Brand>
      <Navbar.Toggle aria-controls="responsive-navbar-nav" />
      <Navbar.Collapse id="responsive-navbar-nav">
        <Nav className="ml-auto">
          {!user && (
            <Fragment>
              <Nav.Link href="/login">Login</Nav.Link>
              <Nav.Link href="/register">Register</Nav.Link>
            </Fragment>
          )}
          {user && (
            <Fragment>
              <Nav.Link href="/profile">Signed in as {user.name}</Nav.Link>
              <Nav.Link href="/logout">Logout</Nav.Link>
            </Fragment>
          )}
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default NavBar;
