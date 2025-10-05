package com.pattama.task_tracker.exception;

import org.springframework.http.HttpStatus;

public class TaskListException extends BaseException {

    public TaskListException (String code, HttpStatus httpStatus) {
        super("tasklist." + code, httpStatus);
    }

    public static TaskListException taskListNotFound() {
        return new TaskListException("not.found", HttpStatus.NOT_FOUND);
    }

    // CREATE EXCEPTION
    public static TaskListException titleNull() {
        return new TaskListException("title.null", HttpStatus.BAD_REQUEST);
    }

    public static TaskListException titleDuplicate() {
        return new TaskListException("title.duplicate", HttpStatus.BAD_REQUEST);
    }
}
