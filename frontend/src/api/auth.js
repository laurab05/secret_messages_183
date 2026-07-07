import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/auth'

export async function register(username, password) {
    const response = await axios.post(`${BASE_URL}/register`, { username, password })
    return response.data
}

export async function login(username, password) {
    const response = await axios.post(`${BASE_URL}/login`, { username, password })
    return response.data
}