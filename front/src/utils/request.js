import axios from 'axios'

const requestCarInfo = axios.create({
    baseURL: "http://192.168.43.194:8083",
    timeout: 20000 // 请求超时时间
})

export default {
    requestCarInfo
}
