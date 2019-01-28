import axios from 'axios'

export default axios.create({
  // baseURL: 'http://49.236.136.5:6335/api',
  baseURL: 'http://localhost:6335/api',
  headers: {
    'Content-type': 'application/json'
  }
})
