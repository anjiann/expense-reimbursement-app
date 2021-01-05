import http from "./http_Service";

const apiEndpoint = "/reimbursements";

function reimbursementUrl(id) {
  return `${apiEndpoint}/${id}`;
}

export function getReimbursements() {
  return http.get(apiEndpoint);
}

export function saveReimbursement(reimbursement) {
  return http.post(apiEndpoint, reimbursement);
}

export function updateReimbursement(reimbursement) {
  return http.put(reimbursementUrl(reimbursement.id), body);
}
