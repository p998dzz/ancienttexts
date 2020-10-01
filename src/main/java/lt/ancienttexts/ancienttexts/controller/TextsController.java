package lt.ancienttexts.ancienttexts.controller;

import lt.ancienttexts.ancienttexts.controller.transfer.TextDetailsResponse;
import lt.ancienttexts.ancienttexts.controller.transfer.TextListResponse;
import lt.ancienttexts.ancienttexts.service.LinearBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("text")
public class TextsController {

    @Autowired
    private LinearBservice linearBservice;

    @GetMapping(value = "/all", produces = "application/json")
    public @ResponseBody
    TextListResponse fetchTextList() throws Exception {
        TextListResponse response = linearBservice.fetchAllEntries();
        return response;
    }

    @GetMapping(value = "/item/{id}", produces = "application/json")
    public @ResponseBody
    TextDetailsResponse fetchTextItem(@PathVariable Long id) throws Exception {
        TextDetailsResponse response = linearBservice.fetchTextDetails(id);
        return response;
    }
}
