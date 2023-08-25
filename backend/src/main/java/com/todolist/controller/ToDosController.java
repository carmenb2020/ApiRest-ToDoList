package com.todolist.controller;

import com.todolist.model.ToDo;
import com.todolist.services.ToDosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/todo")
public class ToDosController{
    @Autowired
    private ToDosServices toDosServices;

    @GetMapping(path = "/list")
    public ArrayList<ToDo> getAllTasks() {
        return toDosServices.getAllTasks();
    }

    @GetMapping(path = "/list/{id}")
    public ToDo getTaskById(@PathVariable long id) {
        return toDosServices.getTaskById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteTask(@PathVariable long id) {
        return toDosServices.deleteTask(id);
    }

    @PostMapping(path = "/add")
    public String saveTask(@RequestBody ToDo toDo) {
        return (toDosServices.saveTask(toDo));
    }

    @PutMapping(path = "/update/{id}")
    public String updateTask (@PathVariable long id, @RequestBody ToDo toDo) {
        toDo.setId(id);
        return (toDosServices.updateTask(toDo));
    }
}
