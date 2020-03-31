import axios from 'axios'
const requestCarInfo = axios.create({
    baseURL: "http://localhost:8083",
    timeout: 20000 // 请求超时时间
})

const requestPassport = axios.create({
    baseURL: "http://localhost:8082",
    timeout: 20000 // 请求超时时间，
})

const requestUser = axios.create({
    baseURL: "http://localhost:8090",
    timeout: 20000 // 请求超时时间
})

export default {
    requestCarInfo,
    requestPassport,
    requestUser
}
