package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persons")
@Api(value = "PersonControllerJpa", description = "REST API Person description")
public class PersonController {
}
