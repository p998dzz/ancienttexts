package lt.ancienttexts.controller.webapp;

import lt.ancienttexts.domain.TextDetailsResponse;
import lt.ancienttexts.domain.TextListResponse;
import lt.ancienttexts.service.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("text")
public class TextsController {

    @Autowired
    private TabletService tabletService;

    @GetMapping(value = "/all", produces = "application/json")
    public @ResponseBody
    TextListResponse fetchTextList() throws Exception {
        TextListResponse response = tabletService.fetchAllEntries();
        return response;
    }

    @GetMapping(value = "/item/{id}", produces = "application/json")
    public @ResponseBody
    TextDetailsResponse fetchTextItem(@PathVariable Long id) throws Exception {
        TextDetailsResponse response = tabletService.fetchTextDetails(id);
        return response;
    }
}
