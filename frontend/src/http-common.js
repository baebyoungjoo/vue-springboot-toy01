import axios from 'axios'

export const axiosInstanceBoard = axios.create({
  // baseURL: 'http://49.236.136.5:6335/api',
  baseURL: 'http://localhost:6335/api',
  headers: {
    'Content-type': 'application/json'
  }
})

export const axiosInstanceMember = axios.create({
  baseURL: 'http://localhost:6335/member',
  headers: {
    'Content-type': 'application/json'
  }
})

export const axiosInstanceCaptcha = axios.create({
  baseURL: 'http://localhost:6335/captcha',
  headers: {
    'Content-type': 'text/plain;charset=UTF-8'
  }
})
