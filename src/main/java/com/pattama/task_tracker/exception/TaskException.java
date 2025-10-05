package com.pattama.task_tracker.exception;

import org.springframework.http.HttpStatus;

public class TaskException extends BaseException {

    public TaskException(String code, HttpStatus status) {
        super("task." + code, status);
    }

    public static TaskException taskNotFound() {
        return new TaskException("not.found", HttpStatus.NOT_FOUND);
    }

    // CREATE EXCEPTION
    public static TaskException titleNull() {
        return new TaskException("title.null", HttpStatus.BAD_REQUEST);
    }

    public static TaskException titleDuplicate() {
        return new TaskException("title.duplicate", HttpStatus.BAD_REQUEST);
    }


}
