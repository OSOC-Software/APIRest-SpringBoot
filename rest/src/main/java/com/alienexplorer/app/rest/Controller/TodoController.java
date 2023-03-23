package com.alienexplorer.app.rest.Controller;

import com.alienexplorer.app.rest.Model.Task;
import com.alienexplorer.app.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value = "/")
    public String holaMundo(){
        return "Hola Mundo";
    }

    @GetMapping(value="/tasks")
    public List<Task> getTasks(){
        return todoRepository.findAll();
    }

    @PostMapping(value = "/savetask" )
    public String saveTask(@RequestBody Task task){
        todoRepository.save(task);
        return "Save taks";
    }

    @PutMapping(value = "/update/{id}")
    public String updateTask(@PathVariable long id ,@RequestBody Task task){
        Task update = todoRepository.findAllById(Collections.singleton(id)).get(0);
        update.setTitle(task.getTitle());
        update.setDescription(task.getDescription());
        todoRepository.save(update);
        return "Updated Task";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable long id){
        todoRepository.deleteById(id);
        return "Task Deleted";
    }
}
