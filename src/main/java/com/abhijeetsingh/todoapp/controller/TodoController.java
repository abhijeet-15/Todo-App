package com.abhijeetsingh.todoapp.controller;

import com.abhijeetsingh.todoapp.Todo;
import com.abhijeetsingh.todoapp.TodoAppApplication;
import com.abhijeetsingh.todoapp.response.TodoResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    private static List<Todo> todoList;

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, false, "Todo 2", 2));
    }

    @GetMapping("/")
    public static ResponseEntity<?> ping() {
        return TodoResponseHandler.generateSuccessResponse("online",HttpStatus.OK,"server is up");
    }

    @GetMapping("todos")
    public static ResponseEntity<?> getAllTodos() {
        try{
            return TodoResponseHandler.generateSuccessResponse("Fetched Todos successfully",
                    HttpStatus.OK, todoList);
        } catch (Exception e) {
            return TodoResponseHandler.generateErrorResponse("Failed to Fetch Todos",
                    HttpStatus.NOT_FOUND, "error fetching the todos " + e);
        }
    }

    @GetMapping("/todos/{id}")
    public static ResponseEntity<?> getTodoById(@PathVariable long id) {

            Todo todo = todoList.stream().filter(item -> item.getId() == id).findFirst()// Find the first matching Todo object
                    .orElse(null);

            if(todo != null) return TodoResponseHandler.generateSuccessResponse("Fetched the todo with " + id + " successfully",
                    HttpStatus.OK,todo);

            return TodoResponseHandler.generateErrorResponse("Failed to Fetch Todos",
                    HttpStatus.NOT_FOUND, "error fetching the todos ");

    }

    @PostMapping("/todos")
    public static ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        try{
            todoList.add(todo);
            return TodoResponseHandler.generateSuccessResponse("Created the todo " + todo.getId() + " successfully",
                    HttpStatus.CREATED,todo);
        }catch (Exception e) {
            return TodoResponseHandler.generateSuccessResponse("Failed to create the todo. Please check the payload",
                    HttpStatus.BAD_REQUEST,"Invalid Request");
        }
    }

    @DeleteMapping("/todos/{id}")
    public static ResponseEntity<?> deleteTodoById(@PathVariable long id) {
        try{
            todoList.removeIf(item -> item.getId() == id);
            return TodoResponseHandler.generateSuccessResponse("Successfully deleted the todo " + id,
                    HttpStatus.OK,"deleted the todo");
        }catch (Exception e){
            return TodoResponseHandler.generateSuccessResponse("Failed to delete todo with " + id,
                    HttpStatus.NOT_FOUND,"no todo with id " + id + "found" );
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodoWithId(@PathVariable long id, @RequestBody Todo todo) {

            for (Todo item : todoList) {
                if (item.getId() == id) {
                    if (todo.getTitle() != null) {
                        item.setTitle(todo.getTitle());
                    }
                    if (todo.isCompleted()) {
                        item.setCompleted(todo.isCompleted());
                    }
                    if(!todo.isCompleted()) {
                        item.setCompleted(todo.isCompleted());
                    }

                    break;
                }
            }
            Todo updatedTodo = todoList.stream().filter(item -> item.getId() == id).findFirst()// Find the first matching Todo object
                    .orElse(null);

            if(updatedTodo != null) return TodoResponseHandler.generateSuccessResponse("successfully updated the todo " + id,
                    HttpStatus.CREATED, updatedTodo);

            return TodoResponseHandler.generateErrorResponse("Failed to update todo with " + id,
                    HttpStatus.NOT_FOUND, "no todo with id " + id + " found");
        
    }
}
