import React from "react";
import Joi from "joi-browser";
import Form from "./common/form";
import auth from "../services/auth_Service";
import { saveReimbursement } from "../services/reimbursementService";

class ReimbursementForm extends Form {
  state = {
    data: {
      amount: "",
      submitted: "1608937200000",
      description: "",
      authorId: auth.getCurrentUserId(),
      typeId: "",
    },
    types: [
      { _id: 1, name: "Lodging" },
      { _id: 2, name: "Travel" },
      { _id: 3, name: "Food" },
      { _id: 4, name: "Other" },
    ],
    errors: [],
  };

  schema = {
    amount: Joi.number().required().min(0).max(500).label("Amount"),
    description: Joi.string().label("Description"),
    typeId: Joi.string().required().label("Type"),
    authorId: Joi.number().required(),
    submitted: Joi.date().timestamp(),
  };

  doSubmit = async () => {
    await saveReimbursement(this.state.data);
    if (auth.getUserRoleId() == 1) {
      this.props.history.push("/tickets");
    } else {
      this.props.history.push("/reimbursements");
    }
  };

  render() {
    return (
      <div>
        <h1>Reimbursement request form</h1>
        <form onSubmit={this.handleSubmit}>
          {this.renderInput("amount", "Amount")}
          {this.renderSelect("typeId", "Type", this.state.types)}
          {this.renderInput("description", "Description")}
          {this.renderButton("Submit")}
        </form>
      </div>
    );
  }
}

export default ReimbursementForm;
