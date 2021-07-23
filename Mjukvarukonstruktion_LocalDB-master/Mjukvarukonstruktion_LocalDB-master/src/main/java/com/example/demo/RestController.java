package com.example.demo;

import com.example.demo.data.MyDatabase;
import com.example.demo.model.JournalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*This is the controller that the JPA uses. @Controller makes this possible.
@Autowired is used to get the repository-beans, we will use it to handle the data.
The repositories inherits methods for CRUD-operations.
@CrossOrigin lets the React-App(from the specified IP-Address) connect to the server.
@Requestmapping tells what the URL starts with.*/

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class RestController {

    @RolesAllowed("user")
    @PostMapping(path="/produceRecord")
    public @ResponseBody String addRecord (@RequestParam String category, @RequestParam String content , @RequestHeader String Authorization) {
        String date = LocalDate.now().toString();
        JournalRecord jr = new JournalRecord(category, content, date);
        MyDatabase.getinstance().saveToDB(jr);
        return "added record";
    }

    @RolesAllowed("user")
    @GetMapping(path="/getRecordById")
    public @ResponseBody JournalRecord getJr (@RequestParam int id, @RequestHeader String Authorization) {
        JournalRecord jr = MyDatabase.getinstance().getJournalRecordById(id);
        return jr;
    }

    @RolesAllowed("user")
    @GetMapping(path="/getAllRecords")
    public @ResponseBody List<JournalRecord> getAllJr (@RequestHeader String Authorization) {

        return MyDatabase.getinstance().getAllJournalRecords();
    }
}
