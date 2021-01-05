import http from "./http_Service";

const apiEndpoint = "/users";

export async function findUsernameById(id) {
  let { data } = await http.get(`${apiEndpoint}/${id}`);
  console.log(data.username);
  return data.username;
}

export function findAllUsers() {
  return http.get(apiEndpoint);
}
