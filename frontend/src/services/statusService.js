import http from "./http_Service";

export function getStatuses() {
  return http.get("/statuses");
}
