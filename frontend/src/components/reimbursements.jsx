import React, { Component } from "react";

import Pagination from "./common/pagination";
import ListGroup from "./common/listGroup";
import ReimbursementsTable from "./reimbursementsTable";
import {
  updateReimbursement,
  getReimbursements,
} from "../services/reimbursementService";
import auth from "../services/auth_Service";
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

    const { data: reimbursements } = await getReimbursements();
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

  updateItem(id, itemAttributes) {
    var index = this.state.items.findIndex((x) => x.id === id);
    this.setState({
      items: [
        ...this.state.items.slice(0, index),
        Object.assign({}, this.state.items[index], itemAttributes),
        ...this.state.items.slice(index + 1),
      ],
    });
  }

  handleUpdate = async (reimbursement, statusId) => {
    console.log("updating reimbursement: ");
    console.log(reimbursement);
    reimbursement.statusId = statusId;
    reimbursement.resolverId = auth.getCurrentUserId();

    try {
      await updateReimbursement(reimbursement);
    } catch (ex) {
      if (ex.response && ex.response.status === 404) {
        console.log("oops something went wrong");
      }
      let newStatus = statusId == 1 ? "Accepted" : "Denied";
      this.updateItem(reimbursement.id, { status: newStatus });
    }
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
          <ReimbursementsTable
            reimbursements={reimbursements}
            sortColumn={sortColumn}
            onAccept={this.handleAccept}
            onUpdate={this.handleUpdate}
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
