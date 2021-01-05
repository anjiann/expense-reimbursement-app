import React, { Component } from "react";

import Pagination from "./common/pagination";
import ListGroup from "./common/listGroup";
import ReimbursementsTable from "./reimbursementsTable";
import auth from "../services/auth_Service";
import { Link } from "react-router-dom";
import { getReimbursements } from "../services/reimbursementService";
import { getStatuses } from "../services/statusService";
import { paginate } from "../utils/paginate";
import _, { filter } from "lodash";

class Reimbursements extends Component {
  state = {
    reimbursements: [],
    statuses: [],
    currentPage: 1,
    pageSize: 4,
    searchQuery: "",
    selectedStatus: null,
    sortColumn: { path: "id", order: "asc" },
  };

  async componentDidMount() {
    const { data } = await getStatuses();
    const statuses = [{ id: 0, name: "All Statuses" }, ...data];

    let { data: reimbursements } = await getReimbursements();
    reimbursements = reimbursements.filter(
      (r) => r.author === auth.getCurrentUser()
    );
    this.setState({ reimbursements, statuses });
  }

  handleSort = (sortColumn) => {
    this.setState({ sortColumn });
  };

  handlePageChange = (page) => {
    this.setState({ currentPage: page });
  };

  handleStatusSelect = (status) => {
    this.setState({ selectedStatus: status, searchQuery: "", currentPage: 1 });
  };

  getPageData = () => {
    const {
      pageSize,
      currentPage,
      sortColumn,
      selectedStatus,
      searchQuery,
      reimbursements: allReimbursements,
    } = this.state;

    let filtered = allReimbursements;
    if (selectedStatus && selectedStatus.id) {
      filtered = allReimbursements.filter(
        (r) => r.statusId === selectedStatus.id
      );
    }

    const sorted = _.orderBy(filtered, [sortColumn.path], [sortColumn.order]);

    const reimbursements = paginate(sorted, currentPage, pageSize);

    return { totalCount: filtered.length, reimbursements };
  };

  render() {
    const { length: count } = this.state.reimbursements;
    const { pageSize, currentPage, sortColumn, searchQuery } = this.state;
    const { user } = this.props;

    const { totalCount, reimbursements } = this.getPageData();

    return (
      <div className="row">
        <div className="col-3">
          <ListGroup
            items={this.state.statuses}
            selectedItem={this.state.selectedStatus}
            onItemSelect={this.handleStatusSelect}
          />
        </div>
        <div className="col">
          <Link
            to="/reimbursements/new"
            className="btn btn-primary"
            style={{ marginBottom: 20 }}
          >
            New reimbursement request
          </Link>
          <ReimbursementsTable
            reimbursements={reimbursements}
            sortColumn={sortColumn}
            onLike={this.handleLike}
            onSort={this.handleSort}
          />
          <Pagination
            itemsCount={totalCount}
            pageSize={pageSize}
            currentPage={currentPage}
            onPageChange={this.handlePageChange}
          />
        </div>
      </div>
    );
  }
}

export default Reimbursements;
