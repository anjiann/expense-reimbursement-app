import React, { Component } from "react";
import auth from "../services/auth_Service";
import { findUsernameById, findAllUsers } from "../services/userService";
import { Link } from "react-router-dom";
import ApprovalButton from "./approvalButton";
import DenyButton from "./denyButton";
import Table from "./common/table";

class ReimbursementsTable extends Component {
  columns = [
    {
      path: "id",
      label: "id",
      content: (reimbursement) => (
        <Link to={`/reimbursements/${reimbursement.id}`}>
          {reimbursement.id}
        </Link>
      ),
    },
    {
      path: "amount",
      label: "Amount",
    },
    {
      path: "author",
      label: "Author",
    },
    { path: "resolver", label: "Resolver" },
    { path: "status", label: "Status" },
    { path: "type", label: "Type" },
  ];

  approveDenyColumn = {
    key: "approve/deny",
    content: (reimbursement) =>
      reimbursement.status === "Pending" && (
        <React.Fragment>
          <button
            type="button"
            className="btn btn-success btn-sm"
            onClick={() => this.props.onUpdate(reimbursement, 1)}
          >
            approve
          </button>
          <button
            type="button"
            className="btn btn-danger btn-sm"
            onClick={() => this.props.onUpdate(reimbursement, 3)}
          >
            deny
          </button>
        </React.Fragment>
      ),
  };

  constructor() {
    super();
    const user = auth.getCurrentUser();
    if (user && auth.getUserRoleId() == 2) {
      this.columns.push(this.approveDenyColumn);
    }
  }

  render() {
    const { reimbursements, sortColumn, onSort } = this.props;

    return (
      <Table
        columns={this.columns}
        data={reimbursements}
        sortColumn={sortColumn}
        onSort={onSort}
      />
    );
  }
}

export default ReimbursementsTable;
