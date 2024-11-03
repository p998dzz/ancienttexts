package lt.ancienttexts.controller.webapp;

import lt.ancienttexts.domain.TabletDetailsResponse;
import lt.ancienttexts.domain.TabletListResponse;
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
    TabletListResponse fetchTextList() {
        return tabletService.fetchAllEntries();
    }

    @GetMapping(value = "/all/{searchPhrase}", produces = "application/json")
    public @ResponseBody
    TabletListResponse searchTextList(@PathVariable String searchPhrase) {
        return tabletService.searchEntries(searchPhrase);
    }

    @GetMapping(value = "/item/{id}", produces = "application/json")
    public @ResponseBody
    TabletDetailsResponse fetchTextItem(@PathVariable Long id) {
        return tabletService.fetchTextDetails(id);
    }
}
