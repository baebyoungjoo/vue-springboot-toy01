import axios from 'axios'

export default axios.create({
  baseURL: 'http://localhost:6335/api',
  headers: {
    'Content-type': 'application/json'
  }
})
