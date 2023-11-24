import axios from "axios";

const api = axios.create({baseURL: "http://ec2-23-22-61-175.compute-1.amazonaws.com:8081/"})



export default api;