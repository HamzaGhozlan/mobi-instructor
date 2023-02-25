package com.psut.controllers;

import org.springframework.web.bind.annotation.RestController;

import static com.psut.controllers.TeachersController.TEACHERS_BASE_URL;

@RestController(TEACHERS_BASE_URL)
public class TeachersController {
    public static final String TEACHERS_BASE_URL = "/api/v1/teachers";
}
