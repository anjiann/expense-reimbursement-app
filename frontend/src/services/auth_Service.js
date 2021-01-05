import http from "./http_Service";

const apiEndpoint = "/login";
const tokenKey = "token";

http.setJwt(getJwt());

export async function login(username, password) {
  const { data } = await http.post(apiEndpoint, { username, password });
  localStorage.setItem("username", data.username);
  localStorage.setItem("user_id", data.id);
  localStorage.setItem("role_id", data.roleId);
}

export function loginWithJwt(jwt) {
  localStorage.setItem(tokenKey, jwt);
}

export function logout() {
  localStorage.removeItem("username");
  localStorage.removeItem("user_id");
  localStorage.removeItem("role_id");
}

export function getCurrentUser() {
  return localStorage.getItem("username");
}

export function getCurrentUserId() {
  return localStorage.getItem("user_id");
}

export function getUserRoleId() {
  return localStorage.getItem("role_id");
}

export function getJwt() {
  return localStorage.getItem(tokenKey);
}

export default {
  login,
  loginWithJwt,
  logout,
  getCurrentUser,
  getCurrentUserId,
  getUserRoleId,
  getJwt,
};
