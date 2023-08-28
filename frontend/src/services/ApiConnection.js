import axios from 'axios'

const   url = "http://localhost:8080/todo";

class   ApiConnection
{
    async getAllTasks()
    {
        try 
        {
            let response = await axios.get(url);
            console.log(response);
            return (response);
        } 
        catch (error) 
        {
            return (error.message);
        }
    }

    async getTaskById(id)
    {
        try 
        {
            let response = await axios.get(`${url}/${id}`);
            return (response);
        } 
        catch (error) 
        {
            return (error.message);
        }
    }

    async deleteTaskById(id)
    {
        try 
        {
            let response = await axios.delete(`${url}/${id}`);
            return (response);
        } 
        catch (error) 
        {
            return (error.message);
        }
    }

    async addTask(task)
    {
        try 
        {
            let response = await axios.post(url, task);
            return (response);
        } 
        catch (error) 
        {
            return (error.message);
        }
    }

    async updateTask(id, task)
    {
        try 
        {
            let response = await axios.put(`${url}/${id}`, task);
            
            return (response);
        } 
        catch (error) 
        {
            return (error.message);
        }
    }
}

export default ApiConnection;